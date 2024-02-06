package com.example.kingearnuser.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.graphics.*;
import android.text.*;
import android.util.*;

import java.util.*;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;

import com.example.kingearnuser.R;
import com.example.kingearnuser.Utils.SketchwareUtil;
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
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import android.graphics.Typeface;


public class LoginActivity extends AppCompatActivity {
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> usermap = new HashMap<>();
	private double tm_difference = 0;
	private double current_time = 0;
	private String ref = "";
	
	private ArrayList<String> ls = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear22;
	private LinearLayout login_layout;
	private LinearLayout signup_layout;
	private TextView textview2;
	private EditText edittext1;
	private EditText edittext2;
	private Button button1;
	private TextView textview3;
	private TextView textview4;
	private LinearLayout linear2;
	private TextView textview6;
	private LinearLayout linear23;
	private EditText edittext3;
	private EditText edittext4;
	private EditText edittext7;
	private Button button2;
	private TextView textview8;
	private EditText edittext5;
	private EditText edittext6;
	
	private Intent i = new Intent();
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
	private Intent n = new Intent();
	private Calendar c = Calendar.getInstance();
	private SharedPreferences tme;
	private Calendar clnd = Calendar.getInstance();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.login);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		linear22 = (LinearLayout) findViewById(R.id.linear22);
		login_layout = (LinearLayout) findViewById(R.id.login_layout);
		signup_layout = (LinearLayout) findViewById(R.id.signup_layout);
		textview2 = (TextView) findViewById(R.id.textview2);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		edittext2 = (EditText) findViewById(R.id.edittext2);
		button1 = (Button) findViewById(R.id.button1);
		textview3 = (TextView) findViewById(R.id.textview3);
		textview4 = (TextView) findViewById(R.id.textview4);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		textview6 = (TextView) findViewById(R.id.textview6);
		linear23 = (LinearLayout) findViewById(R.id.linear23);
		edittext3 = (EditText) findViewById(R.id.edittext3);
		edittext4 = (EditText) findViewById(R.id.edittext4);
		edittext7 = (EditText) findViewById(R.id.edittext7);
		button2 = (Button) findViewById(R.id.button2);
		textview8 = (TextView) findViewById(R.id.textview8);
		edittext5 = (EditText) findViewById(R.id.edittext5);
		edittext6 = (EditText) findViewById(R.id.edittext6);
		auth = FirebaseAuth.getInstance();
		tme = getSharedPreferences("tme", Activity.MODE_PRIVATE);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext1.getText().toString().trim().equals("")) {
					((EditText)edittext1).setError("Enter Email Address");
				}
				else {
					if (edittext2.getText().toString().trim().equals("")) {
						((EditText)edittext2).setError("Enter Password");
					}
					else {
						auth.signInWithEmailAndPassword(edittext1.getText().toString().trim(), edittext2.getText().toString().trim()).addOnCompleteListener(LoginActivity.this, _auth_sign_in_listener);
						SketchwareUtil.hideKeyboard(getApplicationContext());
						_loadingdialog(true, "Please wait...");
					}
				}
			}
		});
		
		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext1.getText().toString().trim().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Enter Register Email Address");
				}
				else {
					auth.sendPasswordResetEmail(edittext1.getText().toString().trim()).addOnCompleteListener(_auth_reset_password_listener);
					SketchwareUtil.hideKeyboard(getApplicationContext());
					_loadingdialog(true, "Please wait...");
				}
			}
		});
		
		textview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				login_layout.setVisibility(View.GONE);
				signup_layout.setVisibility(View.VISIBLE);
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext5.getText().toString().trim().equals("")) {
					((EditText)edittext5).setError("Enter First Name");
				}
				else {
					if (edittext6.getText().toString().trim().equals("")) {
						((EditText)edittext6).setError("Enter Last Name");
					}
					else {
						if (edittext3.getText().toString().trim().equals("")) {
							((EditText)edittext3).setError("Enter Email Address");
						}
						else {
							if (edittext4.getText().toString().trim().equals("")) {
								((EditText)edittext4).setError("Enter Password");
								SketchwareUtil.showMessage(getApplicationContext(), "Enter Password");
							}
							else {
								auth.createUserWithEmailAndPassword(edittext3.getText().toString(), edittext4.getText().toString()).addOnCompleteListener(LoginActivity.this, _auth_create_user_listener);
								SketchwareUtil.hideKeyboard(getApplicationContext());
								_loadingdialog(true, "Please wait...");
							}
						}
					}
				}
			}
		});
		
		textview8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				login_layout.setVisibility(View.VISIBLE);
				signup_layout.setVisibility(View.GONE);
			}
		});
		
		edittext5.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() > 8) {
					edittext6.requestFocus();
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		edittext6.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() > 8) {
					edittext3.requestFocus();
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.containsKey("uid")) {
					if (_childValue.containsKey("referralcode")) {
						ref = _childValue.get("referralcode").toString();
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.containsKey("uid")) {
					if (_childValue.containsKey("referralcode")) {
						ref = _childValue.get("referralcode").toString();
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
				if (_success) {
					usermap = new HashMap<>();
					usermap.put("firstname", edittext5.getText().toString().trim());
					usermap.put("lastname", edittext6.getText().toString().trim());
					usermap.put("email", edittext3.getText().toString().trim());
					usermap.put("pass", edittext4.getText().toString().trim());
					usermap.put("referralcode", edittext5.getText().toString().trim().substring((int)(0), (int)(1)).concat(edittext6.getText().toString().trim().substring((int)(0), (int)(1))).toUpperCase().concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(99999))))));
					usermap.put("account", String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9)))).concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9)))).concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9)))).concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9)))).concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9)))))))).concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9)))).concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9)))).concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9)))).concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9)))))))).concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9))))).concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9)))).concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9)))).concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(0), (int)(9))))))));
					usermap.put("avatar", "null");
					usermap.put("Free", "true");
					usermap.put("Silver", "false");
					usermap.put("Gold", "false");
					usermap.put("Diamond", "false");
					usermap.put("join", new SimpleDateFormat("dd/MM/yyyy").format(c.getTime()));
					usermap.put("money", "0");
					usermap.put("balance", "0");
					usermap.put("block", "false");
					usermap.put("limit", "05");
					usermap.put("click", "0");
					usermap.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(usermap);
					usermap.clear();
					_loadingdialog(false, "");
					auth.getCurrentUser().sendEmailVerification() .addOnCompleteListener(new OnCompleteListener<Void>() {
						@Override
						public void onComplete(Task<Void> task) {
						} });
					edittext1.setText(edittext3.getText().toString().trim());
					edittext2.setText(edittext4.getText().toString().trim());
					final com.google.android.material.bottomsheet.BottomSheetDialog BottomsheetD = new com.google.android.material.bottomsheet.BottomSheetDialog(LoginActivity.this);
					View BottomsheetV;
					BottomsheetV = getLayoutInflater().inflate(R.layout.dialog,null );
					BottomsheetD.setContentView(BottomsheetV);
					final LinearLayout bg = (LinearLayout) BottomsheetV.findViewById(R.id.bg);
					final TextView title = (TextView) BottomsheetV.findViewById(R.id.title);
					final TextView message = (TextView) BottomsheetV.findViewById(R.id.message);
					
					final TextView yes = (TextView) BottomsheetV.findViewById(R.id.yes);
					_rippleRoundStroke(bg, "#FFFFFF", "#FFFFFF", 15, 0, "#FFFFFF");
					_rippleRoundStroke(yes, "#F47F3C", "#FFFFFF", 15, 0, "#FFFFFF");
					title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), 1);
					message.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), 0);
					yes.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/productsans_bold.ttf"), 0);
					BottomsheetD.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
					BottomsheetD.setCancelable(true);
					yes.setOnClickListener(new View.OnClickListener(){
							@Override
							public void onClick(View _view){
									BottomsheetD.dismiss();
							login_layout.setVisibility(View.VISIBLE);
							signup_layout.setVisibility(View.GONE);
						}
					});
					BottomsheetD.show();
				}
				else {
					_loadingdialog(false, "");
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
				}
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					if (auth.getCurrentUser().isEmailVerified()) {
						_loadingdialog(false, "");
						SketchwareUtil.showMessage(getApplicationContext(), "Successfully Login");
						_getTime();
						i.setClass(getApplicationContext(), HomeActivity.class);
						startActivity(i);
						finish();
					}
					else {
						edittext2.setText("");
						_loadingdialog(false, "");
						FirebaseAuth.getInstance().signOut();
						final com.google.android.material.bottomsheet.BottomSheetDialog BottomsheetD = new com.google.android.material.bottomsheet.BottomSheetDialog(LoginActivity.this);
						View BottomsheetV;
						BottomsheetV = getLayoutInflater().inflate(R.layout.dialog2,null );
						BottomsheetD.setContentView(BottomsheetV);
						final LinearLayout bg = (LinearLayout) BottomsheetV.findViewById(R.id.bg);
						final TextView title = (TextView) BottomsheetV.findViewById(R.id.title);
						final TextView message = (TextView) BottomsheetV.findViewById(R.id.message);
						
						final TextView yes = (TextView) BottomsheetV.findViewById(R.id.yes);
						_rippleRoundStroke(bg, "#FFFFFF", "#FFFFFF", 15, 0, "#FFFFFF");
						_rippleRoundStroke(yes, "#F47F3C", "#FFFFFF", 15, 0, "#FFFFFF");
						title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), 1);
						message.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), 0);
						yes.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/productsans_bold.ttf"), 0);
						BottomsheetD.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
						BottomsheetD.setCancelable(true);
						yes.setOnClickListener(new View.OnClickListener(){
								@Override
								public void onClick(View _view){
										BottomsheetD.dismiss();
							}
						});
						BottomsheetD.show();
					}
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
					_loadingdialog(false, "");
				}
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				if (_success) {
					_loadingdialog(false, "");
					final com.google.android.material.bottomsheet.BottomSheetDialog BottomsheetD = new com.google.android.material.bottomsheet.BottomSheetDialog(LoginActivity.this);
					View BottomsheetV;
					BottomsheetV = getLayoutInflater().inflate(R.layout.dialog3,null );
					BottomsheetD.setContentView(BottomsheetV);
					final LinearLayout bg = (LinearLayout) BottomsheetV.findViewById(R.id.bg);
					final TextView title = (TextView) BottomsheetV.findViewById(R.id.title);
					final TextView message = (TextView) BottomsheetV.findViewById(R.id.message);
					
					final TextView yes = (TextView) BottomsheetV.findViewById(R.id.yes);
					_rippleRoundStroke(bg, "#FFFFFF", "#FFFFFF", 15, 0, "#FFFFFF");
					_rippleRoundStroke(yes, "#F47F3C", "#FFFFFF", 15, 0, "#FFFFFF");
					title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), 1);
					message.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), 0);
					yes.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/productsans_bold.ttf"), 0);
					BottomsheetD.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
					BottomsheetD.setCancelable(true);
					yes.setOnClickListener(new View.OnClickListener(){
							@Override
							public void onClick(View _view){
									BottomsheetD.dismiss();
						}
					});
					BottomsheetD.show();
				}
				else {
					_loadingdialog(false, "");
					SketchwareUtil.showMessage(getApplicationContext(), "Something Went Wrong");
				}
			}
		};
	}
	
	private void initializeLogic() {
		vscroll1.setFillViewport(true);
		vscroll1.setVerticalScrollBarEnabled(false);
		edittext5.setFilters(new InputFilter[] {new InputFilter.LengthFilter(8)});
		edittext6.setFilters(new InputFilter[] {new InputFilter.LengthFilter(8)});
		signup_layout.setVisibility(View.GONE);
		edittext7.setVisibility(View.GONE);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), 1);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), 1);
		_SX_CornerRadius_card(button1, "#F47F3C", 12);
		_SX_CornerRadius_card(button2, "#F47F3C", 12);
		_SX_CornerRadius_card(edittext1, "#FFFFFF", 12);
		_SX_CornerRadius_card(edittext2, "#FFFFFF", 12);
		_SX_CornerRadius_card(edittext3, "#FFFFFF", 12);
		_SX_CornerRadius_card(edittext4, "#FFFFFF", 12);
		_SX_CornerRadius_card(edittext7, "#FFFFFF", 12);
		_SX_CornerRadius_card(edittext5, "#FFFFFF", 12);
		_SX_CornerRadius_card(edittext6, "#FFFFFF", 12);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		finishAffinity();
	}
	public void _gd (final View _view, final double _numb, final String _color) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color));
		gd.setCornerRadius((int)_numb);
		_view.setBackground(gd);
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
	
	
	public void _SX_CornerRadius_card (final View _view, final String _color, final double _value) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color));
		gd.setCornerRadius((int)_value);
		_view.setBackground(gd);
		
		if (Build.VERSION.SDK_INT >= 21){
			_view.setElevation(5);
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
	
	
	public void _createSnackBar (final String _message) {
		ViewGroup parentLayout = (ViewGroup) ((ViewGroup) this .findViewById(android.R.id.content)).getChildAt(0);
		   com.google.android.material.snackbar.Snackbar.make(parentLayout, _message, com.google.android.material.snackbar.Snackbar.LENGTH_LONG) 
		        .setAction("OK", new View.OnClickListener() {
			            @Override 
			            public void onClick(View view) {
				
				            } 
			        }).show();
	}
	
	
	public void _getTime () {
		clnd = Calendar.getInstance();
		tme.edit().putString("time_", String.valueOf((long)(clnd.getTimeInMillis()))).commit();
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
