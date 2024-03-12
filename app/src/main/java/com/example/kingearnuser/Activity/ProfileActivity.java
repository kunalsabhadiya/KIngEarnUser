package com.example.kingearnuser.Activity;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.kingearnuser.R;
import com.example.kingearnuser.Utils.FileUtil;
import com.example.kingearnuser.Utils.SketchwareUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.storage.FirebaseStorage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileActivity extends AppCompatActivity {
	public final int REQ_CD_PICK = 101;
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

	private String path = "";
	private String fname = "";
	private String lname = "";
	private HashMap<String, Object> m = new HashMap<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private LinearLayout linear10;
	private LinearLayout linear9;
	private LinearLayout linear8;
	private LinearLayout linear14;
	private LinearLayout linear16;
	private LinearLayout linear19;
	private LinearLayout linear18;
	private LinearLayout ad_linear;
	private ImageView imageview1;
	private LinearLayout linear3;
	private ImageView imageview2;
	private TextView textview1;
	private ShimmerFrameLayout shim_avatar;
	private CircleImageView circleimageview1;
	private TextView textview16;
	private LinearLayout linear5;
	private TextView textview2;
	private ShimmerFrameLayout shim_name;
	private ShimmerFrameLayout shim_email;
	private TextView textview3;
	private TextView textview12;
	private ImageView imageview3;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private TextView textview13;
	private TextView textview15;
	private TextView textview10;
	private TextView textview11;
	private ShimmerFrameLayout shim_m;
	private TextView textview8;
	private TextView textview9;
	private ShimmerFrameLayout shim_rc;
	private TextView textview17;
	private TextView textview18;
	private ShimmerFrameLayout shim_join;
	private TextView textview19;
	private TextView textview20;
	private ShimmerFrameLayout shim_bal;
	private TextView textview21;
	private TextView textview22;
	private ShimmerFrameLayout shim_mon;
	private AdView adview1;

	private TimerTask t;
	private ObjectAnimator anim = new ObjectAnimator();
	private DatabaseReference users = _firebase.getReference("users");

	private Intent pick = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent start = new Intent();
	private TimerTask shimtimer;
	private Intent w = new Intent();
	private ObjectAnimator oa = new ObjectAnimator();
	private Calendar ic = Calendar.getInstance();
	private Intent l = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.profile);
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
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		linear19 = (LinearLayout) findViewById(R.id.linear19);
		linear18 = (LinearLayout) findViewById(R.id.linear18);
		ad_linear = (LinearLayout) findViewById(R.id.ad_linear);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		textview1 = (TextView) findViewById(R.id.textview1);
		shim_avatar = (ShimmerFrameLayout) findViewById(R.id.shim_avatar);
		circleimageview1 = (CircleImageView) findViewById(R.id.circleimageview1);
		textview16 = (TextView) findViewById(R.id.textview16);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		textview2 = (TextView) findViewById(R.id.textview2);
		shim_name = (ShimmerFrameLayout) findViewById(R.id.shim_name);
		shim_email = (ShimmerFrameLayout) findViewById(R.id.shim_email);
		textview3 = (TextView) findViewById(R.id.textview3);
		textview12 = (TextView) findViewById(R.id.textview12);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		textview13 = (TextView) findViewById(R.id.textview13);
		textview15 = (TextView) findViewById(R.id.textview15);
		textview10 = (TextView) findViewById(R.id.textview10);
		textview11 = (TextView) findViewById(R.id.textview11);
		shim_m = (ShimmerFrameLayout) findViewById(R.id.shim_m);
		textview8 = (TextView) findViewById(R.id.textview8);
		textview9 = (TextView) findViewById(R.id.textview9);
		shim_rc = (ShimmerFrameLayout) findViewById(R.id.shim_rc);
		textview17 = (TextView) findViewById(R.id.textview17);
		textview18 = (TextView) findViewById(R.id.textview18);
		shim_join = (ShimmerFrameLayout) findViewById(R.id.shim_join);
		textview19 = (TextView) findViewById(R.id.textview19);
		textview20 = (TextView) findViewById(R.id.textview20);
		shim_bal = (ShimmerFrameLayout) findViewById(R.id.shim_bal);
		textview21 = (TextView) findViewById(R.id.textview21);
		textview22 = (TextView) findViewById(R.id.textview22);
		shim_mon = (ShimmerFrameLayout) findViewById(R.id.shim_mon);
		adview1 = (AdView) findViewById(R.id.adview1);
		pick.setType("image/*");
		pick.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(ProfileActivity.this);
				
				View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.custom_spinner,null );
				bottomSheetDialog.setContentView(bottomSheetView);
				
				bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
				TextView t1 = (TextView) bottomSheetView.findViewById(R.id.t1);
				
				TextView t2 = (TextView) bottomSheetView.findViewById(R.id.t2);
				
				LinearLayout bg1 = (LinearLayout) bottomSheetView.findViewById(R.id.bg1);
				
				LinearLayout bg2 = (LinearLayout) bottomSheetView.findViewById(R.id.bg2);
				_rippleRoundStroke(bg2, "#FBAA37", "#EEEEEE", 15, 0, "#EEEEEE");
				_rippleRoundStroke(bg1, "#FBAA37", "#40FFFFFF", 15, 0, "#000000");
				t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
				t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
				bg1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						bottomSheetDialog.dismiss();
						w.setClass(getApplicationContext(), WalletActivity.class);
						startActivity(w);
					}
				});
				bg2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						bottomSheetDialog.dismiss();
						_ShowDialogTrue("Logout!", "Are you sure want to logout this id?", "No", "Yes");
					}
				});
				bottomSheetDialog.setCancelable(true);
				bottomSheetDialog.show();
			}
		});
		
		textview16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(pick, REQ_CD_PICK);
			}
		});
		
		textview15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				start.setClass(getApplicationContext(), MembershipActivity.class);
				startActivity(start);
			}
		});
		
		users.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.containsKey("firstname")) {
						fname = _childValue.get("firstname").toString();
					}
					if (_childValue.containsKey("lastname")) {
						lname = _childValue.get("lastname").toString();
					}
					textview2.setText(fname.concat(" ".concat(lname)));
					shimtimer = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									shim_name.setVisibility(View.GONE);
									textview2.setVisibility(View.VISIBLE);
								}
							});
						}
					};
					_timer.schedule(shimtimer, (int)(1000));
					if (_childValue.containsKey("email")) {
						textview3.setText(_childValue.get("email").toString());
						shimtimer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										shim_email.setVisibility(View.GONE);
										textview3.setVisibility(View.VISIBLE);
									}
								});
							}
						};
						_timer.schedule(shimtimer, (int)(1000));
					}
					if (_childValue.containsKey("referralcode")) {
						textview9.setText(_childValue.get("referralcode").toString());
						shimtimer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										textview9.setVisibility(View.VISIBLE);
										shim_rc.setVisibility(View.GONE);
									}
								});
							}
						};
						_timer.schedule(shimtimer, (int)(1000));
						textview9.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								((ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", _childValue.get("referralcode").toString()));
								SketchwareUtil.showMessage(getApplicationContext(), "Refer code copied!");
							}
						});
					}
					if (_childValue.get("Free").toString().equals("true")) {
						textview11.setText("Free");
						shimtimer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										textview11.setVisibility(View.VISIBLE);
										shim_m.setVisibility(View.GONE);
									}
								});
							}
						};
						_timer.schedule(shimtimer, (int)(1000));
					}
					else {
						if (_childValue.get("Silver").toString().equals("true")) {
							textview11.setText("Silver");
							shimtimer = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											textview11.setVisibility(View.VISIBLE);
											shim_m.setVisibility(View.GONE);
										}
									});
								}
							};
							_timer.schedule(shimtimer, (int)(1000));
						}
						else {
							if (_childValue.get("Gold").toString().equals("true")) {
								textview11.setText("Gold");
								shimtimer = new TimerTask() {
									@Override
									public void run() {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												textview11.setVisibility(View.VISIBLE);
												shim_m.setVisibility(View.GONE);
											}
										});
									}
								};
								_timer.schedule(shimtimer, (int)(1000));
							}
							else {
								if (_childValue.get("Diamond").toString().equals("true")) {
									textview11.setText("Diamond");
									shimtimer = new TimerTask() {
										@Override
										public void run() {
											runOnUiThread(new Runnable() {
												@Override
												public void run() {
													textview11.setVisibility(View.VISIBLE);
													shim_m.setVisibility(View.GONE);
												}
											});
										}
									};
									_timer.schedule(shimtimer, (int)(1000));
								}
							}
						}
					}
					if (_childValue.get("Free").toString().equals("true")) {
						linear10.setVisibility(View.VISIBLE);
					}
					else {
						linear10.setVisibility(View.GONE);
					}
					if (_childValue.get("avatar").toString().equals("null")) {
						textview16.setVisibility(View.VISIBLE);
						shimtimer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										shim_avatar.setVisibility(View.GONE);
										circleimageview1.setVisibility(View.VISIBLE);
									}
								});
							}
						};
						_timer.schedule(shimtimer, (int)(1000));
					}
					else {
						textview16.setVisibility(View.GONE);
						Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("avatar").toString())).into(circleimageview1);
						shimtimer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										circleimageview1.setVisibility(View.VISIBLE);
										shim_avatar.setVisibility(View.GONE);
									}
								});
							}
						};
						_timer.schedule(shimtimer, (int)(1000));
					}
					if (_childValue.containsKey("join")) {
						textview18.setText(_childValue.get("join").toString());
						shimtimer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										shim_join.setVisibility(View.GONE);
										textview18.setVisibility(View.VISIBLE);
									}
								});
							}
						};
						_timer.schedule(shimtimer, (int)(1000));
					}
					if (_childValue.containsKey("balance")) {
						textview20.setText(new DecimalFormat("#,##,##,###.##").format(Double.parseDouble(_childValue.get("balance").toString())));
						shimtimer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										shim_bal.setVisibility(View.GONE);
										textview20.setVisibility(View.VISIBLE);
									}
								});
							}
						};
						_timer.schedule(shimtimer, (int)(1000));
					}
					if (_childValue.containsKey("money")) {
						textview22.setText(new DecimalFormat("#,##,##,###.##").format(Double.parseDouble(_childValue.get("money").toString())));
						shimtimer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										shim_mon.setVisibility(View.GONE);
										textview22.setVisibility(View.VISIBLE);
									}
								});
							}
						};
						_timer.schedule(shimtimer, (int)(1000));
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
						fname = _childValue.get("firstname").toString();
					}
					if (_childValue.containsKey("lastname")) {
						lname = _childValue.get("lastname").toString();
					}
					textview2.setText(fname.concat(" ".concat(lname)));
					shimtimer = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									shim_name.setVisibility(View.GONE);
									textview2.setVisibility(View.VISIBLE);
								}
							});
						}
					};
					_timer.schedule(shimtimer, (int)(1000));
					if (_childValue.containsKey("email")) {
						textview3.setText(_childValue.get("email").toString());
						shimtimer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										shim_email.setVisibility(View.GONE);
										textview3.setVisibility(View.VISIBLE);
									}
								});
							}
						};
						_timer.schedule(shimtimer, (int)(1000));
					}
					if (_childValue.containsKey("referralcode")) {
						textview9.setText(_childValue.get("referralcode").toString());
						shimtimer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										textview9.setVisibility(View.VISIBLE);
										shim_rc.setVisibility(View.GONE);
									}
								});
							}
						};
						_timer.schedule(shimtimer, (int)(1000));
						textview9.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", _childValue.get("referralcode").toString()));
								SketchwareUtil.showMessage(getApplicationContext(), "Refer code copied!");
							}
						});
					}
					if (_childValue.get("Free").toString().equals("true")) {
						textview11.setText("Free");
						shimtimer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										textview11.setVisibility(View.VISIBLE);
										shim_m.setVisibility(View.GONE);
									}
								});
							}
						};
						_timer.schedule(shimtimer, (int)(1000));
					}
					else {
						if (_childValue.get("Silver").toString().equals("true")) {
							textview11.setText("Silver");
							shimtimer = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											textview11.setVisibility(View.VISIBLE);
											shim_m.setVisibility(View.GONE);
										}
									});
								}
							};
							_timer.schedule(shimtimer, (int)(1000));
						}
						else {
							if (_childValue.get("Gold").toString().equals("true")) {
								textview11.setText("Gold");
								shimtimer = new TimerTask() {
									@Override
									public void run() {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												textview11.setVisibility(View.VISIBLE);
												shim_m.setVisibility(View.GONE);
											}
										});
									}
								};
								_timer.schedule(shimtimer, (int)(1000));
							}
							else {
								if (_childValue.get("Diamond").toString().equals("true")) {
									textview11.setText("Diamond");
									shimtimer = new TimerTask() {
										@Override
										public void run() {
											runOnUiThread(new Runnable() {
												@Override
												public void run() {
													textview11.setVisibility(View.VISIBLE);
													shim_m.setVisibility(View.GONE);
												}
											});
										}
									};
									_timer.schedule(shimtimer, (int)(1000));
								}
							}
						}
					}
					if (_childValue.get("Free").toString().equals("true")) {
						linear10.setVisibility(View.VISIBLE);
					}
					else {
						linear10.setVisibility(View.GONE);
					}
					if (_childValue.get("avatar").toString().equals("null")) {
						textview16.setVisibility(View.VISIBLE);
						shimtimer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										shim_avatar.setVisibility(View.GONE);
										circleimageview1.setVisibility(View.VISIBLE);
									}
								});
							}
						};
						_timer.schedule(shimtimer, (int)(1000));
					}
					else {
						textview16.setVisibility(View.GONE);
						Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("avatar").toString())).into(circleimageview1);
						shimtimer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										circleimageview1.setVisibility(View.VISIBLE);
										shim_avatar.setVisibility(View.GONE);
									}
								});
							}
						};
						_timer.schedule(shimtimer, (int)(1000));
					}
					if (_childValue.containsKey("join")) {
						textview18.setText(_childValue.get("join").toString());
						shimtimer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										shim_join.setVisibility(View.GONE);
										textview18.setVisibility(View.VISIBLE);
									}
								});
							}
						};
						_timer.schedule(shimtimer, (int)(1000));
					}
					if (_childValue.containsKey("balance")) {
						textview20.setText(_childValue.get("balance").toString());
						shimtimer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										shim_bal.setVisibility(View.GONE);
										textview20.setVisibility(View.VISIBLE);
									}
								});
							}
						};
						_timer.schedule(shimtimer, (int)(1000));
					}
					if (_childValue.containsKey("money")) {
						textview22.setText(_childValue.get("money").toString());
						shimtimer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										shim_mon.setVisibility(View.GONE);
										textview22.setVisibility(View.VISIBLE);
									}
								});
							}
						};
						_timer.schedule(shimtimer, (int)(1000));
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
		});

	}
	
	private void initializeLogic() {
		//adview1.loadAd(new AdRequest.Builder().addTestDevice("708001022B2AEFB4CA5DB3785F35FD14")
		//.build());
		_ui();
		_animation();
		shim_avatar.setVisibility(View.VISIBLE);
		linear10.setVisibility(View.GONE);
		circleimageview1.setVisibility(View.GONE);
		textview16.setVisibility(View.GONE);
		textview2.setVisibility(View.GONE);
		textview3.setVisibility(View.GONE);
		textview9.setVisibility(View.GONE);
		textview11.setVisibility(View.GONE);
		textview18.setVisibility(View.GONE);
		textview20.setVisibility(View.GONE);
		textview22.setVisibility(View.GONE);
		ShimmerFrameLayout container = (ShimmerFrameLayout) findViewById(R.id.shim_avatar); container.startShimmer();
		shim_avatar.setBackgroundColor(0xFFE8E8E8);
		ShimmerFrameLayout container1 = (ShimmerFrameLayout) findViewById(R.id.shim_name); container1.startShimmer();
		shim_name.setBackgroundColor(0xFFE8E8E8);
		ShimmerFrameLayout container2 = (ShimmerFrameLayout) findViewById(R.id.shim_email); container2.startShimmer();
		shim_email.setBackgroundColor(0xFFE8E8E8);
		ShimmerFrameLayout container3 = (ShimmerFrameLayout) findViewById(R.id.shim_rc); container3.startShimmer();
		shim_rc.setBackgroundColor(0xFFE8E8E8);
		ShimmerFrameLayout container4 = (ShimmerFrameLayout) findViewById(R.id.shim_m); container4.startShimmer();
		shim_m.setBackgroundColor(0xFFE8E8E8);
		ShimmerFrameLayout container5 = (ShimmerFrameLayout) findViewById(R.id.shim_join); container5.startShimmer();
		shim_join.setBackgroundColor(0xFFE8E8E8);
		ShimmerFrameLayout container6 = (ShimmerFrameLayout) findViewById(R.id.shim_bal); container6.startShimmer();
		shim_bal.setBackgroundColor(0xFFE8E8E8);
		ShimmerFrameLayout container7 = (ShimmerFrameLayout) findViewById(R.id.shim_mon); container7.startShimmer();
		shim_mon.setBackgroundColor(0xFFE8E8E8);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);

			if (_requestCode == REQ_CD_PICK && _resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					} else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				path = _filePath.get((int) (0));
				start.setClass(getApplicationContext(), CropimageActivity.class);
				start.putExtra("url", path);
				start.putExtra("location", FileUtil.getPublicDir(Environment.DIRECTORY_PICTURES).concat("/".concat(String.valueOf((long) (SketchwareUtil.getRandom((int) (4), (int) (999999)))).concat(".png"))));
				start.putExtra("ratio", "set");
				start.putExtra("ratio1", "1");
				start.putExtra("ratio2", "1");
				startActivity(start);
			}

	}
	
	public void _Shadow (final double _sadw, final double _cru, final String _wc, final View _widgets) {
		android.graphics.drawable.GradientDrawable wd = new android.graphics.drawable.GradientDrawable();
		wd.setColor(Color.parseColor(_wc));
		wd.setCornerRadius((int)_cru);
		_widgets.setElevation((int)_sadw);
		_widgets.setBackground(wd);
	}
	
	
	public void _gradientview (final View _view) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.RIGHT_LEFT, new int[]{
			0xFFFBAA37,
			0xFFF47F3C
		});
		gd.setCornerRadius(20f);
		_view.setElevation(6f);
		_view.setBackgroundDrawable(gd);
	}
	
	
	public void _ui () {
		vscroll1.setVerticalScrollBarEnabled(false);
		_Shadow(5, 20, "#EEEEEE", linear10);
		_Shadow(5, 0, "#FFFFFF", linear8);
		_Shadow(5, 0, "#FFFFFF", linear9);
		_Shadow(5, 0, "#FFFFFF", linear14);
		_Shadow(5, 0, "#FFFFFF", linear16);
		_Shadow(5, 0, "#FFFFFF", linear19);
		_gradientview(linear10);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/productsans_bold.ttf"), Typeface.BOLD);
		textview8.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/calculator.ttf"), Typeface.BOLD);
		textview17.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/calculator.ttf"), Typeface.BOLD);
		textview19.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/calculator.ttf"), Typeface.BOLD);
		textview21.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/calculator.ttf"), Typeface.BOLD);
		textview10.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/calculator.ttf"), Typeface.BOLD);
		textview12.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/productsans_bold.ttf"), Typeface.BOLD);
		textview15.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/productsans_bold.ttf"), Typeface.BOLD);
		textview13.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
		textview16.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/googlesansbold.ttf"), Typeface.BOLD);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
		textview18.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
		textview11.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
		textview20.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
		textview22.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans.ttf"), Typeface.NORMAL);
	}
	
	
	public void _animation () {
		t = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						anim.setTarget(linear12);
						anim.setPropertyName("translationX");
						anim.setFloatValues((float)(5));
						anim.setFloatValues((float)(0), (float)(5));
						anim.setDuration((int)(500));
						anim.setRepeatMode(ValueAnimator.REVERSE);
						anim.setRepeatCount((int)(1));
						anim.start();
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(t, (int)(2000), (int)(500));
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
		final AlertDialog dialog = new AlertDialog.Builder(ProfileActivity.this).create();
		
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
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/semi.ttf"), Typeface.NORMAL);
		description.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/regular.ttf"), Typeface.NORMAL);
		positive.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/semi.ttf"), Typeface.NORMAL);
		negative.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/semi.ttf"), Typeface.NORMAL);
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
				FirebaseAuth.getInstance().signOut();
				l.setClass(getApplicationContext(), LoginActivity.class);
				startActivity(l);
				finish();
			}
		});
		img.setImageResource(R.drawable.shutdown);
		dialog.setCancelable(true);
		dialog.show();
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
