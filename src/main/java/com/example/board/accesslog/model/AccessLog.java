package com.example.board.accesslog.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.board.common.dto.ClientInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "access_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class AccessLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String clientIp;

	@Column(nullable = false)
	private String requestUri;

	@Column(nullable = false, length = 500)
	private String userAgent;

	@Column(nullable = false)
	private String httpMethod;

	// @Column(name = "access_time", nullable = false, updatable = false)
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime accessTime;

	public AccessLog(ClientInfo clientInfo) {
		this.clientIp = clientInfo.clientIp();
		this.requestUri = clientInfo.requestUri();
		this.userAgent = clientInfo.userAgent();
		this.httpMethod = clientInfo.httpMethod();
	}
}
