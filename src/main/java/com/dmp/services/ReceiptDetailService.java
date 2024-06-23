package com.dmp.services;

import com.dmp.pojo.ReceiptDetail;

import java.util.List;

public interface ReceiptDetailService {
    public List<ReceiptDetail> getReceiptDetails(int receiptId);
    public List<ReceiptDetail> getReceiptDetailsByServiceId(int serviceId);
}
