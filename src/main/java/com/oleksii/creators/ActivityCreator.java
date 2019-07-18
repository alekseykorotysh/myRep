package com.oleksii.creators;

import java.util.List;

import com.microsoft.bot.schema.models.Activity;
import com.microsoft.bot.schema.models.ActivityTypes;
import com.oleksii.jazzyspellcheck.JazzySpellChecker;
import org.springframework.stereotype.Component;


@Component
public class ActivityCreator {

    private static final String spellCheckedResponsePart = "You have probably meant: ";
    private static final String echoResponsePart = "You typed: ";
    private static JazzySpellChecker spellChecker = new JazzySpellChecker();

    private ActivityCreator() {

    }

    public static Activity createSpellCheckedActivity(Activity activity) {
        return createEmptyActivity(activity)
                .withText(spellCheckedResponsePart + spellChecker.getCorrectedText(activity.text()));
    }

    public static Activity createEchoActivity(Activity activity) {
        return createEmptyActivity(activity)
                .withText(echoResponsePart + activity.text());
    }

    public static Activity createCommandActivity(Activity activity, List<String> keyNames) {
        StringBuilder sb = new StringBuilder();
        keyNames.forEach(p -> sb.append(p).append("\n"));
        return createEmptyActivity(activity)
                .withText(sb.toString());
    }

    private static Activity createEmptyActivity(Activity activity) {
        return new Activity()
                .withType(ActivityTypes.MESSAGE)
                .withRecipient(activity.from())
                .withFrom(activity.recipient());
    }
}
