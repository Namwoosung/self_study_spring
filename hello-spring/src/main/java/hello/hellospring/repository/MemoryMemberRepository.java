package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

//구현체
public class MemoryMemberRepository implements  MemberRepository{


    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //key값 생성 용도

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //member의 id를 setting
        store.put(member.getId(), member); //store에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //id를 통해 store에서 memeber 얻어옴
        //id에 해당하는 member가 없다면 null이 반환될 수도 있기에 NPE방지를 위해 Optional 사용
    }

    @Override
    public Optional<Member> findByName(String name) {
        //store의 모든 item에 대해 반복을 돌며서 filter를 수행
        //member 중 name이 동일한 itemd르 찾으면 반환
        //없으면 Optional에 null이 포함되어 반환(findAny()자체가 Optional하게 반환하는 것)
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        //store.values()로 store가 가지고 있는 value인 member들을 모두 반환
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
