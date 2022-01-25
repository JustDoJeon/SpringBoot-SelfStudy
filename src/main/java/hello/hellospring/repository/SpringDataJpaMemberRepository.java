package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//식별자 id 타입이 Loing
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository{

    //JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
