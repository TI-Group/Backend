package model;

public class FridgeItemRelationship {
    private int id;
    private int itemId;
    private int amount;
    private int fridgeId;
    private int remainTime;

    /* ======================================= */

    public FridgeItemRelationship() {
        super();
    }
    public FridgeItemRelationship(int itemId, int amount, int fridgeId, int remainTime) {
        super();
        this.itemId = itemId;
        this.amount = amount;
        this.fridgeId = fridgeId;
        this.remainTime = remainTime;
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
	/**
	 * @return remainTime
	 */
	public int getRemainTime() {
		return remainTime;
	}
	/**
	 * @param remainTime 要设置的 remainTime
	 */
	public void setRemainTime(int remainTime) {
		this.remainTime = remainTime;
	}
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

}