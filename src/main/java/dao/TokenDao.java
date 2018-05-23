package dao;

public interface TokenDao {
    
    String createToken(int userId);
    boolean checkToken(int userId, String token);
    void deleteToken(int userId);
    
}
