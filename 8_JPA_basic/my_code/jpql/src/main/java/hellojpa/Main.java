package hellojpa;

import hellojpa.jpql.Member;
import hellojpa.jpql.MemberDTO;
import hellojpa.jpql.MemberType;
import hellojpa.jpql.Team;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            //팀 저장
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);
            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);
            Team teamC = new Team();
            teamC.setName("teamC");
            em.persist(teamC);

            //회원저장
            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);
            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);
            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);
            Member member4 = new Member();
            member4.setUsername("회원4");
            em.persist(member4);


            em.flush();
            em.clear();

            String query = "update Member m set m.age = 20";
            int result = em.createQuery(query)
                    .executeUpdate();
            System.out.println("result = " + result);


            tx.commit();
        } catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}