package h.mahdi.alarm;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import h.mahdi.alarm.TakePicture.TakePicture;

/**
 * Created by mahdi.h on 26/05/2018.
 */

public class SMS_R_Service extends Service {
    MyFunctions fu;
    DBHelper mydb;
    Boolean M_Sound,M_Vibrate,M_Silent,M_ShowText,M_TakePicture;
    boolean Receive_switch_Value;
    String Phone_from_number;
    View mView;
    String text="Your text";
    String TAG="Serveice SMS receiver";
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart(Intent intent, int startid) {
        mydb = new DBHelper(this);
        fu = new MyFunctions();
        update_information();
        try {
        Bundle extras = intent.getExtras();

        if (extras != null) {
            String From = intent.getStringExtra("From");
            String Body = intent.getStringExtra("Body");
            char[] BodyArray = Body.toCharArray();
            Log.e("DDD","fROM="+From);
            Log.e("DDD11111","fROM="+Phone_from_number);
           // Toast.makeText(this, "FROM= "+From, Toast.LENGTH_LONG).show();

            if (fu.numberToStandard(From).equals(fu.numberToStandard(Phone_from_number))|| !Receive_switch_Value) {
                Log.e("DDD",fu.numberToStandard(From));
                Log.e("DDD2",fu.numberToStandard(Phone_from_number));
               int length= BodyArray.length;
                Log.e("length",length+"");
            if(BodyArray[0]=='*'&&BodyArray[1]=='#'&&BodyArray[length-2]=='#'&&BodyArray[length-1]=='*') {

                AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                switch (BodyArray[2]) {
                    case '1':
                        if (M_Silent)
                            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                        break;
                    case '2':
                        if (M_Sound)
                            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        break;
                    case '3':
                        if (M_Vibrate)
                            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                        break;
                    case '4':
                            if (M_TakePicture)
                                startActivity(new Intent(SMS_R_Service.this, TakePicture.class));
                        break;
                    case '5':
                        if (M_ShowText) {

                            LayoutInflater localLayoutInflater = LayoutInflater.from(this);
                            mView = localLayoutInflater.inflate(R.layout.show, null);
                            final TextView texView1 = ((TextView) mView.findViewById(R.id.text));
                            texView1.setText(fu.cutBetween(Body, 4, 2));
                            WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams(-2, -2, 2002, 8, -3);
                            ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).addView(mView, localLayoutParams);
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).removeView(mView);
                                    //  stopSelf();
                                    //show=0;
                                }
                            }, 4000L);
                            break;
                        }
                }
            }
            }
        }
        }catch (Exception error){
            Log.e(TAG,"ERROR"+error);
        }
    }
    public void update_information(){
        try {

            Cursor S1 = mydb.getData("switches", "CS_I_Silent");S1.moveToFirst();
            Cursor S2 = mydb.getData("switches", "CS_I_Sound");S2.moveToFirst();
            Cursor S3 = mydb.getData("switches", "CS_I_Vibrate");S3.moveToFirst();
            Cursor S4 = mydb.getData("switches", "CS_I_Show text");S4.moveToFirst();
            Cursor S5 = mydb.getData("switches", "CS_I_Take picture");S5.moveToFirst();
            Cursor S6 = mydb.getData("my_values", "CWM_phone_from");S6.moveToFirst();
            Cursor S7 = mydb.getData("switches", "CWM_receiveNumber");S7.moveToFirst();

            M_Silent = fu.getBoolean(S1.getString(S1.getColumnIndex("value")));
            M_Sound = fu.getBoolean(S2.getString(S2.getColumnIndex("value")));
            M_Vibrate = fu.getBoolean(S3.getString(S3.getColumnIndex("value")));
            M_ShowText = fu.getBoolean(S4.getString(S4.getColumnIndex("value")));
            M_TakePicture = fu.getBoolean(S5.getString(S5.getColumnIndex("value")));
            Phone_from_number = S6.getString(S6.getColumnIndex("value"));
            Receive_switch_Value = fu.getBoolean(S7.getString(S7.getColumnIndex("value")));
            S1.close();
            S2.close();
            S3.close();
            S4.close();
            S5.close();
            S7.close();
            S6.close();
        }catch (Exception error){
        }
    }
}