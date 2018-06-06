package service;

import java.util.List;
import java.util.Map;

import model.User;

public interface UserService extends BaseService {
    // TODO
    /*
    public Map login(String username, String plainPassword);
    public boolean register(User registerInfo);
    public boolean checkTelAvailable(String tel);
    public boolean logout();    // 是否需要接受userId作为参数？
    public User showUserProfile();    // 是否需要接受userId作为参数？
    public boolean updatePassword(String oldPlainPassword, String newPlainPassword);
    public boolean updateUserProfile(User newUserProfile);
    */
    
    /* ======================================================== */
    
    // need admin role
    public List<User> getAllUsers();
    public boolean addUser(User user);
    public boolean updateUser(User newUserProfile);
    public boolean deleteUser(int userId);

    public boolean adminLogin(String username, String password);
    public Map<String, Object> userLogin(String tel, String password);
    //public boolean userSignup(String phone, String username, String password);
    
    public Map<String, Object> setRelationToFridge(int userId, int fridgeId);
    public Map<String, Object> getRelationToFridge(int userId);
    public Map<String, Object> delRelationToFridge(int userId, int fridgeId);
}