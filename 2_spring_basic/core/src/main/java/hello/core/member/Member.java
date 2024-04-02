package hello.core.member;

public class Member {
    private Long Id;
    private String name;
    private Grade grade;

    public Member(Long id, String name, Grade grade) {
        Id = id;
        this.name = name;
        this.grade = grade;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
