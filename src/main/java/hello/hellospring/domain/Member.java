package hello.hellospring.domain;

public class Member {

    private Long id; //데이터 구분용

    private String name; //회원 이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
