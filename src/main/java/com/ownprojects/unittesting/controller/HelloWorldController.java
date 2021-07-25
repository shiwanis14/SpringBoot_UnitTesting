package com.ownprojects.unittesting.controller;

import java.util.List;
import com.ownprojects.unittesting.Item;
import com.ownprojects.unittesting.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloWorldController {

  @Autowired
  private BusinessService businessService;

  @GetMapping
  public Item getItem(){
    return new Item(1, "Ball", 5, 10);
  }

  @GetMapping("service")
  public Item getItemFromService(){
    return  businessService.getItem();
  }

  @GetMapping("serviceAndDB")
  public List<Item> getItemFromServiceANdDB(){
    return  businessService.getItemFromDatabase();
  }


}
