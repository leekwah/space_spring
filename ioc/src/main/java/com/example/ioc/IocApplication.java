package com.example.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class IocApplication {

    public static void main(String[] args) {
        SpringApplication.run(IocApplication.class, args);
        ApplicationContext context = ApplicationContextProvider.getContext();

        // Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
        // Encoder encoder = context.getBean(Encoder.class);

        Encoder encoder = context.getBean("base64Encode",Encoder.class); // base64를 쓰기 위해서는 이것만
        // Encoder encoder = context.getBean("urlEncode",Encoder.class); // url을 쓰기 위해서는 위의 주석처리 후 이것만

        String url = "www.naver.com";
        String result = encoder.encode(url);
        System.out.println(result);
    }
}

@Configuration // 한 개의 클래스에서 여러 개의 Bean 을 생성할 수 있게한다.
class AppConfig {
    @Bean("base64Encode") // @Bean 을 통해서 등록함
    public Encoder encoder(Base64Encoder base64Encoder){
        return new Encoder(base64Encoder);
    }
    @Bean("urlEncode") // @Bean 을 통해서 등록함
    public Encoder encoder(UrlEncoder urlEncoder){
        return new Encoder(urlEncoder);
    }
}