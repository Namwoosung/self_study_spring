package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;


public class ConversionServiceTest {

    @Test
    void conversionService(){
        //DefaultConversionService는 ConversionService인터페이스를 구현한 구현체 중 하나
        DefaultConversionService conversionService = new DefaultConversionService();

        //컨버터 등록
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());
        conversionService.addConverter(new IntegerToStringConverter());

        //사용
        Integer result = conversionService.convert("10", Integer.class); //문자를 숫자로 반환하는 컨버터 탐색 후 실행
        System.out.println("result = " + result);
        assertThat(result).isEqualTo(10);

        assertThat(conversionService.convert(10, String.class)).isEqualTo("10");
        assertThat(conversionService.convert("127.0.0.1:8080", IpPort.class)).isEqualTo(new IpPort("127.0.0.1", 8080));
        assertThat(conversionService.convert(new IpPort("127.0.0.1", 8080), String.class)).isEqualTo("127.0.0.1:8080");
    }
}
