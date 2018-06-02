package dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import dao.FridgeItemRelationshipDao;
import model.FridgeItemRelationship;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class FridgeItemRelationshipDaoImpl extends BaseDaoImpl implements FridgeItemRelationshipDao {

	@Override
	public FridgeItemRelationship getFridgeItemRelationshipById(int id) {
        String hql = "from FridgeItemRelationship fi where fi.id = :id";
        Query query = getSession().createQuery(hql).setParameter("id", id);
        List<FridgeItemRelationship> fridgeItems = query.list();
        FridgeItemRelationship fridgeItem = fridgeItems.size() == 1 ? fridgeItems.get(0) : null;
        return fridgeItem;
	}

	@Override
	public List<FridgeItemRelationship> getAllFridgeItemRelationship() {
        String hql = "from FridgeItemRelationship";
        Query query = getSession().createQuery(hql);
        List<FridgeItemRelationship> fridgeItems = query.list();
        return fridgeItems;
	}

	@Override
	public List<FridgeItemRelationship> getItemsInFridge(int fridge) {
        String hql = "from FridgeItemRelationship fi where fi.fridgeId = :fridgeId";
        Query query = getSession().createQuery(hql).setParameter("fridgeId", fridge);
        List<FridgeItemRelationship> fridgeItems = query.list();
        return fridgeItems;
	}

	@Override
	public List<FridgeItemRelationship> getItemsInFridgeWithRemainTimeLowerThan(int fridge, int remainTime) {
        String hql = "from FridgeItemRelationship fi where fi.fridgeId = :fridgeId and fi.remainTime < :remainTime";
        Query query = getSession().createQuery(hql).setParameter("fridgeId", fridge).setParameter("remainTime", remainTime);
        List<FridgeItemRelationship> fridgeItems = query.list();
        return fridgeItems;
	}
    
}