package com.mis.fit.fitness.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mis.fit.fitness.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
