package pansong291.devlphelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import pansong291.crash.ActivityControl;

public class Zactivity extends Activity
{
 public SharedPreferences msharedp;
 public android.text.ClipboardManager cmb;

 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  ActivityControl.getActivityControl().addActivity(this);
  
  msharedp=getSharedPreferences(getPackageName()+"_preferences",0);
  cmb=(android.text.ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
 }

 @Override
 protected void onDestroy()
 {
  super.onDestroy();
  ActivityControl.getActivityControl().removeActivity(this);
 }
 
 public void toast(String str,int i)
 {
  Toast.makeText(this,str,i).show();
 }
 public void toast(String str)
 {
  toast(str,0);
 }
 public void intentTo(Class<?>cl)
 {
  startActivity(new Intent(this,cl));
 }
}
