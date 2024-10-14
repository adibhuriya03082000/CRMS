package com.crms.hrms_backend.Repository;

import java.util.List;
import java.util.Map;

public interface AssetRepository {

    void createAsset(Map<String, Object> asset);
    
    Map<String, Object> getAssetById(int id);
    
    List<Map<String, Object>> getAllAssets();
    
    void updateAsset(int id, Map<String, Object> asset);
    
    void deleteAsset(int id);
}
