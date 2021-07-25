package com.ownprojects.unittesting;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ownprojects.unittesting.business.BusinessImpl;
import com.ownprojects.unittesting.business.Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BusinessImplTest {

  @InjectMocks
  BusinessImpl business;

  @Mock
  Service mockedService;

/*  @BeforeEach
  public void before(){
  business.setServiceimpl(mockedService);
  }*/

  @Test
  public void addValues() {
    when(mockedService.getArray()).thenReturn(new int[]{1, 2, 3, 4, 5});
    Assertions.assertEquals(15,  business.addValues());
  }


  @Test
  public void addValuesEmptyArray() {
    when(mockedService.getArray()).thenReturn(new int[]{});
    Assertions.assertEquals(0,  business.addValues());
  }

}
