package com.suning.springjpa.service;

import com.suning.springjpa.entity.Member;
import com.suning.springjpa.exception.BizException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    void pointIncrease(int arg0, Long arg1, String arg2) throws BizException;

    void pointDeduction(int arg0, Long arg1, String arg2) throws BizException;

    List<Member> findAll(String arg0, String arg1);
}