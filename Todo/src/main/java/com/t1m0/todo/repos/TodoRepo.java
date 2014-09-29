package com.t1m0.todo.repos;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.t1m0.todo.entities.Todo;

@RepositoryRestResource(collectionResourceRel = "todo", path = "todo")
public interface TodoRepo extends PagingAndSortingRepository<Todo, Long> {
}
