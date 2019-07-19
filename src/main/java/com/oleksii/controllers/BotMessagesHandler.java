package com.oleksii.controllers;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.microsoft.bot.connector.ConnectorClient;
import com.microsoft.bot.connector.Conversations;
import com.microsoft.bot.connector.customizations.MicrosoftAppCredentials;
import com.microsoft.bot.connector.implementation.ConnectorClientImpl;
import com.microsoft.bot.schema.models.Activity;
import com.microsoft.bot.schema.models.ResourceResponse;
import com.oleksii.constants.Command;
import com.oleksii.creators.ActivityCreator;
import com.oleksii.creators.ConversationCreator;
import com.oleksii.dao.KeysDAO;
import com.oleksii.dao.QueriesDAO;
import com.oleksii.model.Keys;
import com.oleksii.model.Queries;
import com.oleksii.searcher.SearcherService;
import com.oleksii.senders.ResourceResponseSender;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/messages")
public class BotMessagesHandler implements BotMessagesController {

    @Autowired
    private MicrosoftAppCredentials credentials;

    @Autowired
    private List<ResourceResponse> responses;

    @Autowired
    private SearcherService searcherService;

    @Autowired
    private KeysDAO keysDAO;

    @Autowired
    private QueriesDAO queriesDAO;

    @GetMapping(path = "/hi")
    public String get() {
        return "Hi";
    }

    ObjectMapper objectMapper = new ObjectMapper();

    @Deprecated
    @PostMapping(path = "old")
    public List<ResourceResponse> createOld(@RequestBody @Valid
                                            @JsonDeserialize(using = DateTimeDeserializer.class) Activity activity) {
        try {
            System.out.println("AAAAAA " + objectMapper.writeValueAsString(activity));
            System.out.printf("AAAAA " + objectMapper.writeValueAsString(credentials));
        } catch (JsonProcessingException e) {
        }

        ConnectorClient connector =
                new ConnectorClientImpl(activity.serviceUrl(), credentials);

        Activity echoActivity = ActivityCreator.createEchoActivity(activity);
        Activity checkedActivity = ActivityCreator.createSpellCheckedActivity(activity);
        Conversations conversation = ConversationCreator.createResponseConversation(connector);
        System.out.println("AAAAAA" + conversation);
        ResourceResponse echoResponse =
                ResourceResponseSender.send(conversation, activity, echoActivity);
        responses.add(echoResponse);

        ResourceResponse spellCheckedResponse =
                ResourceResponseSender.send(conversation, activity, checkedActivity);
        responses.add(spellCheckedResponse);
        System.out.println("AAAAAA" + responses);
        return responses;
    }


    @PostMapping(path = "")
    public List<ResourceResponse> create(@RequestBody @Valid
                                         @JsonDeserialize(using = DateTimeDeserializer.class) Activity activity) {


        ConnectorClient connector = new ConnectorClientImpl(activity.serviceUrl(), credentials);
        Conversations conversation = ConversationCreator.createResponseConversation(connector);
        Activity commandActivity = ActivityCreator.createCommandActivity(activity, getResult(activity.text()));
        ResourceResponse commandResponse = ResourceResponseSender.send(conversation, activity, commandActivity);
        responses.add(commandResponse);
        return responses;
    }

    private <T> List<T> flattenListOfListsImperatively(
            List<List<T>> nestedList) {
        List<T> ls = new ArrayList<>();
        nestedList.forEach(ls::addAll);
        return ls;
    }


    public List<String> getResult(String inputText) {
        List<Keys> allKeys = new ArrayList<>();
        List<Queries> allQueries = new ArrayList<>();
        List<String> outputQuery = new ArrayList<>();
        keysDAO.findAll().forEach(allKeys::add);
        queriesDAO.findAll().forEach(allQueries::add);
        Command command;
        try {
            command = Command.getCommand(inputText);
        } catch (IllegalArgumentException e) {
            log.warn("command not found", e);
            return Collections.singletonList("please start command with:\n" + String.join("\n", Stream.of(Command.values()).map(Command::getCommandName).collect(Collectors.joining("\n"))));
        }
        String activityText = StringUtils.normalizeSpace(inputText.replace(command.getCommandName(), ""));
        Optional<Keys> optionalKey = searcherService.findResult(activityText);
        switch (command) {
            case ADD:
                if (optionalKey.isPresent()) {
                    Keys key = optionalKey.get();
                    activityText = StringUtils.normalizeSpace(activityText.replaceFirst(key.getKeyWord(), ""));
                    Optional<Queries> optionalQuery = allQueries
                            .stream()
                            .filter(query -> query.getKeyId() == key.getId())
                            .findFirst();
                    if (optionalQuery.isPresent()) {
                        Queries queries = optionalQuery.get();
                        queries.setLinkNames(queries.getLinkNames() + "," + activityText);
                        queriesDAO.save(queries);
                    } else {
                        Queries queries = new Queries();
                        queries.setKeyId(key.getId());
                        queries.setLinkNames(activityText);
                        queriesDAO.save(queries);
                    }
                } else {
                    List<String> keyAndLinks = Arrays.asList(activityText.split(" "));
                    assert keyAndLinks.size() >= 2;
                    Keys key = new Keys();
                    key.setKeyWord(keyAndLinks.get(0));
                    keysDAO.save(key);

                    activityText = StringUtils.normalizeSpace(activityText.replaceFirst(key.getKeyWord(), ""));
                    Queries queries = new Queries();
                    queries.setKeyId(key.getId());
                    queries.setLinkNames(activityText);
                    queriesDAO.save(queries);
                }
                return null;
            case GET:
                if (optionalKey.isPresent()) {
                    List<Queries> sortedQueries = allQueries.stream()
                            .filter(queryy -> queryy.getKeyId() == optionalKey.get().getId())
                            .collect(Collectors.toList());
                    outputQuery = flattenListOfListsImperatively(sortedQueries.stream()
                            .map(q -> Arrays.asList(q.getLinkNames().split(","))).
                                    collect(Collectors.toList()));
                }
                break;
            case HELP:
                outputQuery = Stream.of(Command.values()).map(Command::getCommandName).collect(Collectors.toList());
                break;
            case LIST:
                outputQuery = allKeys.stream().map(Keys::getKeyWord).collect(Collectors.toList());
                break;
        }
        return outputQuery;
    }
}
