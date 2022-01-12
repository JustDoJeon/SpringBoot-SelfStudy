<h1> Spring Boot - Self Study 
  <h3>Today I Learned </h3>
●  배운 내용 정리하고 공부하기 (2022-01-10 시작 )<br>


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

정리 내용은 인프런의 김영한님의 강의를 통해 정리되었습니다. 