package pansong291.devlphelper.test;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import pansong291.devlphelper.R;
import pansong291.devlphelper.Zactivity;

public class WebViewActivity extends Zactivity
{
 ProgressBar mpb;
 WebView mWebView;
 Button back,go,re_stop;
 ImageButton goSearch;
 EditText goUrlText;
 View webMb;
 InputMethodManager imm;
 String loadURL,loadTitle;
 public static String uul="url",
 wangyi="http://wap.blog.163.com/pansong291";
 boolean isLoad;
 String black,gary,i;
 int djcs=0;
 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_webview);
  black="#000000";
  gary="#a0a0a0";
  mpb=(ProgressBar)findViewById(R.id.webviewProgressBar);
  mWebView=(WebView)findViewById(R.id.mWebView);
  re_stop=(Button)findViewById(R.id.webviewButton);
  back=(Button)findViewById(R.id.onbackButton);
  go=(Button)findViewById(R.id.ongoButton);
  webMb=findViewById(R.id.webmb);
  webMb.setBackgroundColor(Color.parseColor("#babababa"));
  ActionBar acnb=getActionBar();
  acnb.setCustomView(R.layout.web_edit);
  //使ActionBar不显示标题
  //acnb.setDisplayShowTitleEnabled(false);
  //使ActionBar显示自定义View
  acnb.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
  goSearch=(ImageButton)acnb.getCustomView().findViewById(R.id.websearch);
  goUrlText=(EditText)acnb.getCustomView().findViewById(R.id.webeditt);
  MyWebListen mwl=new MyWebListen();
  goSearch.setOnClickListener(mwl);
  goUrlText.setOnClickListener(mwl);
  goUrlText.setOnKeyListener(new OnKeyListener()
  {
   @Override
   public boolean onKey(View p1,int p2,KeyEvent p3)
   { //软键盘的回车事件
    if(p2==KeyEvent.KEYCODE_ENTER
	    &&p3.getAction()==KeyEvent.ACTION_UP)
	    onSearchBP();
    return false;
   }
  });
  imm=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
  loadURL=getIntent().getStringExtra(uul);
  
  mWebView.setWebViewClient(new WebViewClient()
  {
	@Override //链接点击仍然在本View中而不跳到浏览器
	public boolean shouldOverrideUrlLoading(WebView view,String url)
	{  
	 view.loadUrl(url);
	 return true;  
	}
	@Override //开始加载时
	public void onPageStarted(WebView view,String url,Bitmap favicon)
	{
	 isLoad=true;
	 loadURL=url;
	 msharedp.edit().putString(uul,url).commit();
	 re_stop.setText("停止");
	 mpb.setVisibility(0);
	}
	@Override //加载完毕时
	public void onPageFinished(WebView view,String url)
	{
	 isLoad=false;
	 re_stop.setText("刷新");
	 mpb.setVisibility(8);
	 back.setTextColor(Color.parseColor(i=mWebView.canGoBack()?black:gary));
	 go.setTextColor(Color.parseColor(i=mWebView.canGoForward()?black:gary));
	 back.setClickable(mWebView.canGoBack());
	 go.setClickable(mWebView.canGoForward());
	}
  });
  mWebView.setWebChromeClient(new WebChromeClient()
  {
    @Override //设置进度
	  public void onProgressChanged(WebView view,int progress)
	  {
	   mpb.setProgress(progress);
	  }
    @Override //设置标题
    public void onReceivedTitle(WebView view,String title)
    {
     goUrlText.setHint(loadTitle=title);
    }
  });
  
  //设置WebView属性，能够执行Javascript脚本  
  mWebView.getSettings().setJavaScriptEnabled(true);
  //加载需要显示的网页  
  mWebView.loadUrl(loadURL);
  //re_stop.setText("停止");
  isLoad=true;
  
 }

 private class MyWebListen implements OnClickListener
 {
  @Override
  public void onClick(View p1)
  {
    //ActionBar上的输入框的响应事件
    if(p1.getId()==R.id.webeditt&&djcs==0)
	  {
	   goUrlText.setFocusable(true);
	   goUrlText.requestFocus();
	   goUrlText.setFocusableInTouchMode(true);
	   goUrlText.requestFocusFromTouch();
	   
	   goSearch.setVisibility(0);
	   webMb.setVisibility(0);
     goUrlText.setHint("");
	   goUrlText.setText(loadURL);
 	   goUrlText.selectAll();
	   showIME(true);
	   djcs++;
	  }else if(p1.getId()==R.id.websearch)
	  {
	   onSearchBP();
	  }
  }
 }
 //ActionBar上的搜索按钮响应事件
 public void onSearchBP()
 {
  djcs=0;
  goSearch.setVisibility(8);
  webMb.setVisibility(8);
  goUrlText.setFocusable(false);
  String shouldUrl=toUrl(goUrlText.getText().toString());
  if(shouldUrl.length()>0&&!shouldUrl.equals(loadURL))
  {
   mWebView.loadUrl(shouldUrl);
  }else{
   showIME(false);
   goUrlText.setHint(loadTitle);
  }
  goUrlText.setText("");
 }
 //将不合法的网址字符串转成合法网址字符串
 public String toUrl(String lj)
 {
  if(lj.length()<1)return lj;
  if(bczwzhz(lj))
   return"http://m.baidu.com/s?word="+lj;
  String httpt="http://";
  if(lj.indexOf(httpt)!=0)
   return httpt+lj;
  return lj;
 }
 //不存在网址的后缀
 public boolean bczwzhz(String lj)
 {
  return(lj.lastIndexOf(".com")==-1&&lj.lastIndexOf(".cn")==-1
   &&lj.lastIndexOf(".net")==-1&&lj.lastIndexOf(".cc")==-1
   &&lj.lastIndexOf(".org")==-1&&lj.lastIndexOf(".tv")==-1
   &&lj.lastIndexOf(".gov")==-1&&lj.lastIndexOf(".museum")==-1
   &&lj.lastIndexOf(".int")==-1&&lj.lastIndexOf(".de")==-1
   &&lj.lastIndexOf(".edu")==-1&&lj.lastIndexOf(".info")==-1
   &&lj.lastIndexOf(".biz")==-1&&lj.lastIndexOf(".coop")==-1
   &&lj.lastIndexOf(".pro")==-1&&lj.lastIndexOf(".travel")==-1
   &&lj.lastIndexOf(".tel")==-1&&lj.lastIndexOf(".mobi")==-1
   &&lj.lastIndexOf(".tk")==-1&&lj.lastIndexOf(".jobs")==-1);
 }
 //控制软键盘的显示与隐藏
 public void showIME(boolean sh)
 {
  if(sh)
   imm.toggleSoftInput(InputMethodManager.RESULT_UNCHANGED_SHOWN,InputMethodManager.HIDE_NOT_ALWAYS);
  else
   imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
 }
 //蒙板的响应事件
 public void onMb(View v)
 {
  djcs=0;
  goUrlText.setText("");
  goUrlText.setHint(loadTitle);
  goUrlText.setFocusable(false);
  goSearch.setVisibility(8);
  webMb.setVisibility(8);
  showIME(false);
 }
 
 public void tuiChu(View v)
 {
  if(isLoad)
   mWebView.stopLoading();
  finish();
 }
 
 public void houTui(View v)
 {
   if(mWebView.canGoBack())
   mWebView.goBack();
 }
 
 public void qianJin(View v)
 {
   if(mWebView.canGoForward())
   mWebView.goForward();
 }
 
 public void shuaXin(View v)
 {
  if(!isLoad)
  {
   //re_stop.setText("停止");
   mWebView.reload();
   isLoad=true;
   toast("刷新中...");
  }else
  {
   //re_stop.setText("刷新");
   mWebView.stopLoading();
   isLoad=false;
   toast("正在中断...");
  }
 }
 
 public void wangyi(View v)
 {
  mWebView.loadUrl(wangyi);
 }
 
 @Override
 public void onBackPressed()
 {
  if(webMb.getVisibility()==0)
   onMb(null);
 }

 
}
