package com.example.board.accesslog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.accesslog.model.AccessLog;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
}
