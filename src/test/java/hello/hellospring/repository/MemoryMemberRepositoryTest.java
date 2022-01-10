package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
/*
* 모든 테스트는 순서 보장안됌
* 그래서 테스트 하나 끝나면 데이터 클리어 해줘야함 => afterEach()
*
*
*/
import java.util.List;

import static org.assertj.core.api.Assertions.*;

 class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();


    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring1");

        repository.save(member);

        //옵셔널이니깐 get으로 꺼냄
        Member result = repository.findById(member.getId()).get();

//      System.out.println("result =" + (result == member)); 의 기능을 하는것 AsserThat
//       Assertions.assertEquals(member,result); // parameter 1: 기대값 ,2: 실제값
        assertThat(member).isEqualTo(result);
    }

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }


    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        Member result = repository.findByName("spring1").get();
        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
     public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);

    }


}
