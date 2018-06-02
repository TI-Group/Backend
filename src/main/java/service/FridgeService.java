package service;

import java.io.File;
import java.util.List;

import model.Fridge;

public interface FridgeService {
    List<Fridge> getAllFridges();
    boolean addFridge(Fridge f);
    boolean updateFridge(Fridge f);
    boolean deleteFridge(int fridgeId);
    
    boolean openFridge(int fridgeId, int userId, File image);
}
