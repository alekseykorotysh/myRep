package com.oleksii.controllers;

import com.microsoft.bot.schema.models.Activity;
import com.microsoft.bot.schema.models.ResourceResponse;

import java.util.List;

public interface BotMessagesController {
    List<ResourceResponse> create(Activity activity);
    List<String> getResult(String inputText);
}