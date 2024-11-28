package h.mahdi.alarm;


import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by mahdi.h on 20/09/2017.
 */

public class CWM_Setting extends AppCompatActivity  implements Observer {
    MyFunctions fu;
    private FlowingDrawer mDrawer;
    SwitchCompat Receive_switch,Set_password;
    boolean Receive_switch_Value,Set_password_Value;
    Button Activate,Pick_audio,Set_number;
    int Volume_seekBar_value_I;
    CheckBox Audio_loop,Activity_notification;
    NotificationManager mNotificationManager;
    DBHelper mydb;
    public Handler mHandler;
    public boolean continue_or_stop;
    boolean isFirstRun;
    int Used_times, App_payment;
    boolean Audio_loop_Value,Activity_notification_Value;
    Toolbar toolbar;
    String timer_mode= "on";
    Uri Audio_Location_U;
    TextView t_number;
    ImageButton Delete_audio;
    String TAG="CWM_Setting",number;
    Context context = getApplication();
    String Phone_from_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cwm_setting);
        ObservableObject.getInstance().addObserver(this);
        final SharedPreferences shared = getSharedPreferences("Prefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor = shared.edit();
//        isFirstRun = shared.getBoolean("FIRSTRUN_CC", true);
    //    if (isFirstRun) {
      //  }
      //  editor.putBoolean("FIRSTRUN_CC", false);
      //  editor.commit();
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        fu=new MyFunctions();
        mydb = new DBHelper(this);
        //mydb.start();
        update_information();
        mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Receive_switch = (SwitchCompat) findViewById(R.id.receive_switch);
      //  Set_password = (SwitchCompat) findViewById(R.id.set_password_switch);
        t_number=(TextView) findViewById(R.id.number);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionbar.setTitle(R.string.title_activity_cwm_setting);

        Receive_switch.setChecked(Receive_switch_Value);
     //   Set_password.setChecked(Set_password_Value);
        if(!(Phone_from_number.equals("")))
        t_number.setText(Phone_from_number);
        FragmentManager fm = getSupportFragmentManager();
        MenuListFragment mMenuFragment = (MenuListFragment) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuListFragment();
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }

        if(isFirstRun)
            help();

        Button change =(Button) findViewById(R.id.Change);
                change.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {

                        Context context = getApplicationContext();
                        LinearLayout layout = new LinearLayout(context);
                        layout.setOrientation(LinearLayout.VERTICAL);
                        final EditText titleBox = new EditText(context);
                        titleBox.setHint("09140111111");
                        titleBox.setTextColor(getResources().getColor(R.color.black));
                        titleBox.setText(Phone_from_number);
                        titleBox.setHintTextColor(getResources().getColor(R.color.blue_grey));
                        titleBox.setInputType(InputType.TYPE_CLASS_NUMBER);
                        layout.addView(titleBox);
                        AlertDialog alertDialog = new AlertDialog.Builder(CWM_Setting.this).create();
                        alertDialog.setMessage(getString(R.string.EnteـrnumberـtoـSMS));
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.Cancel), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,  getString(R.string.Save), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mydb.updateSwitchs("my_values", "CWM_phone_from", titleBox.getText() + "");
                                update_information();
                                if (Phone_from_number.equals("")) {
                                    t_number.setText(R.string.No_number_entered);
                                } else {
                                    Log.e("text", titleBox.getText() + "");
                                    t_number.setText(Phone_from_number);
                                }
                            }
                        });
                        alertDialog.setView(layout);
                        alertDialog.show();
                    }
                });

        Receive_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mydb.updateSwitchs("switches", "CWM_receiveNumber", "1");
                } else {
                    mydb.updateSwitchs("switches", "CWM_receiveNumber", "0");
                }
            }
        });

    /*    Set_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mydb.updateSwitchs("switches", "CWM_password", "1");
                } else {
                    mydb.updateSwitchs("switches", "CWM_password", "0");
                }
            }
        });
*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
     //   MenuInflater inflater = getMenuInflater();
     //   inflater.inflate(R.menu.menu_main, menu);
     //  if(isFirstRun)
     //       help();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mDrawer.toggleMenu();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isMenuVisible()) {
            mDrawer.closeMenu();
        } else {
            startActivity(new Intent(CWM_Setting.this, ControlWithSMS.class));
            finish();

        }
    }



    @Override
    protected void onStop() {
        super.onStop();
        timer_mode= "off";
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }


    public void update_information() {
        Cursor S1 = mydb.getData("my_values", "CWM_phone_from");S1.moveToFirst();
        Cursor S2 = mydb.getData("switches", "CWM_password");S2.moveToFirst();
        Cursor S3 = mydb.getData("switches", "CWM_receiveNumber");S3.moveToFirst();
        Phone_from_number = S1.getString(S1.getColumnIndex("value"));
        Set_password_Value = fu.getBoolean(S2.getString(S2.getColumnIndex("value")));
        Receive_switch_Value = fu.getBoolean(S3.getString(S3.getColumnIndex("value")));
        S1.close();



    }

    void help(){

        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.toolbar), getString(R.string.unplugged_of_charger), "")
                        .cancelable(false).targetCircleColor(R.color.green).textColor(R.color.White).outerCircleColor(R.color.green).drawShadow(true).tintTarget(false),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);
                        help2();
                    }

                    @Override
                    public void onOuterCircleClick(TapTargetView view) {
                        super.onOuterCircleClick(view);
                        super.onTargetClick(view);
                        help2();
                    }
                });}
    void help2(){

        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.alarm_switch), getString(R.string.Start_a_test), getString(R.string.Enable_the_siren_first)).cancelable(false),new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);
                        //Alarm_switch.setChecked(true);
                        update_information();
                        help3();
                    }
                }); }
    void help3(){

        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.Activate), getString(R.string.Connect_the_charger_and),getString(R.string.After_activating_if_CC) ).cancelable(false).outerCircleColor(R.color.Orange).targetCircleColor(R.color.Orange).tintTarget(false)
                ,new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                        Intent batteryStatus = CWM_Setting.this.registerReceiver(null, ifilter);
                        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
                        if(isCharging) {
                            super.onTargetClick(view);

                        }else {
                            super.onTargetClick(view);
                            Toast.makeText(CWM_Setting.this, R.string.First_connect_the_charger, Toast.LENGTH_LONG).show();
                        }


                    }}); }


    public boolean getBoolean(String string) {
        boolean b=true;
        if(string.equals("0")){
            b=false;
        }else if(string.equals("1")){
            b=true;
        }
        return b;
    }


    void disable_items(){
        TextView T3 = (TextView) findViewById(R.id.Volume_textView);
        TextView T4 = (TextView) findViewById(R.id.T_Volume);
        TextView T5 = (TextView) findViewById(R.id.T_Volume_change);
        TextView T6 = (TextView) findViewById(R.id.Number);
        TextView T7 = (TextView) findViewById(R.id.text_view_setting);
        TextView T8 = (TextView) findViewById(R.id.Activate_T);
        SeekBar S2 = (SeekBar) findViewById(R.id.Volume_seekBar);
        Button B1 = (Button) findViewById(R.id.Set_number);
        Button B2 = (Button) findViewById(R.id.Pick_audio);
        ImageButton I1 = (ImageButton) findViewById(R.id.Delete_audio);
        if(App_payment==0) {

            if (Used_times > 15) {
                T8.setVisibility(View.VISIBLE);
                T3.setEnabled(false);T4.setEnabled(false);
                T5.setEnabled(false);T6.setEnabled(false);
                T7.setEnabled(false);
                S2.setEnabled(false);B1.setEnabled(false);
                B2.setEnabled(false);I1.setEnabled(false);
            }else{
                T8.setVisibility(View.INVISIBLE);
                T3.setEnabled(true);T4.setEnabled(true);
                T5.setEnabled(true);T6.setEnabled(true);
                T7.setEnabled(true);
                S2.setEnabled(true);B1.setEnabled(true);
                B2.setEnabled(true);I1.setEnabled(true);
            }
        } else {
            T8.setVisibility(View.INVISIBLE);
            T3.setEnabled(true);T4.setEnabled(true);
            T5.setEnabled(true);T6.setEnabled(true);
            T7.setEnabled(true);
            S2.setEnabled(true);B1.setEnabled(true);
            B2.setEnabled(true);I1.setEnabled(true);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        ObservableObject.getInstance().deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals("upgrade")) {
            ObservableObject.getInstance().deleteObserver(this);
            finish();
            startActivity(new Intent(this, MovementPhone.class));

        }

        }

}
