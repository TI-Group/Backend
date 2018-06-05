package model;

// 这个类只是用于显示冰箱中物品信息，并不记录到数据库（见FridgeItemService实现）
public class ItemView {
    private int itemId;
    private String itemName;
    private int amount;
    private int remainTime;
    public ItemView(int itemId, String itemName, int amount, int remainTime) {
        super();
        this.itemId = itemId;
        this.itemName = itemName;
        this.amount = amount;
        this.remainTime = remainTime;
    }
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getRemainTime() {
        return remainTime;
    }
    public void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }
}
