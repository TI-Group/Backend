package service;

import java.util.List;

import model.DailyNutrition;

public interface DailyNutritionService {
    List<DailyNutrition> getAllDailyNutrition();
    boolean addDailyNutrition(DailyNutrition dn);
    boolean updateDailyNutrition(DailyNutrition dn);
    boolean deleteDailyNutrition(int dnId);
}
