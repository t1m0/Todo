package com.t1m0.todo;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.t1m0.todo.entities.Todo;
import com.t1m0.todo.repos.TodoRepo;

@RestController
class BackupController {
  private final Logger LOG    = LoggerFactory.getLogger(BackupController.class);
  @Autowired
  private TodoRepo     repo;
  ObjectMapper         mapper = new ObjectMapper();

  public BackupController() {
    super();
  }

  @RequestMapping("/backup")
  public List<Todo> backup() {
    List<Todo> todos = toList(repo.findAll());
    try {
      mapper.writeValue(new File("/Users/timo/Documents/JavaWorkspace/_workspace/todos.json"), todos);
      LOG.debug(mapper.writeValueAsString(todos));
    } catch (Exception e) {
      LOG.info(e.getMessage());
    }
    return todos;
  }

  private List<Todo> toList(final Iterable<Todo> iterable) {
    return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
  }

  @RequestMapping("/restore")
  public List<Todo> restore() {
    List<Todo> todos = null;
    try {
      JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, Todo.class);
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      todos = mapper.readValue(new File("/Users/timo/Documents/JavaWorkspace/_workspace/todos.json"), type);
      for (Todo t : todos) {
        repo.save(t);
      }
    } catch (Exception e) {
      LOG.info(e.getMessage());
    }
    return todos;
  }
}