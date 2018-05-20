package model;

public class Item {
    private int itemId;
    private String name;
    private int shelflife;
    private int calories;

    /* ======================================= */

    public Item() {
        super();
    }
    public Item(int itemId, String name, int shelflife, int calories) {
        super();
        this.itemId = itemId;
        this.name = name;
        this.shelflife = shelflife;
        this.calories = calories;
    }
    
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getShelflife() {
        return shelflife;
    }
    public void setShelflife(int shelflife) {
        this.shelflife = shelflife;
    }
    public int getCalories() {
        return calories;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }
}