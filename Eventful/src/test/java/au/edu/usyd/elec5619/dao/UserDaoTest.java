package au.edu.usyd.elec5619.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import au.edu.usyd.elec5619.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
	
	@Autowired
    private EntityManager entityManager;
 
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
    public void testAddUser() {
        userDao.addUser(testUser);
        User user = entityManager.find(User.class, 1L);

        assertEquals(user.getEmail(), testUser.getEmail());
    }
	
    @Test
    @Transactional
	public void testUpdateUserAlias() {
		entityManager.persist(testUser);
		entityManager.flush();
		
		userDao.updateUserAlias("John", new Long(1));
		User user = entityManager.find(User.class, 1L);
		
		assertEquals(user.getAlias(), "John");
	}
	
	public void testUpdateUserBio() {
		entityManager.persist(testUser);
		entityManager.flush();
		
		userDao.updateUserBio("This is a test for Peter.", new Long(1));
		User user = entityManager.find(User.class, 1L);
		
		assertEquals(user.getBio(), "This is a test for Peter.");
	}
	
	public void testUpdateUserPassword() {
		entityManager.persist(testUser);
		entityManager.flush();
		
		userDao.updateUserPassword("newpassword", new Long(1));
		User user = entityManager.find(User.class, 1L);
		
		assertEquals(user.getPassword(), "newpassword");
	}
	
	public void testUpdateUserAvatar() {
		entityManager.persist(testUser);
		entityManager.flush();
		
		userDao.updateUserAvatar("newAvatarFilePath", new Long(1));
		User user = entityManager.find(User.class, 1L);
		
		assertEquals(user.getFilePath(), "newAvatarFilePath");
	}
	
	public void testListUsers() {
		entityManager.persist(testUser);
		entityManager.flush();
		
		List<User> users = userDao.listUsers();
		assertEquals(users.get(0).getEmail(), testUser.getEmail());
	}
	
	public void testGetUserById() {
		entityManager.persist(testUser);
		entityManager.flush();
		
		User user = userDao.getUserById(1L);
		assertEquals(user.getEmail(), testUser.getEmail());
	}
	
	public void testGetUserByEmail() {
		entityManager.persist(testUser);
		entityManager.flush();
		
		User user = userDao.getUserByEmail("peter@gmail.com");
		assertEquals(user.getAlias(), testUser.getAlias());
	}
	
	public void testGetUsersByAlias() {
		entityManager.persist(testUser);
		entityManager.flush();
		
		List<User> user = userDao.getUsersByAlias("Peter");
		assertEquals(user.get(0).getEmail(), testUser.getEmail());
	}
	
	public void testRemoveUser() {
		entityManager.persist(testUser);
		entityManager.flush();
		
		userDao.removeUser(testUser.getEmail());
		
		assertNull(userDao.getUserByEmail(testUser.getEmail()));
	}
}