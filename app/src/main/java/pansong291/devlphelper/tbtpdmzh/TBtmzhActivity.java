package pansong291.devlphelper.tbtpdmzh;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import pansong291.devlphelper.R;
import pansong291.devlphelper.Zactivity;

public class TBtmzhActivity extends Zactivity
{
 EditText edt1,edt2,edt3,edt4;
 Button btn1,btn2;
 CheckBox mcb;
 ImageButton colse;
 public static final String WW="WW",HH="HH",BL="BL";
    /** Called when the activity is first created. */
    @Override
  public void onCreate(Bundle savedInstanceState)
  {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_tbtmzh);
		 
	 edt1=(EditText)findViewById(R.id.edt1);
	 edt2=(EditText)findViewById(R.id.edt2);
	 edt3=(EditText)findViewById(R.id.edt3);
	 edt4=(EditText)findViewById(R.id.edt4);
	 mcb=(CheckBox)findViewById(R.id.tmzhCheckBox1);
	 btn1=(Button)findViewById(R.id.btn1);
	 btn2=(Button)findViewById(R.id.btn2);
	 colse=(ImageButton)findViewById(R.id.close_m1);
	 
	 edt1.addTextChangedListener(new MyTextChange(edt1,btn1,btn2,colse,edt4));
	 
	 edt2.setText(msharedp.getString(WW,""));
	 edt3.setText(msharedp.getString(HH,""));
	 mcb.setChecked(msharedp.getBoolean(BL,true));
  }

  @Override
  protected void onResume()
  {
    super.onResume();
    if(cmb.getText().toString().indexOf("http://")!=-1&&
       cmb.getText().toString().indexOf(".jpg")!=-1)
     edt1.setText(cmb.getText());
  }
	
	public void onZHClick(View p)
	{
	 String bn=edt1.getText().toString(),
		t_w=edt2.length()<1?"480":edt2.getText().toString(),
		t_h=edt3.length()<1?"800":edt3.getText().toString();

		msharedp.edit().putString(WW,edt2.getText().toString()).commit();
		msharedp.edit().putString(HH,edt3.getText().toString()).commit();
		
	 int i=0;
	 while(bn.indexOf("http")!=-1&&bn.indexOf(".jpg")!=-1)
	 {
	  Log.v("----->",bn=thz(bn,t_w,t_h));
		i++;
	 }if(!mcb.isChecked())
	  Log.v("----->",bn=qwz(bn,t_w,t_h,i));
	 edt4.setText(bn);
	}
	
	public void onFZClick(View p)
	{
	 cmb.setText(edt4.getText().toString());
	 toast("已复制");
	}
	
	public void onClose1Click(View p)
	{
	 edt1.setText("");
	}
	
	private String thz(String d,String k,String g)
	{
		String a1,a2,a3;
		//获取字符首
		a1=d.substring(0,d.indexOf("http"));
		//获取字符末
		a3=d.substring(d.indexOf(".jpg")+4);
		//取字符中
		 try
		 { a2=d.substring(a1.length(),d.lastIndexOf(a3));
		 }catch(Exception e)
		 {
			System.out.println("Wrong!>>>"+e.getMessage());
		  a2=d.substring(a1.length());
		 }
		//替换中
		a2=a2.replace(a2.substring(0,a2.lastIndexOf("/")+1),"#(pic,").replace(".jpg",","+k+","+g+")");
		//重组
		return a1+a2+a3;
	}
	
	private String qwz(String s,String k,String g,int i)
	{
	 String s1,s2="",s3=","+k+","+g+")";
	 while(i>0)
	 {
		i--;
	  s1=s.substring(s.indexOf("#(pic,"),s.indexOf(s3)+s3.length());
	  s=s.replace(s1,"");
	  s2+=s1;
	 }
	 return s2;
	}
	
	@Override
	public void onBackPressed()
	{
	 super.onBackPressed();
	}
	 
}
