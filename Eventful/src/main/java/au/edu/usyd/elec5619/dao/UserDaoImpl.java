package au.edu.usyd.elec5619.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.edu.usyd.elec5619.domain.Rating;
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
		String query = "from User u where u.alias= ?1";
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

	@Override
	public void updateUserAlias(String alias, Long id) {
		String hql = "UPDATE User u SET u.alias = ?1 WHERE u.id = ?2";
		
		Query query = entityManager.createQuery(hql);
		
		query.setParameter(1, alias);
		query.setParameter(2, id);
		
		query.executeUpdate();
	}

	@Override
	public void updateUserBio(String bio, Long id) {
		String hql = "UPDATE User u SET u.bio = ?1 WHERE u.id = ?2";
		
		Query query = entityManager.createQuery(hql);
		
		query.setParameter(1, bio);
		query.setParameter(2, id);
		
		query.executeUpdate();
	}

	@Override
	public void updateUserPassword(String password, Long id) {
		String hql = "UPDATE User u SET u.password = ?1 WHERE u.id = ?2";
		
		Query query = entityManager.createQuery(hql);
		
		query.setParameter(1, password);
		query.setParameter(2, id);
		
		query.executeUpdate();
	}

	@Override
	public void updateUserAvatar(String filePath, Long id) {
		String hql = "UPDATE User u SET u.filePath = ?1 WHERE u.id = ?2";
		
		Query query = entityManager.createQuery(hql);
		
		query.setParameter(1, filePath);
		query.setParameter(2, id);
		
		query.executeUpdate();
	}
}
