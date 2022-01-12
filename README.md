<h1> Spring Boot - Self Study 
  <h3>Today I Learned </h3>
●  배운 내용 정리하고 공부하기 (2022-01-10 시작 )<br>


<h2> 2022-01-12 학습내용 정리 </h2> <br>
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

## 
● 2022-01-12, 자바코드로 직접 스프링 빈 등록하기  <br>

# 현재 구조 <br>
![image](https://user-images.githubusercontent.com/52389219/149151008-bfcb8558-0378-417a-b3fc-aa79169166d9.PNG)
- 기존 회원 서비스와 회원 레포지토리의 @Service, @Repository, @AutoWired 어노테이션을 제거하고 진행한다. <br>

```
실습 내용
1) 위에 언급한 어노테이션 지운다.
2) SpringConfig 클래스를 생성하여 @Configuration 어노테이션으로 명시하고 @Bean 을 통해 직접 빈 객체를 생성하는 메소드를 만든다. 

```


<br>
정리내용은 인프런의 김영한님의 강의를 통해 정리되었습니다. 