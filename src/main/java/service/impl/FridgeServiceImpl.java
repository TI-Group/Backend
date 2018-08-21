package service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.DailyChangeDao;
import dao.FridgeDao;
import dao.FridgeItemRelationshipDao;
import dao.ItemDao;
import model.Fridge;
import model.FridgeItemRelationship;
import model.Item;
import service.FridgeService;
import common.util.FileUtil;
import common.util.ImageUtil;

public class FridgeServiceImpl implements FridgeService {
    
    private FridgeDao fridgeDao;
    private ItemDao itemDao;
    private FridgeItemRelationshipDao fridgeItemRelationshipDao;
    private DailyChangeDao dailyChangeDao;

    /* ======================================================== */

    public FridgeDao getFridgeDao() {
        return fridgeDao;
    }
    public void setFridgeDao(FridgeDao fridgeDao) {
        this.fridgeDao = fridgeDao;
    }
    public ItemDao getItemDao() {
        return itemDao;
    }
    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }
    public FridgeItemRelationshipDao getFridgeItemRelationshipDao() {
        return fridgeItemRelationshipDao;
    }
    public void setFridgeItemRelationshipDao(FridgeItemRelationshipDao fridgeItemRelationshipDao) {
        this.fridgeItemRelationshipDao = fridgeItemRelationshipDao;
    }    
    public DailyChangeDao getDailyChangeDao() {
        return dailyChangeDao;
    }
    public void setDailyChangeDao(DailyChangeDao dailyChangeDao) {
        this.dailyChangeDao = dailyChangeDao;
    }

    /* ======================================================== */
    
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

    @Override
    public byte[] getFridgeImage(int fridgeId) {
        return this.fridgeDao.getFridgeImage(fridgeId);
    }
    
    @Override
    public boolean openFridge(int fridgeId, int userId, File imageFile) {
        byte[] buffer = FileUtil.getFileContent(imageFile);
        this.fridgeDao.updateFridgeImage(fridgeId, buffer);    // 更新保存的冰箱图片
        List<FridgeItemRelationship> itemsInFridge = this.fridgeItemRelationshipDao.getItemsInFridge(fridgeId);    // 获得冰箱原有的所有物品
        Set<String> newItemsName = ImageUtil.imageToItem(buffer);    // 获得冰箱现在的所有物品（图像识别）
        Set<Integer> newItems = new HashSet<Integer>();    // 把“物品名称”转换为“物品id”
        for(String entry : newItemsName) {
            Item item = this.itemDao.getItemByName(entry);
            int itemId = (item != null) ? item.getItemId() : 1;    // item表有“其他”一项，如果名称不存在，则归入此类。暂时认为“其他”的itemId为1
            if(!newItems.contains(item.getItemId())) {
                newItems.add(itemId);
            }
        }
        for(FridgeItemRelationship fir : itemsInFridge) {
            int oldItemId = fir.getItemId();
            if(newItems.contains(oldItemId)) {    // 如果物品原来和现在都在冰箱里
                newItems.remove(oldItemId);    // 从newItems中删除
            }
            else {    // 如果物品原来在冰箱，现在不在了，只更新冰箱表
                this.fridgeItemRelationshipDao.delete(fir);
            }
        }    // 至此，newItems不再包含原来在冰箱里的物品
        for(Integer newItemId : newItems) {    // 如果物品原来不在冰箱里，现在新加进来，只更新冰箱表，设置数量为0
            Item item = this.itemDao.getItemById(newItemId);    // 获得保质期
            FridgeItemRelationship fir = new FridgeItemRelationship(newItemId, 0, fridgeId, item.getShelflife(), new Date());
            this.fridgeItemRelationshipDao.save(fir);
        }
        return true;
    }
    
    /*
    @Override
    public boolean openFridge(int fridgeId, int userId, File image) {
        Timestamp timestamp = (Timestamp)(new Date());    // 当前时间戳
        List<FridgeItemRelationship> itemsInFridge = this.fridgeItemRelationshipDao.getItemsInFridge(fridgeId);    // 获得冰箱原有的所有物品
        Map<String, Integer> newItemsName = ImageUtil.imageToItem(image);    // 获得冰箱现在的所有物品（图像识别）
        Map<Integer, Integer> newItems = new HashMap<Integer, Integer>();    // 把“物品名称-数量”的对应关系转换为“物品id-数量”
        for(Map.Entry<String, Integer> entry : newItemsName.entrySet()) {
            Item item = this.itemDao.getItemByName(entry.getKey());
            int itemId = (item != null) ? item.getItemId() : 1;    // item表有“其他”一项，如果名称不存在，则归入此类。暂时认为“其他”的itemId为1
            if(newItems.containsKey(item.getItemId())) {
                newItems.put(itemId, newItems.get(itemId)+1);
            }
            else {
                newItems.put(itemId, 1);
            }
        }
        for(FridgeItemRelationship fir : itemsInFridge) {
            int oldItemId = fir.getItemId();
            int oldAmount = fir.getAmount();    // ??
            if(newItems.containsKey(oldItemId)) {    // 如果物品原来和现在都在冰箱里
                int newAmount = newItems.get(oldItemId);
                fir.setAmount(newAmount);    // ??
                if(newAmount > oldAmount) {    // 如果数量变多
                    Item item = this.itemDao.getItemById(oldItemId);
                    fir.setRemainTime(item.getShelflife());    // 重置剩余保存时间
                }
                if(newAmount != oldAmount) {    // 如果数量变多或变少，记录变化的数据，正数表示冰箱里的数量变多，负数表示数量变少（被人吃了）；同时更新冰箱的数据
                    this.dailyChangeDao.save(new DailyChange(fridgeId, oldItemId, userId, newAmount-oldAmount, timestamp));
                    this.fridgeItemRelationshipDao.update(fir);
                }
                newItems.remove(oldItemId)    // 从map中删除
            }
            else {    // 如果物品原来在冰箱，现在不在了，更新两张数据表
                this.dailyChangeDao.save(new DailyChange(fridgeId, oldItemId, userId, -oldAmount, timestamp));
                this.fridgeItemRelationshipDao.delete(fir);
            }
        }
        for(Map.Entry<Integer, Integer> entry : newItems.entrySet()) {    // 如果物品原来不在冰箱里，现在新加进来，更新两张数据表
            int newItemId = entry.getKey();
            int newAmount = entry.getValue();
            this.dailyChangeDao.save(new DailyChange(fridgeId, newItemId, userId, newAmount, timestamp));
            Item item = this.itemDao.getItemById(newItemId);    // 获得保质期
            FridgeItemRelationship fir = new FridgeItemRelationship(newItemId, newAmount, fridgeId, item.getShelflife());
            this.fridgeItemRelationshipDao.save(fir);
        }
        return true;
    }
    */
}
