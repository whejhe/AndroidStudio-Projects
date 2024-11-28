package h.mahdi.alarm.TakePicture;

import android.app.Activity;
import android.content.SharedPreferences.Editor;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import h.mahdi.alarm.R;

public class TakePicture extends Activity implements Callback {
    private boolean _isPreview;
    private boolean _paused;
    private boolean isFocusing;
    private Camera mCamera;
    private Parameters parameters;
    private SurfaceHolder sHolder;
    private SurfaceView sv;
   // RelativeLayout layout;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
     //   layout = (RelativeLayout) findViewById(R.id.layout);
     //   layout.setLayoutParams();
      //  WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams(-2, -2, 2002, 8, -3);
        this.sv = (SurfaceView) findViewById(R.id.surfaceView);
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
    }

    private void stopCamera() {
        if (this.mCamera != null) {
            this.mCamera.stopPreview();
            this.mCamera.release();
            this.mCamera = null;
            this._isPreview = false;
        }
    }

    protected void onPause() {
        super.onPause();
        stopCamera();
        this._paused = true;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        try {
            if (this._isPreview) {
                this.mCamera.stopPreview();
            }
            this.parameters = this.mCamera.getParameters();
            Log.d("HiddenEye", "surfaceChanged");
            this.isFocusing = false;
            this.mCamera.setParameters(this.parameters);
            this.mCamera.startPreview();
            this._isPreview = true;
            this.mCamera.takePicture(null, null, new PhotoHandler(getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int findFrontFacingCamera() {
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            CameraInfo info = new CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == 1) {
                Log.d("HiddenEye", "Camera found");
                return i;
            }
        }
        return -1;
    }

    public void surfaceCreated(SurfaceHolder holder) {
        int camID = findFrontFacingCamera();
        if (camID > 0) {
            try {
                this.mCamera = Camera.open(camID);
                try {
                    this.mCamera.setPreviewDisplay(holder);
                    Log.d("HiddenEye", "Camera open 1");
                    return;
                } catch (IOException e) {
                    this.mCamera.release();
                    this.mCamera = null;
                    return;
                }
            } catch (RuntimeException re) {
                re.printStackTrace();
                if (this.mCamera != null) {
                    stopCamera();
                    this.mCamera = Camera.open(camID);
                    try {
                        this.mCamera.setPreviewDisplay(holder);
                        Log.d("HiddenEye", "Camera open 2");
                        return;
                    } catch (IOException e2) {
                        this.mCamera.release();
                        this.mCamera = null;
                        e2.printStackTrace();
                        return;
                    }
                }
                return;
            } catch (Exception e3) {
                if (this.mCamera != null) {
                    stopCamera();
                }
                e3.printStackTrace();
                return;
            }
        }
        Log.d("HiddenEye", "No Front Cam");

        //---------------------------------tabdil tarikh-----------
        Calendar cal = Calendar.getInstance();
        int mYear = cal.get(Calendar.YEAR);
        int mMonth = cal.get(Calendar.MONTH) + 1;
        int mDay = cal.get(Calendar.DAY_OF_MONTH);
        Roozh jCal = new Roozh();
        jCal.GregorianToPersian(mYear, mMonth, mDay);
        String convertedDate = jCal.toString(); //year-month-day
//--------------------------------------------
        String date1 = new SimpleDateFormat("HH:mm").format(new Date());
        String date = convertedDate +" "+ date1;

      //  String date = new SimpleDateFormat("dd MMM yyyy HH:mm").format(new Date());
        //DatabaseHandler db = new DatabaseHandler(getApplicationContext());
       // db.addImgLog(new TblImgLog(date, null));
       // db.close();
    }

    private void storeSharedPref(String key, String value) {
        Editor prefsEditor = getSharedPreferences("hEyePref", MODE_PRIVATE).edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        stopCamera();
        storeSharedPref("CAPTURE", "1");
        finish();
    }

}
