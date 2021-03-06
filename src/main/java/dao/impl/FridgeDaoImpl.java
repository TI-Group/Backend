package dao.impl;

import java.io.File;
import java.util.List;

import org.hibernate.query.Query;

import common.util.FileUtil;
import dao.FridgeDao;
import model.Fridge;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class FridgeDaoImpl extends BaseDaoImpl implements FridgeDao {

    private String fileBasePath = "~/fridge/images/";
    
    @Override
    public Fridge getFridgeById(int fridgeId) {
        String hql = "from Fridge f where f.fridgeId = :fridgeId";
        Query query = getSession().createQuery(hql).setParameter("fridgeId",fridgeId);
        List<Fridge> fridges = query.list();
        Fridge fridge = fridges.size() == 1 ? fridges.get(0) : null;
        return fridge;
    }

    @Override
    public List<Fridge> getAllFridges() {
        String hql = "from Fridge";
        Query query = getSession().createQuery(hql);
        List<Fridge> fridges = query.list();
        return fridges;
    }

    @Override
    public byte[] getFridgeImage(int fridgeId) {
        File file = new File(this.fileBasePath + fridgeId);
        if(!file.exists()) {
            return null;
        }
        return FileUtil.getFileContent(file);
    }

    @Override
    public boolean updateFridgeImage(int fridgeId, byte[] image) {
        return FileUtil.saveFileContent(image, new File(this.fileBasePath + fridgeId));
    }
    
    
}