package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

@Slf4j
public class CheckedAppTest {

    @Test
    void checked(){
        Controller controller = new Controller();
        Assertions.assertThatThrownBy(() -> controller.request())
                .isInstanceOf(Exception.class);
    }

    static class Controller{
        Service service = new Service();

        public void request() throws SQLException, ConnectException {
            service.login();
        }
    }

    static class Service{
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        public void login() throws ConnectException, SQLException {
            repository.call();
            networkClient.call();
        }
    }
    static class NetworkClient{
        public void call() throws ConnectException {
            throw new ConnectException();
        }
    }
    static class Repository{
        public void call() throws SQLException {
            throw new SQLException();
        }
    }
}
