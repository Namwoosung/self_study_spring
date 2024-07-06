package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            //회원 저장
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");

            em.persist(member);

            //회원 수정, 수정 시에는 따로 저장하지 않아도 commit시점에 알아서 update 쿼리를 날려줌
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");

            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();

            //회원 삭제
            em.remove(findMember);

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
