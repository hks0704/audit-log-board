package com.example.board.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.login.model.LoginHistory;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
}
