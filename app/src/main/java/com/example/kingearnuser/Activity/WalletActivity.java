package com.example.kingearnuser.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.util.*;

import java.util.*;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;

import com.example.kingearnuser.R;
import com.example.kingearnuser.Utils.FileUtil;
import com.example.kingearnuser.Utils.SketchwareUtil;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdListener;
import android.view.View;
import java.text.DecimalFormat;
import android.graphics.Typeface;

import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class WalletActivity extends AppCompatActivity {
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String f = "";
	private String l = "";
	private String payment = "";
	private HashMap<String, Object> m = new HashMap<>();
	private String key = "";
	private HashMap<String, Object> m2 = new HashMap<>();
	private double coinsd = 0;
	private double len = 0;
	private String path = "";
	private String folder_path = "";
	private String output_path = "";
	private String image_name = "";
	private String account = "";
	
	private ArrayList<HashMap<String, Object>> co = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear6;
	private LinearLayout linear13;
	private LinearLayout linear12;
	private TextView textview6;
	private EditText edittext_amount;
	private LinearLayout linear_acc;
	private LinearLayout linear11;
	private ImageView imageview1;
	private LinearLayout linear5;
	private TextView textview7;
	private TextView textview1;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private TextView textview4;
	private TextView textview3;
	private TextView textview2;
	private TextView textview8;
	private LinearLayout linear_bkash;
	private LinearLayout linear_nagad;
	private LinearLayout linear_rocket;
	private LinearLayout linear_topup;
	private ImageView bkash;
	private ImageView nagad;
	private ImageView rocket;
	private ImageView topup;
	private EditText edittext_number;
	private Button button1;
	private AdView adview1;
	
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	private FirebaseAuth auth;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private AlertDialog.Builder dialog1;
	private Calendar c = Calendar.getInstance();
	private DatabaseReference history = _firebase.getReference("history");
	private ChildEventListener _history_child_listener;
	private InterstitialAd Interstitial_ad;
	private AdListener _Interstitial_ad_ad_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.wallet);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		textview6 = (TextView) findViewById(R.id.textview6);
		edittext_amount = (EditText) findViewById(R.id.edittext_amount);
		linear_acc = (LinearLayout) findViewById(R.id.linear_acc);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		textview7 = (TextView) findViewById(R.id.textview7);
		textview1 = (TextView) findViewById(R.id.textview1);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		textview4 = (TextView) findViewById(R.id.textview4);
		textview3 = (TextView) findViewById(R.id.textview3);
		textview2 = (TextView) findViewById(R.id.textview2);
		textview8 = (TextView) findViewById(R.id.textview8);
		linear_bkash = (LinearLayout) findViewById(R.id.linear_bkash);
		linear_nagad = (LinearLayout) findViewById(R.id.linear_nagad);
		linear_rocket = (LinearLayout) findViewById(R.id.linear_rocket);
		linear_topup = (LinearLayout) findViewById(R.id.linear_topup);
		bkash = (ImageView) findViewById(R.id.bkash);
		nagad = (ImageView) findViewById(R.id.nagad);
		rocket = (ImageView) findViewById(R.id.rocket);
		topup = (ImageView) findViewById(R.id.topup);
		edittext_number = (EditText) findViewById(R.id.edittext_number);
		button1 = (Button) findViewById(R.id.button1);
		adview1 = (AdView) findViewById(R.id.adview1);
		auth = FirebaseAuth.getInstance();
		dialog1 = new AlertDialog.Builder(this);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		linear8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
				folder_path = FileUtil.getExternalStorageDir().concat("/Pictures/KingEarn/");
				image_name = "EM".concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(4), (int)(999999)))).concat(".png"));
				_saveView(linear8, folder_path, folder_path.concat(image_name));
			}
		});
		
		linear_bkash.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				payment = "Bkash";
				_Shadow(5, 40, "#BDBDBD", linear_bkash);
				_Shadow(5, 40, "#FFFFFF", linear_nagad);
				_Shadow(5, 40, "#FFFFFF", linear_rocket);
				_Shadow(5, 40, "#FFFFFF", linear_topup);
			}
		});
		
		linear_nagad.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				payment = "Nagad";
				_Shadow(5, 40, "#BDBDBD", linear_nagad);
				_Shadow(5, 40, "#FFFFFF", linear_bkash);
				_Shadow(5, 40, "#FFFFFF", linear_rocket);
				_Shadow(5, 40, "#FFFFFF", linear_topup);
			}
		});
		
		linear_rocket.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				payment = "Rocket";
				_Shadow(5, 40, "#BDBDBD", linear_rocket);
				_Shadow(5, 40, "#FFFFFF", linear_nagad);
				_Shadow(5, 40, "#FFFFFF", linear_bkash);
				_Shadow(5, 40, "#FFFFFF", linear_topup);
			}
		});
		
		linear_topup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				payment = "Mobile Recharge";
				_Shadow(5, 40, "#BDBDBD", linear_topup);
				_Shadow(5, 40, "#FFFFFF", linear_nagad);
				_Shadow(5, 40, "#FFFFFF", linear_rocket);
				_Shadow(5, 40, "#FFFFFF", linear_bkash);
			}
		});
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("firstname")) {
						f = _childValue.get("firstname").toString();
					}
					if (_childValue.containsKey("lastname")) {
						l = _childValue.get("lastname").toString();
					}
					if (_childValue.containsKey("account")) {
						account = _childValue.get("account").toString();
						textview3.setText(_childValue.get("account").toString().substring((int)(0), (int)(4)).concat(" ".concat(_childValue.get("account").toString().substring((int)(4), (int)(8)).concat(" ".concat(_childValue.get("account").toString().substring((int)(8), (int)(13)))))));
					}
					textview4.setText(f.concat(" ".concat(l)));
					if (_childValue.containsKey("balance")) {
						textview2.setText("৳ ".concat(new DecimalFormat("#,##,##,###.##").format(Double.parseDouble(_childValue.get("money").toString()))));
						textview7.setText("💎 ".concat(_show(Double.parseDouble(_childValue.get("balance").toString()))));
						if (textview7.getText().toString().length() > 6) {
							textview7.setText(textview7.getText().toString().substring((int)(0), (int)(3)).concat(".."));
						}
						textview7.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								dialog1.setTitle("Convert from Diamond to Money");
								dialog1.setMessage("💎 ".concat(new DecimalFormat("#,##,##,###.##").format(Double.parseDouble(_childValue.get("balance").toString()))).concat(" = ".concat("💰 ".concat(new DecimalFormat("#,##,##,###.##").format(Double.parseDouble(_childValue.get("balance").toString()) / 10)))));
								dialog1.setPositiveButton("Later", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										
									}
								});
								if (Double.parseDouble(_childValue.get("balance").toString()) > 0) {
									dialog1.setNeutralButton("Convert", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface _dialog, int _which) {
											m = new HashMap<>();
											m.put("money", new DecimalFormat("0.00").format(Double.parseDouble(_childValue.get("money").toString()) + (Double.parseDouble(_childValue.get("balance").toString()) / 10)));
											m.put("balance", "0");
											users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(m);
											m.clear();
										}
									});
								}
								dialog1.create().show();
							}
						});
					}
					button1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							if (!payment.equals("") && (!edittext_amount.getText().toString().equals("") && !edittext_number.getText().toString().equals(""))) {
								if (!(Double.parseDouble(edittext_amount.getText().toString()) < 50)) {
									if (!(Double.parseDouble(_childValue.get("money").toString()) < Double.parseDouble(edittext_amount.getText().toString())) && !(Double.parseDouble(_childValue.get("money").toString()) == Double.parseDouble(edittext_amount.getText().toString()))) {
										final AlertDialog dialog1 = new AlertDialog.Builder(WalletActivity.this).create();
										View inflate = getLayoutInflater().inflate(R.layout.cus2,null); 
										dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
										dialog1.setView(inflate);
										TextView t1 = (TextView) inflate.findViewById(R.id.t1);
										
										TextView t2 = (TextView) inflate.findViewById(R.id.t2);
										
										TextView t3 = (TextView) inflate.findViewById(R.id.t3);
										
										TextView t4 = (TextView) inflate.findViewById(R.id.t4);
										
										TextView t5 = (TextView) inflate.findViewById(R.id.t5);
										
										TextView t6 = (TextView) inflate.findViewById(R.id.t6);
										
										TextView t7 = (TextView) inflate.findViewById(R.id.t7);
										
										LinearLayout bt1 = (LinearLayout) inflate.findViewById(R.id.bt1);
										
										LinearLayout bt2 = (LinearLayout) inflate.findViewById(R.id.bt2);
										
										LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
										t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/productsans_bold.ttf"), Typeface.BOLD);
										t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/productsans_bold.ttf"), Typeface.BOLD);
										t3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/productsans_bold.ttf"), Typeface.BOLD);
										t4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
										t5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
										t6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
										t7.setVisibility(View.GONE);
										t4.setText("৳ ".concat(new DecimalFormat("#,##,##,###.##").format(Double.parseDouble(edittext_amount.getText().toString()))));
										t5.setText(payment);
										t6.setText(edittext_number.getText().toString());
										_rippleRoundStroke(bg, "#FFFFFF", "#000000", 15, 0, "#000000");
										_radius_4("#35BC9C", "#FFFFFF", 0, 0, 0, 0, 15, bt1);
										_radius_4("#FC716A", "#FFFFFF", 0, 0, 0, 15, 0, bt2);
										bt1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){ 
												key = history.push().getKey();
												m = new HashMap<>();
												m.put("name", f.concat(" ".concat(l)));
												m.put("amount", edittext_amount.getText().toString());
												m.put("acc", edittext_number.getText().toString());
												m.put("method", payment);
												m.put("date", new SimpleDateFormat("dd-MM-yyyy").format(c.getTime()));
												m.put("key", key);
												m.put("subject", "withdraw");
												m.put("status", "Pending");
												m.put("seen", "false");
												m.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
												m.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
												history.child(key).updateChildren(m);
												m2.clear();
												m2 = new HashMap<>();
												m2.put("money", new DecimalFormat("0.00").format(Double.parseDouble(_childValue.get("money").toString()) - Double.parseDouble(edittext_amount.getText().toString())).replace("-", ""));
												users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(m2);
												m2.clear();
												users.addListenerForSingleValueEvent(new ValueEventListener() {
													@Override
													public void onDataChange(DataSnapshot _dataSnapshot) {
														co = new ArrayList<>();
														try {
															GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
															for (DataSnapshot _data : _dataSnapshot.getChildren()) {
																HashMap<String, Object> _map = _data.getValue(_ind);
																co.add(_map);
															}
														}
														catch (Exception _e) {
															_e.printStackTrace();
														}
														coinsd = co.size() - 1;
														len = co.size();
														for(int _repeat193 = 0; _repeat193 < (int)(len); _repeat193++) {
															if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(co.get((int)coinsd).get("uid").toString())) {
																textview2.setText("৳ ".concat(new DecimalFormat("#,##,##,###.##").format(Double.parseDouble(co.get((int)coinsd).get("money").toString()))));
															}
															else {
																co.remove((int)(coinsd));
															}
															coinsd--;
														}
													}
													@Override
													public void onCancelled(DatabaseError _databaseError) {
													}
												});
												_confirm();
												edittext_amount.setText("");
												edittext_number.setText("");
												payment = "";
												_Shadow(5, 40, "#FFFFFF", linear_bkash);
												_Shadow(5, 40, "#FFFFFF", linear_nagad);
												_Shadow(5, 40, "#FFFFFF", linear_rocket);
												_Shadow(5, 40, "#FFFFFF", linear_topup);
												dialog1.dismiss();
											}
										});
										bt2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){ 
												dialog1.dismiss();
											}
										});
										dialog1.setCancelable(false);
										dialog1.show();
									}
									else {
										SketchwareUtil.showMessage(getApplicationContext(), "Not enough balance");
									}
								}
								else {
									SketchwareUtil.showMessage(getApplicationContext(), "Minimum withdraw 50 taka");
								}
							}
							else {
								SketchwareUtil.showMessage(getApplicationContext(), "Something wrong!\nCheck all requirements you fill up.");
							}
						}
					});
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("firstname")) {
						f = _childValue.get("firstname").toString();
					}
					if (_childValue.containsKey("lastname")) {
						l = _childValue.get("lastname").toString();
					}
					if (_childValue.containsKey("account")) {
						account = _childValue.get("account").toString();
						textview3.setText(_childValue.get("account").toString().substring((int)(0), (int)(4)).concat("  ".concat(_childValue.get("account").toString().substring((int)(4), (int)(8)).concat("  ".concat(_childValue.get("account").toString().substring((int)(8), (int)(13)))))));
					}
					textview4.setText(f.concat(" ".concat(l)));
					if (_childValue.containsKey("balance")) {
						textview2.setText("৳ ".concat(new DecimalFormat("#,##,##,###.##").format(Double.parseDouble(_childValue.get("money").toString()))));
						textview7.setText("💎 ".concat(_show(Double.parseDouble(_childValue.get("balance").toString()))));
						if (textview7.getText().toString().length() > 6) {
							textview7.setText(textview7.getText().toString().substring((int)(0), (int)(3)).concat(".."));
						}
						textview7.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								dialog1.setTitle("Convert from Diamond to Money");
								dialog1.setMessage("💎 ".concat(new DecimalFormat("#,##,##,###.##").format(Double.parseDouble(_childValue.get("balance").toString()))).concat(" = ".concat("💰 ".concat(new DecimalFormat("#,##,##,###.##").format(Double.parseDouble(_childValue.get("balance").toString()) / 10)))));
								dialog1.setPositiveButton("Later", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										
									}
								});
								if (Double.parseDouble(_childValue.get("balance").toString()) > 0) {
									dialog1.setNeutralButton("Convert", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface _dialog, int _which) {
											m = new HashMap<>();
											m.put("money", new DecimalFormat("0.00").format(Double.parseDouble(_childValue.get("money").toString()) + (Double.parseDouble(_childValue.get("balance").toString()) / 10)));
											m.put("balance", "0");
											users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(m);
											m.clear();
										}
									});
								}
								dialog1.create().show();
							}
						});
					}
					button1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							if (!payment.equals("") && (!edittext_amount.getText().toString().equals("") && !edittext_number.getText().toString().equals(""))) {
								if (!(Double.parseDouble(edittext_amount.getText().toString()) < 50)) {
									if (!(Double.parseDouble(_childValue.get("money").toString()) < Double.parseDouble(edittext_amount.getText().toString())) && !(Double.parseDouble(_childValue.get("money").toString()) == Double.parseDouble(edittext_amount.getText().toString()))) {
										final AlertDialog dialog1 = new AlertDialog.Builder(WalletActivity.this).create();
										View inflate = getLayoutInflater().inflate(R.layout.cus2,null); 
										dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
										dialog1.setView(inflate);
										TextView t1 = (TextView) inflate.findViewById(R.id.t1);
										
										TextView t2 = (TextView) inflate.findViewById(R.id.t2);
										
										TextView t3 = (TextView) inflate.findViewById(R.id.t3);
										
										TextView t4 = (TextView) inflate.findViewById(R.id.t4);
										
										TextView t5 = (TextView) inflate.findViewById(R.id.t5);
										
										TextView t6 = (TextView) inflate.findViewById(R.id.t6);
										
										LinearLayout bt1 = (LinearLayout) inflate.findViewById(R.id.bt1);
										
										LinearLayout bt2 = (LinearLayout) inflate.findViewById(R.id.bt2);
										
										LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
										t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/productsans_bold.ttf"), Typeface.BOLD);
										t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/productsans_bold.ttf"), Typeface.BOLD);
										t3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/productsans_bold.ttf"), Typeface.BOLD);
										t4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
										t5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
										t6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
										t4.setText("৳ ".concat(new DecimalFormat("#,##,##,###.##").format(Double.parseDouble(edittext_amount.getText().toString()))));
										t5.setText(payment);
										t6.setText(edittext_number.getText().toString());
										_rippleRoundStroke(bg, "#FFFFFF", "#000000", 15, 0, "#000000");
										_radius_4("#35BC9C", "#FFFFFF", 0, 0, 0, 0, 15, bt1);
										_radius_4("#FC716A", "#FFFFFF", 0, 0, 0, 15, 0, bt2);
										bt1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){ 
												key = history.push().getKey();
												m = new HashMap<>();
												m.put("name", f.concat(" ".concat(l)));
												m.put("amount", edittext_amount.getText().toString());
												m.put("acc", edittext_number.getText().toString());
												m.put("method", payment);
												m.put("date", new SimpleDateFormat("dd-MM-yyyy").format(c.getTime()));
												m.put("key", key);
												m.put("subject", "withdraw");
												m.put("status", "Pending");
												m.put("seen", "false");
												m.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
												m.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
												history.child(key).updateChildren(m);
												m2.clear();
												m2 = new HashMap<>();
												m2.put("money", new DecimalFormat("0.00").format(Double.parseDouble(_childValue.get("money").toString()) - Double.parseDouble(edittext_amount.getText().toString())).replace("-", ""));
												users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(m2);
												m2.clear();
												users.addListenerForSingleValueEvent(new ValueEventListener() {
													@Override
													public void onDataChange(DataSnapshot _dataSnapshot) {
														co = new ArrayList<>();
														try {
															GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
															for (DataSnapshot _data : _dataSnapshot.getChildren()) {
																HashMap<String, Object> _map = _data.getValue(_ind);
																co.add(_map);
															}
														}
														catch (Exception _e) {
															_e.printStackTrace();
														}
														coinsd = co.size() - 1;
														len = co.size();
														for(int _repeat790 = 0; _repeat790 < (int)(len); _repeat790++) {
															if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(co.get((int)coinsd).get("uid").toString())) {
																textview2.setText("৳ ".concat(new DecimalFormat("#,##,##,###.##").format(Double.parseDouble(co.get((int)coinsd).get("money").toString()))));
															}
															else {
																co.remove((int)(coinsd));
															}
															coinsd--;
														}
													}
													@Override
													public void onCancelled(DatabaseError _databaseError) {
													}
												});
												_confirm();
												edittext_amount.setText("");
												edittext_number.setText("");
												payment = "";
												_Shadow(5, 40, "#FFFFFF", linear_bkash);
												_Shadow(5, 40, "#FFFFFF", linear_nagad);
												_Shadow(5, 40, "#FFFFFF", linear_rocket);
												_Shadow(5, 40, "#FFFFFF", linear_topup);
												dialog1.dismiss();
											}
										});
										bt2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){ 
												dialog1.dismiss();
											}
										});
										dialog1.setCancelable(false);
										dialog1.show();
									}
									else {
										SketchwareUtil.showMessage(getApplicationContext(), "Not enough balance");
									}
								}
								else {
									SketchwareUtil.showMessage(getApplicationContext(), "Minimum withdraw 50 taka");
								}
							}
							else {
								SketchwareUtil.showMessage(getApplicationContext(), "Something wrong!\nCheck all requirements you fill up.");
							}
						}
					});
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
		users.addChildEventListener(_users_child_listener);
		
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
		
		auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
		
		_Interstitial_ad_ad_listener = new AdListener() {
			@Override
			public void onAdLoaded() {
				Interstitial_ad.show();
			}
			
			@Override
			public void onAdFailedToLoad(int _param1) {
				final int _errorCode = _param1;

			}
			
			@Override
			public void onAdOpened() {
				
			}
			
			@Override
			public void onAdClosed() {
				
			}
		};
	}
	
	@SuppressLint("MissingPermission")
	private void initializeLogic() {
		adview1.loadAd(new AdRequest.Builder().addTestDevice("708001022B2AEFB4CA5DB3785F35FD14")
		.build());
		_ui();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@SuppressLint("MissingPermission")
	@Override
	public void onStart() {
		super.onStart();
		Interstitial_ad = new InterstitialAd(getApplicationContext());
		Interstitial_ad.setAdListener(_Interstitial_ad_ad_listener);
		Interstitial_ad.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
		Interstitial_ad.loadAd(new AdRequest.Builder().addTestDevice("708001022B2AEFB4CA5DB3785F35FD14")
		.build());
	}
	public void _ui () {
		vscroll1.setVerticalScrollBarEnabled(false);
		_SX_CornerRadius_card(edittext_amount, "#FFFFFF", 12);
		_SX_CornerRadius_card(edittext_number, "#FFFFFF", 12);
		_SX_CornerRadius_card(button1, "#F47F3C", 12);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/productsans_bold.ttf"), Typeface.BOLD);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/calculator.ttf"), Typeface.BOLD);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/creditcard.ttf"), Typeface.NORMAL);
		textview8.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
		textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
		_Shadow(5, 40, "#FFFFFF", linear_bkash);
		_Shadow(5, 40, "#FFFFFF", linear_nagad);
		_Shadow(5, 40, "#FFFFFF", linear_rocket);
		_Shadow(5, 40, "#FFFFFF", linear_topup);
	}
	
	
	public void _Shadow (final double _sadw, final double _cru, final String _wc, final View _widgets) {
		android.graphics.drawable.GradientDrawable wd = new android.graphics.drawable.GradientDrawable();
		wd.setColor(Color.parseColor(_wc));
		wd.setCornerRadius((int)_cru);
		_widgets.setElevation((int)_sadw);
		_widgets.setBackground(wd);
	}
	
	
	public void _SX_CornerRadius_card (final View _view, final String _color, final double _value) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color));
		gd.setCornerRadius((int)_value);
		_view.setBackground(gd);
		
		if (Build.VERSION.SDK_INT >= 21){
			_view.setElevation(5);
		}
	}
	
	
	public void _confirm () {
		final AlertDialog dialog2 = new AlertDialog.Builder(WalletActivity.this).create();
		View inflate = getLayoutInflater().inflate(R.layout.custom5,null); 
		dialog2.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		dialog2.setView(inflate);
		TextView t = (TextView) inflate.findViewById(R.id.t);
		
		TextView tt = (TextView) inflate.findViewById(R.id.tt);
		
		LinearLayout bt = (LinearLayout) inflate.findViewById(R.id.bt);
		
		ImageView i1 = (ImageView) inflate.findViewById(R.id.i1);
		
		LinearLayout back = (LinearLayout) inflate.findViewById(R.id.back);
		
		LinearLayout bg1 = (LinearLayout) inflate.findViewById(R.id.bg1);
		
		LinearLayout bg2 = (LinearLayout) inflate.findViewById(R.id.bg2);
		t.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/productsans_bold.ttf"), Typeface.BOLD);
		tt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
		i1.setImageResource(R.drawable.check);
		_rippleRoundStroke(back, "#FFFFFF", "#000000", 15, 0, "#000000");
		_rippleRoundStroke(bt, "#00000000", "#FFFFFF", 100, 1, "#9E9E9E");
		_radius("#35C28D", "#35C28D", 0, 15, 15, 0, 0, bg1);
		bt.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){ 
				dialog2.dismiss();
			}
		});
		dialog2.setCancelable(true);
		dialog2.show();
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
	
	
	public void _saveView (final View _view, final String _folderPath, final String _outputPath) {
		output_path = _outputPath;
		if (!FileUtil.isExistFile(_folderPath)) {
			FileUtil.makeDir(_folderPath);
		}
		Bitmap returnedBitmap = Bitmap.createBitmap(_view.getWidth(), _view.getHeight(),Bitmap.Config.ARGB_8888);
		
		Canvas canvas = new Canvas(returnedBitmap);
		android.graphics.drawable.Drawable bgDrawable =_view.getBackground();
		if (bgDrawable!=null) {
			bgDrawable.draw(canvas);
		} else {
			canvas.drawColor(Color.TRANSPARENT);
		}
		_view.draw(canvas);
		
		java.io.File pictureFile = new java.io.File(output_path);
		if (pictureFile == null) {
			showMessage("Error creating media file, check storage permissions: ");
			return; }
		try {
			java.io.FileOutputStream fos = new java.io.FileOutputStream(pictureFile); returnedBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.close();
			showMessage("Image Saved in " + output_path);
		} catch (java.io.FileNotFoundException e) {
			showMessage("File not found: " + e.getMessage()); } catch (java.io.IOException e) {
			showMessage("Error accessing file: " + e.getMessage());
		}
	}
	
	
	public void _radius (final String _color1, final String _color2, final double _str, final double _n1, final double _n2, final double _n3, final double _n4, final View _view) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		
		gd.setColor(Color.parseColor(_color1));
		
		gd.setStroke((int)_str, Color.parseColor(_color2));
		
		gd.setCornerRadii(new float[]{(int)_n1,(int)_n1,(int)_n2,(int)_n2,(int)_n3,(int)_n3,(int)_n4,(int)_n4});
		
		_view.setBackground(gd);
	}
	
	
	public String _show (final double _value) {
		String format = "";
		double n = 0;
		String f = ""; 
		ArrayList<String> list = new ArrayList<>(); 
		list.add(""); 
		list.add("K"); 
		list.add("M"); 
		list.add("B"); 
		list.add("T"); 
		n = (String.valueOf((long)(_value)).length() - 1) / 3; 
		if (n > 4) { 
			 n = 4; 
		} 
		f = "###,###.##".concat(list.get((int)(n))); 
		f = new DecimalFormat(f).format(_value / Math.pow(1000, n)); 
		return f;
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
