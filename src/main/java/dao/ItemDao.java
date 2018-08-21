package dao;

import java.util.List;

import model.Item;

public interface ItemDao extends BaseDao {
    Item getItemById(int id);
    Item getItemByName(String name);
    Item getItemByBarcode(String barcode);
    List<Item> getAllItems();
}