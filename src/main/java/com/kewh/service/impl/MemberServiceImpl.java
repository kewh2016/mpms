package com.kewh.service.impl;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.kewh.dao.MemberDao;
import com.kewh.dao.PointChangeRecordDao;
import com.kewh.entity.Member;
import com.kewh.entity.PointChangeRecord;
import com.kewh.exception.BizException;
import com.kewh.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private PointChangeRecordDao pointChangeRecordDao;

    @Transactional
    public void pointIncrease(int point, Long memberId, String reason) throws BizException {
	if (point <= 0) {
	    throw new BizException("积分增加参数错误！增加的分值需要大于0。");
	} else {
	    Member member = (Member) this.memberDao.findOne(memberId);
	    member.setPoints(Integer.valueOf(member.getPoints().intValue() + point));
	    Date current = new Date();
	    member.setUpdateTime(current);
	    this.memberDao.save(member);
	    this.saveHistoryRecord(memberId, reason, current, "增加");
	}
    }

    @Transactional
    public void pointDeduction(int point, Long memberId, String reason) throws BizException {
	if (point <= 0) {
	    throw new BizException("积分扣减参数错误！扣减的分值需要大于0。");
	} else {
	    Member member = (Member) this.memberDao.findOne(memberId);
	    int points = member.getPoints().intValue() - point;
	    if (points < 0) {
		throw new BizException("积分扣减错误！不能超过当前总积分。");
	    } else {
		member.setPoints(Integer.valueOf(points));
		Date current = new Date();
		member.setUpdateTime(current);
		this.memberDao.save(member);
		this.saveHistoryRecord(memberId, reason, current, "扣减");
	    }
	}
    }

    private void saveHistoryRecord(Long memberId, String reason, Date current, String type) {
	PointChangeRecord record = new PointChangeRecord();
	record.setChangeTime(current);
	record.setMemberId(memberId);
	record.setReason(reason);
	record.setType(type);
	this.pointChangeRecordDao.save(record);
    }

    public List<Member> findAll(String phoneNo, String memberName) {
	Order order = new Order(Direction.DESC, "createTime");
	Sort sort = new Sort(new Order[] { order });
	return StringUtils.hasText(phoneNo) && StringUtils.hasText(memberName)
		? this.memberDao.findAllByPhoneNoAndMemberName(phoneNo.trim(), memberName.trim(), sort)
		: (StringUtils.hasText(phoneNo) ? this.memberDao.findAllByPhoneNo(phoneNo.trim(), sort)
			: (StringUtils.hasText(memberName) ? this.memberDao.findAllByMemberName(memberName.trim(), sort)
				: this.memberDao.findAll(sort)));
    }
}