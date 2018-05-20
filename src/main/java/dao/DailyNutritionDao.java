package dao;

import model.DailyNutrition;

public interface DailyNutritionDao extends BaseDao {
    public DailyNutrition getDailyNutritionById(int id);
    
}