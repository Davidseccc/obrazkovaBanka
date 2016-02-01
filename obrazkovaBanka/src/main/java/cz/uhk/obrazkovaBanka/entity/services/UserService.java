package cz.uhk.obrazkovaBanka.entity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import cz.uhk.obrazkovaBanka.entity.User;
import cz.uhk.obrazkovaBanka.entity.dao.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public long countAllUsers() {
		return userDao.countUsers();
	}

	public void deleteUser(User user) {
		userDao.remove(user);
	}

	public User findUser(Integer id) {
		return userDao.findUser(id);
	}

	public User findUser(String name) {
		return userDao.findUserByNameEquals(name).getSingleResult();
	}

	public User findUserByEmail(String email) {
		return userDao.findUserByEmailEquals(email).getSingleResult();
	}

	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	public List<User> findUserEntries(int firstResult, int maxResults) {
		return userDao.findUserEntries(firstResult, maxResults);
	}

	public void saveUser(User user) {
		userDao.persist(user);
	}

	public User updateUser(User user) {
		return userDao.merge(user);
	}

	public User loginUser(String email, String password) {
		User user = this.findUserByEmail(email);
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
			return user;
		}
		return null;
	}

	public boolean checkUser(User user, String chackedPassword) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		if (user != null && bCryptPasswordEncoder.matches(chackedPassword, user.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	public User findUserByNickName(String nickName) {
		User user = userDao.findUserByNickNameEquals(nickName).getSingleResult();
		if (user != null) {
			return user;
		}
		return null;
	}

	public void changeUserPassword(User user, String newPass) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		if (user != null)  {
			user.setPassword(bCryptPasswordEncoder.encode(newPass));
			userDao.merge(user);
		}

	}
}
