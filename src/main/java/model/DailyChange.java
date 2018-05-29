package model;

import java.sql.Timestamp;

public class DailyChange {
    private int changeId;
    private int fridgeId;
    private int itemId;
    private int userId;
    private int amount;
    private Timestamp time;
    
    public DailyChange() {
        super();
    }
    
    public DailyChange(int fridgeId, int itemId, int userId, int amount) {
    	this.fridgeId = fridgeId;
    	this.itemId = itemId;
    	this.userId = userId;
    	this.amount = amount;
    }
    
	public int getChangeId() {
		return changeId;
	}
	public void setChangeId(int changeId) {
		this.changeId = changeId;
	}
	public int getFridgeId() {
		return fridgeId;
	}
	public void setFridgeId(int fridgeId) {
		this.fridgeId = fridgeId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * @return time
	 */
	public Timestamp getTime() {
		return time;
	}
	/**
	 * @param time 要设置的 time
	 */
	public void setTime(Timestamp time) {
		this.time = time;
	}

}
