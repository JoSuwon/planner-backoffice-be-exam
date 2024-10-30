package com.planner.backoffice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 데이터베이스 사용
@Transactional // 테스트 후 데이터 롤백
class BackofficeApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(BackofficeApplicationTests.class);

	@Test
	void contextLoads() {
	}

	@Test
	void testUsersIsSeven() {
	}

}
