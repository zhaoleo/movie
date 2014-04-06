package com.leo.movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private ViewPager viewPager;// 页卡内容
	private ImageView imageView;// 动画图片
	private TextView textView1, textView2, textView3;
	private List<View> views;// Tab页面列表
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	private View view1,view3,view2;// 各个页卡

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		InitImageView();
		InitTextView();
		InitViewPager();
	}

	private void InitViewPager() {
		viewPager = (ViewPager) findViewById(R.id.vPager);
		views = new ArrayList<View>();
		LayoutInflater inflater = getLayoutInflater();
		view1 =  inflater.inflate(R.layout.lay1, null);
		view2 =  inflater.inflate(R.layout.lay2, null);
		view3 = inflater.inflate(R.layout.lay3, null);
		//////////////////////////////////@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		ListView listview = (ListView) view2.findViewById(R.id.nearbycinema);
		ReleaseTabAdapter adapter = new ReleaseTabAdapter(this);
	    listview.setAdapter(adapter);
	    listview.setOnItemClickListener(adapter);
		//////////////////////////////////@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	      //生成动态数组，并且转入数据  
	      GridView gridview = (GridView)view1.findViewById(R.id.gridview);
	      ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();  
	      for(int i=0;i<22;i++)  
	      {  
	        HashMap<String, Object> map = new HashMap<String, Object>();  
	        map.put("ItemImage", R.drawable.ic_launcher);//添加图像资源的ID  
	        map.put("ItemText", "NO."+String.valueOf(i));//按序号做ItemText  
	        lstImageItem.add(map);  
	      }  
	      //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应  
	      SimpleAdapter saImageItems = new SimpleAdapter(this, //没什么解释  
	                                                lstImageItem,//数据来源   
	                                                R.layout.night_item,//night_item的XML实现  
	                                                  
	                                                //动态数组与ImageItem对应的子项          
	                                                new String[] {"ItemImage","ItemText"},   
	                                                  
	                                                //ImageItem的XML文件里面的一个ImageView,两个TextView ID  
	                                                new int[] {R.id.ItemImage,R.id.ItemText});  
	      gridview.setAdapter(saImageItems);  
		//////////////////////////////////@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		
		views.add(view1);
		views.add(view2);
		views.add(view3);
		viewPager.setAdapter(new MyViewPagerAdapter(views));
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	private void InitTextView() {
		textView1 = (TextView) findViewById(R.id.text1);
		textView2 = (TextView) findViewById(R.id.text2);
		textView3 = (TextView) findViewById(R.id.text3);

		textView1.setOnClickListener(new MyOnClickListener(0));
		textView2.setOnClickListener(new MyOnClickListener(1));
		textView3.setOnClickListener(new MyOnClickListener(2));
	}

	private void InitImageView() {
		imageView = (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a).getWidth();// 获取图片宽度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		imageView.setImageMatrix(matrix);// 设置动画初始位置
	}

	private class MyOnClickListener implements OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		public void onClick(View v) {
			viewPager.setCurrentItem(index);
		}

	}

	public class MyViewPagerAdapter extends PagerAdapter {
		private List<View> mListViews;

		public MyViewPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(mListViews.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mListViews.get(position), 0);
			return mListViews.get(position);
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	}
	
	 public class MyOnPageChangeListener implements OnPageChangeListener{  
		  
	        int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量  
	        int two = one * 2;// 页卡1 -> 页卡3 偏移量  
	        public void onPageScrollStateChanged(int arg0) {  
	              
	              
	        }  
	  
	        public void onPageScrolled(int arg0, float arg1, int arg2) {  
	              
	              
	        }  
	  
	        public void onPageSelected(int arg0) {  
	            /*两种方法，这个是一种，下面还有一种，显然这个比较麻烦 
	            Animation animation = null; 
	            switch (arg0) { 
	            case 0: 
	                if (currIndex == 1) { 
	                    animation = new TranslateAnimation(one, 0, 0, 0); 
	                } else if (currIndex == 2) { 
	                    animation = new TranslateAnimation(two, 0, 0, 0); 
	                } 
	                break; 
	            case 1: 
	                if (currIndex == 0) { 
	                    animation = new TranslateAnimation(offset, one, 0, 0); 
	                } else if (currIndex == 2) { 
	                    animation = new TranslateAnimation(two, one, 0, 0); 
	                } 
	                break; 
	            case 2: 
	                if (currIndex == 0) { 
	                    animation = new TranslateAnimation(offset, two, 0, 0); 
	                } else if (currIndex == 1) { 
	                    animation = new TranslateAnimation(one, two, 0, 0); 
	                } 
	                break; 
	                 
	            } 
	            */  
	            Animation animation = new TranslateAnimation(one*currIndex, one*arg0, 0, 0);//显然这个比较简洁，只有一行代码。  
	            currIndex = arg0;  
	            animation.setFillAfter(true);// True:图片停在动画结束位置  
	            animation.setDuration(300);  
	            imageView.startAnimation(animation);  
	            Toast.makeText(MainActivity.this, "您选择了"+ viewPager.getCurrentItem()+"页卡", Toast.LENGTH_SHORT).show();  
	        }  
	          
	    }  

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu); 
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == R.id.action_search) {
			 Toast.makeText(MainActivity.this, "查询一下下", Toast.LENGTH_SHORT).show();
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
