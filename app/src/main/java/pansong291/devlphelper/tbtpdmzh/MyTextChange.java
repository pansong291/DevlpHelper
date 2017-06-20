package pansong291.devlphelper.tbtpdmzh;

import android.content.*;
import android.text.*;
import android.widget.*;

public class MyTextChange implements TextWatcher
{
 EditText edt,edt2;
 Button btn1,btn2;
 ImageButton ibtn;
 
 public MyTextChange(EditText edt,Button btn1,Button btn2,ImageButton ibtn,EditText edt2)
 {
	this.edt=edt;this.btn1=btn1;
	this.btn2=btn2;this.ibtn=ibtn;
	this.edt2=edt2;
 }
 
 @Override
 public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4){}

 @Override
 public void onTextChanged(CharSequence p1, int p2, int p3, int p4)
 {
	if(edt.length()<1)
	{
	 btn1.setClickable(false);
	 btn2.setClickable(false);
	 ibtn.setVisibility(8);
	 edt2.setVisibility(8);
	}
	else
	{
	 btn1.setClickable(true);
	 btn2.setClickable(true);
	 ibtn.setVisibility(0);
	 edt2.setVisibility(0);
	}
 }

 @Override
 public void afterTextChanged(Editable p1){}
 
 
}
