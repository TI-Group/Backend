package service;

import java.util.List;

import model.DailyChange;
import model.ItemView;

public interface FridgeItemService {
    List<ItemView> getItemsOfFridge(int user, int fridge);
    boolean changeItemOfFridge(int user, int fridge, int itemId, int amount);
    List<DailyChange> getDailyChangeOfFridge(int user, int fridge);
}