package pansong291.devlphelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import pansong291.devlphelper.jiajiemi.JiajMiActivity;
import pansong291.devlphelper.jzzh.JzzhActivity;
import pansong291.devlphelper.tbtpdmzh.TBtmzhActivity;
import pansong291.devlphelper.test.AppListActivity;
import pansong291.devlphelper.test.WebViewActivity;

public class MainActivity extends Zactivity
{
 CheckBox mcb;
 public static boolean guolv;

    /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);
	 mcb=(CheckBox)findViewById(R.id.alCheckB);
	 mcb.setChecked(guolv=msharedp.getBoolean("loadUser",true));
  }
  public void togxygg(View v)
  {
   msharedp.edit().putBoolean("loadUser",guolv=mcb.isChecked()).commit();
   intentTo(AppListActivity.class);
  }
  
  public void tollq(View v)
  {
   Intent in=new Intent(this,WebViewActivity.class);
   in.putExtra(WebViewActivity.uul,msharedp.getString(WebViewActivity.uul,WebViewActivity.wangyi));
   startActivity(in);
  }
  
  public void tozfjjm(View v)
  {
   intentTo(JiajMiActivity.class);
  }

  public void totmzh(View v)
  {
   intentTo(TBtmzhActivity.class);
  }
  
  public void tojzzh(View v)
  {
   intentTo(JzzhActivity.class);
  }
  
  @Override
  public void onBackPressed()
  {
   super.onBackPressed();
   finish();
   System.exit(0);
  }
  
  
}
