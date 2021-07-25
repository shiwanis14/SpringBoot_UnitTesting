package com.ownprojects.unittesting.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import com.ownprojects.unittesting.Item;
import com.ownprojects.unittesting.business.BusinessImpl;
import com.ownprojects.unittesting.business.Service;
import com.ownprojects.unittesting.repository.ItemRepository;
import com.ownprojects.unittesting.service.BusinessService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
public class BusinessServiceTest {

  @InjectMocks
  private BusinessService service;

  @Mock
  private ItemRepository repository;

  @Test
  public void testGetItemFromDatabase() {
    when(repository.findAll()).thenReturn((Arrays.asList(
        (new Item(2, "Item2" , 10, 1)),
        (new Item(3, "Item3" , 10, 2)),
        (new Item(4, "Item4" , 10, 3)))));
    List<Item> itemList = service.getItemFromDatabase();
    Assertions.assertEquals(10,  itemList.get(0).getValue());
    Assertions.assertEquals(20,  itemList.get(1).getValue());
    Assertions.assertEquals(30,  itemList.get(2).getValue());
  }


}
