package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

//회원 객체를 저장하는 저장소 interface -> 필요한 기능을 정의
public interface MemberRepository {
    Member save(Member member); //회원 저장
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll(); //모든 회원 조회

}
