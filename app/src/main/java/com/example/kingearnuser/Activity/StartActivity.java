package com.example.kingearnuser.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.util.*;

import java.util.*;

import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import android.animation.ObjectAnimator;
import android.view.View;
import com.bumptech.glide.Glide;
import android.graphics.Typeface;

import com.example.kingearnuser.Activity.LoginActivity;
import com.example.kingearnuser.R;


public class StartActivity extends AppCompatActivity {
	
	private LinearLayout allback;
	private LinearLayout linear8;
	private LinearLayout cod;
	private LinearLayout tabslinear;
	private LinearLayout linear5;
	private LinearLayout linear1;
	private LinearLayout base;
	private LinearLayout trash;
	private LinearLayout layout1;
	private LinearLayout layout2;
	private LinearLayout layout3;
	private ImageView imageview1;
	private TextView textview4;
	private TextView textview5;
	private ImageView imageview2;
	private TextView textview6;
	private TextView textview7;
	private ImageView imageview3;
	private TextView textview8;
	private TextView textview9;
	private LinearLayout linear9;
	private TextView textview3;
	private LinearLayout dot1;
	private LinearLayout linear14;
	private LinearLayout dot2;
	private LinearLayout linear13;
	private LinearLayout dot3;
	
	private SharedPreferences data_1;
	private Intent i = new Intent();
	private ObjectAnimator oa = new ObjectAnimator();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.start);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		allback = (LinearLayout) findViewById(R.id.allback);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		cod = (LinearLayout) findViewById(R.id.cod);
		tabslinear = (LinearLayout) findViewById(R.id.tabslinear);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		base = (LinearLayout) findViewById(R.id.base);
		trash = (LinearLayout) findViewById(R.id.trash);
		layout1 = (LinearLayout) findViewById(R.id.layout1);
		layout2 = (LinearLayout) findViewById(R.id.layout2);
		layout3 = (LinearLayout) findViewById(R.id.layout3);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview4 = (TextView) findViewById(R.id.textview4);
		textview5 = (TextView) findViewById(R.id.textview5);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		textview6 = (TextView) findViewById(R.id.textview6);
		textview7 = (TextView) findViewById(R.id.textview7);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		textview8 = (TextView) findViewById(R.id.textview8);
		textview9 = (TextView) findViewById(R.id.textview9);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		textview3 = (TextView) findViewById(R.id.textview3);
		dot1 = (LinearLayout) findViewById(R.id.dot1);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		dot2 = (LinearLayout) findViewById(R.id.dot2);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		dot3 = (LinearLayout) findViewById(R.id.dot3);
		data_1 = getSharedPreferences("data_1", Activity.MODE_PRIVATE);
		
		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				data_1.edit().putString("start", ".").commit();
				i.setClass(getApplicationContext(), LoginActivity.class);
				startActivity(i);
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		Glide.with(getApplicationContext()).load(Uri.parse("file:///android_asset/congratulations-animation.gif")).into(imageview1);
		Glide.with(getApplicationContext()).load(Uri.parse("file:///android_asset/ads-gift-box.gif")).into(imageview2);
		Glide.with(getApplicationContext()).load(Uri.parse("file:///android_asset/cash.gif")).into(imageview3);
		textview3.setVisibility(View.GONE);
		_NavStatusBarColor("#FFFFFF", "#FFFFFF");
		_DARK_ICONS();
		_viewpager();
		_GradientDrawable(textview3, 20, 0, 0, "#F47F3C", "#F47F3C", true, true, 300);
		dot1.setLayoutParams(new LinearLayout.LayoutParams(40, 20));
		dot2.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
		dot3.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
		_rippleRoundStroke(dot1, "#FBAA37", "#FFFFFF", 100, 0, "#671FFF");
		_rippleRoundStroke(dot2, "#EEEEEE", "#FFFFFF", 100, 0, "#671FFF");
		_rippleRoundStroke(dot3, "#EEEEEE", "#FFFFFF", 100, 0, "#671FFF");
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/calculator.ttf"), Typeface.BOLD);
		textview8.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/calculator.ttf"), Typeface.BOLD);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/calculator.ttf"), Typeface.BOLD);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/calculator.ttf"), Typeface.BOLD);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _viewpager () {
		viewPager = new androidx.viewpager.widget.ViewPager
		(this);
		
		viewPager.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		MyPagerAdapter adapter = new MyPagerAdapter();
		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(0);
		viewPager.addOnPageChangeListener(new androidx.viewpager.widget.ViewPager.OnPageChangeListener() {
			public void onPageSelected(int position) {
				
				if (viewPager.getCurrentItem() == 0) {
					_autoTransitionScroll(linear8);
					dot1.setLayoutParams(new LinearLayout.LayoutParams(40, 20));
					dot2.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
					dot3.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
					_rippleRoundStroke(dot1, "#FBAA37", "#FFFFFF", 100, 0, "#1877F2");
					_rippleRoundStroke(dot2, "#EEEEEE", "#FFFFFF", 100, 0, "#1877F2");
					_rippleRoundStroke(dot3, "#EEEEEE", "#FFFFFF", 100, 0, "#1877F2");
					textview3.setVisibility(View.GONE);
				}
				else {
					if (viewPager.getCurrentItem() == 1) {
						_autoTransitionScroll(linear8);
						dot2.setLayoutParams(new LinearLayout.LayoutParams(40, 20));
						dot1.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
						dot3.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
						_rippleRoundStroke(dot2, "#FBAA37", "#FFFFFF", 100, 0, "#1877F2");
						_rippleRoundStroke(dot1, "#EEEEEE", "#FFFFFF", 100, 0, "#1877F2");
						_rippleRoundStroke(dot3, "#EEEEEE", "#FFFFFF", 100, 0, "#1877F2");
						textview3.setVisibility(View.GONE);
					}
					else {
						if (viewPager.getCurrentItem() == 2) {
							_autoTransitionScroll(linear8);
							dot3.setLayoutParams(new LinearLayout.LayoutParams(40, 20));
							dot2.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
							dot1.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
							_rippleRoundStroke(dot3, "#FBAA37", "#FFFFFF", 100, 0, "#1877F2");
							_rippleRoundStroke(dot2, "#EEEEEE", "#FFFFFF", 100, 0, "#1877F2");
							_rippleRoundStroke(dot1, "#EEEEEE", "#FFFFFF", 100, 0, "#1877F2");
							textview3.setVisibility(View.VISIBLE);
						}
					}
				}
			}
			@Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				
			}
			@Override public void onPageScrollStateChanged(int state) {
				
			}
		});
		
		
		base.addView(viewPager);
		
		tabLayout = new com.google.android.material.tabs.TabLayout
		(this);
		tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);
	}
	
	private class MyPagerAdapter extends androidx.viewpager.widget.PagerAdapter
	 {
		public int getCount() {
			return 3;
		}
		
		@Override public Object instantiateItem(ViewGroup collection, int position) {
			
			LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = inflater.inflate(R.layout.empty, null);
			
			LinearLayout container = (LinearLayout) v.findViewById(R.id.linear1);
			
			if (position == 0) {
				ViewGroup parent = (ViewGroup) layout1.getParent();
				if (parent != null) {
					parent.removeView(layout1);
				}container.addView(layout1);
				
			} else if (position == 1) {
				ViewGroup parent = (ViewGroup) layout2.getParent();
				if (parent != null) {
					parent.removeView(layout2);
				}
				container.addView(layout2);
				
				
			} else if (position == 2) {
				ViewGroup parent = (ViewGroup) layout3.getParent();
				if (parent != null) {
					parent.removeView(layout3);
				}
				container.addView(layout3);
			}
			collection.addView(v, 0);
			return v;
		}
		@Override public void destroyItem(ViewGroup collection, int position, Object view) {
			collection.removeView((View) view);
			trash.addView((View) view);
		}
		@Override public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == ((View) arg1);}
		@Override public Parcelable saveState() {
			return null;
		}
	}
	androidx.viewpager.widget.ViewPager
	 viewPager;
	com.google.android.material.tabs.TabLayout
	 tabLayout;
	private void foo() {
	}
	
	
	public void _rippleRoundStroke (final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
	}
	
	
	public void _NavStatusBarColor (final String _color1, final String _color2) {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
			Window w = this.getWindow();	w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);	w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(Color.parseColor("#" + _color1.replace("#", "")));	w.setNavigationBarColor(Color.parseColor("#" + _color2.replace("#", "")));
		}
	}
	
	
	public void _autoTransitionScroll (final View _scroll) {
		android.transition.TransitionManager.beginDelayedTransition((LinearLayout)_scroll, new android.transition.AutoTransition());
	}
	
	
	public void _DARK_ICONS () {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
	}
	
	
	public void _GradientDrawable (final View _view, final double _radius, final double _stroke, final double _shadow, final String _color, final String _borderColor, final boolean _ripple, final boolean _clickAnim, final double _animDuration) {
		if (_ripple) {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			gd.setStroke((int)_stroke,Color.parseColor(_borderColor));
			if (Build.VERSION.SDK_INT >= 21){
				_view.setElevation((int)_shadow);}
			android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#9e9e9e")});
			android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
			_view.setClickable(true);
			_view.setBackground(ripdrb);
		}
		else {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			gd.setStroke((int)_stroke,Color.parseColor(_borderColor));
			_view.setBackground(gd);
			if (Build.VERSION.SDK_INT >= 21){
				_view.setElevation((int)_shadow);}
		}
		if (_clickAnim) {
			_view.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()){
						case MotionEvent.ACTION_DOWN:{
							ObjectAnimator scaleX = new ObjectAnimator();
							scaleX.setTarget(_view);
							scaleX.setPropertyName("scaleX");
							scaleX.setFloatValues(0.9f);
							scaleX.setDuration((int)_animDuration);
							scaleX.start();
							
							ObjectAnimator scaleY = new ObjectAnimator();
							scaleY.setTarget(_view);
							scaleY.setPropertyName("scaleY");
							scaleY.setFloatValues(0.9f);
							scaleY.setDuration((int)_animDuration);
							scaleY.start();
							break;
						}
						case MotionEvent.ACTION_UP:{
							
							ObjectAnimator scaleX = new ObjectAnimator();
							scaleX.setTarget(_view);
							scaleX.setPropertyName("scaleX");
							scaleX.setFloatValues((float)1);
							scaleX.setDuration((int)_animDuration);
							scaleX.start();
							
							ObjectAnimator scaleY = new ObjectAnimator();
							scaleY.setTarget(_view);
							scaleY.setPropertyName("scaleY");
							scaleY.setFloatValues((float)1);
							scaleY.setDuration((int)_animDuration);
							scaleY.start();
							
							break;
						}
					}
					return false;
				}
			});
		}
	}
	
	
	public void _gradientview (final View _view) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.RIGHT_LEFT, new int[]{
			0xFFE52662,
			0xFFEB6320
		});
		gd.setCornerRadius(20f);
		_view.setElevation(6f);
		_view.setBackgroundDrawable(gd);
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}

	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx)){
				_result.add((double)_arr.keyAt(_iIdx));
			}
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}
