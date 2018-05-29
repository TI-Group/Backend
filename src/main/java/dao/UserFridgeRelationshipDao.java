package dao;

import java.util.List;

import model.UserFridgeRelationship;

public interface UserFridgeRelationshipDao extends BaseDao {
	UserFridgeRelationship getUserFridgeRelationshipById(int id);
	List<UserFridgeRelationship> getAllUserFridgeRelationship();
	List<UserFridgeRelationship> getFridgesOfUser(int user);
	List<UserFridgeRelationship> getUsersOfFridge(int fridge);
}