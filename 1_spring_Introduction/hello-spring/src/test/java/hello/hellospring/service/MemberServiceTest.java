package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }


    //test code method이름은 한글로 하는 경우도 존재
    //test code는 실제 build시 code에 포함되지 않음
    @Test
    void 회원가입() {
        //test code는 given, when, then 구조로 작성하는 것이 좋음
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        


        //then

        //두번째 파라미터의 람다함수가 시행될 때 첫번재 파라미터의 exception이 발생해야한다는 의미
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        
        
/*
        try{ //try를 실행하다가 exception이 발생하면 catch로 넘어감
            memberService.join(member2);
            fail(); //동일한 name의 member를 회원가입했는데 exception이 발생하지 않아 여기로 내려왔다면 test fail
        }catch(IllegalStateException e){ //exception을 받음
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); //해당 exception의 message가 정상적인지 확인
        }
*/
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}