package service;

import java.util.List;

import model.DailyChange;
import model.ItemView;

public interface FridgeItemService {
    List<ItemView> getItemsOfFridge(int user, int fridge);
    boolean changeItemOfFridge(int user, int fridge, int itemId, int amount);
    boolean addItemIntoFridge(int fridge, String item, int amount);
    boolean deleteItemFromFridge(int fridge, String item);
    List<DailyChange> getDailyChangeOfFridge(int user, int fridge);
}
