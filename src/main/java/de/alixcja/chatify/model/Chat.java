package de.alixcja.chatify.model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Chat {
  @Id
  private String id;

  private String name;

  private String description;

  private List<String> members;

  public Chat() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<String> getMembers() {
    return members;
  }

  public void setMembers(List<String> members) {
    this.members = members;
  }
}
