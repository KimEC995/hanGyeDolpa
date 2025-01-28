package com.koreait.hanGyeDolpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.hanGyeDolpa.entity.User;
import com.koreait.hanGyeDolpa.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // 비밀번호 설정
        user.setName(username); 
        
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    // 사용자 ID로 사용자 조회
    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null); // 사용자 ID로 조회, 없으면 null 반환
    }
}


