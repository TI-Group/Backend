package model;

public class Item {
    private Integer itemId;
    private String name;
    private Integer shelflife;
    private Integer calories;
    private String barcode;

    /* ======================================= */

    public Item() {
        super();
    }
    public Item(Integer itemId, String name, Integer shelflife, Integer calories, String barcode) {
        super();
        this.itemId = itemId;
        this.name = name;
        this.shelflife = shelflife;
        this.calories = calories;
        this.barcode = barcode;
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
    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}