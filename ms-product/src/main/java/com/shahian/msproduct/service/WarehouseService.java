package com.shahian.msproduct.service;

import com.shahian.msproduct.model.Warehouse;
import com.shahian.msproduct.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseById(long id) {
        return warehouseRepository.findById(id).orElse(null);
    }

    public Warehouse addWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateWarehouse(long id, Warehouse updatedWarehouse) {
        Warehouse warehouse = warehouseRepository.findById(id).orElse(null);
        if (warehouse == null) {
            return null;
        }
        warehouse.setName(updatedWarehouse.getName());
        warehouse.setStorekeeper(updatedWarehouse.getStorekeeper());
        warehouse.setLocation(updatedWarehouse.getLocation());
        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(long id) {
        warehouseRepository.deleteById(id);
    }
}