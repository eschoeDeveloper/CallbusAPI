# Callbus Community API

**Callbus 커뮤니티 API 프로젝트**는 공인중개사, 임대인, 임차인 전용 게시판 서비스입니다. 외부 사용자는 읽기 전용이며, 사용자 타입 별로 글을 구분하고, 좋아요 기능을 지원합니다.

---

### callbus : Callbus Community API 구현
### callbus_db : SQLite 기반 Database
### CallbusAPI.postman_collection.json : 포스트맨 컬렉션 정보

---

# 기능 요구사항

- 자리톡에 가입되어 있는 공인중개사, 임대인, 임차인 모두 사용 가능
- 외부 사용자는 글 작성 불가 ( ReadOnly )
- 글 목록에서 작성한 사용자가 어떤 계정 타입인지를 표시할 수 있어야 한다.
- 글 목록에서 좋아요 수를 표시할 수 있어야 한다.
- 자신이 좋아요한 글인지 아닌지 표시할 수 있어야 한다.

---

# 기술 요구사항

- 글 작성, 수정, 삭제
- 글 좋아요
- 글 목록
- [ 식별자 구분 ]
  1. 임차인 - Lessee
  2. 임대인 - Lessor
  3. 공인중개사 - Realtor
  4. 외부 사용자
- 좋아요는 계정당 한 글에 한번만 가능
- 사용자가 어떤 글에 좋아요를 했는지 히스토리를 확인할 수 있어야 한다.
- 각 글은 작성시간, 마지막 수정시간, 삭제시간에 대한 히스토리를 확인할 수 있어야 한다.

---

## 🛠️ 기술 스택

- Java 19 (빌드 시 JDK 1.8 사용)
- Spring Boot 2.7.8
- Spring Security
- Spring Web
- Spring Data JPA
- QueryDSL 5
- SQLite 3
- Lombok
- Gradle
- Embedded Tomcat
- YAML 기반 설정

---

# 프로젝트 정보

- GroupId : com.callbus.restapi
- ArtifactId : callbus_api

# 프로젝트 구현

- 서버 기본포트 사용 -> 8080
- Java Configuration
- RESTFul API 방식 적용 ( RestController -> Service -> Jpa Repository )
- Lombok Data Model 적용 ( Entity 객체 활용 )
- API Method : GET - 조회, POST - 등록, PUT - 수정, DELETE - 삭제
- 사용자 정보는 SecurityContext에서 관리할 수 있도록 구현
- 테이블 정보 -> github의 callbus_db 디렉토리 아래에 있는 callbus_db 데이터베이스 파일 참조
  - CALLBUS_POST : 게시글 정보
  - CALLBUS_POST_LIKE : 게시글 좋아요 정보
  - CALLBUS_USER : 사용자 정보 ( 현재 저장 기능은 없지만, 필요 시 구현 가능 )

# 프로젝트 구조
```text
callbus_api/
├── src/
│ ├── main/
│ │ ├── java/com/callbus/restapi/
│ │ │ ├── controller/
│ │ │ ├── service/
│ │ │ ├── repository/
│ │ │ └── domain/
│ │ └── resources/
│ │ └── application.yml
├── callbus_db/ # SQLite 데이터베이스 디렉토리
├── CallbusAPI.postman_collection.json
└── build.gradle
```

---

## ✅ 주요 기능

### 사용자

- 사용자 유형 구분: `임차인(Lessee)`, `임대인(Lessor)`, `공인중개사(Realtor)`, `외부 사용자`
- 인증된 사용자만 게시글 작성 가능 (외부 사용자는 ReadOnly)

### 게시글

- **목록 조회**: 작성자 타입, 좋아요 수, 내가 좋아요한 여부 포함
- **작성/수정/삭제**: 인증 사용자만 가능
- **히스토리**: 생성일, 수정일, 삭제일 저장

### 좋아요

- **좋아요 설정/취소**: 계정당 한 글에 한 번만 가능
- **좋아요 히스토리 조회**: 어떤 글에 좋아요했는지 확인 가능

## 🔐 인증

- Spring Security 기반 사용자 인증
- 사용자 정보는 `SecurityContext` 에서 관리

---

## 🧪 테스트

### 1. 단위 테스트 (JUnit + SpringBootTest)

- `PostTest`: 게시글 작성/수정/삭제 테스트
- `PostLikeTest`: 좋아요 설정/취소 테스트
- TDD 기반: `Given - When - Then`

### 2. 포스트맨 테스트

- Postman Collection 파일: `CallbusAPI.postman_collection.json`
- 포함된 API 테스트:
  - 글 목록 조회
  - 삭제글 목록 조회
  - 글 작성
  - 글 수정
  - 글 삭제
  - 좋아요 설정/취소
  - 좋아요한 글 목록

---

# 서버 실행

## 프로젝트 빌드
```bash
./gradlew build
```

##  서버 실행
```bash
./gradlew bootRun
```

---

# 📜 라이선스
이 프로젝트는 내부 테스트용으로 제공되며 별도 라이선스가 적용되지 않았습니다.

---

# 📬 문의
- 작성자 : Choe Eui Seung
- Email : develop.eschoe@gmail.com 




