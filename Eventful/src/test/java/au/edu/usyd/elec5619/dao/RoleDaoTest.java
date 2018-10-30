package au.edu.usyd.elec5619.dao;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import au.edu.usyd.elec5619.domain.Role;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleDaoTest {

	@Autowired
    private EntityManager entityManager;
 
    @Autowired
    private RoleDao roleDao;
 
    private Role testRole;
    
    @Before
    public void setUp() throws Exception {
    	testRole = new Role();
    	testRole.setRole("USER");
    }

    @Test
    @Transactional
    public void testFindByRole() {
    	entityManager.persist(testRole);
		entityManager.flush();
		
		Role role = roleDao.findByRole("USER");
		
		assertEquals(role.getRole(), testRole.getRole());
    }
}
