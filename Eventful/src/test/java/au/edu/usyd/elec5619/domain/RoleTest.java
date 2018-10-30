package au.edu.usyd.elec5619.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RoleTest {

	private Role role;
	
	@Before
	public void setUp() throws Exception {
		role = new Role();
	}
	
	@Test
	public void testSetAndGetRole() {
		String role = "user";
		assertNull(this.role.getRole());
		this.role.setRole(role);
		assertEquals(role, this.role.getRole());
	}
}
