package dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import dao.UserFridgeRelationshipDao;
import model.UserFridgeRelationship;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserFridgeRelationshipDaoImpl extends BaseDaoImpl implements UserFridgeRelationshipDao {

	@Override
	public UserFridgeRelationship getUserFridgeRelationshipById(int id) {
        String hql = "from UserFridgeRelationship uf where uf.id = :id";
        Query query = getSession().createQuery(hql).setParameter("id", id);
        List<UserFridgeRelationship> userFridges = query.list();
        UserFridgeRelationship userFridge = userFridges.size() == 1 ? userFridges.get(0) : null;
        return userFridge;
	}

	@Override
	public List<UserFridgeRelationship> getAllUserFridgeRelationship() {
        String hql = "from UserFridgeRelationship";
        Query query = getSession().createQuery(hql);
        List<UserFridgeRelationship> userFridges = query.list();
        return userFridges;
	}

	@Override
	public List<UserFridgeRelationship> getFridgesOfUser(int user) {
        String hql = "from UserFridgeRelationship uf where uf.userId = :userId";
        Query query = getSession().createQuery(hql).setParameter("userId", user);
        List<UserFridgeRelationship> userFridges = query.list();
        return userFridges;
	}

	@Override
	public List<UserFridgeRelationship> getUsersOfFridge(int fridge) {
        String hql = "from UserFridgeRelationship uf where uf.fridgeId = :fridgeId";
        Query query = getSession().createQuery(hql).setParameter("fridgeId", fridge);
        List<UserFridgeRelationship> userFridges = query.list();
        return userFridges;
	}
    
}