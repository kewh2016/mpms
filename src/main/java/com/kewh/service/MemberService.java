package com.kewh.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.kewh.entity.Member;
import com.kewh.exception.BizException;

@Service
public interface MemberService {
    void pointIncrease(int arg0, Long arg1, String arg2) throws BizException;

    void pointDeduction(int arg0, Long arg1, String arg2) throws BizException;

    List<Member> findAll(String arg0, String arg1);
}