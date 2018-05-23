package dao.impl;

import org.springframework.data.redis.core.StringRedisTemplate;

import common.util.TokenUtil;
import dao.TokenDao;

public class TokenDaoImpl implements TokenDao {

    private StringRedisTemplate redisTemplate;

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String createToken(int userId) {
        String token = TokenUtil.getToken(userId);
        redisTemplate.opsForValue().set(String.valueOf(userId), token);
        return token;
    }

    @Override
    public boolean checkToken(int userId, String token) {
        Object obj = redisTemplate.opsForValue().get(String.valueOf(userId));
        if (obj == null) {
            return false;
        } else {
            String trueToken = String.valueOf(obj);
            return token.equals(trueToken);
        }
    }

    @Override
    public void deleteToken(int userId) {
        redisTemplate.delete(String.valueOf(userId));
    }

}
