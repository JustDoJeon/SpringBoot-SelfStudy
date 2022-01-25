<h1> Spring Boot - Self Study 
  <h3>Today I Learned </h3>
●  배운 내용 정리하고 공부하기 (2022-01-10 시작 )<br>
● 정리 내용은 인프런의 김영한님의 강의를 통해 정리되었습니다. 

<h2> 2022-01-12 학습내용 정리 </h2>
<h3> 일반적인 웹 애플리케이션 계층 구조 </h3> 
<br>

![image](https://user-images.githubusercontent.com/52389219/149151010-58f1d4f6-10d0-4bde-a9ed-d8281b2e358d.PNG)

<br>
● 컨트롤러: 웹 MVC의 컨트롤러 역할 <br>
● 서비스: 핵심 비즈니스 로직 구현 <br>
● 리포지토리: 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리 <br>
● 도메인: 비즈니스 도메인 객체, 예) 회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장하고 관리됨 <br>

<h3> 클래스 의존관계 </h3> 

![image](https://user-images.githubusercontent.com/52389219/149151005-873e342a-5e36-4075-9e33-8afed2f84d5a.PNG)
- 추후 인터페이스에 디비 어떤거 사용할지 정하고 바꿀것 <br>

## 
● 2022-01-12, 자바코드로 직접 스프링 빈 등록하기  <br>

<h3> 현재 구조 </h3>

![image](https://user-images.githubusercontent.com/52389219/149151008-bfcb8558-0378-417a-b3fc-aa79169166d9.PNG)
- 기존 회원 서비스와 회원 레포지토리의 @Service, @Repository, @AutoWired 어노테이션을 제거하고 진행한다. <br>

<h3> 실습 내용 </h3>

```
1) 위에 언급한 어노테이션 지운다. 

2) SpringConfig 클래스를 생성하여 @Configuration 어노테이션으로 명시하고
 @Bean 을 통해 직접 빈 객체를 생성하는 메소드를 만든다. 

3) 동작원리를 이해한다.  

4) 이렇게 자바로 빈 등록할때의 장점 기존의 운영중인 코드를 memoryMemberRepository를 바꾸면서 할수있음  
=> config 파일에서 객체 생성 return할때 어떤 객체를 생성해서 리턴할지만 바꿔주면 된다.  

```
<h3> 블로그에 추가 게시하며 공부할 것 </h3>

```
1. setter 주입, 생성자 주입, 필드 주입 관련 내용 및 실습정리
2. DI에 대한 정확한 개념

```

<h2> 2022-01-18 학습내용 정리 </h2>

<h3>순수 jdbc 사용 (난 왜 이걸 쓰면서 불편함을 못느꼈을까... ) </h3>

```
1. build.gradl에 라이브러리 추가 

implementation 'org.springframework.boot:spring-boot-starter-jdbc'

runtimeOnly 'com.h2database:h2'

2. application.properties에 경로 추가

spring.datasource.url=jdbc:h2:~/test

spring.datasource.driver-class-name=org.h2.Driver

(원랜 아이디비번 치는데 h2 디비라서 스킵) 
+ springframework.jdbc 가 import 되지않아서 gradle 다시 확인했는데 있어서 ide 툴을 재부팅하니깐 적용되었다! 다행히 10분날림 !

jdbcMemberRepository 클래스를 생성해서 고전의 방법으로 쿼리 작성하며 커넥션을 시도하고 반환하는 작업을 해보았다.

이제 실행하면 될까? 정답은 아니다 Config를 맞춰줘야한다. 

Spring Config 파일에 기존의 메모리멤버레포지토리 대신 jdbc레포지로 바꿔줌

돌렸더니 화이트라벨... 

원인은 jdbc:h2:tcp://localhost/~/test 로 설정을 줘야하고

부트 2.4 버전부터는 spring.datasource.username=sa 를 설정파일(application.properties)에 추가해야한다고 한다.

```


<h2> 2022-01-19 공부 정리 </h2>

<h3> 스프링 통합 테스트 </h3>

```
1. 테스트를 위한 클래스와 
@SpringBootTest
@Transactional 추가


2. 테스트에 필요한 객체 주입, 테스트니깐 그냥 필드 주입 사용

3. 위에 필드 주입과 기존 테스트 코드를 넣고 실행하니깐 SpringConfig 다 올라옴 !

4. @Transactional을 달면 테스트케이스에서 테스트를 실행할때 db 넣었던데이터가 반영이 안되고 다 roll back 됨 
-> 다음 테스트를 또 반복해서 할 수 있다 ( 중복 데이터 생길 일 없으니깐 ) 

@SpringBootTest : 스프링 컨테이너와 테스트를 함께 실행한다

* @Transactional : 테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백한다. 
이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.

+ 순수한 단위테스트가 좋은 테스트일 확률이 높다. 스프링 띄어서 하는 통합테스트 보다...!
```
<h2> 2022-01-24 공부 정리 </h2>

<h3> 스프링 JdbcTemplate </h3>
```
1. JdbcTemplateMemberRepository 클래스 만들고 여기 MemberRepository 인터페이스를 구현

2. DataSource를 주입받아서 생성자 생성

3. 생성자가 딱 ! 1개일때 @AutoWired 생략가능!!

4. 조회하는 메소드인 findById를 구현할때, return jdbcTemplate.query(매개변수1,매개변수2);
   작성했는데, 매개변수 1 : 실행할 sql 쿼리를 넣어주면 되고 
   매개변수 2에는 memberRowMapper()를 넣어주면된다. 
   이 매개변수2는 jdbc 사용시 결과를 반환해주는 resultSet이라고 생각하면 된다. 

5. JdbcTemplateMemberRepoisory 클래스의 save를 구현하는데 SimpleJdbcInsert 클래스를 사용하면 
쿼리를 쓸 필요가 없다고 하셨다. document를 보면서 하면 충분히 구현할수 있는 범위같다. 
insert할때 mvc에서 xml을 기계적으로 이용했는데 이런 방법이 있는것도 알게되었다.
위 클래스 객체의 메소드에 테이블 이름과 키 칼럼을 넣고 맵 형식의 파라미터에 데이터를 읽어서 사용하는 방식이다. 

6. 다른 메소드들 (CRUD 구현)도 save와 동일한 방법을 사용하거나 List<Member> 형식으로 반환된 값을 리턴하는 형식으로 사용한다. 

7. SpringConfig에 jdbcTemplate 관련 설정으로 빈 등록해놔야함 ! 

8.

<h3> 블로그에 추가 게시하며 공부할 것 </h3>

```
자바 람다 표현식
```
<br>

<h2> 2022-01-25 공부 정리 </h2>

<h3> JPA </h3>
드디어 쿼리를 사용하지않고 데이터 매핑을 한다는 JPA를 배우게되었다. 
<BR>
요약 강의라 깊은 내용은 아니지만 이번기회에 맛을 보고 깊게 공부해 가야겠다.


<h4> JPA 간단 설명  </h4>
● JPA는 기존의 반복 코드는 물론이고, 기본적인 SQL도 JPA가 직접 만들어서 실행해준다. <br>
● JPA를 사용하면, SQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임을 전환을 할 수 있다.<br>
● JPA를 사용하면 개발 생산성을 크게 높일 수 있다 <br>

1...스프링 부트에 jpa 환경설정 세팅하기 
1) build.gradle에 jpa 관련 라이브러리 추가하고 원래 쓰던 jdbc는 주석을 해놓기로 한다 
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
	implementation 'org.springframework.boot:spring-boot-starter-web'

	testImplementation 'org.springframework.boot:spring-boot-starter-test'
//	implementation 'org.springframework.boot:spring-boot-starter-jdbc'
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
 	runtimeOnly 'com.h2database:h2'
}

2) application.properties 파일 
spring.jpa.show-sql=true      
-> jpa가 날리는 sql 볼수있음
spring.jpa.hibernate.ddl-auto=none
-> jpa시 객체를 보고 자동으로 테이블을 만들어주는데 현재는 테이블있으니깐  none으로 설정 

여기서, external library에 jpa 라이브러리와 hibernate 라이브러리 들어온것 확인

jpa는 인터페이스를 제공하는것인데 구현기술이 여러 벤더가 있음
구현은 여러 업체가 한다고 생각하면 된다. (어떤 업체 구현이 성능이 좋더라~~)

본격 실습
1) Member 테이블에 @Entity 어노테이션 추가
2) @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  // GeneratedValue를 통해 DB가 알아서 시퀀스 생성해서 추가되듯이 사용한다.
    private Long id; //데이터 구분용 
3)JpaMemberRepository를 생성하고 implements 를 통해 메소드를 구현한다.
!! 이때, EntityManager를 생성하는데 JPA는 이 클래스의 객체를 통해 모든 동작을 한다고 한다. (주입해서 사용)

4) 위 클래스의 소스
-> pk를 사용하지않는 쿼리의 경우 JPQL을 사용해야한다 하지만 스프링 데이터 JPA를 사용하면 이마저도 사용하지 않아도된다고 한다. 공부할게 많은것같다 ㅎㅎ...

5) JPA를 사용하려면 항상 Transaction이 필요 Service단 메소드에 @Transaction 추가!


 <h2> 2022-01-25 공부 정리 </h2>

<h3> 스프링 DATA JPA</h3> 
특징 by spring document
```
Spring 및 JPA 기반의 리포지토리 구축을 위한 정교한 지원

Querydsl 술어 지원 및 이에 따른 유형 안전 JPA 쿼리

도메인 클래스의 투명한 감사

페이지 매김 지원, 동적 쿼리 실행, 맞춤형 데이터 액세스 코드 통합 기능

@Query부트스트랩 시간 에 주석 이 달린 쿼리의 유효성 검사

XML 기반 엔티티 매핑 지원

@EnableJpaRepositories을 도입하여 JavaConfig 기반 저장소 구성 
```


본격 실습
1. SpringDataJpaMemberRepository 인터페이스 생성 및 JpaRepository<> ,MeberRepository를 다중상속받음 (인터페이스가 인터페이스 받는 구조)
2. SpringDataJpa가 이 클래스를 구현체로 만들어서 등록을 해줌 
3. 그러므로 SpringConfig 에서 빈등록 하기만 하면됨..
4. 자료 그림 꼭 참고 
5. JpaRepository를 보면 기본 메소드 들이 다 제공이됨 


+ 실무에선 JPA와 스프링 데이터 JPA를 기본으로 사용하고, 복잡한 동적쿼리는 Querydsl이라는 라이브러리를 사용한다고 한다.
이 조합으로 해결하기 어려운 쿼리는 jpa가 제공하는 네이티브 쿼리를 사용하거나, JdbcTemplate을 같이 사용한다고 한다.




<h2> 2022-01-26 공부 정리 </h2>
<h3> AOP </h3>

AOP가 필요한 상황

- 모든 메소드의 호출 시간을 측정하고 싶다면? <br>
- 공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern) <br>
- 회원 가입 시간, 회원 조회 시간을 측정하고 싶다면? <br>

필요한 상황 만들기
```
JOIN에 걸리는 시간을 System.currentTimeMilss();과 try-catch를 이용해서 구하는 상황을 만들었다.
그런데, 모든 메소드에 적용하려면.. try-catch 를 공통메서드로 만들수도 없고, 핵심로직과 섞여있으니깐 유지보수가 어렵다.
시간 측정 로직 변경하려면 모든 로직 다 찾아야하니깐 빡세다.
그래서 공통 관심 사항과 핵심 관심 사항을 나눌수있고 이것을 AOP를 적용하면서 해결가능하다.
  
```

실습
```
aop 패키지와 함께 TimeTraceAop 클래스를 작성한다.
이 클래스는 SpringConfig를 통해 Bean 으로 등록해줘야한다 (해당 클래스에 @Component 라고 명시해도되긴함)
그리고 사용하기위해 구현한 메소드에 @Around("execution(* 패키지명..*(..))") 
이때 SpringConfig를 통해 bean으로 등록한 TimeTraceAop가 순환참조가 나왔는데
원인은 @Around를 통해 자기 자신도 참조하기 때문이다.
그래서 @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)")
이렇게 써야 순환참조가 나타나지 않는다 !
강의자료에서 사진 추가할것 !

jointPoint 에 대한 추가 학습이 필요할것 같다.
```


블로그 추가 공부해야할 내용 : try-catch-finally , JointPoint

📕 정리 내용은 인프런의 김영한님의 강의를 통해 정리되었습니다. 

# 🏔 목표
# ⛲️ 개요
# 💻 언어
# ✏️ 배울 개념
# 📐 방법
## 📕 참고할 만한 책