package dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import dao.DailyChangeDao;
import model.DailyChange;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class DailyChangeDaoImpl extends BaseDaoImpl implements DailyChangeDao {

	@Override
	public DailyChange getDailyChangeById(int id) {
        String hql = "from DailyChange dc where dc.changeId = :changeId";
		Query query = getSession().createQuery(hql).setParameter("changeId", id);
		List<DailyChange> dailyChangeList = query.list();
        DailyChange dailyChange = dailyChangeList.size() == 1 ? dailyChangeList.get(0) : null;
        return dailyChange;
	}

	@Override
	public List<DailyChange> getAllDailyChange() {
        String hql = "from DailyChange";
		Query query = getSession().createQuery(hql);
		List<DailyChange> dailyChangeList = query.list();
        return dailyChangeList;
	}

	@Override
	public List<DailyChange> getDailyChangeOfFridge(int fridge) {
        String hql = "from DailyChange dc where dc.fridgeId = :fridgeId";
		Query query = getSession().createQuery(hql).setParameter("fridgeId", fridge);
		List<DailyChange> dailyChangeList = query.list();
        return dailyChangeList;
	}

	@Override
	public List<DailyChange> getDailyChangeOfUser(int user) {
        String hql = "from DailyChange dc where dc.userId = :userId";
		Query query = getSession().createQuery(hql).setParameter("userId", user);
		List<DailyChange> dailyChangeList = query.list();
        return dailyChangeList;
	}

}
