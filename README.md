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



정리 내용은 인프런의 김영한님의 강의를 통해 정리되었습니다. 