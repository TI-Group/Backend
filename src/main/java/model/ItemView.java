package model;

import java.util.Date;

// 这个类只是用于显示冰箱中物品信息，并不记录到数据库（见FridgeItemService实现）
public class ItemView {
    private Integer itemId;
    private String itemName;
    private Integer amount;
    private Integer remainTime;
    private String barcode;
    private Date putInTime;
    private Integer shelflife;
    public ItemView(Integer itemId, String itemName, Integer amount, Integer remainTime, String barcode, Date putInTime, Integer shelflife) {
        super();
        this.itemId = itemId;
        this.itemName = itemName;
        this.amount = amount;
        this.remainTime = remainTime;
        this.barcode = barcode;
        this.putInTime = putInTime;
        this.shelflife = shelflife;
    }
    public Integer getItemId() {
        return itemId;
    }
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public Integer getRemainTime() {
        return remainTime;
    }
    public void setRemainTime(Integer remainTime) {
        this.remainTime = remainTime;
    }
    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public Date getPutInTime() {
        return putInTime;
    }
    public void setPutInTime(Date putInTime) {
        this.putInTime = putInTime;
    }
    public Integer getShelflife() {
        return shelflife;
    }
    public void setShelflife(Integer shelflife) {
        this.shelflife = shelflife;
    }
}
