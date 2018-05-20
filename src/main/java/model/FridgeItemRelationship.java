package model;

public class FridgeItemRelationship {
    private int id;
    private int itemId;
    private int fridgeId;

    /* ======================================= */

    public FridgeItemRelationship() {
        super();
    }
    public FridgeItemRelationship(int id, int itemId, int fridgeId) {
        super();
        this.id = id;
        this.itemId = itemId;
        this.fridgeId = fridgeId;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public int getFridgeId() {
        return fridgeId;
    }
    public void setFridgeId(int fridgeId) {
        this.fridgeId = fridgeId;
    }

}