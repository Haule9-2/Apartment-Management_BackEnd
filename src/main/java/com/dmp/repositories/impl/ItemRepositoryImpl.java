package com.dmp.repositories.impl;

import com.dmp.pojo.Item;
import com.dmp.repositories.ItemRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class ItemRepositoryImpl implements ItemRepository {
    @Override
    public List<Item> getItem(Map<String, String> params) {
        return List.of();
    }

    @Override
    public void addOrUpdateItem(Item item) {

    }

}