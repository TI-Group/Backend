package model;

import java.util.Date;

public class DailyNutrition {
    private int id;
    private Integer userId;
    Date date;
    Float protein;
    Integer calories;
    Float vitaminA;
    Float vitaminB;
    Float vitaminC;
    Float carbohydrates;

    /* ======================================= */

    public DailyNutrition() {
        super();
    }
    public DailyNutrition(int id, Integer userId, Date date, Float protein, Integer calories, Float vitaminA,
            Float vitaminB, Float vitaminC, Float carbohydrates) {
        super();
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.protein = protein;
        this.calories = calories;
        this.vitaminA = vitaminA;
        this.vitaminB = vitaminB;
        this.vitaminC = vitaminC;
        this.carbohydrates = carbohydrates;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
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
    
}