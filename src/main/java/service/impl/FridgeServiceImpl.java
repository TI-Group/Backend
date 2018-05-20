package service.impl;

import java.util.List;

import dao.FridgeDao;
import model.Fridge;
import service.FridgeService;

public class FridgeServiceImpl implements FridgeService {
    
    private FridgeDao fridgeDao;

    public FridgeDao getFridgeDao() {
        return fridgeDao;
    }

    public void setFridgeDao(FridgeDao fridgeDao) {
        this.fridgeDao = fridgeDao;
    }

    @Override
    public List<Fridge> getAllFridges() {
        return fridgeDao.getAllFridges();
    }

    @Override
    public boolean addFridge(Fridge f) {
        return fridgeDao.save(f);
    }

    @Override
    public boolean updateFridge(Fridge f) {
        return fridgeDao.update(f);
    }

    @Override
    public boolean deleteFridge(int fridgeId) {
        Fridge fridge = fridgeDao.getFridgeById(fridgeId);
        return fridgeDao.delete(fridge);
    }

}
