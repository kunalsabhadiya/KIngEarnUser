package com.example.kingearnuser.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.util.*;
import android.animation.*;

import java.util.*;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.ScrollView;

import com.example.kingearnuser.R;
import com.example.kingearnuser.Utils.SketchwareUtil;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import java.util.Timer;
import java.util.TimerTask;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.graphics.Typeface;


public class MembershipActivity extends AppCompatActivity {
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String method = "";
	private String badge = "";
	private String amount = "";
	private HashMap<String, Object> m = new HashMap<>();
	private String key = "";
	private String f = "";
	private String l = "";
	private String s = "";
	private String g = "";
	private String d = "";
	
	private ArrayList<String> spin = new ArrayList<>();
	
	private ScrollView vscroll1;
	private AdView adview1;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear9;
	private LinearLayout linear14;
	private ImageView imageview1;
	private LinearLayout linear3;
	private ImageView imageview2;
	private TextView textview1;
	private LinearLayout linear5;
	private LinearLayout linear10;
	private LinearLayout linear12;
	private TextView textview2;
	private LinearLayout linear8;
	private TextView textview3;
	private TextView textview4;
	private TextView textview5;
	private LinearLayout linear11;
	private TextView textview6;
	private TextView textview7;
	private TextView textview8;
	private LinearLayout linear13;
	private TextView textview9;
	private TextView textview10;
	private Spinner spinner1;
	private EditText edittext1;
	private Button button1;
	
