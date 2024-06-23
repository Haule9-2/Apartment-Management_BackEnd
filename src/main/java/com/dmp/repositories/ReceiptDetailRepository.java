package com.dmp.repositories;

import com.dmp.pojo.ReceiptDetail;

import java.util.List;

public interface ReceiptDetailRepository {
    public List<ReceiptDetail> getReceiptDetails(int receiptId);
    public List<ReceiptDetail> getReceiptDetailsByServiceId(int serviceId);
    void saveDetailReceipt(ReceiptDetail receiptDetail);
    void deleteDetailReceiptById(int id) ;
    void updateDetailReceipt(ReceiptDetail receiptDetail);

}
