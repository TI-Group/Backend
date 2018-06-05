package dao;

import java.util.List;
import model.Fridge;

public interface FridgeDao extends BaseDao {
    public Fridge getFridgeById(int id);
    public List<Fridge> getAllFridges();
    
    public byte[] getFridgeImage(int fridgeId);
    public boolean updateFridgeImage(int fridgeId, byte[] image);
}