package com.koreait.hanGyeDolpa.service;

import org.springframework.stereotype.Service;

import com.koreait.hanGyeDolpa.bean.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public UserVO makeSampleUserData() {
		UserVO vo = new UserVO();
		vo.setUserID("SampleUser01");
		vo.setUserName("QWERTY");
		vo.setUserAlias("Samply");
		vo.setUserNo(1L);
		vo.setUserProfilePath("http://k.kakaocdn.net/dn/KRzD3/btsL7rjaJeE/BZnUaoP4ixtxWOAgh9AJQk/img_640x640.jpg");
		return vo;
	}

	@Override
	public boolean makeUserLogin(boolean flag, boolean str) {
		flag = str;
		
		return flag;
	}

}
