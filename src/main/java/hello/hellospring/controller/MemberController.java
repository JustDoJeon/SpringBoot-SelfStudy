package hello.hellospring.controller;


import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    //이렇게 new로 하면 다른곳에서도 가져다가 쓸수있죠
//    private final MemberService memberService = new MemberService();

    //그래서 스프링 컨테이너에 빈 등록해서 사용해야함
    private final MemberService memberService;

    @Autowired //스프링컨테이너에서 멤버서비스 가져옴
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
