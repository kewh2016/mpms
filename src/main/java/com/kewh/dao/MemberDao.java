package com.kewh.dao;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kewh.entity.Member;

@Repository
public interface MemberDao extends JpaRepository<Member, Long> {
    List<Member> findAllByPhoneNoAndMemberName(String arg0, String arg1, Sort arg2);

    List<Member> findAllByPhoneNo(String arg0, Sort arg1);

    List<Member> findAllByMemberName(String arg0, Sort arg1);
}