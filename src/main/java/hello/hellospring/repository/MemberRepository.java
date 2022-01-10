package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

/*
* 현재 DB 뭐 쓸지 JPA방식할지 RDB 연동해서 쓸지가 없기때문에 인터페이스 형태로 회원 레포지토리 만들어놓음
*
*/
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
