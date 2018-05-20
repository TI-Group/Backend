package dao;

import java.util.List;
import model.Fridge;

public interface FridgeDao extends BaseDao {
    public Fridge getFridgeById(int id);

    public List<Fridge> getAllFridges();
    
}