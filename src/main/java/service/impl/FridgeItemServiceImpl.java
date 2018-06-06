package service.impl;

import java.util.ArrayList;
import java.util.List;

import dao.DailyChangeDao;
import dao.FridgeItemRelationshipDao;
import dao.ItemDao;
import dao.UserFridgeRelationshipDao;
import model.DailyChange;
import model.FridgeItemRelationship;
import model.ItemView;
import model.UserFridgeRelationship;
import service.FridgeItemService;

public class FridgeItemServiceImpl implements FridgeItemService {
    
    private FridgeItemRelationshipDao fridgeItemRelationshipDao;
    private UserFridgeRelationshipDao userFridgeRelationshipDao;
    private DailyChangeDao dailyChangeDao;
    private ItemDao itemDao;

    public FridgeItemRelationshipDao getFridgeItemRelationshipDao() {
        return fridgeItemRelationshipDao;
    }

    public void setFridgeItemRelationshipDao(FridgeItemRelationshipDao fridgeItemRelationshipDao) {
        this.fridgeItemRelationshipDao = fridgeItemRelationshipDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public DailyChangeDao getDailyChangeDao() {
        return dailyChangeDao;
    }

    public void setDailyChangeDao(DailyChangeDao dailyChangeDao) {
        this.dailyChangeDao = dailyChangeDao;
    }

    public UserFridgeRelationshipDao getUserFridgeRelationshipDao() {
        return userFridgeRelationshipDao;
    }

    public void setUserFridgeRelationshipDao(UserFridgeRelationshipDao userFridgeRelationshipDao) {
        this.userFridgeRelationshipDao = userFridgeRelationshipDao;
    }
    
    private boolean checkUser(int user, int fridge) {
        // 用户只能看到自己拥有的冰箱的情况
        List<UserFridgeRelationship> users = userFridgeRelationshipDao.getUsersOfFridge(fridge);
        for (UserFridgeRelationship r : users) {
            if (r.getUserId() == user) return true;
        }
        return false;
    }

    @Override
    public List<ItemView> getItemsOfFridge(int user, int fridge) {
        if (!checkUser(user, fridge)) return null;
        
        List<FridgeItemRelationship> items = fridgeItemRelationshipDao.getItemsInFridge(fridge);
        List<ItemView> result = new ArrayList<>();
        for (FridgeItemRelationship i : items) {
            ItemView iv = new ItemView(i.getId(), itemDao.getItemById(i.getId()).getName(), i.getAmount(), i.getRemainTime());
            result.add(iv);
        }
        return result;
    }

    @Override
    public boolean changeItemOfFridge(int user, int fridge, int itemId, int amount) {
        if (!checkUser(user, fridge)) return false;
        try {
            FridgeItemRelationship fi = fridgeItemRelationshipDao.getItemInFridgeByItemId(fridge, itemId);
            if (fi == null) return false;
            DailyChange dc = new DailyChange();
            dc.setFridgeId(fridge);
            dc.setItemId(itemId);
            dc.setAmount(amount - fi.getAmount());
            dc.setUserId(user);
            fi.setAmount(amount);
            fridgeItemRelationshipDao.update(fi);
            dailyChangeDao.save(dc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<DailyChange> getDailyChangeOfFridge(int user, int fridge) {
        if (!checkUser(user, fridge)) return null;
        return dailyChangeDao.getDailyChangeOfFridge(fridge);
    }

}
