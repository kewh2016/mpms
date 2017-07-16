package com.kewh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kewh.entity.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer> {
}