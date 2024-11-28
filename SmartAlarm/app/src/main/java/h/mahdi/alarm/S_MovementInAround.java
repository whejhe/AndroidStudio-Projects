package h.mahdi.alarm;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import h.mahdi.alarm.MotionDetection.MotionDetector;
import h.mahdi.alarm.MotionDetection.MotionDetectorCallback;

/**
 * Created by mahdi.h on 27/09/2017.
 */

public class S_MovementInAround extends Service {

    private MotionDetector motionDetector;
    View mView;
    DBHelper mydb;
    public Handler mHandler;
    public boolean continue_or_stop;
    String Alarm_switch_Value,Picture_switch_Value,SMS_switch_Value,Notification_switch_Value,Video_switch_Value,Movement_In_Around_mode,Is_alarming;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("MODE","STARTED");


        mydb = new DBHelper(this);

        LayoutInflater localLayoutInflater = LayoutInflater.from(this);
        mView = localLayoutInflater.inflate(R.layout.activity_camera, null);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
        WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.RIGHT | Gravity.TOP;
        params.setTitle("Load Average");
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.addView(mView, params);
        update_information();
        motionDetector = new MotionDetector(S_MovementInAround.this, (SurfaceView) mView.findViewById(R.id.surfaceView));
        update_information();
     //   motionDetector.onResume();
        
        motionDetector.setMotionDetectorCallback(new MotionDetectorCallback() {
            @Override
            public void onMotionDetected() {
               // if (Movement_In_Around_mode.equals("true")) {
                  //  if(timer==1) {
                 //       timer = 0;
                       // actions();
                //    }
                Log.e("MOVED","MOVED");
                }
         //       Toast.makeText(getBaseContext(),"MOVED", Toast.LENGTH_LONG).show();

        //    }

            @Override
            public void onTooDark() {

            }


        });
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void update_information(){
        Cursor S1 = mydb.getData("switches","MI_Alarm"); S1.moveToFirst();
        Cursor S2 = mydb.getData("switches","MI_Picture"); S2.moveToFirst();
        Cursor S3 = mydb.getData("switches","MI_SMS"); S3.moveToFirst();
        Cursor S4 = mydb.getData("switches","MI_Notification"); S4.moveToFirst();
        Cursor S5 = mydb.getData("switches","MI_Video"); S5.moveToFirst();
        Cursor S6 = mydb.getData("my_values","movement_in_around_mode"); S6.moveToFirst();
        Cursor S7 = mydb.getData("my_values","is_alarming"); S7.moveToFirst();

        Alarm_switch_Value = S1.getString(S1.getColumnIndex("value"));
        Picture_switch_Value = S2.getString(S2.getColumnIndex("value"));
        SMS_switch_Value = S3.getString(S3.getColumnIndex("value"));
        Notification_switch_Value = S4.getString(S4.getColumnIndex("value"));
        Video_switch_Value = S5.getString(S5.getColumnIndex("value"));
        Movement_In_Around_mode = S6.getString(S6.getColumnIndex("value"));
        Is_alarming = S7.getString(S7.getColumnIndex("value"));

        S1.close();
        S2.close();
        S3.close();
        S4.close();
        S5.close();
        S6.close();
        S7.close();

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
       motionDetector.onPause();
        Log.e("MODE","STOPED");
    }
}