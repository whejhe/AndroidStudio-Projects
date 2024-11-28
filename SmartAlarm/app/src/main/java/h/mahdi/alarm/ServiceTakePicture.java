package h.mahdi.alarm;

import android.app.Service;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by mahdi.h on 05/12/2017.
 */

    public class ServiceTakePicture extends Service {

    View mView;
    private boolean _isPreview;
    private boolean _paused;
    private boolean isFocusing;
    private Camera mCamera;
    private Camera.Parameters parameters;
    private SurfaceHolder sHolder;
    private SurfaceView sv;
    RelativeLayout layout;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /*
        LayoutInflater localLayoutInflater = LayoutInflater.from(this);
        mView = localLayoutInflater.inflate(R.layout.show, null);
        this.sv = (SurfaceView) mView.findViewById(R.id.surfaceView1);
        this.sHolder = this.sv.getHolder();
        this.sHolder.setType(3);
        this.sHolder.addCallback(this);
        this.sHolder.setType(3);
        storeSharedPref("CAPTURE", "0");
        new Handler().postDelayed(new Runnable(){
            public void run(){
                stopCamera();
                _paused = true;
                finish();
            }},3000L);
  */
        return START_STICKY;

    }








    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}