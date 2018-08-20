package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

import common.constant.UserRole;
import dao.UserDao;
import dao.UserFridgeRelationshipDao;
import dao.DailyChangeDao;
import dao.FridgeDao;
import dao.ItemDao;
import dao.TokenDao;
import model.DailyChange;
import model.Item;
import model.User;
import model.UserFridgeRelationship;
import service.UserService;

public class UserServiceImpl extends BaseServiceImpl implements UserService {
    private UserDao userDao;
    private TokenDao tokenDao;
    private FridgeDao fridgeDao;
    private UserFridgeRelationshipDao userFridgeRelationshipDao;
    private DailyChangeDao dailyChangeDao;
    private ItemDao itemDao;
    
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
    public FridgeDao getFridgeDao() {
        return fridgeDao;
    }
    public void setFridgeDao(FridgeDao fridgeDao) {
        this.fridgeDao = fridgeDao;
    }
    public UserFridgeRelationshipDao getUserFridgeRelationshipDao() {
        return userFridgeRelationshipDao;
    }
    public void setUserFridgeRelationshipDao(UserFridgeRelationshipDao userFridgeRelationshipDao) {
        this.userFridgeRelationshipDao = userFridgeRelationshipDao;
    }
    public DailyChangeDao getDailyChangeDao() {
        return dailyChangeDao;
    }
    public void setDailyChangeDao(DailyChangeDao dailyChangeDao) {
        this.dailyChangeDao = dailyChangeDao;
    }
    public ItemDao getItemDao() {
        return itemDao;
    }
    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
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
    
    @Override
    public Map<String, Object> setRelationToFridge(int userId, int fridgeId) {
        Map<String, Object> params = new HashMap<String, Object>();
        UserFridgeRelationship ufr = this.userFridgeRelationshipDao.getRelationshipByUserAndFridge(userId, fridgeId);
        if(this.userDao.getUserById(userId) == null) {
            params.put("success", false);
            params.put("message", "user not exists");
            return params;
        }
        if(this.fridgeDao.getFridgeById(fridgeId) == null) {
            params.put("success", false);
            params.put("message", "fridge not exists");
            return params;
        }
        if(ufr != null) {
            params.put("success", false);
            params.put("message", "relationship already exists");
            return params;
        }
        this.userFridgeRelationshipDao.save(new UserFridgeRelationship(0, fridgeId, userId));
        params.put("success", true);
        return params;
    }
    @Override
    public Map<String, Object> getRelationToFridge(int userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        List<UserFridgeRelationship> lufr = this.userFridgeRelationshipDao.getFridgesOfUser(userId);
        if(lufr == null) {
            params.put("success", false);
            return params;
        }
        params.put("success", true);
        params.put("result", lufr);
        return params;
    }
    @Override
    public Map<String, Object> delRelationToFridge(int userId, int fridgeId) {
        Map<String, Object> params = new HashMap<String, Object>();
        UserFridgeRelationship ufr = this.userFridgeRelationshipDao.getRelationshipByUserAndFridge(userId, fridgeId);
        if(ufr == null) {
            params.put("success", false);
            params.put("message", "relationship doesn't exists");
            return params;
        }
        this.userFridgeRelationshipDao.delete(ufr);
        params.put("success", true);
        return params;
    }
    
    @Override
    public List<Map<String, Object>> getEatingRecords(int userId) {
        List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
        List<DailyChange> ld = this.dailyChangeDao.getDailyChangeOfUser(userId);
        Date today = new Date();
        for(DailyChange d : ld) {
            if(DateUtils.isSameDay(today, d.getTime())) {    // 只保留当天的结果
                Item i = this.itemDao.getItemById(d.getItemId());
                Map<String, Object> p = new HashMap<String, Object>();
                p.put("changeId", d.getChangeId());
                p.put("fridgeId", d.getChangeId());
                p.put("itemId", d.getItemId());
                p.put("itemName", i.getName());
                p.put("userId", d.getUserId());
                p.put("amount", d.getAmount());
                p.put("time", d.getTime());
                params.add(p);
            }
        }
        return params;
    }

}