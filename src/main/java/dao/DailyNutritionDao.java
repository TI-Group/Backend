package dao;

import java.util.List;

import model.DailyNutrition;

public interface DailyNutritionDao extends BaseDao {
    public DailyNutrition getDailyNutritionById(int id);
    public List<DailyNutrition> getAllDailyNutrition();
}