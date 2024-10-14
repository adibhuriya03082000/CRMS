package com.crms.hrms_backend.Service.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crms.hrms_backend.Repository.AssetRepository;
import com.crms.hrms_backend.Service.AssetService;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Override
    public void createAsset(Map<String, Object> asset) {
        assetRepository.createAsset(asset);
    }

    @Override
    public Map<String, Object> getAssetById(int id) {
        return assetRepository.getAssetById(id);
    }

    @Override
    public List<Map<String, Object>> getAllAssets() {
        return assetRepository.getAllAssets();
    }

    @Override
    public void updateAsset(int id, Map<String, Object> asset) {
        assetRepository.updateAsset(id, asset);
    }

    @Override
    public void deleteAsset(int id) {
        assetRepository.deleteAsset(id);
    }
    
}
