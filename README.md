# Request Tracker Board(프로젝트 방향성 참고 용도)

Spring Boot 기반으로 구현한 학습용 게시판 프로젝트입니다.  
단순 CRUD 게시판에서 확장하여 HTTP 요청 흐름과 네트워크 정보를 이해하기 위해 IP 로깅 및 요청 추적 기능을 추가했습니다.

---

## 📌 주요 기능

- 게시글 CRUD (Create / Read / Update / Delete)
- Spring MVC 기반 REST API 설계
- 인터셉터 기반 공통 요청 로깅
- 클라이언트 IP 추적 (X-Forwarded-For 고려)
- 요청 URL, HTTP Method, 처리 시간 로깅
- 간단한 Audit Log 구조 설계

---

## 🧠 학습 목표

- HTTP 요청이 서버에 도달하는 과정 이해
- Servlet Filter vs HandlerInterceptor 역할 구분
- 프록시 환경에서 실제 IP 추출 방식 이해
- 로그 기반 요청 추적 구조 설계 경험

---

## 🛠 Tech Stack

- Java 17+
- Spring Boot
- Spring MVC
- MySQL
- JPA (optional)
- Gradle

---

## 📂 주요 설계 포인트

- `Interceptor`를 활용한 공통 요청 처리
- `X-Forwarded-For` 기반 실제 클라이언트 IP 추적
- Controller 레벨과 인프라 레벨 관심사 분리
- 로그를 통한 요청 흐름 분석 가능 구조
- 게시글의 좋아요 구현시 N+1 문제를 해결하기 위한 JPQL 쿼리
```java
@Query(value = """
		select new com.example.board.post.dto.PostListResponseDto(
		p.id, p.title, p.createdDate, p.updatedDate, count(pl.id)
		)
		from Post p
		left join PostLike pl
		on p.id = pl.post.id
		where p.title = :title
		group by 
		p.id, p.title, p.createdDate, p.updatedDate
		order by p.createdDate desc
		""")
	List<PostListResponseDto> findPostListResponseDtosByTitle(@Param("title") String title);
```
---
## 🧩 프로젝트 QNA

### localhost 환경 실습시 클라이언트 정보가 입력되지 않아 NULL 관련 오류가 발생시
1. RESTAPI 요청시 X-Forward-For 헤더에 임의의 IP를 추가
2. application.yml에 아래와 같이 설정이 되었는지 확인
```yml
ip:
  mode: vulnerable   # secure | vulnerable
```
---

## 🚀 향후 개선 아이디어

- Kafka 기반 로그 비동기 처리
- Redis 기반 요청 rate limiting
- ELK stack 연동 (ElasticSearch + Logstash + Kibana)
- 사용자 인증(JWT) 기반 IP 추적 확장
