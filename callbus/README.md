# 기능 요구사항

* 자리톡에 가입되어 있는 공인중개사, 임대인, 임차인 모두 사용 가능
* 외부 사용자는 글 작성 불가 ( ReadOnly )
* 글 목록에서 작성한 사용자가 어떤 계정 타입인지를 표시할 수 있어야 한다.
* 글 목록에서 좋아요 수를 표시할 수 있어야 한다.
* 자신이 좋아요한 글인지 아닌지 표시할 수 있어야 한다.

# 기술 요구사항

* 글 작성, 수정, 삭제
* 글 좋아요
* 글 목록
* [ 식별자 구분 ]
  1) 임차인 - Lessee
  2) 임대인 - Lessor
  3) 공인중개사 - Realtor
  4) 외부 사용자
* 좋아요는 계정당 한 글에 한번만 가능
* 사용자가 어떤 글에 좋아요를 했는지 히스토리를 확인할 수 있어야 한다.
* 각 글은 작성시간, 마지막 수정시간, 삭제시간에 대한 히스토리를 확인할 수 있어야 한다.

# 개발환경

* Spring Boot 2.7.8
  - Security
  - Web
  - Data JPA


* SDK - OpenJDK 19 -> JDK 1.8로 변경 설정
* Embedded Tomcat
* YAML
* Gradle
* QueryDSL 5
* SQLite 3
* Lombok

# 프로젝트 정보

* GroupId : com.callbus.restapi
* ArtifactId : callbus_api

# 프로젝트 구현
* RESTFul API 방식 적용 ( RestController -> Service -> Jpa Repository )
* Lombok Data Model 적용 ( Entity 객체 활용 )
* API Method : GET - 조회, POST - 등록, PUT - 수정, DELETE - 삭제
* 테이블 정보 -> callbus/database 아래 callbus_db 참조
  - CALLBUS_POST : 게시글 정보
  - CALLBUS_POST_LIKE : 게시글 좋아요 정보
  - CALLBUS_USER : 사용자 정보

# 테스트 방식

* 테스트 클래스 구현 ( SpringBootTest + JUnit )
  - TDD 패턴 적용 ( Given-When-Then )
  - 구현 클래스 목록
    - PostTest : 글 작성/수정/삭제 테스트
    - PostLikeTest : 글 좋아요 설정/취소 테스트
* 포스트맨 활용 테스트
  - 

