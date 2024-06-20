package com.dmp.services.impl;

import com.dmp.pojo.Receipt;
import com.dmp.pojo.Services;
import com.dmp.repositories.ReceiptRepository;
import com.dmp.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    @Autowired
    private ReceiptRepository repo;
    @Override
    public List<Receipt> getAllReceipt(Map<String, String> params) {
        return this.repo.getAllReceipt(params);
    }

    @Override
    public Receipt getReceiptById(int id) {
        return this.repo.getReceiptById(id);
    }

    @Override
    public void deleteReceiptById(int id) {
        this.repo.deleteReceiptById(id);

    }

    @Override
    public void addReceipt(Map<String, Services> s) {
        this.repo.addReceipt(s);

    }

    @Override
    public void updateReceipt(Receipt receipt) {
        this.repo.updateReceipt(receipt);

    }
}
