package dao;

import java.util.List;

import model.FridgeItemRelationship;

public interface FridgeItemRelationshipDao extends BaseDao {
	FridgeItemRelationship getFridgeItemRelationshipById(int id);
	List<FridgeItemRelationship> getAllFridgeItemRelationship();
	List<FridgeItemRelationship> getItemsInFridge(int fridge);
	List<FridgeItemRelationship> getItemsInFridgeWithRemainTimeLowerThan(int fridge, int remainTime);
}