package h.mahdi.alarm;


import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.OpenableColumns;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
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
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import java.util.Observable;
import java.util.Observer;

import h.mahdi.alarm.RecordVideo.RecordVideo;
import h.mahdi.alarm.TakePicture.TakePicture;

/**
 * Created by mahdi.h on 20/09/2017.
 */

public class ChargeCheck  extends AppCompatActivity  implements Observer {

    private FlowingDrawer mDrawer;
    SwitchCompat Alarm_switch,Picture_switch,SMS_switch,Notification_switch,Video_switch;
    String Charge_check_mode,Is_alarming,Volume_seekBar_value,Audio_Location,Phone_Number,Audio_Duration,Audio_Name;
    boolean Alarm_switch_Value,Picture_switch_Value,SMS_switch_Value,Notification_switch_Value,Video_switch_Value;
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
    TextView Number_textView,Audio_name;
    ImageButton Delete_audio;
    String TAG="CC";
    Context context = getApplication();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ObservableObject.getInstance().addObserver(this);
        final SharedPreferences shared = getSharedPreferences("Prefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor = shared.edit();
        isFirstRun = shared.getBoolean("FIRSTRUN_CC", true);
        if (isFirstRun) {
        }
        editor.putBoolean("FIRSTRUN_CC", false);
        editor.commit();
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        mydb = new DBHelper(this);
        timer_mode= "on";
        mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        Alarm_switch = (SwitchCompat) findViewById(R.id.alarm_switch);
        Picture_switch = (SwitchCompat) findViewById(R.id.picture_switch);
        SMS_switch = (SwitchCompat) findViewById(R.id.SMS_switch);
        Notification_switch = (SwitchCompat) findViewById(R.id.notification_switch);
        Pick_audio=(Button) findViewById(R.id.Pick_audio);
        Set_number = (Button) findViewById(R.id.Set_number);
        Video_switch = (SwitchCompat) findViewById(R.id.video_switch);
        Activate=(Button) findViewById(R.id.Activate);
        Delete_audio = (ImageButton) findViewById(R.id.Delete_audio);
        Number_textView = (TextView) findViewById(R.id.number);
        Audio_name = (TextView) findViewById(R.id.audio_name);
        Audio_loop = (CheckBox) findViewById(R.id.audio_loop);
        RelativeLayout Sensitivity_RelativeLayout = (RelativeLayout) findViewById(R.id.Sensitivity_layout);
        Sensitivity_RelativeLayout.setVisibility(View.GONE);
        Activity_notification = (CheckBox) findViewById(R.id.activity_notification);
        final TextView Volume_textView=(TextView) findViewById(R.id.Volume_textView);
        final SeekBar Volume_seekBar=(SeekBar) findViewById(R.id.Volume_seekBar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        update_information();
        Audio_loop.setChecked(Audio_loop_Value);
        Activity_notification.setChecked(Activity_notification_Value);
        Volume_seekBar_value_I = Integer.parseInt(Volume_seekBar_value);
        Volume_seekBar.setProgress(Volume_seekBar_value_I);
        Volume_textView.setText(Volume_seekBar_value);
        Audio_name.setText(Audio_Name);
        if(Phone_Number.equals("")){
            Number_textView.setText(R.string.No_number_entered);
        }else{
            Number_textView.setText(Phone_Number);
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String myVariable_2 = extras.getString("key_name");
            Toast.makeText(ChargeCheck.this, myVariable_2, Toast.LENGTH_LONG).show();

        }

        Alarm_switch.setChecked(Alarm_switch_Value);
        Picture_switch.setChecked(Picture_switch_Value);
        SMS_switch.setChecked(SMS_switch_Value);
        Notification_switch.setChecked(Notification_switch_Value);
        Video_switch.setChecked(Video_switch_Value);

        if(Is_alarming.equals("true")){
            toolbar.setBackgroundColor(ContextCompat.getColor(ChargeCheck.this, R.color.green));
            Activate.setText(R.string.Mute);
            toolbar.setBackgroundColor(ContextCompat.getColor(ChargeCheck.this, R.color.red));
        }else if (Charge_check_mode.equals("true")){
            toolbar.setBackgroundColor(ContextCompat.getColor(ChargeCheck.this, R.color.red));
            Activate.setText(R.string.Inactivate);
        }else{
            toolbar.setBackgroundColor(ContextCompat.getColor(ChargeCheck.this, R.color.green));
            Activate.setText(R.string.Activate);
        }

        Alarm_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){mydb.updateSwitchs("switches","CC_Alarm","1");}else{mydb.updateSwitchs("switches","CC_Alarm","0");}}});
        Picture_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){mydb.updateSwitchs("switches","CC_Picture","1");}else{mydb.updateSwitchs("switches","CC_Picture","0");}}});
        SMS_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){mydb.updateSwitchs("switches","CC_SMS","1");}else{mydb.updateSwitchs("switches","CC_SMS","0");}}});
        Notification_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){mydb.updateSwitchs("switches","CC_Notification","1");}else{mydb.updateSwitchs("switches","CC_Notification","0");}}});
        Video_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){mydb.updateSwitchs("switches","CC_OpenApp","1");}else{mydb.updateSwitchs("switches","CC_OpenApp","0");}}});

        Activate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Activate();

            }
        });
        Delete_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ChargeCheck.this);
                alertDialog.setTitle("");
                alertDialog.setMessage(R.string.Siren_back_to_orginal);
                alertDialog.setPositiveButton(R.string.no,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Audio_name.setText(R.string.Police_siren);
                        mydb.updateSwitchs("my_values", "CC_Audio_Name", getString(R.string.Police_siren));
                        mydb.updateSwitchs("my_values", "CC_Audio_Duration","32422");
                        mydb.updateSwitchs("my_values", "CC_Audio_Location", "");
                        update_information();
                    }
                });
                alertDialog.show();
            }
        });
        Set_number.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Context context=getApplicationContext();
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);
                final EditText titleBox = new EditText(context);
                titleBox.setHint("09140111111");
                titleBox.setTextColor(getResources().getColor(R.color.black));
                titleBox.setText(Phone_Number);
                titleBox.setHintTextColor(getResources().getColor(R.color.blue_grey));
                titleBox.setInputType(InputType.TYPE_CLASS_NUMBER);
                layout.addView(titleBox);
                AlertDialog alertDialog = new AlertDialog.Builder(ChargeCheck.this).create();
                alertDialog.setMessage(getString(R.string.EnteـrnumberـtoـSMS));
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.Cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.Save), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mydb.updateSwitchs("my_values", "CC_Phone_Number",titleBox.getText()+"" );
                        update_information();
                        if(Phone_Number.equals("")){
                            Number_textView.setText(R.string.No_number_entered);
                        }else{
                            Log.e("text",titleBox.getText()+"");
                            Number_textView.setText(Phone_Number);
                        }
                    }
                });
                alertDialog.setView(layout);
                alertDialog.show();

            }
        });

        Pick_audio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent_upload = new Intent();
                intent_upload.setType("audio/*");
                intent_upload.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent_upload,1);
            }
        });

        Volume_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (Volume_seekBar_value_I < 0) {
                    Volume_seekBar_value_I = 0;
                    Volume_seekBar.setProgress(Volume_seekBar_value_I);
                }}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Volume_seekBar_value_I = progress;
                Volume_seekBar_value = String.valueOf(Volume_seekBar_value_I);
                mydb.updateSwitchs("my_values","CC_Volume",Volume_seekBar_value);
                update_information();
                Volume_textView.setText(Volume_seekBar_value);
            }});

        FragmentManager fm = getSupportFragmentManager();
        MenuListFragment mMenuFragment = (MenuListFragment) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuListFragment();
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }
        disable_items();

        mHandler = new Handler();
        continue_or_stop = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (continue_or_stop) {
                    try {
                        Thread.sleep(500); // every 5 seconds
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub


                            }
                        });
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();
        if(isFirstRun)
            help();

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
            super.onBackPressed();

        }
    }


    public void actions(){
        mydb.updateSwitchs("my_values", "Used_times", String.valueOf(++Used_times));
        try {
            mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.cancel(002);
        }catch (Exception e){

        }
        update_information();
        mydb.updateSwitchs("my_values", "charge_check_mode", "false");
        if(Alarm_switch.isChecked()){
            if(Is_alarming.equals("false")){
                mydb.updateSwitchs("my_values", "is_alarming", "true");
                Intent intent = new Intent(this, Alarm.class);
                intent.putExtra("FROM", "CC");
                startService(intent);
                Activate.setText(R.string.Mute);
                if(!Audio_loop_Value){
                    new CountDownTimer(Integer.parseInt(Audio_Duration),1000){
                        @Override
                        public void onTick(long millisUntilFinished){}

                        @Override
                        public void onFinish(){
                            Context context = getApplication();
                            Intent intent = new Intent(context, Alarm.class);
                            context.stopService(intent);
                            toolbar.setBackgroundColor(ContextCompat.getColor(ChargeCheck.this, R.color.green));
                            Activate.setText(R.string.Activate);
                            update_information();
                        }
                    }.start();
                }
                Log.e("ChargeCheck","The siren was started");
            }
        }else{
            toolbar.setBackgroundColor(ContextCompat.getColor(ChargeCheck.this, R.color.green));
            Activate.setText(R.string.Activate);
        }
        if(Picture_switch.isChecked()){
            startActivity(new Intent(ChargeCheck.this, TakePicture.class));
            Log.e("ChargeCheck","The picture was taken");
        }
        if(SMS_switch.isChecked()) {
            if (!Phone_Number.equals("")) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(Phone_Number, null, getString(R.string.charger_disconnected), null, null);
                Log.e("ChargeCheck", "SMS Sent");
            } else {
                Toast.makeText(getApplicationContext(), R.string.No_number_entered, Toast.LENGTH_SHORT).show();
            }
        }
        if(Notification_switch.isChecked()){

            NotificationCompat.Builder mBuilder =   new NotificationCompat.Builder(this)
                    .setSmallIcon(getNotificationIcon())
                    .setContentTitle(getString(R.string.charger_disconnected))
                    .setContentText(getString(R.string.Someone_from_charger))
                    .setAutoCancel(true);
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(0, mBuilder.build());
        }
        if(Video_switch.isChecked()){
            startActivity(new Intent(ChargeCheck.this, RecordVideo.class));
            Log.e("ChargeCheck","The video is recording");
        }
        update_information();
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
    public void update_information(){
        Cursor S1 = mydb.getData("switches","CC_Alarm"); S1.moveToFirst();
        Cursor S2 = mydb.getData("switches","CC_Picture"); S2.moveToFirst();
        Cursor S3 = mydb.getData("switches","CC_SMS"); S3.moveToFirst();
        Cursor S4 = mydb.getData("switches","CC_Notification"); S4.moveToFirst();
        Cursor S5 = mydb.getData("switches","CC_Video"); S5.moveToFirst();
        Cursor S6 = mydb.getData("my_values","charge_check_mode"); S6.moveToFirst();
        Cursor S7 = mydb.getData("my_values","is_alarming"); S7.moveToFirst();
        Cursor S8 = mydb.getData("my_values","CC_Volume"); S8.moveToFirst();
        Cursor S9 = mydb.getData("my_values","CC_Audio_Location"); S9.moveToFirst();
        Cursor S10 = mydb.getData("my_values","CC_Audio_Duration"); S10.moveToFirst();
        Cursor S11 = mydb.getData("my_values","CC_Audio_Name"); S11.moveToFirst();
        Cursor S12 = mydb.getData("my_values","CC_Phone_Number"); S12.moveToFirst();
        Cursor S13 = mydb.getData("check_boxes","CC_Activity_Notification"); S13.moveToFirst();
        Cursor S14 = mydb.getData("my_values", "App_payment");S14.moveToFirst();
        Cursor S15 = mydb.getData("my_values", "Used_times");S15.moveToFirst();
        Alarm_switch_Value = getBoolean(S1.getString(S1.getColumnIndex("value")));
        Picture_switch_Value = getBoolean(S2.getString(S2.getColumnIndex("value")));
        SMS_switch_Value = getBoolean(S3.getString(S3.getColumnIndex("value")));
        Notification_switch_Value = getBoolean(S4.getString(S4.getColumnIndex("value")));
        Video_switch_Value = getBoolean(S5.getString(S5.getColumnIndex("value")));
        Charge_check_mode = S6.getString(S6.getColumnIndex("value"));
        Is_alarming = S7.getString(S7.getColumnIndex("value"));
        Volume_seekBar_value = S8.getString(S8.getColumnIndex("value"));
        Audio_Location = S9.getString(S9.getColumnIndex("value"));
        Audio_Duration = S10.getString(S10.getColumnIndex("value"));
        Audio_Name = S11.getString(S11.getColumnIndex("value"));
        Phone_Number = S12.getString(S12.getColumnIndex("value"));
        Activity_notification_Value = getBoolean(S13.getString(S13.getColumnIndex("value")));
        App_payment = S14.getInt(S14.getColumnIndex("value"));
        Used_times = S15.getInt(S15.getColumnIndex("value"));
        S1.close();S2.close();
        S3.close();S4.close();
        S5.close();S6.close();
        S7.close();S8.close();
        S9.close();S10.close();
        S11.close();S12.close();
        S13.close();S14.close();
        S15.close();
    }
    private int getNotificationIcon() {
        boolean useWhiteIcon = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.drawable.ic_notification_black_white : R.drawable.ic_notification ;
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Audio_Location_U = data.getData();
            Audio_Location= Audio_Location_U.toString();
            mydb.updateSwitchs("my_values","CC_Audio_Location",Audio_Location);
            Audio_name();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void Audio_name(){
        try{
            Uri mediaPath = Audio_Location_U ;
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(this, mediaPath);
            String sponsorTitle = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            String sponsorDuration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);


            if(sponsorTitle != null){
                Audio_name.setText(sponsorTitle);
                mydb.updateSwitchs("my_values", "CC_Audio_Name",sponsorTitle);
                mydb.updateSwitchs("my_values", "CC_Audio_Duration",sponsorDuration);
                Log.e("Duration",sponsorDuration);
            }else {
                mydb.updateSwitchs("my_values", "CC_Audio_Duration",sponsorDuration);
                Uri returnUri = Audio_Location_U;
                Cursor returnCursor = getContentResolver().query(returnUri, null, null, null, null);
                int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                returnCursor.moveToFirst();
                Audio_name.setText(returnCursor.getString(nameIndex));
                mydb.updateSwitchs("my_values", "CC_Audio_Name",returnCursor.getString(nameIndex));
            }
        } catch (Exception e){

        }
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
                        Alarm_switch.setChecked(true);
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
                        Intent batteryStatus = ChargeCheck.this.registerReceiver(null, ifilter);
                        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
                        if(isCharging) {
                            super.onTargetClick(view);
                            Activate();
                        }else {
                            super.onTargetClick(view);
                            Toast.makeText(ChargeCheck.this, R.string.First_connect_the_charger, Toast.LENGTH_LONG).show();
                        }


                    }}); }
    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.activity_notification:
                if (checked) {
                    mydb.updateSwitchs("check_boxes", "CC_Activity_Notification", "1");
                } else {
                    try{
                        mNotificationManager.cancel(001);
                    }catch(Exception e){
                    }
                    mydb.updateSwitchs("check_boxes", "CC_Activity_Notification", "0");
                }
                update_information();
                break;
            case R.id.audio_loop:
                if (checked) {
                    mydb.updateSwitchs("check_boxes", "CC_Audio_Loop", "1");
                } else {
                    mydb.updateSwitchs("check_boxes", "CC_Audio_Loop", "0");
                }
                update_information();
                break;
            // TODO: Veggie sandwich
        }
    }

    public boolean getBoolean(String string) {
        boolean b=true;
        if(string.equals("0")){
            b=false;
        }else if(string.equals("1")){
            b=true;
        }
        return b;
    }

                    void Activate() {


                        update_information();
                        if(Is_alarming.equals("true")){
                            Context context = getApplication();
                            Intent intent = new Intent(context, Alarm.class);
                            context.stopService(intent);
                            mydb.updateSwitchs("my_values", "is_alarming", "false");
                            toolbar.setBackgroundColor(ContextCompat.getColor(ChargeCheck.this, R.color.green));
                            Activate.setText(R.string.Activate);
                            update_information();
                        }else {
                            if (Charge_check_mode.equals("true")) {
                                toolbar.setBackgroundColor(ContextCompat.getColor(ChargeCheck.this, R.color.green));
                                mydb.updateSwitchs("my_values", "charge_check_mode", "false");
                                Activate.setText(R.string.Activate);
                                try{
                                    mNotificationManager.cancel(001);
                                }catch(Exception e){
                                }
                            } else {
                                IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                                Intent batteryStatus = ChargeCheck.this.registerReceiver(null, ifilter);
                                int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                                boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;

                                if(isCharging==true){
                                    toolbar.setBackgroundColor(ContextCompat.getColor(ChargeCheck.this, R.color.red));
                                    mydb.updateSwitchs("my_values", "charge_check_mode", "true");
                                    Activate.setText(R.string.Inactivate);
                                    if(Activity_notification_Value) {
                                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ChargeCheck.this)
                                                .setSmallIcon(getNotificationIcon())
                                                .setContentTitle(getString(R.string.app_name))
                                                .setContentText(getString(R.string.Identify_charger_running));
                                        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                        mNotificationManager.notify(001, mBuilder.build());
                                    }
                                }else {
                                    Toast.makeText(ChargeCheck.this, R.string.First_connect_the_charger, Toast.LENGTH_LONG).show();
                                }

                            }
                        }
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
        if(arg.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
                update_information();
                 if(Charge_check_mode.equals("true")) {
                     actions();

                 }
        }
    }
}
