package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.constant.UserRole;
import dao.UserDao;
import dao.TokenDao;
import model.User;
import service.UserService;

public class UserServiceImpl extends BaseServiceImpl implements UserService {
    private UserDao userDao;
    private TokenDao tokenDao; 
    
    /* ======================================================== */

    public UserDao getUserDao() {
        return userDao;
    }
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public TokenDao getTokenDao() {
        return tokenDao;
    }
    public void setTokenDao(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }
    
    /* ======================================================== */
    
    // need admin role
    
    @Override
    public List<User> getAllUsers() {
        List<User> userList = this.userDao.getAllUsers();
        return userList;
    }
    @Override
    public boolean addUser(User user) {
        user.setUserId(0);
        return this.userDao.save(user);
    }
    @Override
    public boolean updateUser(User newUserProfile) {
        return this.userDao.update(newUserProfile);
    }
    @Override
    public boolean deleteUser(int userId) {
        User user = this.userDao.getUserById(userId);
        return this.userDao.delete(user);
    }

    @Override
    public Map<String, Object>  userLogin(String tel, String password){
        Map<String, Object> result = new HashMap<>();
        User user = this.userDao.getUserByTel(tel);
        int id = 0;
        String token = null;
        if (user != null && user.getRole() == UserRole.COMMON && password.equals(user.getPassword())){
            token = this.tokenDao.createToken(user.getUserId());
            id = user.getUserId();
        }
        result.put("user_id", id);
        result.put("token", token);
        return result;
    }
    @Override
    public boolean adminLogin(String username, String password){
        User user = this.userDao.getUserByUsername(username);
        if (user != null
                && user.getRole() == UserRole.ADMIN 
                && password.equals(user.getPassword())){
            return true;
        }
        return false;
    }
    
}