//섹션 3강의를 위한 폴더
package hello.hellospring.domain;

public class Member {
    //요구사항 분석에서 유저에게 필요한 정보 2개를 선언
    //id는 user의 계정 id가 아니라 DB에서 식별자 id느낌
    private Long id;
    private String name;

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
