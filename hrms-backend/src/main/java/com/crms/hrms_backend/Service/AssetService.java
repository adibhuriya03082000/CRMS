package com.crms.hrms_backend.Service;

import java.util.List;
import java.util.Map;

public interface AssetService {

    public void createAsset(Map<String, Object> asset);

    public Map<String, Object> getAssetById(int id);

    public List<Map<String, Object>> getAllAssets();

    public void updateAsset(int id, Map<String, Object> asset);

    public void deleteAsset(int id);

    
}
