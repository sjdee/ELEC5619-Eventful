package au.edu.usyd.elec5619.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.edu.usyd.elec5619.domain.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

	protected final Log log = LogFactory.getLog(getClass());

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addUser(User user) {
		log.info(user.getAlias() + " " + user.getEmail() + " " + user.getPassword());
		entityManager.persist(user);
		log.info("User added successfully, User Details=" + user);
	}

	@Override
	public void updateUser(User user) {
		User u1 = getUserById(user.getId());
		u1.setAlias(user.getAlias());
		u1.setBio(user.getBio());
		entityManager.flush();
		log.info("User updated successfully, User Details=" + user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers() {
		String query = "from User as u ORDER BY u.Id";
		List<User> users = entityManager.createQuery(query).getResultList();

		for (User user : users)
			log.info("User List::" + user);

		return users;
	}

	@Override
	public User getUserById(long id) {
		return entityManager.find(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByEmail(String email) {
		String selectQuery = "FROM User where email= ?1";

		List<User> results = entityManager.createQuery(selectQuery).setParameter(1, email).getResultList();

		if (results.isEmpty()) {
			return null;
		} else {
			log.info("User :: " + results.get(0));

			return results.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByAlias(String alias) {
		String query = "from User where alias= ?1";
		List<User> users = entityManager.createQuery(query).setParameter(1, alias).getResultList();
		for (User user : users)
			log.info("User List:: " + user);
		return users;
	}

	@Override
	public void removeUser(String email) {
		entityManager.remove(getUserByEmail(email));
		log.info("User deleted successfully, user email=" + email);
	}

}
