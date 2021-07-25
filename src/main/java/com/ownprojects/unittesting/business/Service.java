package com.ownprojects.unittesting.business;

public interface Service {

   default int[] getArray(){
    return new int[] {1,2,3,4,5};
  }

}
