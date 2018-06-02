package action.manage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import action.BaseAction;
import model.DailyNutrition;
import service.DailyNutritionService;

public class DailyNutritionAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    
    private Map<String, Object> params;
    
    @Autowired
    private DailyNutritionService dailyNutritionService;
    
    private Integer id;
    private Integer userId;
    private Date date;
    private Float protein;
    private Integer calories;
    private Float vitaminA;
    private Float vitaminB;
    private Float vitaminC;
    private Float carbohydrates;
    

    /* =================================================== */
    
    // getters and setters

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public DailyNutritionService getDailyNutritionService() {
        return dailyNutritionService;
    }

    public void setDailyNutritionService(DailyNutritionService dailyNutritionService) {
        this.dailyNutritionService = dailyNutritionService;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getProtein() {
        return protein;
    }

    public void setProtein(Float protein) {
        this.protein = protein;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Float getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(Float vitaminA) {
        this.vitaminA = vitaminA;
    }

    public Float getVitaminB() {
        return vitaminB;
    }

    public void setVitaminB(Float vitaminB) {
        this.vitaminB = vitaminB;
    }

    public Float getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(Float vitaminC) {
        this.vitaminC = vitaminC;
    }

    public Float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    /* =================================================== */
    
    // actions
    
    public String all() {
        this.params = new HashMap<String, Object>();
        List<DailyNutrition> allDailyNutrition = dailyNutritionService.getAllDailyNutrition();
        this.params.put("result", true);
        this.params.put("total", allDailyNutrition.size());
        this.params.put("rows", allDailyNutrition);
        return SUCCESS;
    }
    
    public String addDailyNutrition() {
        this.params = new HashMap<String, Object>();
        DailyNutrition dn = new DailyNutrition(0, userId, date, protein, calories, vitaminA, vitaminB, vitaminC, carbohydrates);
        boolean result = dailyNutritionService.addDailyNutrition(dn);
        this.params.put("result", result);
        return SUCCESS;
    }
    
    public String updateDailyNutrition() {
        this.params = new HashMap<String, Object>();
        DailyNutrition dn = new DailyNutrition(id, userId, date, protein, calories, vitaminA, vitaminB, vitaminC, carbohydrates);
        boolean result = dailyNutritionService.updateDailyNutrition(dn);
        this.params.put("result", result);
        return SUCCESS;
    }
    
    public String deleteDailyNutrition() {
        this.params = new HashMap<String, Object>();
        boolean result = dailyNutritionService.deleteDailyNutrition(id);
        this.params.put("result", result);
        return SUCCESS;
    }
}
