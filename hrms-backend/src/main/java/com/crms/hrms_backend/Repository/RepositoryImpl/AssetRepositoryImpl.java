package com.crms.hrms_backend.Repository.RepositoryImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.crms.hrms_backend.Repository.AssetRepository;

@Repository
public class AssetRepositoryImpl implements AssetRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getAllAssets() {
        String sql = "SELECT a.user_id, a.`assigned_date`, a.`assigned_by`, a.`dateOfCreation`, a.`dateOfModification`, a.`status`, " +
                     "GROUP_CONCAT(at.`name` SEPARATOR ', ') AS asset_item_names " +
                     "FROM assets a LEFT JOIN asset_items at ON a.id = at.asset_id " +
                     "GROUP BY a.id";
        
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
        
        for (Map<String, Object> resultMap : resultList) {
            String assetItemNames = (String) resultMap.get("asset_item_names");
            if (assetItemNames != null) {
                resultMap.put("asset_item_names", assetItemNames.split(", "));
            } else {
                resultMap.put("asset_item_names", new String[0]);
            }
        }
        
        return resultList;
    }
    

    @Override
    public Map<String, Object> getAssetById(int id) {
        String sql = "SELECT a.user_id, a.`assigned_date`, a.`assigned_by`, a.`dateOfCreation`, a.`dateOfModification`, a.`status`, " +
                     "GROUP_CONCAT(at.`name` SEPARATOR ', ') AS asset_item_names " +
                     "FROM assets a LEFT JOIN asset_items at ON a.id = at.asset_id " +
                     "WHERE a.id = ? GROUP BY a.id";
        
        Map<String, Object> resultMap = jdbcTemplate.queryForMap(sql, id);
        
        String assetItemNames = (String) resultMap.get("asset_item_names");
        if (assetItemNames != null) {
            resultMap.put("asset_item_names", assetItemNames.split(", "));
        } else {
            resultMap.put("asset_item_names", new String[0]);
        }
        
        return resultMap;
    }
    
    
    

    @Override
    public void createAsset(Map<String, Object> asset) {
        asset.put("dateOfCreation", new Date());
        String sql = "INSERT INTO assets (user_id, assigned_date, assigned_by, dateOfCreation, dateOfModification, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, 
            asset.get("user_id"), 
            asset.get("assigned_date"), 
            asset.get("assigned_by"), 
            asset.get("dateOfCreation"),
            asset.get("dateOfModification"),
            asset.get("status"));

        String assetIdQuery = "SELECT LAST_INSERT_ID()";
        int assetId = jdbcTemplate.queryForObject(assetIdQuery, Integer.class);

        String itemSql = "INSERT INTO asset_items (asset_id, name) VALUES (?, ?)";
        List<String> assetNames = (List<String>) asset.get("asset_name");
        for (String name : assetNames) {
            jdbcTemplate.update(itemSql, assetId, name);
        }
    }

    @Override
    public void updateAsset(int id, Map<String, Object> asset) {
        asset.put("dateOfModification", new Date());
        String sql = "UPDATE assets SET user_id = ?, assigned_date = ?, assigned_by = ?, " +
                     "dateOfModification = ?, status = ? WHERE id = ?";
        jdbcTemplate.update(sql, 
            asset.get("user_id"), 
            asset.get("assigned_date"), 
            asset.get("assigned_by"), 
            asset.get("dateOfModification"), 
            asset.get("status"), 
            id);

    }

    @Override
    public void deleteAsset(int id) {
        String deleteItemsSql = "DELETE FROM asset_items WHERE asset_id = ?";
        jdbcTemplate.update(deleteItemsSql, id);
        String sql = "DELETE FROM assets WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    
}
