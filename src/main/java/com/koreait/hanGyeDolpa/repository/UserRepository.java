package com.koreait.hanGyeDolpa.repository;

import com.koreait.hanGyeDolpa.entity.User_Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User_Table, Long> {
	User_Table findByUserId(String uid);
}
