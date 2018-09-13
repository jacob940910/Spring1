package dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoodDaoFactory {
	@Bean
	//GoodDao 인스턴스를 생성해서 리턴하는 메소드
	public static GoodDao create() {
		
		return new GoodDao();
	}
}
