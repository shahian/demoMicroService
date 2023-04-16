package com.shahian.msproduct.service;

import com.shahian.msproduct.model.Stock;
import com.shahian.msproduct.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock findById(Long id) {
        return stockRepository.findById(id).orElse(null);
    }

    public List<Stock> findAllByWarehouseId(Long id) {
        return stockRepository.findAllByWarehouseId(id);
    }
    public List<Stock> findAllByProductId(Long id) {
        return stockRepository.findAllByProductId(id);
    }
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }
    public void deleteById(Long id) {
        stockRepository.deleteById(id);
    }

    public Stock update(Stock stock) {
        Stock existingStock = stockRepository.findById(stock.getId()).orElse(null);
        if(existingStock != null) {
            existingStock.setQuantity(stock.getQuantity());
            existingStock.setWarehouseId(stock.getWarehouseId());
            existingStock.setProductId(stock.getProductId());
            existingStock.setQuantity(stock.getQuantity());
            existingStock.setEntryDate(stock.getEntryDate());
            existingStock.setExitDate(stock.getExitDate());
            return stockRepository.save(existingStock);
        }
        return null;
    }
}