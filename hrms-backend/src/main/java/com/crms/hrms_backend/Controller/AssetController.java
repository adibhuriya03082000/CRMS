package com.crms.hrms_backend.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crms.hrms_backend.Service.AssetService;

@RestController
@RequestMapping("/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping("/createasset")
    public ResponseEntity<Map<String, Object>> createAsset(@RequestBody Map<String, Object> asset) {
        assetService.createAsset(asset);
        asset.put("Success", "Create asset Successfully");
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }

    @GetMapping("/getassetbyid/{id}")
    public ResponseEntity<Map<String, Object>> getAssetById(@PathVariable int id) {
        Map<String, Object> asset = assetService.getAssetById(id);
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }

    @GetMapping("/getallasset")
    public ResponseEntity<List<Map<String, Object>>> getAllAssets() {
        List<Map<String, Object>> asset = assetService.getAllAssets();
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }

    @PutMapping("/updateasset/{id}")
    public ResponseEntity<Map<String, Object>> updateAsset(@PathVariable int id, @RequestBody Map<String, Object> asset) {
        assetService.updateAsset(id, asset);
        asset.put("Success", "Asset update successfully");
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }

    @DeleteMapping("deleteasset/{id}")
    public ResponseEntity<Map<String, Object>> deleteAsset(@PathVariable int id) {
        assetService.deleteAsset(id);
        Map<String, Object> asset = new HashMap<>();
        asset.put("Success", "Role delete successfully");
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }
    
}
