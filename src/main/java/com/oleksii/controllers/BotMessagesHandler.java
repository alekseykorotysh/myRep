package com.oleksii.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/messages")
@Slf4j
public class BotMessagesHandler {

  @GetMapping(path = "")
  public String get() {
      log.error("HELLO HELLO");
   return "Hi";
  }
}
