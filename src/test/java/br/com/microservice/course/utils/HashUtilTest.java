package br.com.microservice.course.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HashUtilTest {
	
	@Test
	public void getSecureHashTest() {
		String hash = HashUtil.getSecureHash("123");		
		System.out.println("HASH:::" + hash);
		
		assertThat(hash.length()).isEqualTo(64);
		
		//a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3
	}

}
