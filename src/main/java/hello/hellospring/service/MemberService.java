package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    //new 생성이아니라 외부에서 넣어주는걸로 바꿈
    //멤버 서비스입장에서 외부에서 멤버레포지토리를 넣어줌 이것을 DI 라고함
    //테스트 코드를 작성할때 실제 클래스의 객체와 다른것을 객체생성해서 사용하니깐 이렇게함
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member) {
        //같은 이름이 있는 중복회원 x
        Optional<Member> result = memberRepository.findByName(member.getName());

        validateDuplcateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplcateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /*
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
