package com.mis.fit.fitness.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mis.fit.fitness.entity.FileDB;

public interface FileDBRepository extends JpaRepository<FileDB, String> {

}
