package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient {

    private String url; //접속할 네트워크의 url

    public NetworkClient() {
        System.out.println("생성자를 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect(){
        System.out.println("connect: " + url); //실제 네트워크와 연결을 맺는 것이 아니라 간단히 출력하는 것으로 대체
    }

    public void call(String message){ //접속한 네트워크에 메시지를 전달하는 함수
        System.out.println("call: " + url + " message: " + message);
    }

    public void disconnect(){ //서비스 종료 시 호출
        System.out.println("close: " + url);
    }

    @PostConstruct
    public void init() { //의존관계 주입이 끝날때 호출되는 메서드
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() { //빈 종료 시 호출
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
