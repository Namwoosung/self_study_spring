package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //메서드 이름을 기반으로 JPA가 메서드를 구현한 객체를 넣어줌
    List<Member> findByName(String name);
}
