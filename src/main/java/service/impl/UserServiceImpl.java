package service.impl;

import java.util.List;

import dao.UserDao;
import model.User;
import service.UserService;

public class UserServiceImpl extends BaseServiceImpl implements UserService {
    private UserDao userDao; 
    
    /* ======================================================== */

    public UserDao getUserDao() {
        return userDao;
    }
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /* ======================================================== */

    // TODO
    /*
     */
    
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
    
}