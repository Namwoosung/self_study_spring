//.idea 폴더는 inellig가 사용하는 설정파일들
//gradle 폴더는 우리 프로젝트가 gradle이므로 사용하는 파일들
//src에는 main과 test폴더로 나위어져 있음
//main은 실제 코드, test는 tset용 코드
//main아래에 resources 폴더는 코드를 제외한 XML, HTML, 설정 파일 등이 들어가 있음
//build.gradle은 프로젝트 설정 파일
//예전에는 build.gradle을 다 하나씩 작성했지만, 현재는 spring boot가 알아서 생성해줌

//우리가 외부에서 가져온 라이브러리들은 external libraries에 저장되어 있음

package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {
	//main 함수
	public static void main(String[] args) {
		//SpringApplication.run(실행할 class)를 하면 해당 class가 실행
		//내장한 tomcat이라는 web server를 띄우면서 spring boot를 올림
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
