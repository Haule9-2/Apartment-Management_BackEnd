package com.dmp.repositories;

import com.dmp.pojo.Item;

import java.util.List;
import java.util.Map;

public interface ItemRepository {
    List<Item> getItem(Map<String, String> params);
    void addOrUpdateItem(Item item);
}
