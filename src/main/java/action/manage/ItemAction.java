package action.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import action.BaseAction;
import model.Item;
import service.ItemService;

public class ItemAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    
    private Map<String, Object> params;
    
    @Autowired
    private ItemService itemService;
    
    private Integer itemId;
    private String name;
    private Integer shelflife;
    private Integer calories;
    

    /* =================================================== */
    
    // getters and setters

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
    
    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getShelflife() {
        return shelflife;
    }

    public void setShelflife(Integer shelflife) {
        this.shelflife = shelflife;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    /* =================================================== */
    
    // actions
    
    public String allItems() {
        this.params = new HashMap<String, Object>();
        List<Item> allItems = this.itemService.getAllItems();
        this.params.put("result", true);
        this.params.put("total", allItems.size());
        this.params.put("rows", allItems);
        return SUCCESS;
    }
    
    public String addItem() {
        this.params = new HashMap<String, Object>();
        Item item = new Item(0, name, shelflife, calories);
        boolean result = itemService.addItem(item);
        this.params.put("result", result);
        return SUCCESS;
    }
    
    public String updateItem() {
        this.params = new HashMap<String, Object>();
        Item item = new Item(itemId, name, shelflife, calories);
        boolean result = itemService.updateItem(item);
        this.params.put("result", result);
        return SUCCESS;
    }
    
    public String deleteItem() {
        this.params = new HashMap<String, Object>();
        boolean result = itemService.deleteItem(itemId);
        this.params.put("result", result);
        return SUCCESS;
    }
}
