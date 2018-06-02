package dao;

import java.util.List;
import model.User;

public interface UserDao extends BaseDao {
    public User getUserById(int id);
    public User getUserByTel(String tel);
    public User getUserByUsername(String username);

    public List<User> getAllUsers();
    
}