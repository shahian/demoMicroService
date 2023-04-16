package com.shahian.msproduct.controller;

import com.shahian.msproduct.model.Warehouse;
import com.shahian.msproduct.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping("/v1/warehouses")
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/v1/warehouse")
    public Warehouse getWarehous(@RequestParam Long id) {
        return warehouseService.getWarehouseById(id);
    }

    @PostMapping("/v1/warehouse")
    public Warehouse addWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.addWarehouse(warehouse);
    }

    @PutMapping("/v1/warehouse")
    public Warehouse updateWarehouse(@RequestParam Long id, @RequestBody Warehouse warehouse) {
        return warehouseService.updateWarehouse(id, warehouse);
    }

    @DeleteMapping("/v1/warehouse/{id}")
    public void deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
    }
}