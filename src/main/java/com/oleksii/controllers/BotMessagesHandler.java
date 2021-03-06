package com.oleksii.controllers;

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
import com.oleksii.creators.ActivityCreator;
import com.oleksii.senders.ResourceResponseSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/messages")
public class BotMessagesHandler {

    @Autowired
    private MicrosoftAppCredentials credentials;

    @Autowired
    private List<ResourceResponse> responses;

    @GetMapping(path = "/hi")
    public String get() {
        return "Hi";
    }

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(path = "")
    public List<ResourceResponse> create(@RequestBody @Valid
                                         @JsonDeserialize(using = DateTimeDeserializer.class) Activity activity) {
        try {
            System.out.println("AAAAAA " + objectMapper.writeValueAsString(activity));
        } catch (JsonProcessingException e) {


        }
        MicrosoftAppCredentials credentials1 = new MicrosoftAppCredentials("8c786aac-5e1b-45c6-b33f-d923c503ecf0",
                "asdfqwerty123");
        ConnectorClient connector =
                new ConnectorClientImpl(activity.serviceUrl(), credentials1);

        Activity echoActivity = ActivityCreator.createEchoActivity(activity);
        Activity checkedActivity = ActivityCreator.createSpellCheckedActivity(activity);
        Conversations conversation = connector.conversations();
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
}
