package action.fridge;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import action.BaseAction;
import service.FridgeService;

public class FridgeAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    
    private Map<String, Object> params;
    
    @Autowired
    private FridgeService fridgeService;
    
    private Integer fridgeId;
    private Integer userId;
    private File uploadImage;    // 上传的文件对象
    private String uploadImageFileName;    // 上传的文件名
    private String uploadImageContentType;
    private InputStream downloadImage;    // 供下载的文件流对象
    private String downloadImageFileName;    // 供下载的文件名
    private String downloadImageContentType;
    
    /* ============================================================ */
    
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
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public File getUploadImage() {
        return uploadImage;
    }
    public void setUploadImage(File uploadImage) {
        this.uploadImage = uploadImage;
    }
    public String getUploadImageFileName() {
        return uploadImageFileName;
    }
    public void setUploadImageFileName(String uploadImageFileName) {
        this.uploadImageFileName = uploadImageFileName;
    }
    public String getUploadImageContentType() {
        return uploadImageContentType;
    }
    public void setUploadImageContentType(String uploadImageContentType) {
        this.uploadImageContentType = uploadImageContentType;
    }
    public InputStream getDownloadImage() {
        return downloadImage;
    }
    public void setDownloadImage(InputStream downloadImage) {
        this.downloadImage = downloadImage;
    }
    public String getDownloadImageFileName() {
        return downloadImageFileName;
    }
    public void setDownloadImageFileName(String downloadImageFileName) {
        this.downloadImageFileName = downloadImageFileName;
    }
    public String getDownloadImageContentType() {
        return downloadImageContentType;
    }
    public void setDownloadImageContentType(String downloadImageContentType) {
        this.downloadImageContentType = downloadImageContentType;
    }
    
    /* ============================================================ */

    public String getFridgeImage() {
        byte[] buffer = this.fridgeService.getFridgeImage(this.fridgeId);
        this.downloadImage = (buffer != null)? new ByteArrayInputStream(buffer) : new ByteArrayInputStream(new byte[0]);
        this.downloadImageFileName = "" + this.fridgeId;
        this.downloadImageContentType = "image/*";
        return SUCCESS;
    }
    
    public String openFridge() {
        this.params = new HashMap<String, Object>();
        boolean result = this.fridgeService.openFridge(this.fridgeId, this.userId, this.uploadImage);
        this.params.put("result", result);
        return SUCCESS;
    }

}