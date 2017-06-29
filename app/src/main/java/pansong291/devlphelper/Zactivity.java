package pansong291.devlphelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import pansong291.crash.ActivityControl;

public class Zactivity extends Activity
{
 public static final String SP_LOAD_USER="loadUser",SP_MY_KEY="mykey",SP_WW="WW",SP_HH="HH",SP_BL="BL",SP_MAIN_JS="mainJS";
 public static final String T_VER_NAME="VersName",T_VER_CODE="VersCode",T_FILE_SIZE="FileSize",T_DOWN_URL="DownUrl",T_NEW_MSG="NewMessage",T_PRC_MSG="ProclamationMessage",T_FORCE_UP="ForceUpdate",T_SHOW_TIME="ShowTime";
 
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
