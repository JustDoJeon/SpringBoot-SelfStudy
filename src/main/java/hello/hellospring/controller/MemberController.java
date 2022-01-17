package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        //여기 매개변수 form 에 있는 name에 박스안에 입력받은값이 들어오게됨
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member  : "+ member.getName());

        memberService.join(member);
        return  "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

}
