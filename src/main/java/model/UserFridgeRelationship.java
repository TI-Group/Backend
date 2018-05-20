package model;

public class UserFridgeRelationship {
    private int id;
    private int fridgeId;
    private int userId;

    /* ======================================= */

    public UserFridgeRelationship() {
        super();
    }
    public UserFridgeRelationship(int id, int fridgeId, int userId) {
        super();
        this.id = id;
        this.fridgeId = fridgeId;
        this.userId = userId;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getFridgeId() {
        return fridgeId;
    }
    public void setFridgeId(int fridgeId) {
        this.fridgeId = fridgeId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

}