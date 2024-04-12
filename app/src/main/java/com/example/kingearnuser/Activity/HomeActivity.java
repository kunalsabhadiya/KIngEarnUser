package com.example.kingearnuser.Activity;


import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.example.kingearnuser.BuildConfig;
import com.example.kingearnuser.Controller.RequestNetwork;
import com.example.kingearnuser.Controller.RequestNetworkController;
import com.example.kingearnuser.R;
import com.example.kingearnuser.Utils.SketchwareUtil;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeActivity extends AppCompatActivity {
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private DrawerLayout _drawer;

	private double tm_difference = 0;
	private double current_time = 0;
	private String username = "";
	private String slider1 = "";
	private String slider2 = "";
	private String slider3 = "";

	private HashMap<String, Object> map = new HashMap<>();
	private double now_time = 0;
	private String balance = "";
	private double last_Claimed = 0;
	private String time_X = "";
	private String key = "";

	private double limit = 0;
	private String time = "";
	private double last = 0;
	private double now = 0;
	private double click = 0;
	private HashMap<String, Object> m = new HashMap<>();
	private ImageView imageview1;

	private ImageView imageview2;

	private LinearLayout linear12;
	private LinearLayout linear13;
	private LinearLayout linear_1;

	private LinearLayout linear_3;
	private AdView adview3;
	private TextView textview4;
	private ImageView imageview3;
	private LinearLayout daily_bonus;
	private LinearLayout quick_quiz;

	private LinearLayout website_visit;
	private ImageView bkash;
	private TextView textview6;
	private ImageView nagad;
	private TextView textview7;
	private ImageView rocket;
	private TextView textview8;
	private LinearLayout hist;
	private LinearLayout support;
	private LinearLayout others;
	private LinearLayout _drawer_home;
	private LinearLayout _drawer_profile;
	private LinearLayout _drawer_membership;
	private LinearLayout _drawer_share;
	private LinearLayout _drawer_privacy;
	private LinearLayout _drawer_about;
	private LinearLayout _drawer_report;
	private LinearLayout _drawer_guide;
	private LinearLayout _drawer_exit;
	private LinearLayout _drawer_linear3;
	private CircleImageView _drawer_imageview_avatar;
	private TextView _drawer_textview_name;
	private ImageView _drawer_imageview14;
	private ImageView _drawer_imageview15;
	private ImageView _drawer_imageview16;
	
	private RequestNetwork net;
	private RequestNetwork.RequestListener _net_request_listener;
	private Intent nett = new Intent();
	private Intent i = new Intent();
	private Intent p = new Intent();
	private Intent a = new Intent();
	private Intent profile = new Intent();
	private Intent member = new Intent();
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	private AlertDialog.Builder dialog;
	private FirebaseAuth auth;
//	private DatabaseReference sponsor = _firebase.getReference("sponsor");
//	private ChildEventListener _sponsor_child_listener;
	private Intent go = new Intent();
	private SharedPreferences tme;
	private Calendar clnd = Calendar.getInstance();
	private DatabaseReference control = _firebase.getReference("control");
	private ChildEventListener _control_child_listener;
	private DatabaseReference claimed_time = _firebase.getReference("claimed_time");
	private ChildEventListener _claimed_time_child_listener;
	private Intent page = new Intent();
	private DatabaseReference history = _firebase.getReference("history");
	private ChildEventListener _history_child_listener;
	private InterstitialAd inter;
	private AdListener _inter_ad_listener;
	private DatabaseReference ad_time = _firebase.getReference("ad_time");
	private ChildEventListener _ad_time_child_listener;
	private boolean vpnInUse = false;
	private Calendar c = Calendar.getInstance();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize();
		com.google.firebase.FirebaseApp.initializeApp(this);
		//initializeLogic();
	}
	
	private void initialize() {

		setSupportActionBar(_toolbar);
	/*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
		public void onClick(View _v) {
				onBackPressed();
			}
		});
	*/	_drawer = (DrawerLayout) findViewById(R.id._drawer);
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(HomeActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view);
		
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		linear_1 = (LinearLayout) findViewById(R.id.linear_1);

		linear_3 = (LinearLayout) findViewById(R.id.linear_3);
		adview3 = (AdView) findViewById(R.id.adview3);
		textview4 = (TextView) findViewById(R.id.textview4);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		daily_bonus = (LinearLayout) findViewById(R.id.daily_bonus);
		quick_quiz = (LinearLayout) findViewById(R.id.quick_quiz);
		website_visit = (LinearLayout) findViewById(R.id.website_visit);
		bkash = (ImageView) findViewById(R.id.bkash);
		textview6 = (TextView) findViewById(R.id.textview6);
		nagad = (ImageView) findViewById(R.id.nagad);
		textview7 = (TextView) findViewById(R.id.textview7);
		rocket = (ImageView) findViewById(R.id.rocket);
		textview8 = (TextView) findViewById(R.id.textview8);
		hist = (LinearLayout) findViewById(R.id.hist);
		support = (LinearLayout) findViewById(R.id.support);
		others = (LinearLayout) findViewById(R.id.others);
		_drawer_home = (LinearLayout) _nav_view.findViewById(R.id.home);
		_drawer_profile = (LinearLayout) _nav_view.findViewById(R.id.profile);
		_drawer_membership = (LinearLayout) _nav_view.findViewById(R.id.membership);
		_drawer_share = (LinearLayout) _nav_view.findViewById(R.id.share);
		_drawer_privacy = (LinearLayout) _nav_view.findViewById(R.id.privacy);
		_drawer_about = (LinearLayout) _nav_view.findViewById(R.id.about);
		_drawer_report = (LinearLayout) _nav_view.findViewById(R.id.report);
		_drawer_guide = (LinearLayout) _nav_view.findViewById(R.id.guide);
		_drawer_exit = (LinearLayout) _nav_view.findViewById(R.id.exit);
		_drawer_linear3 = (LinearLayout) _nav_view.findViewById(R.id.linear3);
		_drawer_imageview_avatar = (CircleImageView) _nav_view.findViewById(R.id.imageview_avatar);
		_drawer_textview_name = (TextView) _nav_view.findViewById(R.id.textview_name);
		_drawer_imageview14 = (ImageView) _nav_view.findViewById(R.id.imageview14);
		_drawer_imageview15 = (ImageView) _nav_view.findViewById(R.id.imageview15);
		_drawer_imageview16 = (ImageView) _nav_view.findViewById(R.id.imageview16);
		net = new RequestNetwork(this);
		auth = FirebaseAuth.getInstance();
		tme = getSharedPreferences("tme", Activity.MODE_PRIVATE);

		MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
			@SuppressLint("VisibleForTests")
			@Override
			public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
				adview3.loadAd(new AdRequest.Builder().build());
				adview3.setAdListener(new AdListener() {
					@Override
					public void onAdLoaded() {
						super.onAdLoaded();
					}
					@Override
					public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
						super.onAdFailedToLoad(loadAdError);
					}
				});


			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_drawer.openDrawer(GravityCompat.START);
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), NotificationActivity.class);
				startActivity(i);
			}
		});
		
		daily_bonus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (time_X.equals("")) {
					clnd = Calendar.getInstance();
					map = new HashMap<>();
					map.put("balance", String.valueOf((long)(Double.parseDouble(balance) + 10)));
					users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
					map.clear();
					key = history.push().getKey();
					map = new HashMap<>();
					map.put("subject", "daily bonus");
					map.put("date", new SimpleDateFormat("dd-MM-yyyy").format(clnd.getTime()));
					map.put("amount", "10");
					map.put("key", key);
					map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					history.child(key).updateChildren(map);
					map.clear();
					map = new HashMap<>();
					map.put("timer", String.valueOf((long)(clnd.getTimeInMillis())));
					claimed_time.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
					map.clear();
					_congratulation("+10 points");
				}
				else {
					if (!time_X.equals("")) {
						last_Claimed = Double.parseDouble(time_X);
						clnd = Calendar.getInstance();
						now_time = clnd.getTimeInMillis() - last_Claimed;
						if (((now_time / (60 * 60000)) > 24) || ((now_time / (60 * 60000)) == 24)) {
							clnd = Calendar.getInstance();
							map = new HashMap<>();
							map.put("balance", String.valueOf((long)(Double.parseDouble(balance) + 10)));
							users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
							map.clear();
							key = history.push().getKey();
							map = new HashMap<>();
							map.put("subject", "daily bonus");
							map.put("date", new SimpleDateFormat("dd-MM-yyyy").format(clnd.getTime()));
							map.put("amount", "10");
							map.put("key", key);
							map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
							history.child(key).updateChildren(map);
							map.clear();
							map = new HashMap<>();
							map.put("timer", String.valueOf((long)(clnd.getTimeInMillis())));
							claimed_time.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
							map.clear();
							_congratulation("+10 points");
						}
						else {
							SketchwareUtil.showMessage(getApplicationContext(), "Wait 24 hours");
						}
					}
				}
			}
		});
		
		quick_quiz.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("MissingPermission")
			@RequiresApi(api = Build.VERSION_CODES.M)
			@Override
			public void onClick(View _view) {
				try{
					android.net.ConnectivityManager cm = (android.net.ConnectivityManager) HomeActivity.this.getSystemService(android.content.Context.CONNECTIVITY_SERVICE);

					Network activeNetwork = cm.getActiveNetwork();
					NetworkCapabilities caps = cm.getNetworkCapabilities(activeNetwork);

					vpnInUse = caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
				}catch(Exception e){
					SketchwareUtil.showMessage( getApplicationContext(),"No Internet");
				}
			if (vpnInUse) {
					//inter = new InterstitialAd(HomeActivity.this);
					/*inter.setAdListener(_inter_ad_listener);
					inter.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
					inter.loadAd(new AdRequest.Builder().addTestDevice("708001022B2AEFB4CA5DB3785F35FD14")
					.build());*/
					_loadingdialog(true, "Ad loading...");
					if (limit == (click + 1)) {
						if (time.equals("")) {
							c = Calendar.getInstance();
							m = new HashMap<>();
							m.put("timer", String.valueOf((long)(c.getTimeInMillis())));
							ad_time.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(m);
							m.clear();
						}
						else {
							if (!time.equals("")) {
								last = Double.parseDouble(time);
								c = Calendar.getInstance();
								now = clnd.getTimeInMillis() - last;
								if (((now / (60 * 60000)) > 24) || ((now / (60 * 60000)) == 24)) {
									clnd = Calendar.getInstance();
									m = new HashMap<>();
									m.put("timer", String.valueOf((long)(c.getTimeInMillis())));
									ad_time.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(m);
									m.clear();
								}
							}
						}
					}
				}
				else {
					inter = new InterstitialAd() {
						@Nullable
						@Override
						public FullScreenContentCallback getFullScreenContentCallback() {
							return null;
						}

						@Nullable
						@Override
						public OnPaidEventListener getOnPaidEventListener() {
							return null;
						}

						@NonNull
						@Override
						public ResponseInfo getResponseInfo() {
							return null;
						}

						@NonNull
						@Override
						public String getAdUnitId() {
							return null;
						}

						@Override
						public void setFullScreenContentCallback(@Nullable FullScreenContentCallback fullScreenContentCallback) {

						}

						@Override
						public void setImmersiveMode(boolean b) {

						}

						@Override
						public void setOnPaidEventListener(@Nullable OnPaidEventListener onPaidEventListener) {

						}

						@Override
						public void show(@NonNull Activity activity) {

						}
					};/*
					inter.setAdListener(_inter_ad_listener);
					inter.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
					inter.loadAd(new AdRequest.Builder().addTestDevice("708001022B2AEFB4CA5DB3785F35FD14")
							.build());*/
					_loadingdialog(true, "Ad loading...");
					if (limit == (click + 1)) {
						if (time.equals("")) {
							c = Calendar.getInstance();
							m = new HashMap<>();
							m.put("timer", String.valueOf((long)(c.getTimeInMillis())));
							ad_time.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(m);
							m.clear();
						}
						else {
							if (!time.equals("")) {
								last = Double.parseDouble(time);
								c = Calendar.getInstance();
								now = clnd.getTimeInMillis() - last;
								if (((now / (60 * 60000)) > 24) || ((now / (60 * 60000)) == 24)) {
									clnd = Calendar.getInstance();
									m = new HashMap<>();
									m.put("timer", String.valueOf((long)(c.getTimeInMillis())));
									ad_time.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(m);
									m.clear();
								}
							}
						}
					}
					SketchwareUtil.showMessage(getApplicationContext(), "No VPN CONNECTION");
				}
			}
		});

		website_visit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				page.setClass(getApplicationContext(), Web2Activity.class);
				startActivity(page);
			}
		});

		hist.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				page.setClass(getApplicationContext(), HistoryActivity.class);
				startActivity(page);
			}
		});
		
		support.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				go.setAction(Intent.ACTION_VIEW);
				go.setData(Uri.parse("https://google.com"));
				startActivity(go);
			}
		});
		
		others.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				page.setClass(getApplicationContext(), WalletActivity.class);
				startActivity(page);
			}
		});
		
		_net_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				nett.setClass(getApplicationContext(), NeterrorActivity.class);
				startActivity(nett);
			}
		};
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					_loadingdialog(false, "");
					if (_childValue.get("avatar").toString().equals("null")) {
						_drawer_linear3.setVisibility(View.VISIBLE);
						_drawer_imageview_avatar.setVisibility(View.GONE);
					}
					else {
						_drawer_linear3.setVisibility(View.GONE);
						_drawer_imageview_avatar.setVisibility(View.VISIBLE);
						Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("avatar").toString())).into(_drawer_imageview_avatar);
					}
					if (_childValue.containsKey("balance")) {
						balance = _childValue.get("balance").toString();
					}
					if (_childValue.containsKey("limit")) {
						limit = Double.parseDouble(_childValue.get("limit").toString());
					}
					if (_childValue.containsKey("click")) {
						click = Double.parseDouble(_childValue.get("click").toString());
					}
					if (_childValue.get("block").toString().equals("true")) {
						_blocked();
					}
					if (_childValue.containsKey("firstname") && _childValue.containsKey("lastname")) {
						_drawer_textview_name.setText(_childValue.get("firstname").toString().trim().substring((int)(0), (int)(1)).concat(_childValue.get("lastname").toString().trim().substring((int)(0), (int)(1))).toUpperCase());
						username = _childValue.get("firstname").toString().concat(" ".concat(_childValue.get("lastname").toString()));
						if (!tme.getString("time_", "").equals("")) {
							linear12.setVisibility(View.VISIBLE);
							current_time = Double.parseDouble(tme.getString("time_", ""));
							clnd = Calendar.getInstance();
							tm_difference = clnd.getTimeInMillis() - current_time;
							if (tm_difference < 60000) {
								if ((tm_difference / 1000) < 2) {
									_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / 1000)).concat(" second ago")))));
								}
								else {
									_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / 1000)).concat(" seconds ago")))));
								}
							}
							else {
								if (tm_difference < (60 * 60000)) {
									if ((tm_difference / 60000) < 2) {
										_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / 60000)).concat(" minute ago")))));
									}
									else {
										_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / 60000)).concat(" minutes ago")))));
									}
								}
								else {
									if (tm_difference < (24 * (60 * 60000))) {
										if ((tm_difference / (60 * 60000)) < 2) {
											_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (60 * 60000))).concat(" hour ago")))));
										}
										else {
											_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (60 * 60000))).concat(" hours ago")))));
										}
									}
									else {
										if (tm_difference < (7 * (24 * (60 * 60000)))) {
											if ((tm_difference / (24 * (60 * 60000))) < 2) {
												_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (24 * (60 * 60000)))).concat(" day ago")))));
											}
											else {
												_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (24 * (60 * 60000)))).concat(" days ago")))));
											}
										}
										else {
											if (tm_difference < (4 * (7 * (24 * (60 * 60000))))) {
												if ((tm_difference / (7 * (24 * (60 * 60000)))) < 2) {
													_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (7 * (24 * (60 * 60000))))).concat(" week ago")))));
												}
												else {
													_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (7 * (24 * (60 * 60000))))).concat(" weeks ago")))));
												}
											}
											else {
												if (tm_difference < (12 * (4 * (7 * (24 * (60 * 60000)))))) {
													if ((tm_difference / (4 * (7 * (24 * (60 * 60000))))) < 2) {
														_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (4 * (7 * (24 * (60 * 60000)))))).concat(" month ago")))));
													}
													else {
														_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (4 * (7 * (24 * (60 * 60000)))))).concat(" months ago")))));
													}
												}
												else {
													if ((tm_difference / (12 * (4 * (7 * (24 * (60 * 60000)))))) < 2) {
														_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (12 * (4 * (7 * (24 * (60 * 60000))))))).concat(" year ago")))));
													}
													else {
														_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (12 * (4 * (7 * (24 * (60 * 60000))))))).concat(" years ago")))));
													}
												}
											}
										}
									}
								}
							}
						}
						else {
							linear12.setVisibility(View.GONE);
						}
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					_loadingdialog(false, "");
					if (_childValue.get("avatar").toString().equals("null")) {
						_drawer_linear3.setVisibility(View.VISIBLE);
						_drawer_imageview_avatar.setVisibility(View.GONE);
					}
					else {
						_drawer_linear3.setVisibility(View.GONE);
						_drawer_imageview_avatar.setVisibility(View.VISIBLE);
						Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("avatar").toString())).into(_drawer_imageview_avatar);
					}
					if (_childValue.containsKey("balance")) {
						balance = _childValue.get("balance").toString();
					}
					if (_childValue.containsKey("limit")) {
						limit = Double.parseDouble(_childValue.get("limit").toString());
					}
					if (_childValue.containsKey("click")) {
						click = Double.parseDouble(_childValue.get("click").toString());
					}
					if (_childValue.get("block").toString().equals("true")) {
						_blocked();
					}
					if (_childValue.containsKey("firstname") && _childValue.containsKey("lastname")) {
						_drawer_textview_name.setText(_childValue.get("firstname").toString().trim().substring((int)(0), (int)(1)).concat(_childValue.get("lastname").toString().trim().substring((int)(0), (int)(1))).toUpperCase());
						username = _childValue.get("firstname").toString().concat(" ".concat(_childValue.get("lastname").toString()));
						if (!tme.getString("time_", "").equals("")) {
							linear12.setVisibility(View.VISIBLE);
							current_time = Double.parseDouble(tme.getString("time_", ""));
							clnd = Calendar.getInstance();
							tm_difference = clnd.getTimeInMillis() - current_time;
							if (tm_difference < 60000) {
								if ((tm_difference / 1000) < 2) {
									_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / 1000)).concat(" second ago")))));
								}
								else {
									_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / 1000)).concat(" seconds ago")))));
								}
							}
							else {
								if (tm_difference < (60 * 60000)) {
									if ((tm_difference / 60000) < 2) {
										_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / 60000)).concat(" minute ago")))));
									}
									else {
										_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / 60000)).concat(" minutes ago")))));
									}
								}
								else {
									if (tm_difference < (24 * (60 * 60000))) {
										if ((tm_difference / (60 * 60000)) < 2) {
											_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (60 * 60000))).concat(" hour ago")))));
										}
										else {
											_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (60 * 60000))).concat(" hours ago")))));
										}
									}
									else {
										if (tm_difference < (7 * (24 * (60 * 60000)))) {
											if ((tm_difference / (24 * (60 * 60000))) < 2) {
												_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (24 * (60 * 60000)))).concat(" day ago")))));
											}
											else {
												_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (24 * (60 * 60000)))).concat(" days ago")))));
											}
										}
										else {
											if (tm_difference < (4 * (7 * (24 * (60 * 60000))))) {
												if ((tm_difference / (7 * (24 * (60 * 60000)))) < 2) {
													_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (7 * (24 * (60 * 60000))))).concat(" week ago")))));
												}
												else {
													_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (7 * (24 * (60 * 60000))))).concat(" weeks ago")))));
												}
											}
											else {
												if (tm_difference < (12 * (4 * (7 * (24 * (60 * 60000)))))) {
													if ((tm_difference / (4 * (7 * (24 * (60 * 60000))))) < 2) {
														_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (4 * (7 * (24 * (60 * 60000)))))).concat(" month ago")))));
													}
													else {
														_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (4 * (7 * (24 * (60 * 60000)))))).concat(" months ago")))));
													}
												}
												else {
													if ((tm_difference / (12 * (4 * (7 * (24 * (60 * 60000)))))) < 2) {
														_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (12 * (4 * (7 * (24 * (60 * 60000))))))).concat(" year ago")))));
													}
													else {
														_MarqueeText(textview4, "Hello! ".concat(username.concat(", Welcome to CoinWise Earn. Your last login time ".concat(String.valueOf((long)(tm_difference / (12 * (4 * (7 * (24 * (60 * 60000))))))).concat(" years ago")))));
													}
												}
											}
										}
									}
								}
							}
						}
						else {
							linear12.setVisibility(View.GONE);
						}
					}
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {}
			
			@Override
			public void onCancelled(DatabaseError _param1) {}
		};
		users.addChildEventListener(_users_child_listener);
		
	/*	_sponsor_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("type").toString().equals("slider1")) {
					if (_childValue.containsKey("image")) {
						slider1 = _childValue.get("image").toString();
					}
					imageview3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							if (_childValue.containsKey("link1")) {
								go.putExtra("title", "");
								go.putExtra("link", _childValue.get("link1").toString());
								go.setClass(getApplicationContext(), WebActivity.class);
								startActivity(go);
							}
						}
					});
				}
				if (_childValue.get("type").toString().equals("slider2")) {
					if (_childValue.containsKey("image")) {
						slider2 = _childValue.get("image").toString();
					}
					imageview3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							if (_childValue.containsKey("link2")) {
								go.putExtra("title", "");
								go.putExtra("link", _childValue.get("link2").toString());
								go.setClass(getApplicationContext(), WebActivity.class);
								startActivity(go);
							}
						}
					});
				}
				if (_childValue.get("type").toString().equals("slider3")) {
					if (_childValue.containsKey("image")) {
						slider3 = _childValue.get("image").toString();
					}
					if (_childValue.containsKey("link3")) {
						imageview3.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								go.putExtra("title", "");
								go.putExtra("link", _childValue.get("link3").toString());
								go.setClass(getApplicationContext(), WebActivity.class);
								startActivity(go);
							}
						});
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("type").toString().equals("slider1")) {
					if (_childValue.containsKey("image")) {
						slider3 = _childValue.get("image").toString();
					}
					imageview3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							if (_childValue.containsKey("link1")) {
								go.putExtra("title", "");
								go.putExtra("link", _childValue.get("link1").toString());
								go.setClass(getApplicationContext(), WebActivity.class);
								startActivity(go);
							}
						}
					});
				}
				if (_childValue.get("type").toString().equals("slider2")) {
					if (_childValue.containsKey("image")) {
						slider3 = _childValue.get("image").toString();
					}
					imageview3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							if (_childValue.containsKey("link2")) {
								go.putExtra("title", "");
								go.putExtra("link", _childValue.get("link2").toString());
								go.setClass(getApplicationContext(), WebActivity.class);
								startActivity(go);
							}
						}
					});
				}
				if (_childValue.get("type").toString().equals("slider3")) {
					if (_childValue.containsKey("image")) {
						slider3 = _childValue.get("image").toString();
					}
					if (_childValue.containsKey("link3")) {
						imageview3.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								go.putExtra("title", "");
								go.putExtra("link", _childValue.get("link3").toString());
								go.setClass(getApplicationContext(), WebActivity.class);
								startActivity(go);
							}
						});
					}
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};*/
	//	sponsor.addChildEventListener(_sponsor_child_listener);

		control.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);
                if (_childValue.get("control").toString().equals("disable")) {
                    final AlertDialog dialog2 = new AlertDialog.Builder(HomeActivity.this).create();
                    View inflate = getLayoutInflater().inflate(R.layout.custom,null);
                    dialog2.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog2.setView(inflate);
                    TextView t1 = (TextView) inflate.findViewById(R.id.t1);

                    TextView t2 = (TextView) inflate.findViewById(R.id.t2);

                    LinearLayout b2 = (LinearLayout) inflate.findViewById(R.id.b2);

                    ImageView i1 = (ImageView) inflate.findViewById(R.id.i1);

                    LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);

                    TextView t3 = (TextView)
                            inflate.findViewById(R.id.t3);
                    t2.setTextColor(0xFF3F51B5);
                    i1.setImageResource(R.drawable.img);
                    t1.setText("Temporary Closed");
                    t2.setText("But, Will Be Back Soon");
                    t3.setText("Exit");
                    _rippleRoundStroke(bg, "#B7D4D8", "#000000", 15, 0, "#000000");
                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View _view) {
                            finishAffinity();
                        }
                    });
                    dialog2.setCancelable(false);
                    dialog2.show();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);
                if (_childValue.get("control").toString().equals("disable")) {
                    final AlertDialog dialog2 = new AlertDialog.Builder(HomeActivity.this).create();
                    View inflate = getLayoutInflater().inflate(R.layout.custom,null);
                    dialog2.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog2.setView(inflate);
                    TextView t1 = (TextView) inflate.findViewById(R.id.t1);

                    TextView t2 = (TextView) inflate.findViewById(R.id.t2);

                    LinearLayout b2 = (LinearLayout) inflate.findViewById(R.id.b2);

                    ImageView i1 = (ImageView) inflate.findViewById(R.id.i1);

                    LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);

                    TextView t3 = (TextView)
                            inflate.findViewById(R.id.t3);
                    t2.setTextColor(0xFF3F51B5);
                    i1.setImageResource(R.drawable.img);
                    t1.setText("Temporary Closed");
                    t2.setText("But, Will Be Back Soon");
                    t3.setText("Exit");
                    _rippleRoundStroke(bg, "#B7D4D8", "#000000", 15, 0, "#000000");
                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View _view) {
                            finishAffinity();
                        }
                    });
                    dialog2.setCancelable(false);
                    dialog2.show();
                }
            }

            @Override
            public void onChildMoved(DataSnapshot _param1, String _param2) {

            }

            @Override
            public void onChildRemoved(DataSnapshot _param1) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);

            }

            @Override
            public void onCancelled(DatabaseError _param1) {
                final int _errorCode = _param1.getCode();
                final String _errorMessage = _param1.getMessage();

            }
        });
		
		_claimed_time_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("timer")) {
						time_X = _childValue.get("timer").toString();
						last_Claimed = Double.parseDouble(_childValue.get("timer").toString());
						clnd = Calendar.getInstance();
						now_time = clnd.getTimeInMillis() - last_Claimed;
						if (((now_time / (60 * 60000)) > 24) || ((now_time / (60 * 60000)) == 24)) {
							textview6.setText("Claimed Bonus");
							textview6.setTextColor(0xFF000000);
						}
						else {
							textview6.setText("Wait 24 hours");
							textview6.setTextColor(0xFF9E9E9E);
						}
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("timer")) {
						time_X = _childValue.get("timer").toString();
						last_Claimed = Double.parseDouble(_childValue.get("timer").toString());
						clnd = Calendar.getInstance();
						now_time = clnd.getTimeInMillis() - last_Claimed;
						if (((now_time / (60 * 60000)) > 24) || ((now_time / (60 * 60000)) == 24)) {
							textview6.setText("Claimed Bonus");
							textview6.setTextColor(0xFF000000);
						}
						else {
							textview6.setText("Wait 24 hours");
							textview6.setTextColor(0xFF9E9E9E);
						}
					}
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		claimed_time.addChildEventListener(_claimed_time_child_listener);
		
		_history_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		history.addChildEventListener(_history_child_listener);
		
		_ad_time_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("timer")) {
						time = _childValue.get("timer").toString();
						last = Double.parseDouble(_childValue.get("timer").toString());
						c = Calendar.getInstance();
						now = c.getTimeInMillis() - last;
						if (((now / (60 * 60000)) > 24) || ((now / (60 * 60000)) == 24)) {
							textview7.setText("Watch Ads");
							textview7.setTextColor(0xFF000000);
							quick_quiz.setEnabled(true);
							m = new HashMap<>();
							m.put("click", "0");
							users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(m);
							m.clear();
						}
						else {
							quick_quiz.setEnabled(false);
							textview7.setText("Wait 24 hours");
							textview7.setTextColor(0xFF9E9E9E);
						}
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("timer")) {
						time = _childValue.get("timer").toString();
						last = Double.parseDouble(_childValue.get("timer").toString());
						c = Calendar.getInstance();
						now = c.getTimeInMillis() - last;
						if (((now / (60 * 60000)) > 24) || ((now / (60 * 60000)) == 24)) {
							textview7.setText("Watch Ads");
							textview7.setTextColor(0xFF000000);
							quick_quiz.setEnabled(true);
							m = new HashMap<>();
							m.put("click", "0");
							users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(m);
							m.clear();
						}
						else {
							quick_quiz.setEnabled(false);
							textview7.setText("Wait 24 hours");
							textview7.setTextColor(0xFF9E9E9E);
						}
					}
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		ad_time.addChildEventListener(_ad_time_child_listener);
		
		_drawer_home.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_profile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				profile.setClass(getApplicationContext(), ProfileActivity.class);
				startActivity(profile);
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_membership.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				member.setClass(getApplicationContext(), MembershipActivity.class);
				startActivity(member);
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_shareText("Hey! You want to make money from online? CoinWise Earn is best platform. I am using this. You can join with us. Click here:https://drive.google.com/file/d/1-zT1Cwa120imh1RrjrBvfk0RnGrxTlfQ/view?usp=drivesdk");
			}
		});
		
		_drawer_privacy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				p.putExtra("title", "Privacy Policy");
				p.putExtra("link", "https://google.com");
				p.setClass(getApplicationContext(), WebActivity.class);
				startActivity(p);
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_about.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				a.putExtra("title", "About Us");
				a.putExtra("link", "https://google.com");
				a.setClass(getApplicationContext(), WebActivity.class);
				startActivity(a);
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_report.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				p.setAction(Intent.ACTION_VIEW);
				p.setData(Uri.parse("mailto:coinwiseearn@gmail.com"));
				p.putExtra("subject", "Report or Suggestion | CoinWise Earn");
				startActivity(p);
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_guide.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				a.putExtra("link", "https://google.com");
				a.setClass(getApplicationContext(), WebActivity.class);
				startActivity(a);
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_exit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_ShowDialogTrue("Leave!", "Are you sure want to leave this app?", "No", "Yes");
			}
		});
		
		_drawer_imageview14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				p.putExtra("link", "https://google.com");
				p.setClass(getApplicationContext(), WebActivity.class);
				startActivity(p);
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_imageview15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				p.setAction(Intent.ACTION_VIEW);
				p.setData(Uri.parse("https://google.com"));
				startActivity(p);
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_imageview16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				p.setAction(Intent.ACTION_VIEW);
				p.setData(Uri.parse("https://youtube.com/"));
				startActivity(p);
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		_inter_ad_listener = new AdListener() {
			@Override
			public void onAdLoaded() {
				_loadingdialog(false, "");
				inter.show(HomeActivity.this);
			}


			
			@Override
			public void onAdOpened() {
				
			}
			
			@Override
			public void onAdClosed() {
				clnd = Calendar.getInstance();
				map = new HashMap<>();
				map.put("balance", String.valueOf((long)(Double.parseDouble(balance) + 5)));
				map.put("click", String.valueOf((long)(click + 1)));
				users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
				map.clear();
				key = history.push().getKey();
				map = new HashMap<>();
				map.put("subject", "watch ad");
				map.put("date", new SimpleDateFormat("dd-MM-yyyy").format(clnd.getTime()));
				map.put("amount", "5");
				map.put("key", key);
				map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				history.child(key).updateChildren(map);
				map.clear();
				_adView("+5 points", String.valueOf((long)(click + 1)));
			}
		};
	}
	
	@SuppressLint("MissingPermission")
	/*private void initializeLogic() {
		adview3.loadAd(new AdRequest.Builder().addTestDevice("708001022B2AEFB4CA5DB3785F35FD14")
		.build());

		_toolbar.setVisibility(View.GONE);
		vscroll2.setVerticalScrollBarEnabled(false);
		_loadingdialog(true, "Loading...");
		_NavStatusBarColor("#FFFFFF", "#FFFFFF");
		_DARK_ICONS();
		_draweritem();
		_ui();
		_Shadow(8, 20, "#FFFFFF", linear13);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/productsans_bold.ttf"), Typeface.BOLD);
		slide = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						count = count;
						count++;
						if (count == 1) {
							if (!slider1.equals("")) {
								Glide.with(getApplicationContext()).load(Uri.parse(slider1)).into(imageview3);
							}
						}
						if (count == 2) {
							if (!slider2.equals("")) {
								Glide.with(getApplicationContext()).load(Uri.parse(slider2)).into(imageview3);
							}
						}
						if (count == 3) {
							if (!slider3.equals("")) {
								Glide.with(getApplicationContext()).load(Uri.parse(slider3)).into(imageview3);
							}
						}
						if (count == 4) {
							count = 0;
						}
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(slide, (int)(0), (int)(3000));
	}*/
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);

	}
	
	@Override
	public void onStart() {
		super.onStart();
		_networkCheck();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if (_drawer.isDrawerOpen(GravityCompat.START)) {
			_drawer.closeDrawer(GravityCompat.START);
		} else {
			_ShowDialogTrue("Leave!", "Are you sure want to leave this app?", "No", "Yes");
		}
	}
	public void _NavStatusBarColor (final String _color1, final String _color2) {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
			Window w = this.getWindow();	w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);	w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(Color.parseColor("#" + _color1.replace("#", "")));	w.setNavigationBarColor(Color.parseColor("#" + _color2.replace("#", "")));
		}
	}
	
	
	public void _DARK_ICONS () {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
	}
	
	
	public void _networkCheck () {
		net.startRequestNetwork(RequestNetworkController.GET, "https://google.com", "a", _net_request_listener);
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
	

	
	
	public void _rippleRoundStroke (final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor("#FF757575")}), GG, null);
		_view.setBackground(RE);
		_view.setElevation(5);
	}
	
	
	public void _GradientDrawable (final View _view, final double _radius, final double _stroke, final double _shadow, final String _color, final String _borderColor, final boolean _ripple, final boolean _clickAnim, final double _animDuration) {
		if (_ripple) {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			gd.setStroke((int)_stroke,Color.parseColor(_borderColor));
			if (Build.VERSION.SDK_INT >= 21){
				_view.setElevation((int)_shadow);}
			android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#E0E0E0")});
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
	
	
	public void _CardView (final String _color, final double _radius, final double _shadow, final View _view) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color));
		gd.setCornerRadius((int)_radius);
		_view.setBackground(gd);
		
		try {
			if(Build.VERSION.SDK_INT >= 21) {
				_view.setElevation((int)_shadow);
			}
		} catch (Exception e) {}
	}
	
	
	public void _ShowDialogTrue (final String _title, final String _description, final String _positive, final String _negative) {
		final AlertDialog dialog = new AlertDialog.Builder(HomeActivity.this).create();
		
		View inflate = getLayoutInflater().inflate(R.layout.exit, null);
		 
		dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		
		dialog.setView(inflate);
		LinearLayout main = (LinearLayout)inflate.findViewById(R.id.linear1);
		
		TextView title = (TextView) inflate.findViewById(R.id.textview2);
		TextView description = (TextView) inflate.findViewById(R.id.textview1);
		TextView positive = (TextView) inflate.findViewById(R.id.textview3);
		TextView negative = (TextView) inflate.findViewById(R.id.textview4);
		ImageView img = (ImageView) inflate.findViewById(R.id.img);
		_CardView("#FF111A21", 10, 15, main);
		title.setText(_title);
		description.setText(_description);
		positive.setText(_positive);
		negative.setText(_negative);
		positive.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialog.dismiss();
			}
		});
		negative.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialog.dismiss();
				finishAffinity();
			}
		});
		img.setImageResource(R.drawable.exit);
		dialog.setCancelable(true);
		dialog.show();
	}
	
	

	
	
	public void _MarqueeText (final TextView _view, final String _text) {
		_view.setText(_text);
		_view.setSingleLine(true);
		_view.setEllipsize(TextUtils.TruncateAt.MARQUEE);
		_view.setSelected(true);
	}
	
	
	public void _radius_4 (final String _color1, final String _color2, final double _str, final double _n1, final double _n2, final double _n3, final double _n4, final View _view) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		
		gd.setColor(Color.parseColor(_color1));
		
		gd.setStroke((int)_str, Color.parseColor(_color2));
		
		gd.setCornerRadii(new float[]{(int)_n1,(int)_n1,(int)_n2,(int)_n2,(int)_n3,(int)_n3,(int)_n4,(int)_n4});
		
		_view.setBackground(gd);
		
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor("#FFFFFF")}), gd, null);
		_view.setBackground(RE);
	}
	
	
	public void _blocked () {
		final AlertDialog dialog1 = new AlertDialog.Builder(HomeActivity.this).create();
		View inflate = getLayoutInflater().inflate(R.layout.custom,null); 
		dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		dialog1.setView(inflate);
		TextView t1 = (TextView) inflate.findViewById(R.id.t1);
		
		TextView t2 = (TextView) inflate.findViewById(R.id.t2);
		
		LinearLayout b2 = (LinearLayout) inflate.findViewById(R.id.b2);
		
		ImageView i1 = (ImageView) inflate.findViewById(R.id.i1);
		
		LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
		
		TextView t3 = (TextView)
		inflate.findViewById(R.id.t3);
		t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/play.ttf"), Typeface.NORMAL);
		t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/raleway_regular.ttf"), Typeface.NORMAL);
		t3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/play.ttf"), Typeface.NORMAL);
		i1.setImageResource(R.drawable.party_2);
		t1.setText("Blocked !");
		t2.setText("Your account is blocked ");
		t3.setText("Contact Us");
		_rippleRoundStroke(bg, "#FFFFFF", "#000000", 15, 0, "#000000");
		_radius_4("#820062", "#FFFFFF", 0, 0, 0, 15, 15, b2);
		b2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("https://google.com"));
				startActivity(i);
			}
		});
		dialog1.setCancelable(false);
		dialog1.show();
	}
	
	
	public void _loadingdialog (final boolean _ifShow, final String _title) {
		if (_ifShow) {
			if (prog == null){
				prog = new ProgressDialog(this);
				prog.setMax(100);
				prog.setIndeterminate(true);
				prog.setCancelable(false);
				prog.setCanceledOnTouchOutside(false);
			}
			prog.setMessage(_title);
			prog.show();
			
		}
		else {
			if (prog != null){
				prog.dismiss();
			}
			
			
		}
	} private ProgressDialog prog; {
		
		
	}
	
	
	public void _notification (final String _Title, final String _Desc, final double _id, final boolean _sticky) {
		if (_sticky) {
			final Context context = getApplicationContext();
			
			
			NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			
			Intent intent = new Intent(this, HomeActivity.class); 
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP); 
			PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE  );
			androidx.core.app.NotificationCompat.Builder builder; 
			
			    int notificationId = (int) _id;
			    String channelId = "channel-01";
			    String channelName = "Channel Name";
			    int importance = NotificationManager.IMPORTANCE_HIGH;
			
			    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
				        NotificationChannel mChannel = new NotificationChannel(
				                channelId, channelName, importance);
				        notificationManager.createNotificationChannel(mChannel);
				    }
			
			   
			 androidx.core.app.NotificationCompat.Builder mBuilder = new androidx.core.app.NotificationCompat.Builder(context, channelId)
			            .setSmallIcon(R.drawable.more_logo)
			            .setContentTitle(_Title)
			            .setContentText(_Desc)
			            .setAutoCancel(false)
			            .setOngoing(true)
			            .setContentIntent(pendingIntent);
			    TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
			    stackBuilder.addNextIntent(intent);
			    PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
			            0,
			            PendingIntent.FLAG_UPDATE_CURRENT
			    );
			    mBuilder.setContentIntent(resultPendingIntent);
			
			    notificationManager.notify(notificationId, mBuilder.build());
			
			
		}
		else {
			final Context context = getApplicationContext();
			
			
			NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			
			Intent intent = new Intent(this, HomeActivity.class); 
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP); 
			PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
			androidx.core.app.NotificationCompat.Builder builder; 
			
			    int notificationId = (int) _id;
			    String channelId = "channel-01";
			    String channelName = "Channel Name";
			    int importance = NotificationManager.IMPORTANCE_HIGH;
			
			    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
				        NotificationChannel mChannel = new NotificationChannel(
				                channelId, channelName, importance);
				        notificationManager.createNotificationChannel(mChannel);
				    }
			
			  
			 androidx.core.app.NotificationCompat.Builder mBuilder = new androidx.core.app.NotificationCompat.Builder(context, channelId)
			            .setSmallIcon(R.drawable.more_logo)
			            .setContentTitle(_Title)
			            .setContentText(_Desc)
			            .setAutoCancel(true)
			            .setOngoing(false)
			            .setContentIntent(pendingIntent);
			    TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
			    stackBuilder.addNextIntent(intent);
			    PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
			            0,
			            PendingIntent.FLAG_UPDATE_CURRENT
			    );
			    mBuilder.setContentIntent(resultPendingIntent);
			
			    notificationManager.notify(notificationId, mBuilder.build());
			
		}
	}
	
	
	public void _congratulation (final String _point) {
		final AlertDialog dialog1 = new AlertDialog.Builder(HomeActivity.this).create();
		View inflate = getLayoutInflater().inflate(R.layout.custom10,null); 
		dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		dialog1.setView(inflate);
		TextView t1 = (TextView) inflate.findViewById(R.id.t1);
		
		TextView t2 = (TextView) inflate.findViewById(R.id.t2);
		
		TextView t3 = (TextView) inflate.findViewById(R.id.t3);
		
		LinearLayout b1 = (LinearLayout) inflate.findViewById(R.id.b1);
		
		LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
		
		LinearLayout bg2 = (LinearLayout) inflate.findViewById(R.id.bg2);
		
		ImageView i1 = (ImageView)
		inflate.findViewById(R.id.i1);
		t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
		t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
		t3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
		t3.setText(_point);
		_rippleRoundStroke(bg, "#FC716A", "#FC716A", 10, 0, "#000000");
		_radius_4("#FAFAFA", "#EEEEEE", 3, 0, 0, 10, 10, b1);
		_radius_4("#F06159", "#F06159", 0, 10, 10, 0, 0, bg2);
		dialog1.setCancelable(true);
		dialog1.show();
	}
	
	
	public void _shareText (final String _text) {
		Intent sendIntent = new Intent();
		
		sendIntent.setAction(Intent.ACTION_SEND);
		
		sendIntent.putExtra(Intent.EXTRA_TEXT, _text);
		
		sendIntent.setType("text/plain");
		
		Intent shareIntent = Intent.createChooser(sendIntent, null);
		
		startActivity(shareIntent);



	}
	
	
	public void _adView (final String _point, final String _click) {
		final AlertDialog dialog3 = new AlertDialog.Builder(HomeActivity.this).create();
		View inflate = getLayoutInflater().inflate(R.layout.cus_ad,null); 
		dialog3.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		dialog3.setView(inflate);
		TextView t1 = (TextView) inflate.findViewById(R.id.t1);
		
		TextView t2 = (TextView) inflate.findViewById(R.id.t2);
		
		TextView t3 = (TextView) inflate.findViewById(R.id.t3);
		
		TextView t4 = (TextView) inflate.findViewById(R.id.t4);
		
		TextView t5 = (TextView) inflate.findViewById(R.id.t5);
		
		TextView t6 = (TextView) inflate.findViewById(R.id.t6);
		
		LinearLayout b1 = (LinearLayout) inflate.findViewById(R.id.b1);
		
		LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
		
		LinearLayout bg2 = (LinearLayout) inflate.findViewById(R.id.bg2);
		t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
		t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
		t3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
		t4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
		t5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
		t6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
		t3.setText(_point);
		t5.setText(_click);
		_rippleRoundStroke(bg, "#FFFFFF", "#FC716A", 10, 0, "#000000");
		_radius_4("#FFFFFF", "#EEEEEE", 3, 0, 0, 10, 10, b1);
		_radius_4("#F6910F", "#F06159", 0, 10, 10, 0, 0, bg2);
		dialog3.setCancelable(true);
		dialog3.show();
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
