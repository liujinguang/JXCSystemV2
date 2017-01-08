package com.lzw.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lzw.model.User;

public class TestUser {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		UserDao dao = new UserDao();
		User user = new User("administor", "administor", "administor", "A");
		dao.saveOrUpdate(user);
		
//		User user1 = new User("Xiaoming", "xm", "xm1", "O");
//		dao.saveOrUpdate(user1);
//		
//		User user2 = new User("Xinxin", "xx", "xx2", "A");
//		dao.saveOrUpdate(user2);
	}

}
