package dao;

import java.util.List;

import model.DailyChange;

public interface DailyChangeDao extends BaseDao {
	DailyChange getDailyChangeById(int id);
	List<DailyChange> getAllDailyChange();
	List<DailyChange> getDailyChangeOfFridge(int fridge);
	List<DailyChange> getDailyChangeOfUser(int user);
}
