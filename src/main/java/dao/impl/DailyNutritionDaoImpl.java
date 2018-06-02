package dao.impl;

import model.DailyNutrition;
import java.util.List;

import org.hibernate.query.Query;

import dao.DailyNutritionDao;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class DailyNutritionDaoImpl extends BaseDaoImpl implements DailyNutritionDao {

    @Override
    public DailyNutrition getDailyNutritionById(int id) {
        String hql = "from DailyNutrition dn where dn.id = :id";
        Query query = getSession().createQuery(hql).setParameter("id", id);
        List<DailyNutrition> dailyNutritions = query.list();
        DailyNutrition dailyNutrition = dailyNutritions.size() == 1 ? dailyNutritions.get(0) : null;
        return dailyNutrition;
    }

    @Override
    public List<DailyNutrition> getAllDailyNutrition() {
        String hql = "from DailyNutrition";
        Query query = getSession().createQuery(hql);
        List<DailyNutrition> dailyNutrition = query.list();
        return dailyNutrition;
    }
    
}