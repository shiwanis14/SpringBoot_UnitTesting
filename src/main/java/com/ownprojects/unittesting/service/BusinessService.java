package com.ownprojects.unittesting.service;

import java.util.List;
import com.ownprojects.unittesting.Item;
import com.ownprojects.unittesting.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessService {

  @Autowired
  ItemRepository repository;

  public Item getItem() {
    return new Item(1, "Ball", 5, 10);
  }

  public List<Item> getItemFromDatabase() {
    List<Item> items = repository.findAll();
    items.stream().forEach(i -> i.setValue(i.getPrice() * i.getQuantity()));
    return items;
  }

}
