package de.alixcja.chatify;

import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.alixcja.chatify.model.Chat;
import de.alixcja.chatify.repository.ChatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@WebMvcTest
@AutoConfigureMockMvc
public class ChatResourceTests {
  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  ChatRepository repository;

  private Chat chat2;
  private Chat chat1;

  @BeforeEach
  public void setup() throws Exception {

      chat1 = new Chat();
      chat1.setName("First chat");
      chat1.setMembers(List.of("member 1", "member2"));

      chat2 = new Chat();
      chat2.setName("Second chat");
      chat2.setMembers(List.of("member 2", "member 3", "member 4"));

      repository.saveAll(List.of(chat1, chat2));
    }

    @Test
    void shouldReturnTwoChats() throws Exception {
      String expectedResponseBody = new ObjectMapper().writeValueAsString(List.of(chat1, chat2));

      this.mockMvc.perform(get("/chats")).andDo(print()).andExpect(status().isOk())
              .andExpect(content().json(expectedResponseBody));
    }

  @Test
  void shouldCreateNewChat() throws Exception {

    Chat newChat = new Chat();
    newChat.setName("This is my new chat");
    newChat.setMembers(List.of("Leni", "Rocco", "member 2"));
    newChat.setDescription("Welcome to my first chat!");

    String requestBody = new ObjectMapper().writeValueAsString(newChat);

    this.mockMvc
            .perform(post("/chats")
                    .content(requestBody)
                    .contentType(APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(content().string(containsString(requestBody)));
  }
}
