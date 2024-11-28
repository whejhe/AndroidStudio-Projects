package h.mahdi.alarm;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;

import java.io.IOException;

/**
 * Created by mahdi.h on 27/09/2017.
 */

public class Alarm extends Service {
    public MediaPlayer mediaPlayer;
    public Handler mHandler;
    public boolean continue_or_stop,MP_looping,MI_looping,CC_looping;
    DBHelper mydb;
    AudioManager audioManager;
    int Volume;
    String FROM, MP_Volume,MI_Volume,CC_Volume,MP_Audio_Location,MI_Audio_Location,CC_Audio_Location;
    Uri MP_Audio_Location_U,MI_Audio_Location_U,CC_Audio_Location_U;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mydb = new DBHelper(this);
        update_information();

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        try {
            if (intent.getExtras() != null) {
                FROM = intent.getStringExtra("FROM");
            }
        }catch (Exception Error) {

        }
        mydb.updateSwitchs("my_values", "is_alarming", "true");
        switch (FROM) {
            case "MP":
                if(MP_Audio_Location.isEmpty()) {
                    mediaPlayer = MediaPlayer.create(Alarm.this, R.raw.alarm);
                    mediaPlayer.start();
                }else {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    try {
                        mediaPlayer.setDataSource(getApplicationContext(), MP_Audio_Location_U);
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                mediaPlayer.setLooping(MP_looping);
                break;
            case "MI":
                if(MI_Audio_Location.isEmpty()) {
                    mediaPlayer = MediaPlayer.create(Alarm.this, R.raw.alarm);
                    mediaPlayer.start();
                }else {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    try {
                        mediaPlayer.setDataSource(getApplicationContext(), MI_Audio_Location_U);
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                mediaPlayer.setLooping(MI_looping);
                break;
            case "CC":
                if(CC_Audio_Location.isEmpty()) {
                    mediaPlayer = MediaPlayer.create(Alarm.this, R.raw.alarm);
                    mediaPlayer.start();
                }else {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    try {
                        mediaPlayer.setDataSource(getApplicationContext(), CC_Audio_Location_U);
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                mediaPlayer.setLooping(CC_looping);
                break;
            default:
                mediaPlayer = MediaPlayer.create(Alarm.this, R.raw.alarm);
                mediaPlayer.start();
                mediaPlayer.setLooping(CC_looping);
        }
        mHandler = new Handler();
        continue_or_stop = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (continue_or_stop) {try {Thread.sleep(300);mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        update_information();
                        switch (FROM) {
                            case "MP": Volume = Integer.parseInt(MP_Volume);break;
                            case "MI": Volume = Integer.parseInt(MI_Volume);break;
                            case "CC": Volume = Integer.parseInt(CC_Volume);break;
                            default:   Volume =10;
                        }
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,Volume,AudioManager.FLAG_ALLOW_RINGER_MODES);
                    }
                });} catch (Exception e){}}}
        }).start();

        mediaPlayer.start();
        return Service.START_STICKY;
    }
    @Override
    public void onDestroy(){
        mediaPlayer.stop();
        mydb.updateSwitchs("my_values", "is_alarming", "false");
        continue_or_stop = false;

        super.onDestroy();
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public void update_information(){

        Cursor S1 = mydb.getData("my_values","MP_Volume"); S1.moveToFirst();
        Cursor S2 = mydb.getData("my_values","MI_Volume"); S2.moveToFirst();
        Cursor S3 = mydb.getData("my_values","CC_Volume"); S3.moveToFirst();
        Cursor S4 = mydb.getData("my_values","MP_Audio_Location"); S4.moveToFirst();
        Cursor S5 = mydb.getData("my_values","MI_Audio_Location"); S5.moveToFirst();
        Cursor S6 = mydb.getData("my_values","CC_Audio_Location"); S6.moveToFirst();
        Cursor S7 = mydb.getData("check_boxes","MP_Audio_Loop"); S7.moveToFirst();
        Cursor S8 = mydb.getData("check_boxes","MI_Audio_Loop"); S8.moveToFirst();
        Cursor S9 = mydb.getData("check_boxes","CC_Audio_Loop"); S9.moveToFirst();
        MP_Volume = S1.getString(S1.getColumnIndex("value"));
        MI_Volume = S2.getString(S2.getColumnIndex("value"));
        CC_Volume = S3.getString(S3.getColumnIndex("value"));
        MP_Audio_Location = S4.getString(S4.getColumnIndex("value"));
        MI_Audio_Location = S5.getString(S5.getColumnIndex("value"));
        CC_Audio_Location = S6.getString(S6.getColumnIndex("value"));
        MP_looping = getBoolean(S7.getString(S7.getColumnIndex("value")));
        MI_looping = getBoolean(S8.getString(S8.getColumnIndex("value")));
        CC_looping = getBoolean(S9.getString(S9.getColumnIndex("value")));
        S1.close();
        S2.close();
        S3.close();
        S4.close();
        S5.close();
        S6.close();
        S7.close();
        S8.close();
        S9.close();
        MP_Audio_Location_U = Uri.parse(MP_Audio_Location);
        MI_Audio_Location_U = Uri.parse(MI_Audio_Location);
        CC_Audio_Location_U = Uri.parse(CC_Audio_Location);


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
}
