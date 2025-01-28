package com.koreait.hanGyeDolpa.service;

import com.koreait.hanGyeDolpa.entity.User_Table;
import com.koreait.hanGyeDolpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class UserService {
	@Autowired
    private UserRepository userRepository;
	
	public User_Table createUser(int userno, String Long) {
        User_Table user = new User_Table();
        user.setUserno();
        user.AuthId(id);
        return userRepository.save(user);
    }

    public User_Table getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    // 사용자 ID로 사용자 조회
    public User_Table findById(Long userId, User_Table utable ) {
        return userRepository.findById(userId).orElse(null); // 사용자 ID로 조회, 없으면 null 반환
    }
}
