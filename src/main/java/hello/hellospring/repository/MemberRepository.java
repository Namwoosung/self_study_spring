//섹션3에서 회원정보 저장 repository
package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //member를 넘겨줘서 회원을 저장
    Member save(Member member);

    //optional의 의미: find를 했는데, 결과가 없으면 null이 반환될 것
    //요즘에는 null을 그대로 받아서 처리하는 것 보다 Optional로 받아서 처리하는 것을 선호하는 편
    Optional<Member> findById(Long id);
    Optional<Member> findbyname(String name);

    //지금까지 저장된 모든 회원을 list로 받아옴
    List<Member> findAll();

}
