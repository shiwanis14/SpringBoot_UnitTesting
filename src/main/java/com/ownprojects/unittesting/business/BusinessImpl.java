package com.ownprojects.unittesting.business;

import java.util.Arrays;

public class BusinessImpl {

  private Service serviceimpl;

  public int addValues(){
    int[] arr = serviceimpl.getArray();
    return Arrays.stream(arr).sum();
//    return Arrays.stream(arr).reduce(0, Integer::sum);
  }

  public void setServiceimpl(Service serviceimpl) {
    this.serviceimpl = serviceimpl;
  }
}
