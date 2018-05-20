package service.impl;

import java.util.List;

import dao.ItemDao;
import model.Item;
import service.ItemService;

public class ItemServiceImpl implements ItemService {
    
    private ItemDao itemDao;

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    @Override
    public boolean addItem(Item i) {
        return itemDao.save(i);
    }

    @Override
    public boolean updateItem(Item i) {
        return itemDao.update(i);
    }

    @Override
    public boolean deleteItem(int id) {
        Item i = itemDao.getItemById(id);
        return itemDao.delete(i);
    }

}
