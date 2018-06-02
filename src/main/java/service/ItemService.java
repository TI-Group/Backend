package service;

import java.util.List;

import model.Item;

public interface ItemService {
    List<Item> getAllItems();
    boolean addItem(Item i);
    boolean updateItem(Item i);
    boolean deleteItem(int id);
}
