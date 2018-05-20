package service;

import java.util.List;

import model.Fridge;

public interface FridgeService {
    List<Fridge> getAllFridges();
    boolean addFridge(Fridge f);
    boolean updateFridge(Fridge f);
    boolean deleteFridge(int fridgeId);
}
