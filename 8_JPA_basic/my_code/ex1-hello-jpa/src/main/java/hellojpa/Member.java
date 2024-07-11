package hellojpa;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@SequenceGenerator(name = "member-seq-generator", sequenceName = "member_seq")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member-seq-generator")
    private Long id;
    @Column(name = "name", nullable = false)
    private String username;


    public Member() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
