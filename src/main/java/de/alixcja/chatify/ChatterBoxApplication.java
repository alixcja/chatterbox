package de.alixcja.chatify;

import de.alixcja.chatify.model.Chat;
import de.alixcja.chatify.repository.ChatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ChatterBoxApplication implements CommandLineRunner {

  private static final Logger LOG = LoggerFactory.getLogger(ChatterBoxApplication.class);

  @Autowired
  private ChatRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(ChatterBoxApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    repository.deleteAll();

    Chat chat1 = new Chat();
    chat1.setName("My girls");
    chat1.setMembers(List.of("Melinda", "Amy", "Nathali"));

    Chat chat2 = new Chat();
    chat2.setName("La familia");
    chat2.setMembers(List.of("Mom", "Dad", "Big brother", "Little brother", "Grandma"));

    repository.save(chat1);
    repository.save(chat2);

    LOG.info("------- Found {} with .findAll() -------", repository.count());
    for (Chat chat : repository.findAll()) {
      LOG.info("Name: {}, amount of members: {}", chat.getName(), chat.getMembers().size());
    }
  }
}