	private TimerTask slide;
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
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	private DatabaseReference history = _firebase.getReference("history");
	private ChildEventListener _history_child_listener;
	private Calendar c = Calendar.getInstance();
	private DatabaseReference membership = _firebase.getReference("membership");
	private ChildEventListener _membership_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.membership);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		adview1 = (AdView) findViewById(R.id.adview1);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		textview1 = (TextView) findViewById(R.id.textview1);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		textview2 = (TextView) findViewById(R.id.textview2);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		textview3 = (TextView) findViewById(R.id.textview3);
		textview4 = (TextView) findViewById(R.id.textview4);
		textview5 = (TextView) findViewById(R.id.textview5);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		textview6 = (TextView) findViewById(R.id.textview6);
		textview7 = (TextView) findViewById(R.id.textview7);
		textview8 = (TextView) findViewById(R.id.textview8);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		textview9 = (TextView) findViewById(R.id.textview9);
		textview10 = (TextView) findViewById(R.id.textview10);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		button1 = (Button) findViewById(R.id.button1);
		auth = FirebaseAuth.getInstance();
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		linear5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				badge = "GOLD";
				amount = g;
				_GradientDrawable(linear5, 20, 5, 5, "#FFFFFF", "#F47F3C", false, true, 300);
				_GradientDrawable(linear10, 20, 0, 5, "#FFFFFF", "#F47F3C", false, true, 300);
				_GradientDrawable(linear12, 20, 0, 5, "#FFFFFF", "#F47F3C", false, true, 300);
			}
		});
		
		linear10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				badge = "SILVER";
				amount = s;
				_GradientDrawable(linear5, 20, 0, 5, "#FFFFFF", "#F47F3C", false, true, 300);
				_GradientDrawable(linear10, 20, 5, 5, "#FFFFFF", "#F47F3C", false, true, 300);
				_GradientDrawable(linear12, 20, 0, 5, "#FFFFFF", "#F47F3C", false, true, 300);
			}
		});
		
		linear12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				badge = "DIAMOND";
				amount = d;
				_GradientDrawable(linear5, 20, 0, 5, "#FFFFFF", "#F47F3C", false, true, 300);
				_GradientDrawable(linear10, 20, 0, 5, "#FFFFFF", "#F47F3C", false, true, 300);
				_GradientDrawable(linear12, 20, 5, 5, "#FFFFFF", "#F47F3C", false, true, 300);
			}
		});
		
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 1) {
					method = "bkash";
				}
				if (_position == 2) {
					method = "nagad";
				}
				if (_position == 3) {
					method = "rocket";
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (method.equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Select Payment Method");
				}
				else {
					if (badge.equals("")) {
						SketchwareUtil.showMessage(getApplicationContext(), "Select Membership");
					}
					else {
						if (edittext1.getText().toString().equals("")) {
							SketchwareUtil.showMessage(getApplicationContext(), "Enter TrxID");
						}
						else {
							final AlertDialog dialog1 = new AlertDialog.Builder(MembershipActivity.this).create();
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
							t4.setText(amount);
							t7.setText(badge);
							t5.setText(method);
							t6.setText(edittext1.getText().toString());
							_rippleRoundStroke(bg, "#FFFFFF", "#000000", 15, 0, "#000000");
							_radius_4("#35BC9C", "#FFFFFF", 0, 0, 0, 0, 15, bt1);
							_radius_4("#FC716A", "#FFFFFF", 0, 0, 0, 15, 0, bt2);
							bt1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){ 
									key = history.push().getKey();
									m = new HashMap<>();
									m.put("name", f.concat(" ".concat(l)));
									m.put("amount", amount);
									m.put("trxid", edittext1.getText().toString());
									m.put("method", method);
									m.put("date", new SimpleDateFormat("dd-MM-yyyy").format(c.getTime()));
									m.put("key", key);
									m.put("subject", "subscription");
									m.put("seen", "false");
									m.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
									m.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
									history.child(key).updateChildren(m);
									m.clear();
									_confirm();
									edittext1.setText("");
									method = "";
									badge = "";
									amount = "";
									_GradientDrawable(linear5, 20, 0, 5, "#FFFFFF", "#F47F3C", false, true, 300);
									_GradientDrawable(linear10, 20, 0, 5, "#FFFFFF", "#F47F3C", false, true, 300);
									_GradientDrawable(linear12, 20, 0, 5, "#FFFFFF", "#F47F3C", false, true, 300);
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
					}
				}
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
		
		_membership_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("membership").toString().equals("Silver")) {
					if (_childValue.containsKey("fee")) {
						textview7.setText("USD. ".concat(_childValue.get("fee").toString()));
						s = _childValue.get("fee").toString();
					}
					if (_childValue.containsKey("ad")) {
						textview6.setText(_childValue.get("ad").toString().concat(" ads /per day"));
					}
				}
				if (_childValue.get("membership").toString().equals("Gold")) {
					if (_childValue.containsKey("fee")) {
						textview4.setText("USD. ".concat(_childValue.get("fee").toString()));
						g = _childValue.get("fee").toString();
					}
					if (_childValue.containsKey("ad")) {
						textview3.setText(_childValue.get("ad").toString().concat(" ads /per day"));
					}
				}
				if (_childValue.get("membership").toString().equals("Diamond")) {
					if (_childValue.containsKey("fee")) {
						textview10.setText("USD. ".concat(_childValue.get("fee").toString()));
						d = _childValue.get("fee").toString();
					}
					if (_childValue.containsKey("ad")) {
						textview9.setText(_childValue.get("ad").toString().concat(" ads /per day"));
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("membership").toString().equals("Silver")) {
					if (_childValue.containsKey("fee")) {
						textview7.setText("USD. ".concat(_childValue.get("fee").toString()));
						s = _childValue.get("fee").toString();
					}
					if (_childValue.containsKey("ad")) {
						textview6.setText(_childValue.get("ad").toString().concat(" ads /per day"));
					}
				}
				if (_childValue.get("membership").toString().equals("Gold")) {
					if (_childValue.containsKey("fee")) {
						textview4.setText("USD. ".concat(_childValue.get("fee").toString()));
						g = _childValue.get("fee").toString();
					}
					if (_childValue.containsKey("ad")) {
						textview3.setText(_childValue.get("ad").toString().concat(" ads /per day"));
					}
				}
				if (_childValue.get("membership").toString().equals("Diamond")) {
					if (_childValue.containsKey("fee")) {
						textview10.setText("USD. ".concat(_childValue.get("fee").toString()));
						d = _childValue.get("fee").toString();
					}
					if (_childValue.containsKey("ad")) {
						textview9.setText(_childValue.get("ad").toString().concat(" ads /per day"));
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
		membership.addChildEventListener(_membership_child_listener);
		
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
	}
	
	@SuppressLint("MissingPermission")
	private void initializeLogic() {
		vscroll1.setVerticalScrollBarEnabled(false);
		adview1.loadAd(new AdRequest.Builder().addTestDevice("708001022B2AEFB4CA5DB3785F35FD14")
		.build());
		spin.add("Select Payment Method");
		spin.add("Bkash/বিকাশ");
		spin.add("Nagad/নগদ");
		spin.add("Rocket/রকেট");
		spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, spin));
		((ArrayAdapter)spinner1.getAdapter()).notifyDataSetChanged();
		imageview2.setVisibility(View.INVISIBLE);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/productsans_bold.ttf"), Typeface.BOLD);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
		textview8.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
		textview9.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
		textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
		textview10.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
		_SX_CornerRadius_card(button1, "#F47F3C", 12);
		_SX_CornerRadius_card(edittext1, "#FFFFFF", 12);
		_GradientDrawable(linear5, 20, 0, 5, "#FFFFFF", "#F47F3C", false, true, 300);
		_GradientDrawable(linear10, 20, 0, 5, "#FFFFFF", "#F47F3C", false, true, 300);
		_GradientDrawable(linear12, 20, 0, 5, "#FFFFFF", "#F47F3C", false, true, 300);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		switch (_requestCode) {
			
			default:
			break;
		}
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
	
	
	public void _radius_4 (final String _color1, final String _color2, final double _str, final double _n1, final double _n2, final double _n3, final double _n4, final View _view) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		
		gd.setColor(Color.parseColor(_color1));
		
		gd.setStroke((int)_str, Color.parseColor(_color2));
		
		gd.setCornerRadii(new float[]{(int)_n1,(int)_n1,(int)_n2,(int)_n2,(int)_n3,(int)_n3,(int)_n4,(int)_n4});
		
		_view.setBackground(gd);
		
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor("#FFFFFF")}), gd, null);
		_view.setBackground(RE);
	}
	
	
	public void _Shadow (final double _sadw, final double _cru, final String _wc, final View _widgets) {
		android.graphics.drawable.GradientDrawable wd = new android.graphics.drawable.GradientDrawable();
		wd.setColor(Color.parseColor(_wc));
		wd.setCornerRadius((int)_cru);
		_widgets.setElevation((int)_sadw);
		_widgets.setBackground(wd);
	}
	
	
	public void _confirm () {
		final AlertDialog dialog2 = new AlertDialog.Builder(MembershipActivity.this).create();
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
		t.setText("Request Successful");
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
	
	
	public void _radius (final String _color1, final String _color2, final double _str, final double _n1, final double _n2, final double _n3, final double _n4, final View _view) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		
		gd.setColor(Color.parseColor(_color1));
		
		gd.setStroke((int)_str, Color.parseColor(_color2));
		
		gd.setCornerRadii(new float[]{(int)_n1,(int)_n1,(int)_n2,(int)_n2,(int)_n3,(int)_n3,(int)_n4,(int)_n4});
		
		_view.setBackground(gd);
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
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
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
