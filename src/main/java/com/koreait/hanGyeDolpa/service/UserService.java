package com.koreait.hanGyeDolpa.service;

import com.koreait.hanGyeDolpa.bean.UserVO;

public interface UserService {
	public UserVO makeSampleUserData();
	public boolean makeUserLogin(boolean flag, boolean str);
}
