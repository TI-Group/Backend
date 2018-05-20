package model;

public class Fridge {
    private int fridgeId;

    /* ======================================= */

    public Fridge() {
        super();
    }
    public Fridge(int fridgeId) {
        super();
        this.fridgeId = fridgeId;
    }
    
    public int getFridgeId() {
        return fridgeId;
    }
    public void setFridgeId(int fridgeId) {
        this.fridgeId = fridgeId;
    }
}