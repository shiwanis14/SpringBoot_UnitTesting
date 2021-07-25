package com.ownprojects.unittesting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.assertj.core.api.Assertions.*;

public class ListMockTest {
  List mockedList = mock(List.class);;
  @Test
  public void test(){
    when(mockedList.size()).thenReturn(5).thenReturn(10);
    Assertions.assertEquals(5, mockedList.size());
    Assertions.assertEquals(10, mockedList.size());
  }

  @Test
  public void testSpecificParams(){
    when(mockedList.get(0)).thenReturn("List");
    Assertions.assertEquals("List", mockedList.get(0));
  }

  @Test
  public void testGenericParams(){
    when(mockedList.get(anyInt())).thenReturn("List");
    Assertions.assertEquals("List", mockedList.get(0));
    Assertions.assertEquals("List", mockedList.get(1));
  }

  @Test
  public void testVerify(){
    mockedList.get(0);
    verify(mockedList).get(0);
    verify(mockedList,times(1)).get(0);
    verify(mockedList, atLeastOnce()).get(0);
    verify(mockedList, atMostOnce()).get(0);
    verify(mockedList, never()).get(1);
  }

  @Test
  public void testVerifyArgumentPassed(){
    mockedList.add("anyString");
    ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
    verify(mockedList).add(argumentCaptor.capture());
    Assertions.assertEquals("anyString",argumentCaptor.getValue());
  }

  @Test
  public void testVerifyArgumentPassedMultiple(){
    mockedList.add("anyString");
    mockedList.add("anyStringNew");
    ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
    verify(mockedList,times(2)).add(argumentCaptor.capture());
    int size = argumentCaptor.getAllValues().size();
    Assertions.assertEquals(2,size);
  }

  @Test
  public void tesSpy(){
    ArrayList arrayListMock = mock(ArrayList.class);
    arrayListMock.add("String");
    Assertions.assertNull(arrayListMock.get(0)); //returns null

    ArrayList arrayListSpy = spy(ArrayList.class);
    arrayListSpy.add("String");
    Assertions.assertNotNull(arrayListSpy.get(0)); //returns null

    when(arrayListSpy.size()).thenReturn(10);
    Assertions.assertEquals(10, arrayListSpy.size()); //size remains 10 as we have overriden behaviour
  }

  @Test
  public void testHamcrest(){
    when(mockedList.size()).thenReturn(5).thenReturn(10);
    /*MatcherAssert.assertThat(mockedList, Matchers.hasSize(3));
    MatcherAssert.assertThat(mockedList, Matchers.hasItems(10));*/
  }
}
