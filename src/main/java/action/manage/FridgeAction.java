package action.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import action.BaseAction;
import model.Fridge;
import service.FridgeService;

public class FridgeAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    
    private Map<String, Object> params;
    
    @Autowired
    private FridgeService fridgeService;
    
    private Integer fridgeId;
    

    /* =================================================== */
    
    // getters and setters

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public FridgeService getFridgeService() {
        return fridgeService;
    }

    public void setFridgeService(FridgeService fridgeService) {
        this.fridgeService = fridgeService;
    }

    public Integer getFridgeId() {
        return fridgeId;
    }

    public void setFridgeId(Integer fridgeId) {
        this.fridgeId = fridgeId;
    }

    /* =================================================== */
    
    // actions
    
    public String allFridges() {
        this.params = new HashMap<String, Object>();
        List<Fridge> allFridges = fridgeService.getAllFridges();
        this.params.put("result", true);
        this.params.put("total", allFridges.size());
        this.params.put("rows", allFridges);
        return SUCCESS;
    }
    
    public String addFridge() {
        this.params = new HashMap<String, Object>();
        Fridge fridge = new Fridge(0);
        boolean result = fridgeService.addFridge(fridge);
        this.params.put("result", result);
        return SUCCESS;
    }
    
    public String updateFridge() {
        this.params = new HashMap<String, Object>();
        Fridge fridge = new Fridge(fridgeId);
        boolean result = fridgeService.updateFridge(fridge);
        this.params.put("result", result);
        return SUCCESS;
    }
    
    public String deleteFridge() {
        this.params = new HashMap<String, Object>();
        boolean result = fridgeService.deleteFridge(fridgeId);
        this.params.put("result", result);
        return SUCCESS;
    }
    
}
