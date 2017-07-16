package com.kewh.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kewh.entity.PointChangeRecord;

public interface PointChangeRecordDao extends JpaRepository<PointChangeRecord, Long> {
    List<PointChangeRecord> findAllByMemberId(Long arg0);
}