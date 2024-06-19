package com.dmp.services;

import com.dmp.pojo.Receipt;
import com.dmp.pojo.Services;

import java.util.List;
import java.util.Map;

public interface ReceiptService {
    List<Receipt> getAllReceipt(Map<String, String> params);

    Receipt getReceiptById(int id);

    void deleteReceiptById(int id);

    void addReceipt(Map<String, Services> s);

    void updateReceipt(Receipt receipt);
}
