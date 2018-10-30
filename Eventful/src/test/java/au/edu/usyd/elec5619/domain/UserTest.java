package au.edu.usyd.elec5619.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	private User user;
	
	@Before
	public void setUp() throws Exception {
		user = new User();
	}
	
	@Test
	public void testSetAndGetEmail() {
		String email = "test@gmail.com";
		assertNull(user.getEmail());
		user.setEmail(email);
		assertEquals(email, user.getEmail());
	}
	
	@Test
	public void testSetAndGetPassword() {
		String password = "password";
		assertNull(user.getPassword());
		user.setPassword(password);
		assertEquals(password, user.getPassword());
	}
	
	@Test
	public void testSetAndGetConfirmPassword() {
		String password = "password";
		assertNull(user.getConfirmPassword());
		user.setConfirmPassword(password);
		assertEquals(password, user.getConfirmPassword());
	}
	
	@Test
	public void testSetAndGetAlias() {
		String alias = "John";
		assertNull(user.getAlias());
		user.setAlias(alias);
		assertEquals(alias, user.getAlias());
	}
	
	@Test
	public void testSetAndGetFilePath() {
		String filePath = "localhost:8080/abcdefg";
		assertEquals("https://cdn.onlinewebfonts.com/svg/img_191958.png", user.getFilePath());
		user.setFilePath(filePath);
		assertEquals(filePath, user.getFilePath());
	}
	
	@Test
	public void testSetAndGetBio() {
		String bio = "test bio";
		assertNull(user.getBio());
		user.setBio(bio);
		assertEquals(bio, user.getBio());
	}
	
	@Test
	public void testGetEnabled() {
		assertTrue(user.getEnabled());
	}
}
