package action.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.BaseAction;
import common.constant.UserRole;
import common.util.PasswordUtil;
import model.User;
import service.UserService;

public class UserAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    private Map<String, Object> params;

    private UserService userService;

    private Integer userId;
    private String username;
    private String password;
    private String tel;
    private Integer role;

    private Integer fridgeId;
    
    /* ======================================================= */
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getFridgeId() {
        return fridgeId;
    }

    public void setFridgeId(Integer fridgeId) {
        this.fridgeId = fridgeId;
    }

    /* ========================================================== */
    
    //action
    public String userLogin() {
        this.params = this.userService.userLogin(this.username,PasswordUtil.getEncryptedPassword(this.password));
        return SUCCESS;
    }

    public String adminLogin() {
        this.params = new HashMap<String, Object>();
        boolean result = this.userService.adminLogin(this.username,PasswordUtil.getEncryptedPassword(this.password));
        this.params.put("result", result);
        return SUCCESS;
    }

    public String userSignup() {
        this.params = new HashMap<String, Object>();
        User user = new User(0, this.username, PasswordUtil.getEncryptedPassword(this.password), this.tel, UserRole.values()[this.role]);
        boolean result = this.userService.addUser(user);
        this.params.put("result", result);
        return SUCCESS;
    }
    
    public String setRelationToFridge() {
        this.params = this.userService.setRelationToFridge(this.userId, this.fridgeId);
        return SUCCESS;
    }
    public String getRelationToFridge() {
        this.params = this.userService.getRelationToFridge(this.userId);
        return SUCCESS;
    }
    public String delRelationToFridge() {
        this.params = this.userService.delRelationToFridge(this.userId, this.fridgeId);
        return SUCCESS;
    }
    
    
    public String getEatingRecords() {
        this.params = new HashMap<String, Object>();
        List<Map<String, Object>> list = userService.getEatingRecords(this.userId);
        this.params.put("success", true);
        this.params.put("result", list);
        return SUCCESS;
    }
}