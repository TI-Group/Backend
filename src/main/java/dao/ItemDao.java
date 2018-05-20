package dao;

import java.util.List;

import model.Item;

public interface ItemDao extends BaseDao {
    Item getItemById(int id);
    List<Item> getAllItems();
}