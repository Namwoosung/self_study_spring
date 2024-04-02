package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em; //jpa는 EntityManager를 통해 모든 것이 동작

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        //persist는 영속하다는 의미 => 인자로 넘어온 객체를 db에 알아서 저장(id값 생성, pk설정 등 모든 것을 함)
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); //조회할 type과 식별자 pk값을 넘겨주면 해당 식별자를 찾아 type을 반환
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) //Member라는 객체에서를 전부 select
                .getResultList();
    }
}
