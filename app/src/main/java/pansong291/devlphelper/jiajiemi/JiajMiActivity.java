package pansong291.devlphelper.jiajiemi;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import pansong291.devlphelper.R;
import pansong291.devlphelper.Zactivity;

public class JiajMiActivity extends Zactivity
{
 String my;
 DesUtils des;
 ZhuanYi zy;
 EditText edt1,edt2,edt3;
 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_zifucl);
  edt1=(EditText)findViewById(R.id.myedt);
  edt2=(EditText)findViewById(R.id.miwed);
  edt3=(EditText)findViewById(R.id.mwedt);
  zy=new ZhuanYi();
  my=msharedp.getString("mykey","");
  edt1.setText(my);
 }
 
 public void bcmy(View v)
 {
  if((my=edt1.getText().toString()).length()<1)
  {
   toast("请输入密钥");
   return;
  }
  if(msharedp.edit().putString("mykey",my).commit())
  toast("密钥已保存");
 }
 
 public void jijm(View v)
 {
  boolean ed2=true,ed3=true;
  if(my.length()<1)
  {
   toast("请先设置密钥");
   return;
  }else if((ed2=edt2.length()<1)&(ed3=edt3.length()<1))
  return;
  des=new DesUtils(my);
  String str="error";
  try{
   str=ed3?des.encrypt(edt2.getText().toString()):des.decrypt(edt3.getText().toString());
  }catch(Exception e){}
  if(!ed2&&!ed3)
   toast("究竟是解密还是加密？");
  else if(!ed2)
   edt3.setText(str);
  else if(!ed3)
   edt2.setText(str);
 }
 
 public void fuhaozy(View v)
 {
  if(edt2.length()<1)
   return;
  String s=edt2.getText().toString();
  edt3.setText(zy.yinhao(s));
 }
 
 public void clear2(View v)
 {
  edt2.setText("");
 }
 
 public void clear3(View v)
 {
  edt3.setText("");
 }
 
}
