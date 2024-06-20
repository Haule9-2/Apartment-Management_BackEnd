package com.dmp.services;

import com.dmp.pojo.Item;

import java.util.List;
import java.util.Map;

public interface ItemService {
    List<Item> getItem(Map<String, String> params);
    void addOrUpdateItem(Item item);
    void setReceivedDate(Item item);

}
