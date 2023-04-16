package com.shahian.mscustomer.controller;

import com.shahian.mscustomer.model.Address;
import com.shahian.mscustomer.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping(value = "/v1/address")
    public ResponseEntity<Address> save(@RequestBody Address address) {
        Address savedAddress = addressService.save(address);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

    @GetMapping(value = "/v1/address")
    public ResponseEntity<Address> findById(@RequestParam Long id) {
        Address address = addressService.findById(id);
        if (address == null) {
            throw new NullPointerException("Address not found");
        }
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/addresses")
    public ResponseEntity<List<Address>> findAll() {
        List<Address> addresses = addressService.findAll();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/addressByUserId")
    public ResponseEntity<List<Address>> findByUserId(@RequestParam Long userId) {
        List<Address> addresses = addressService.findByUserId(userId);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @PutMapping(value = "/v1/address")
    public ResponseEntity<Address> update(@RequestParam Long id, @RequestBody Address updatedAddress) {
        Address address = addressService.update(id, updatedAddress);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/address")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        addressService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
