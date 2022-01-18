package hello.hellospring.controller;

public class MemberForm {
    // 서버로 올라갈때 그 html 의 name=" " 이부분에 해당하는 값 임
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
