package com.t1m0.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.t1m0.todo.repos.TodoRepo;

@Component
public class DBLoader {
  @Autowired
  private TodoRepo repo;

  // @PostConstruct
  // @SuppressWarnings("unused")
  // private void initalLoad() {
  // Todo[] todos = { new Todo("First Todo"), new Todo("Second Todo",
  // Todo.PRIORITY.LOW), new Todo("Third Todo", "Description",
  // Todo.PRIORITY.HIGH) };
  // Arrays.asList(todos).forEach(t -> repo.save(t));
  // }

}
