package service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.DailyChangeDao;
import dao.FridgeItemRelationshipDao;
import dao.ItemDao;
import dao.UserFridgeRelationshipDao;
import model.DailyChange;
import model.FridgeItemRelationship;
import model.Item;
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
            ItemView iv = new ItemView(i.getItemId(), itemDao.getItemById(i.getItemId()).getName(), i.getAmount(), i.getRemainTime());
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

    @Override
    public boolean addItemIntoFridge(int fridge, String item, int amount) {
        Item it = itemDao.getItemByName(item);
        if (it == null) return false;
        FridgeItemRelationship fi = fridgeItemRelationshipDao.getItemInFridgeByItemId(fridge, it.getItemId());
        if (fi != null) return false;
        fi = new FridgeItemRelationship(it.getItemId(), amount, fridge, it.getShelflife());
        return fridgeItemRelationshipDao.save(fi);
    }

    @Override
    public boolean deleteItemFromFridge(int fridge, String item) {
        Item it = itemDao.getItemByName(item);
        if (it == null) return false;
        FridgeItemRelationship fi = fridgeItemRelationshipDao.getItemInFridgeByItemId(fridge, it.getItemId());
        if (fi == null) return false;
        return fridgeItemRelationshipDao.delete(fi);
    }

    @Override
    public boolean increaseItem(int fridgeId, String itemName) {
        Item it = this.itemDao.getItemByName(itemName);
        if(it != null) {
            FridgeItemRelationship fi = fridgeItemRelationshipDao.getItemInFridgeByItemId(fridgeId, it.getItemId());
            if(fi != null) {
                fi.setAmount(fi.getAmount()+1);
                this.fridgeItemRelationshipDao.update(fi);
            }
            else {
                fi = new FridgeItemRelationship(it.getItemId(), 1, fridgeId, it.getShelflife());
                this.fridgeItemRelationshipDao.save(fi);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean decreaseItem(int userId, int fridgeId, String itemName) {
        // 使冰箱里的某个物品数量减一，同时在dailychange中添加一条记录
        Item it = this.itemDao.getItemByName(itemName);
        if(it != null) {
            FridgeItemRelationship fi = fridgeItemRelationshipDao.getItemInFridgeByItemId(fridgeId, it.getItemId());
            if(fi != null && fi.getAmount() >= 1) {
                fi.setAmount(fi.getAmount()-1);
                this.fridgeItemRelationshipDao.update(fi);    // 即使数量为0，也不删掉记录
                DailyChange dc = new DailyChange(fridgeId, it.getItemId(), userId, 1, (Timestamp)new Date());
                this.dailyChangeDao.save(dc);
                return true;
            }
        }
        return false;
    }

}
