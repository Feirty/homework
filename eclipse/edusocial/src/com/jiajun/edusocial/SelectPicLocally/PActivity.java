package com.jiajun.edusocial.SelectPicLocally;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jiajun.edusocial.R;

public class PActivity extends Activity{
	private ArrayList<View> listViews = null;
	private ViewPager pager;
	private MyPageAdapter adapter;
	private int count;
	RelativeLayout photo_relativeLayout;
	ArrayList<String> pathList;
	int posID;
	ArrayList<Bitmap> bmList = new ArrayList<Bitmap>();
	ArrayList<Bitmap> bmp = new ArrayList<Bitmap>();
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {

			Bundle a = msg.getData();
			bmp = a.getParcelableArrayList("ok");
			
			
			
			pager = (ViewPager) findViewById(R.id.viewpager);
			pager.setOnPageChangeListener(pageChangeListener);
//			for (int i = 0; i < bmp.size(); i++) {
//				initListViews(bmp.get(i));//
//			}
			for (int j = 0; j < bmp.size(); j++) {
				initListViews(bmp.get(j));
			}
			adapter = new MyPageAdapter(listViews);// 构造adapter
			pager.setAdapter(adapter);// 设置适配器
//			Intent intent2 = getIntent();
//			int id = intent.getIntExtra("ID", 0);
			pager.setCurrentItem(posID);
			
//			switch (msg.what) {
//			case 0:
//				System.out.println("success");
//				for (int u = 0; u < bmList.size(); u++) {
//					bmp.add(bmList.get(u));
//				}
//				break;
//
//			default:
//				break;
//			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_photo);
		
		photo_relativeLayout = (RelativeLayout) findViewById(R.id.photo_relativeLayout);
		photo_relativeLayout.setBackgroundColor(0x70000000);
		
		Intent intent = getIntent();
		posID = intent.getIntExtra("ID", 0);
		pathList = intent.getStringArrayListExtra("selectPicPath");
		for (int k = 0; k < pathList.size(); k++) {
			System.out.println(pathList.get(k));
		}
		//获取市局
		new Thread(){
			public void run() {
				Message msg = new Message();
				msg.what = 0;
				try {
//					for (int l = 0; l < pathList.size(); l++) {
//					String pathPic = pathList.get(l);
					for (int i = 0; i < pathList.size(); i++) {
						

						Bitmap bm = Bimp.revitionImageSize(pathList.get(i));
						bmList.add(bm);
						System.out.println(pathList.get(i)+ " is ok!");
						
					}

					Bundle b = new Bundle();
					b.putParcelableArrayList("ok", bmList);
					msg.setData(b);
//					bmList.add(bm);

//					String newStr = pathPic.substring(
//							pathPic.lastIndexOf("/") + 1,
//							pathPic.lastIndexOf("."));
//					FileUtils.saveBitmap(bm, "" + newStr);
//					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
//				msg.obj = bmList;
				
				handler.sendMessage(msg);
			}
		}.start();
//		for (int o = 0; o < bmList.size(); o++) {
//			bmp.add(bmList.get(o));
//		}
		Button photo_bt_exit = (Button) findViewById(R.id.photo_bt_exit);
		photo_bt_exit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				finish();
			}
		});
		Button photo_bt_del = (Button) findViewById(R.id.photo_bt_del);
		photo_bt_del.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
			}
		});
		Button photo_bt_enter = (Button) findViewById(R.id.photo_bt_enter);
		photo_bt_enter.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});
//		pager = (ViewPager) findViewById(R.id.viewpager);
//		pager.setOnPageChangeListener(pageChangeListener);
////		for (int i = 0; i < bmp.size(); i++) {
////			initListViews(bmp.get(i));//
////		}
//		for (int j = 0; j < bmp.size(); j++) {
//			initListViews(bmp.get(j));
//		}
//		adapter = new MyPageAdapter(listViews);// 构造adapter
//		pager.setAdapter(adapter);// 设置适配器
////		Intent intent2 = getIntent();
////		int id = intent.getIntExtra("ID", 0);
//		pager.setCurrentItem(posID);
	}
	private void initListViews(Bitmap bm) {
		if (listViews == null)
			listViews = new ArrayList<View>();
		ImageView img = new ImageView(this);// 构造textView对象
		img.setBackgroundColor(0xff000000);
		img.setImageBitmap(bm);
		img.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		listViews.add(img);// 添加view
	}

	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		public void onPageSelected(int arg0) {// 页面选择响应函数
			count = arg0;
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {// 滑动中。。。

		}

		public void onPageScrollStateChanged(int arg0) {// 滑动状态改变

		}
	};

	class MyPageAdapter extends PagerAdapter {

		private ArrayList<View> listViews;// content

		private int size;// 页数

		public MyPageAdapter(ArrayList<View> listViews) {// 构造函数
															// 初始化viewpager的时候给的一个页面
			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		public void setListViews(ArrayList<View> listViews) {// 自己写的一个方法用来添加数据
			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		public int getCount() {// 返回数量
			return size;
		}

		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		public void destroyItem(View arg0, int arg1, Object arg2) {// 销毁view对象
			((ViewPager) arg0).removeView(listViews.get(arg1 % size));
		}

		public void finishUpdate(View arg0) {
		}

		public Object instantiateItem(View arg0, int arg1) {// 返回view对象
			try {
				((ViewPager) arg0).addView(listViews.get(arg1 % size), 0);

			} catch (Exception e) {
			}
			return listViews.get(arg1 % size);
		}

		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}
}
