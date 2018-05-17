package dao.impl;

import java.util.List;
import org.hibernate.query.Query;

import model.User;
import dao.UserDao;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    @Override
	public User getUserById(int userId) {
		String hql = "from User u where u.userId = :userId";
		Query query = getSession().createQuery(hql).setParameter("userId", userId);
        List<User> users = query.list();
		User user = users.size() == 1 ? users.get(0) : null;
		return user;
	}

    @Override
    public User getUserByUsername(String username) {
        String hql = "from User where username = :username";
        Query query = getSession().createQuery(hql).setParameter("username", username);
        List result = query.list();
        if(result.size() == 1) {
            return (User)result.get(0);
        } 
        return null;
    }

    @Override
    public User getUserByTel(String tel) {
        String hql = "from User where tel = :tel";
        Query query = getSession().createQuery(hql).setParameter("tel", tel);
        List result = query.list();
        if(result.size() == 1) {
            return (User)result.get(0);
        } 
        return null;
    }

    @Override
	public List<User> getAllUsers() {
		String hql = "from User";
		Query query = getSession().createQuery(hql);
		List<User> users = query.list();
		return users;
	}

}
