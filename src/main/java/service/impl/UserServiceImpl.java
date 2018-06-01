package service.impl;

import java.util.List;

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
    public String userLogin(String tel, String password){
        User user = this.userDao.getUserByTel(tel);
        if (user.getRole() == UserRole.COMMON && user.getPassword() == password && user != null){
            return this.tokenDao.createToken(user.getUserId());
        }
        return null;
    }
    @Override
    public boolean adminLogin(String username, String password){
        User user = this.userDao.getUserByUsername(username);
        if (user.getRole() == UserRole.ADMIN && user.getPassword() == password && user != null){
            return true;
        }
        return false;
    }
    
}