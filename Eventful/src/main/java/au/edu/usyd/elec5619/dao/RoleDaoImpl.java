package au.edu.usyd.elec5619.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.edu.usyd.elec5619.domain.Role;

@Transactional
@Repository
public class RoleDaoImpl implements RoleDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Role findByRole(String role) {
		String query = "from Role where role= ?1";
		
		List<Role> roles = entityManager.createQuery(query).setParameter(1, role).getResultList();
		
		if (roles == null || roles.isEmpty()) 
			return null;
		
		return roles.get(0);
	}
	
}
