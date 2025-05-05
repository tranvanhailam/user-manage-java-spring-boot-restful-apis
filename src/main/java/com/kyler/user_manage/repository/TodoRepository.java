package com.kyler.user_manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kyler.user_manage.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
