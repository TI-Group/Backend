package model;

import java.util.Date;

public class FridgeItemRelationship {
    private int id;
    private int itemId;
    private int amount;
    private int fridgeId;
    private int remainTime;
    private Date putInTime;    // 食物放入的时间戳

    /* ======================================= */

    public FridgeItemRelationship() {
        super();
    }
    public FridgeItemRelationship(int itemId, int amount, int fridgeId, int remainTime, Date putInTime) {
        super();
        this.itemId = itemId;
        this.amount = amount;
        this.fridgeId = fridgeId;
        this.remainTime = remainTime;
        this.putInTime = putInTime;
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
    public Date getPutInTime() {
        return putInTime;
    }
    public void setPutInTime(Date putInTime) {
        this.putInTime = putInTime;
    }

}