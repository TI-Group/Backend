package service.impl;

import java.util.List;

import dao.DailyNutritionDao;
import model.DailyNutrition;
import service.DailyNutritionService;

public class DailyNutritionServiceImpl implements DailyNutritionService {
    
    private DailyNutritionDao dailyNutritionDao;

    /**
     * @return dailyNutritionDao
     */
    public DailyNutritionDao getDailyNutritionDao() {
        return dailyNutritionDao;
    }

    /**
     * @param dailyNutritionDao 要设置的 dailyNutritionDao
     */
    public void setDailyNutritionDao(DailyNutritionDao dailyNutritionDao) {
        this.dailyNutritionDao = dailyNutritionDao;
    }

    @Override
    public List<DailyNutrition> getAllDailyNutrition() {
        return dailyNutritionDao.getAllDailyNutrition();
    }

    @Override
    public boolean addDailyNutrition(DailyNutrition dn) {
        return dailyNutritionDao.save(dn);
    }

    @Override
    public boolean updateDailyNutrition(DailyNutrition dn) {
        return dailyNutritionDao.update(dn);
    }

    @Override
    public boolean deleteDailyNutrition(int dnId) {
        DailyNutrition dn = dailyNutritionDao.getDailyNutritionById(dnId);
        return dailyNutritionDao.delete(dn);
    }

}
