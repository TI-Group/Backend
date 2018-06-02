package action; 

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import common.constant.UserRole;
import common.util.PasswordUtil;
import dao.TokenDao;
import model.User;
import service.*;

@SuppressWarnings( {"rawtypes", "unchecked" })
public class TestAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    
    private Map params;
    
    @Autowired
    private TokenDao tokenDao;
    
    private Integer userId;
    private String token;
    
    /* =================================================== */
    
    // getters and setters
    
    public Map getParams() {
        return params;
    }
    public void setParams(Map params) {
        this.params = params;
    }
    
    public TokenDao getTokenDao() {
        return tokenDao;
    }
    public void setTokenDao(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    
    /* =================================================== */
    
    // actions
    
    public String createToken() {
        this.params = new HashMap();
        token = tokenDao.createToken(userId);
        this.params.put("result", true);
        this.params.put("token", token);
        return SUCCESS;
    }
    
    public String checkToken() {
        this.params = new HashMap();
        boolean result = tokenDao.checkToken(userId, token);
        this.params.put("result", result);
        return SUCCESS;
    }
    
    public String deleteToken() {
        this.params = new HashMap();
        tokenDao.deleteToken(userId);
        this.params.put("result", true);
        return SUCCESS;
    }

    
}