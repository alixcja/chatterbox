package de.alixcja.chatify.rest;

import ch.qos.logback.core.util.StringUtil;
import de.alixcja.chatify.model.Chat;
import de.alixcja.chatify.repository.ChatRepository;
import jdk.jfr.ContentType;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ChatResource {

  @Autowired
  ChatRepository chatRepository;

  @GetMapping("/chats")
  public List<Chat> getAllChats() {
    return chatRepository.findAll();
  }

  @PostMapping(value = "/chats", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<Chat> createNewChat(@RequestBody Chat newChat) throws BadRequestException {
    verifyNewChatObject(newChat);
    return new ResponseEntity<>(
            newChat,
            HttpStatus.CREATED);
  }

  private static void verifyNewChatObject(Chat newChat) throws BadRequestException {
    if (newChat == null) {
      throw new BadRequestException("Request body must not be null");
    }
    if (StringUtil.isNullOrEmpty(newChat.getName())) {
      throw new BadRequestException("Name of chat must not be null");
    }
    if (CollectionUtils.isEmpty(newChat.getMembers())) {
      throw new BadRequestException("Members of chat must be at least two");
    }
  }
}
