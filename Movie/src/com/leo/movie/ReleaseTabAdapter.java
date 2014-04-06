package com.leo.movie;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ReleaseTabAdapter extends BaseAdapter implements OnItemClickListener{

	private int[] colors = new int[] { 0xff626569, 0xff4f5257 };

	private Context context;

	private static final String[] mListTitle = { "姓名", "性别", "年龄", "居住地", "邮箱" };
	private static final String[] mListStr = { "雨松MOMO", "男", "25", "北京",
			"xuanyusong@gmail.com" };

	public ReleaseTabAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListStr.length;
	}

	@Override
	public Object getItem(int pos) {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public long getItemId(int pos) {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		ImageView iamge = null;
		TextView title = null;
		TextView text = null;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.content1, null);
			iamge = (ImageView) view.findViewById(R.id.color_image);
			title = (TextView) view.findViewById(R.id.color_title);
			text = (TextView) view.findViewById(R.id.color_text);
		}
		int colorPos = position % colors.length;
		view.setBackgroundColor(colors[colorPos]);
		if(title != null)
		title.setText(mListTitle[position]);
		if(text != null)
		text.setText(mListStr[position]);
		if(iamge != null)
		iamge.setImageResource(R.drawable.ic_launcher);
		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		View view2 = adapterView.getChildAt(position);
		view2.setBackgroundColor(Color.RED);
		Toast.makeText(context,"您选择了" + mListStr[position], Toast.LENGTH_LONG).show();  
	}

}
