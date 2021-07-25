package com.ownprojects.unittesting.repository;

import java.util.List;
import com.ownprojects.unittesting.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ItemRepositoryTest {

  @Autowired
  private ItemRepository itemRepository;

  @Test
  public void testFindAll(){
    List<Item> itemList = itemRepository.findAll();
    Assertions.assertEquals(3, itemList.size());
  }


}
