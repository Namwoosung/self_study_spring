package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 트랜잭션 - 파라미터 연동, 풀을 고려한 종료
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Connection con = dataSource.getConnection();
        try{
            con.setAutoCommit(false); //트랜잭션 시작

            bizLogic(con, fromId, toId, money); //계좌이체하는 비즈니스 로직 부분

            con.commit(); //정상 수행의 경우 커밋
        } catch (Exception e){
            con.rollback(); // 실패 시 롤백
            throw new IllegalStateException(e);
        }finally {
            if(con != null){
                try{
                    con.setAutoCommit(true);//커넥션 풀을 고려
                    //만약 Datasource가 커넥션 풀을 사용하는 상황이라면 이 커넥션은 닫히는 것이 아니라 풀로 돌아갈 것
                    //그런데 현재 우리는 AutoCommit을 false로 설정했기에, false인 채로 풀로 돌아감
                    //=> 기본값인 true로 바꿔주고 풀로 반환해야 함
                    //(만약 datasource가 커넥션 풀을 사용하지 않으면 굳이 true로 바꿀 필요는 없음)
                    //현재는 datasource가 무엇을 사용할지 모르기에 커넥션 풀을 사용하는 상황까지 고려한 것
                    con.close();
                }catch(Exception e){
                    log.info("error", e);
                }
            }
        }
    }

    private void bizLogic(Connection con, String fromId, String toId, int money) throws SQLException {
        //비즈니스 로직
        Member fromMember = memberRepository.findById(con, fromId);
        Member toMember = memberRepository.findById(con, toId);

        memberRepository.update(con, fromId, fromMember.getMoney() - money);
        validation(toMember); //임의로 예외 상황 발생을 위한 메서드 => toMember가 ex라면 계좌이체 실패라고 가정
        memberRepository.update(con, toId, toMember.getMoney() + money);
    }

    private static void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체중 예외 발생");
        }
    }
}
