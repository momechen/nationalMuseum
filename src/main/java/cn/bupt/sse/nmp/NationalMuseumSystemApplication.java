package cn.bupt.sse.nmp;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@MapperScan(value = "cn.bupt.sse.nmp.dao")
@SpringBootApplication
public class NationalMuseumSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(NationalMuseumSystemApplication.class);
    }
}
