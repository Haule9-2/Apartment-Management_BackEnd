package com.dmp.services.impl;

import com.dmp.pojo.Item;
import com.dmp.repositories.ItemRepository;
import com.dmp.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository repo;
    @Override
    public List<Item> getItem(Map<String, String> params) {
        return this.repo.getItem(params);
    }

    @Override
    public void addOrUpdateItem(Item item) {
        this.repo.addOrUpdateItem(item);

    }

    @Override
    public void setReceivedDate(Item item) {
        this.repo.setReceivedDate(item);

    }
}
