package au.edu.usyd.elec5619.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import au.edu.usyd.elec5619.dao.UserDao;
import au.edu.usyd.elec5619.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDao userDao;

	private User testUser;

	@Before
	public void setUp() throws Exception {
		testUser = new User();
		testUser.setAlias("Peter");
		testUser.setBio("This is Peter's Bio.");
		testUser.setEmail("peter@gmail.com");
		testUser.setPassword("password");
		testUser.setConfirmPassword("password");
		testUser.setEnabled(true);
		testUser.setFilePath("https://cdn.onlinewebfonts.com/svg/img_191958.png");
	}
	
	@Test
    public void UserServiceAutowired() {   
        assertNotNull(userService);
        assertNotNull(userDao);
    }
	
	@Test
    public void testCreateUser() {
		userService.createUser(testUser);
		User user = userDao.getUserByEmail(testUser.getEmail());
		
        assertEquals(user.getEmail(), testUser.getEmail());
    }
	
	@Test
	public void testGetUserByEmail() {
		userService.createUser(testUser);
		User user = userService.getUserByEmail(testUser.getEmail());
		
		assertEquals(user.getEmail(), testUser.getEmail());
	}
	
	@Test
	public void testGetUserById() {
		userDao.addUser(testUser);
		User user = userService.getUserById(testUser.getId());
		assertEquals(user.getEmail(), testUser.getEmail());
	}
	
	@Test
	public void testIsUserExist() {
		assertTrue(!userService.isUserExist(testUser));
		userDao.addUser(testUser);
		assertFalse(userService.isUserExist(testUser));
	}
	
	@Test
	public void testDeleteUser() {
		userService.createUser(testUser);
		userService.deleteUser(testUser.getEmail());
		assertTrue(!userService.isUserExist(testUser));
	}
	
	@Test
	public void testGetUserAverageRating() {
		userService.createUser(testUser);
		
		User user = userService.getUserByEmail(testUser.getEmail());
		assertEquals(userService.getUserAverageRating(user), 0.0, 0.5);
	}
}
