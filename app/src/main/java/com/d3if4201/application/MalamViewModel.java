package com.d3if4201.application;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MalamViewModel extends ViewModel {

   Boolean malam ;

 public Boolean ReturnMalam(){


     return malam;

 }

 public void setTrue(){

     malam=true;
 }



}
