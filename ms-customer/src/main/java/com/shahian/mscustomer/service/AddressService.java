package com.shahian.mscustomer.service;

import com.shahian.mscustomer.model.Address;
import com.shahian.mscustomer.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public Address findById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public List<Address> findByUserId(Long userId) {
        return addressRepository.findByUserId(userId);
    }
    public Address update(Long id, Address updatedAddress) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new NullPointerException("Address not found"));
        address.setAddressLine1(updatedAddress.getAddressLine1());
        address.setAddressLine2(updatedAddress.getAddressLine2());
        address.setCity(updatedAddress.getCity());
        address.setCountry(updatedAddress.getCountry());
        address.setZipCode(updatedAddress.getZipCode());
        return addressRepository.save(address);
    }
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}
