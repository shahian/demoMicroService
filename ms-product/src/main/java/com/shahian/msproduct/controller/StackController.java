package com.shahian.msproduct.controller;

import com.shahian.msproduct.model.Stock;
import com.shahian.msproduct.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StackController {

    @Autowired
    private StockService stockService;

    @GetMapping("/v1/stocks")
    public List<Stock> getAllStacks(@RequestParam(required = false) Long warehouseId,@RequestParam(required = false) Long productId) {
        if (warehouseId != null   && productId==null) {
            return stockService.findAllByWarehouseId(warehouseId);
        } else  if (warehouseId == null   && productId!=null) {
            return stockService.findAllByWarehouseId(productId);
        }else  {
            return stockService.findAll();
        }
    }
    @GetMapping("/v1/stock")
    public  Stock  getStock(@RequestParam Long id) {
            return stockService.findById(id);
    }
    @PostMapping("/v1/stock")
    public Stock addStock(@RequestBody Stock  stock) {
        return stockService.save(stock);
    }
    @PutMapping("/v1/stock")
    public Stock updateStock(@RequestBody Stock  stock) {
        return stockService.update(stock);
    }

    @DeleteMapping("/v1/stock/{id}")
    public void deleteStock(@PathVariable Long id) {
        stockService.deleteById(id);
    }
}