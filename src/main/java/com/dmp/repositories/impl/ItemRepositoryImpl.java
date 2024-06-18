package com.dmp.repositories.impl;

import com.dmp.pojo.Item;
import com.dmp.repositories.ItemRepository;

import java.util.List;
import java.util.Map;

public class ItemRepositoryImpl implements ItemRepository {
    @Override
    public List<Item> getItem(Map<String, String> params) {
        return List.of();
    }

    @Override
    public void addOrUpdateItem(Item item) {

    }

}
