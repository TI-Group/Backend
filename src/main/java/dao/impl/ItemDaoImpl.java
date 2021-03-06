package dao.impl;

import java.util.List;

import org.hibernate.query.Query;

import dao.ItemDao;
import model.Item;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ItemDaoImpl extends BaseDaoImpl implements ItemDao {

    @Override
    public List<Item> getAllItems() {
        String hql = "from Item";
        Query query = getSession().createQuery(hql);
        List<Item> items = query.list();
        return items;
    }

    @Override
    public Item getItemByName(String name) {
        String hql = "from Item i where i.name = :name";
        Query query = getSession().createQuery(hql).setParameter("name", name);
        List<Item> items = query.list();
        Item item = items.size() == 1 ? items.get(0) : null;
        return item;
    }
    
    @Override
    public Item getItemByBarcode(String barcode) {
        String hql = "from Item i where i.barcode = :barcode";
        Query query = getSession().createQuery(hql).setParameter("barcode", barcode);
        List<Item> items = query.list();
        Item item = items.size() == 1 ? items.get(0) : null;
        return item;
    }
    
    @Override
    public Item getItemById(int id) {
        String hql = "from Item i where i.itemId = :itemId";
        Query query = getSession().createQuery(hql).setParameter("itemId", id);
        List<Item> items = query.list();
        Item item = items.size() == 1 ? items.get(0) : null;
        return item;
    }

}