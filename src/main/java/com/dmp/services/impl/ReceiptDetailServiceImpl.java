package com.dmp.services.impl;

import com.dmp.pojo.ReceiptDetail;
import com.dmp.repositories.ReceiptDetailRepository;
import com.dmp.services.ReceiptDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReceiptDetailServiceImpl implements ReceiptDetailService {
    @Autowired
    private ReceiptDetailRepository repo;
    @Override
    public List<ReceiptDetail> getReceiptDetails(int receiptId) {
        return this.repo.getReceiptDetails(receiptId);
    }

    @Override
    public List<ReceiptDetail> getReceiptDetailsByServiceId(int serviceId) {
        return this.repo.getReceiptDetailsByServiceId(serviceId);
    }

    @Override
    public void saveDetailReceipt(ReceiptDetail receiptDetail) {
        this.repo.saveDetailReceipt(receiptDetail);
    }

    @Override
    public void deleteDetailReceiptById(int id) {
        this.repo.deleteDetailReceiptById(id);
    }

    @Override
    public void updateDetailReceipt(ReceiptDetail receiptDetail) {
        this.repo.updateDetailReceipt(receiptDetail);

    }
}
