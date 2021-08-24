package com.mis.fit.fitness.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mis.fit.fitness.entity.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

}
