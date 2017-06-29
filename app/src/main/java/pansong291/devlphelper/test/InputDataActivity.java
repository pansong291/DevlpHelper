package pansong291.devlphelper.test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import org.json.JSONException;
import org.json.JSONObject;
import pansong291.devlphelper.R;
import pansong291.devlphelper.Zactivity;

public class InputDataActivity extends Zactivity
{
 String inSize,inUrl,inMessage,inGGMess,inQZup;
 EditText inS,inU,inM,inGM,inQZ,outMes;
 View inLinear;int day=0,hour=0,minu=-1;
 //数据
 public static String ApName,ApPack,ApVerName,ApVerCode;

 @Override
 protected void onResume()
 {
  super.onResume();
  /***
  inS.setText(msharedp.getString("inS",""));
  inU.setText(msharedp.getString("inU",""));
  inM.setText(msharedp.getString("inM",""));
  inGM.setText(msharedp.getString("inGM",""));
  inQZ.setText(msharedp.getString("inQZ",""));
  /***/
  try
  {
   JSONObject jso1=new JSONObject(msharedp.getString(SP_MAIN_JS,""));
   inS.setText(jso1.optString(T_FILE_SIZE));
   inU.setText(jso1.optString(T_DOWN_URL));
   inM.setText(jso1.optString(T_NEW_MSG));
   inGM.setText(jso1.optString(T_PRC_MSG));
   inQZ.setText(jso1.optString(T_FORCE_UP));
  }catch(JSONException e)
  {}
 }

 @Override
 protected void onPause()
 {
  super.onPause();
  /***
  msharedp.edit().putString("inS",inS.getText().toString()).commit();
  msharedp.edit().putString("inU",inU.getText().toString()).commit();
  msharedp.edit().putString("inM",inM.getText().toString()).commit();
  msharedp.edit().putString("inGM",inGM.getText().toString()).commit();
  msharedp.edit().putString("inQZ",inQZ.getText().toString()).commit();
  /***/
  try
  {
   JSONObject jso2=new JSONObject();
   jso2.put(T_FILE_SIZE,inS.getText().toString());
   jso2.put(T_DOWN_URL,inU.getText().toString());
   jso2.put(T_NEW_MSG,inM.getText().toString());
   jso2.put(T_PRC_MSG,inGM.getText().toString());
   jso2.put(T_FORCE_UP,inQZ.getText().toString());
   msharedp.edit().putString(SP_MAIN_JS,jso2.toString()).commit();
  }catch(JSONException e)
  {}
 }
 
 public void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_inputdata);
  inLinear=findViewById(R.id.inLinear);
  inS=(EditText)findViewById(R.id.inSize);
  inU=(EditText)findViewById(R.id.inUrl);
  inM=(EditText)findViewById(R.id.inMessage);
  inGM=(EditText)findViewById(R.id.inGGMess);
  inQZ=(EditText)findViewById(R.id.inQZup);
  outMes=(EditText)findViewById(R.id.outMes);
  setTitle(ApName);
 }
 
 public void setTime(View v)
 {
  View vw=getLayoutInflater().inflate(R.layout.dialog_time,null);
  final EditText edday=(EditText)vw.findViewById(R.id.edday),
   edhour=(EditText)vw.findViewById(R.id.edhour),
   edminu=(EditText)vw.findViewById(R.id.edminu);
  if(day>0)edday.setText(""+day);
  if(hour>0)edhour.setText(""+hour);
  if(minu>=0)edminu.setText(""+minu);
  new AlertDialog.Builder(this)
   .setTitle("显示时间").setView(vw)
   .setPositiveButton("确定",new DialogInterface.OnClickListener(){
	@Override
	public void onClick(DialogInterface p1,int p2)
	{
	 day=edday.getText().toString().equals("")?0:Integer.parseInt(edday.getText().toString());
	 hour=edhour.getText().toString().equals("")?0:Integer.parseInt(edhour.getText().toString());
	 minu=edminu.getText().toString().equals("")?-1:Integer.parseInt(edminu.getText().toString());
	}
   }).show();
 }
 
 public void gengXin(View v)
 {
  if((inUrl=inU.getText().toString()).equals("")
   ||(inSize=inS.getText().toString()).equals("")
   ||(inMessage=inM.getText().toString()).equals(""))
  { toast("请填写更新数据");return; }
  inLinear.setVisibility(0);
  bb=outMes.getText().toString();
  try
  {
   JSONObject jso4=new JSONObject();
   jso4.put(T_VER_NAME,ApVerName);
   jso4.put(T_VER_CODE,Integer.parseInt(ApVerCode));
   jso4.put(T_FILE_SIZE,inSize);
   jso4.put(T_DOWN_URL,inUrl);
   jso4.put(T_NEW_MSG,inMessage);
   tt1=jso4.toString().replace("\\/","/");
   tt1=tt+ApName+"：</b></font>"+ApPack+tt1+ApPack;
  }catch(JSONException e)
  {
   tt1="异常："+e.getMessage();
  }
  outMes.setText((bb+=outMes.getText().toString().equals("")?"":"\n\n")+tt1);
 }
 String bb,tt="<br><font color=\"#ff0000\"><b>",tt1;
 public void gongGao(View v)
 {
  if((inGGMess=inGM.getText().toString()).equals("")
   |(inQZup=inQZ.getText().toString()).equals(""))
  { toast("请填写公告内容");return; }
  inLinear.setVisibility(0);
  long time;int jilu=minu;
  switch(minu){
  case 0:time=0;break;
  case -1:if(day==0&&hour==0){time=1;break;}minu=0;
  default:
  time=System.currentTimeMillis()+((day*24+hour)*60+minu)*60000;
  }
  minu=jilu;
  bb=outMes.getText().toString();
  try
  {
   JSONObject jso3=new JSONObject();
   jso3.put(T_FORCE_UP,Integer.parseInt(inQZup));
   jso3.put(T_SHOW_TIME,time);
   jso3.put(T_PRC_MSG,inGGMess);
   tt1=jso3.toString().replace("\\/","/");
   tt1=tt+ApName+"：</b></font>"+ApPack+tt1+ApPack;
  }catch(JSONException e)
  {
   tt1="异常："+e.getMessage();
  }
  outMes.setText((bb+=outMes.getText().toString().equals("")?"":"\n\n")+tt1);
 }
 
 public void copy(View v)
 {
  cmb.setText(outMes.getText().toString());
  toast("已复制");
 }
 
 public void goWeb(View v)
 {
  Intent it=new Intent(this,WebViewActivity.class);
  it.putExtra(WebViewActivity.uul,WebViewActivity.wangyi);
  startActivity(it);
  finish();
 }
 
 public void qingk(View v)
 {
  outMes.setText("");
  inLinear.setVisibility(8);
 }
 
}
