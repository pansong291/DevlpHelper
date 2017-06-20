package pansong291.devlphelper.jzzh;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import pansong291.devlphelper.R;
import pansong291.devlphelper.Zactivity;

public class JzzhActivity extends Zactivity
{
 CheckBox cb;
 EditText edn,edi,edo,ed1,ed2;
 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_jzzh);
  cb=(CheckBox)findViewById(R.id.jzzhChB);
  edn=(EditText)findViewById(R.id.edn);
  edi=(EditText)findViewById(R.id.edin);
  edo=(EditText)findViewById(R.id.edout);
  ed1=(EditText)findViewById(R.id.frjz);
  ed2=(EditText)findViewById(R.id.tojz);
 }
 
 public void onjzzhc(View v)
 {
  if(edi.length()<1||ed1.length()<1||ed2.length()<1)
  {
   toast("请输入数据");return;
  }
  String in=edi.getText().toString();
  int jz1=Integer.parseInt(ed1.getText().toString()),
   jz2=Integer.parseInt(ed2.getText().toString()),
   ni,inl=in.length();
  if(jz1==0||jz1==1||jz2==0||jz2==1)
  {
   toast("请输入正确进制");return;
  }
  if(cb.isChecked())
  {
   if(edn.length()<1)
   {
	toast("请输入分组数");return;
   }
   ni=Integer.parseInt(edn.getText().toString());
   if(ni==0||ni==1)
   {
	toast("请输入正确分组数");return;
   }
   if(inl%ni==0)
   {
	String str="";
	for(int i=inl/ni;i>0;i--)
	{
	 str+=toX(to10(in.substring(inl-ni*i,inl-ni*i+ni),jz1),jz2);
	}
	edo.setText(str);
   }else toast("字符个数不能均分");
  }
  else
  {
   if(jz1==jz2)
	edo.setText(in);
   else if(jz1==10)
	edo.setText(toX(in,jz2));
   else if(jz2==10)
	edo.setText(to10(in,jz1));
   else edo.setText(toX(to10(in,jz1),jz2));
  }
 }
 
 //将10进制转换成其它进制
 public String toX(String s,int jz)
 {
  int si;
  try{
  si=Integer.parseInt(s);
  }catch(Exception e){return"";}
  if(si==jz)return"10";
  if(si<jz&&si>9)return toStr(si);
  if(si>jz)
  {
   int bc=si,sh=1,yu;
   s="";
   while(sh!=0)
   {
	sh=bc/jz;
	yu=bc%jz;
	bc=sh;
	s=toStr(yu)+s;
   }
  }
  return s;
 }

 //将其它进制转换成10进制
 public String to10(String s,int jz)
 {
  int[]sz=toInt(s);
  int i=1;
  if(sz.length==i)
   return sz[i-1]+"";
  long x=sz[i-1];
  while(i<sz.length)
  {
   x=jz*x+sz[i++];
  }
  return x+"";
 }

 //将一串字符拆成数组
 public int[] toInt(String s)
 {
  byte[]bs=s.getBytes();
  int l=bs.length;
  int[]sz=new int[l];
  for(int i=0;i<l;i++)
  {
   if(bs[i]<60)sz[i]=bs[i]-48;
   else if(bs[i]>80)sz[i]=bs[i]-87;
   else sz[i]=bs[i]-55;
  }
  return sz;
 }

 //将大于9的数替换成字母
 public String toStr(int t)
 {
  String s=""+t;
  s=s.replace("10","A").replace("11","B")
   .replace("12","C").replace("13","D")
   .replace("14","E").replace("15","F");
  return s;
 }
 
}
