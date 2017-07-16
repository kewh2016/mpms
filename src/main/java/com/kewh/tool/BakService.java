package com.kewh.tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.kewh.dao.MemberDao;
import com.kewh.entity.Member;

@Component
public class BakService {
    @Autowired
    private MemberDao memberDao;

    public void bak() {
	List<Member> members = this.memberDao.findAll();
	if (!CollectionUtils.isEmpty(members)) {
	    ArrayList<String> lines = new ArrayList<>();
	    Iterator<Member> file = members.iterator();

	    while (file.hasNext()) {
		Member e = (Member) file.next();
		StringBuilder sb = new StringBuilder();
		sb.append("姓名:").append(e.getMemberName()).append(";手机号：").append(e.getPhoneNo()).append(";积分：")
			.append(e.getPoints());
		lines.add(sb.toString());
	    }

	    File file1 = new File(System.getProperty("user.dir") + File.separator + "bak.txt");
	    if (!file1.exists()) {
		try {
		    file1.createNewFile();
		} catch (IOException arg6) {
		    arg6.printStackTrace();
		}
	    }

	    try {
		FileUtils.writeLines(file1, lines, false);
	    } catch (IOException arg5) {
		arg5.printStackTrace();
	    }

	}
    }
}