package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//만들어 놓았던 MemberRepository interface를 implement해서 사용
public class MemoryMemberRepository implements MemberRepository{
    //save할 때 저장할 변수로 Map을 활용
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    //새로운 member를 저장하는 method
    //아이디는 고객이 회원가입할 떄 입력한 값이 이미 들어가 있고, system에서 저장할 때
    //system id를 설정하면서 저장해야 하니 id만 setting해주고 고객이름으로 member를 넘겨주는 것
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //store이라는 map에 id로 검색을 진행
        //여기서 null이 반환될 가능성이 있으니 Optional이라는 것으로 감싸서 반환해 줌
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findbyname(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
