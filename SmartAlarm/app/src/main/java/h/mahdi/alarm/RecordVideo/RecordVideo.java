package h.mahdi.alarm.RecordVideo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import h.mahdi.alarm.R;

public class RecordVideo extends Activity {
    private Camera mCamera;
    private CameraPreview mPreview;
    private MediaRecorder mediaRecorder;
    private Button capture, switchCamera;
    private Context myContext;
    private LinearLayout cameraPreview;
    private boolean cameraFront = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        myContext = this;
        initialize();
        releaseCamera();
        chooseCamera();


        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                startRecord();
            }
        }.start();
        new CountDownTimer(7000, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                startRecord();
                finish();
            }
        }.start();

    }

    private int findFrontFacingCamera() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                cameraFront = true;
                break;
            }
        }
        return cameraId;
    }

    private int findBackFacingCamera() {
        int cameraId = -1;
        // Search for the back facing camera
        // get the number of cameras
        int numberOfCameras = Camera.getNumberOfCameras();
        // for every camera check
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                cameraFront = false;
                break;
            }
        }
        return cameraId;
    }

    public void onResume() {
        super.onResume();
        if (!hasCamera(myContext)) {
            Toast toast = Toast.makeText(myContext, "Sorry, your phone does not have a camera!", Toast.LENGTH_LONG);
            toast.show();
            finish();
        }
        if (mCamera == null) {
            // if the front facing camera does not exist
            if (findFrontFacingCamera() < 0) {
                Toast.makeText(this, "No front facing camera found.", Toast.LENGTH_LONG).show();
                switchCamera.setVisibility(View.GONE);
            }
            mCamera = Camera.open(findBackFacingCamera());
            mPreview.refreshCamera(mCamera);
        }
    }

    public void initialize() {
        cameraPreview = (LinearLayout) findViewById(R.id.camera_preview);

        mPreview = new CameraPreview(myContext, mCamera);
        cameraPreview.addView(mPreview);

    }


    public void chooseCamera() {
        // if the camera preview is the front
        if (cameraFront) {
            int cameraId = findBackFacingCamera();
            if (cameraId >= 0) {
                // open the backFacingCamera
                // set a picture callback
                // refresh the preview

                mCamera = Camera.open(cameraId);
                // mPicture = getPictureCallback();
                mPreview.refreshCamera(mCamera);
            }
        } else {
            int cameraId = findFrontFacingCamera();
            if (cameraId >= 0) {
                // open the backFacingCamera
                // set a picture callback
                // refresh the preview

                mCamera = Camera.open(cameraId);
                // mPicture = getPictureCallback();
                mPreview.refreshCamera(mCamera);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // when on Pause, release camera in order to be used from other
        // applications
        releaseCamera();
    }

    private boolean hasCamera(Context context) {
        // check if the device has camera
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

    boolean recording = false;


    private void releaseMediaRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.reset(); // clear recorder configuration
            mediaRecorder.release(); // release the recorder object
            mediaRecorder = null;
            mCamera.lock(); // lock camera for later use
        }
    }
    public void startRecord() {
        if (recording) {
            mediaRecorder.stop();
            releaseMediaRecorder();
            recording = false;
        } else {
            if (!prepareMediaRecorder()) {
                finish();
            }
            runOnUiThread(new Runnable() {
                public void run() {
                    try {

                        mediaRecorder.start();


                    } catch (final Exception ex) {
                    }
                }
            });

            recording = true;
        }
    }
    private boolean prepareMediaRecorder() {

        mediaRecorder = new MediaRecorder();

        mCamera.unlock();
        mediaRecorder.setCamera(mCamera);

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_720P));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        mediaRecorder.setOutputFile("storage/emulated/0/DCIM/دزدگیر هوشمند/"+currentDateandTime+".mp4");
        mediaRecorder.setMaxDuration(600000); // Set max duration 60 sec.
        mediaRecorder.setMaxFileSize(50000000); // Set max file size 50M
        try {
            mediaRecorder.prepare();
        } catch (IllegalStateException e) {
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            releaseMediaRecorder();
            return false;
        }
        return true;

    }

    private void releaseCamera() {
        // stop and release camera
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }
}