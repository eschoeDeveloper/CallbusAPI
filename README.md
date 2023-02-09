callbus : Callbus Community API 구현
callbus_db : SQLite 기반 Database
CallbusAPI.postman_collection.json : 포스트맨 컬렉션 정보

---

# 기능 요구사항

- 자리톡에 가입되어 있는 공인중개사, 임대인, 임차인 모두 사용 가능
- 외부 사용자는 글 작성 불가 ( ReadOnly )
- 글 목록에서 작성한 사용자가 어떤 계정 타입인지를 표시할 수 있어야 한다.
- 글 목록에서 좋아요 수를 표시할 수 있어야 한다.
- 자신이 좋아요한 글인지 아닌지 표시할 수 있어야 한다.

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

# 개발환경

- Spring Boot 2.7.8

  - Security
  - Web
  - Data JPA

- SDK - OpenJDK 19 -> 프로젝트 설정 시 JDK 1.8로 지정
- Embedded Tomcat
- YAML
- Gradle
- QueryDSL 5
- SQLite 3
- Lombok

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

# 빌드 방법

- 프로젝트 루트 경로에서 아래 명령어 실행
  - gradlew build

# 테스트 방식

### 1] 테스트 클래스 구현 ( SpringBootTest + JUnit )

- TDD 패턴 적용 ( Given-When-Then )
- 구현 클래스 목록
  - PostTest : 글 작성/수정/삭제 테스트
  - PostLikeTest : 글 좋아요 설정/취소 테스트

### 2] 포스트맨 활용 테스트

- 컬렉션 정보 : CallbusAPI.postman_collection.json 파일 참조
- 아래 기능별 컬렉션 생성 후, Local Test 진행
  - 글 목록 조회
  - 삭제글 목록 조회
  - 글 작성
  - 글 수정
  - 글 삭제
  - 글 좋아요 목록
  - 글 좋아요 설정
  - 글 좋아요 취소
