package de.alixcja.chatify.repository;

import de.alixcja.chatify.model.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "chat", path = "chat")
public interface ChatRepository extends MongoRepository<Chat, String> {

  public List<Chat> getAllMyChats(@Param("id") String id);

  public Chat findByName(@Param("name") String name);
}
