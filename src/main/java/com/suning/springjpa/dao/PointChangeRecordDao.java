package com.suning.springjpa.dao;

import com.suning.springjpa.entity.PointChangeRecord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointChangeRecordDao extends JpaRepository<PointChangeRecord, Long> {
    List<PointChangeRecord> findAllByMemberId(Long arg0);
}