package pansong291.devlphelper.test;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import pansong291.devlphelper.MainActivity;
import pansong291.devlphelper.R;
import pansong291.devlphelper.Zactivity;

public class AppListActivity extends Zactivity
{
 //用来存储获取的应用信息数据
 ArrayList<AppInfo> appList;
 List<PackageInfo> packages;
 //显示列表
 ListView alListV;
 AppAdapter appAdapter;

 @Override
 public void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  //大概是使标题栏有进度条吧，但这样会使其直接显示出来
  requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
  setContentView(R.layout.activity_applist);
  //使ActionBar上的Progress显示
  setProgressBarIndeterminateVisibility(true);
  appList=new ArrayList<AppInfo>();
  //获取设备上的应用程序
  packages=getPackageManager().getInstalledPackages(0);
  alListV=(ListView)findViewById(R.id.appLV);
  new Thread(new Runnable(){
   public void run()
   {
	loadAppInfo();
	appAdapter=new AppAdapter(AppListActivity.this,appList);
	Message msg=new Message();
    msg.what=1;
	handler.sendMessage(msg);
   }
  }).start();
  //设置ListView的间隔线宽度
  alListV.setDividerHeight(1);
  alListV.setOnItemClickListener(new OnItemClickListener(){
   @Override
   public void onItemClick(AdapterView<?> p1,View p2,int p3,long p4)
   {
	//toast("View is "+p2+"\nint is "+p3+"\nlong is "+p4);
	InputDataActivity.ApName=((TextView)p2.findViewById(R.id.aName)).getText().toString();
	InputDataActivity.ApPack=((TextView)p2.findViewById(R.id.aPack)).getText().toString();
	InputDataActivity.ApVerName=((TextView)p2.findViewById(R.id.aVers)).getText().toString().substring(3);
	InputDataActivity.ApVerCode=((TextView)p2.findViewById(R.id.aVerC)).getText().toString().substring(4);
	intentTo(InputDataActivity.class);
	finish();
   }
  });
 }

 Handler handler=new Handler()
 {
  public void handleMessage(Message msg)
  {
   //绑定适配器
   if(msg.what==1)alListV.setAdapter(appAdapter);
   setProgressBarIndeterminateVisibility(false);
  }
 };

 //装载应用信息到ArrayList<AppInfo>里
 public void loadAppInfo()
 {
  for(int i=0;i<packages.size();i++)
  {   
   PackageInfo packageInfo=packages.get(i);
   //是否需要过滤系统程序||判断是否是用户程序，过滤系统程序
   if((!MainActivity.guolv)||(packageInfo.applicationInfo.flags&ApplicationInfo.FLAG_SYSTEM)<=0)
   {
    AppInfo tmpInfo=new AppInfo();   
    tmpInfo.appName=packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
    tmpInfo.packageName=packageInfo.packageName;
    tmpInfo.versionName=packageInfo.versionName;
	tmpInfo.versionCode=""+(packageInfo.versionCode);
    tmpInfo.appIcon=packageInfo.applicationInfo.loadIcon(getPackageManager());
    appList.add(tmpInfo);
   }
  }/***
  appAdapter=new AppAdapter(this,appList);
  //绑定适配器
  alListV.setAdapter(appAdapter);/***/
 }
 
 /** * 自定义BaseAdapter */
 public class AppAdapter extends BaseAdapter
 {
   Context context;
   ArrayList<AppInfo>dataList=new ArrayList<AppInfo>();
   public AppAdapter(Context context,ArrayList<AppInfo>inputDataList)
   { this.context=context;
     dataList.clear();
     for(int i=0;i<inputDataList.size();i++)
     {
      dataList.add(inputDataList.get(i));
     }
   }
   @Override public int getCount()
   { return dataList.size(); }
   @Override public Object getItem(int position)
   { return dataList.get(position); }
   @Override public long getItemId(int position)
   { return position; }
   @Override
   public View getView(int position,View convertView,ViewGroup parent)
   {
	final AppInfo appUnit=dataList.get(position);
    if(convertView==null)
    { LayoutInflater vi=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 convertView=vi.inflate(R.layout.list_item_app,null);
	 //这句要注释掉，否则item不能点击
	 //convertView.setClickable(true);
    }
    TextView appNameText=(TextView)convertView.findViewById(R.id.aName),
	appPackText=(TextView)convertView.findViewById(R.id.aPack),
	appVerNameText=(TextView)convertView.findViewById(R.id.aVers),
	appVerCodeText=(TextView)convertView.findViewById(R.id.aVerC);
    ImageView appIcon=(ImageView)convertView.findViewById(R.id.aIcon);
	if(appNameText!=null)appNameText.setText(appUnit.appName);
	if(appPackText!=null)appPackText.setText(appUnit.packageName);
	if(appVerNameText!=null)appVerNameText.setText("版本:"+appUnit.versionName);
	if(appVerCodeText!=null)appVerCodeText.setText("版本号:"+appUnit.versionCode);
    if(appIcon!=null)appIcon.setImageDrawable(appUnit.appIcon);
    return convertView;
   }
 }
}
