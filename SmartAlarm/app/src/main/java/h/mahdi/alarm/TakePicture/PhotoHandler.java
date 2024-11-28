package h.mahdi.alarm.TakePicture;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class PhotoHandler implements PictureCallback {
    private final Context context;
    Date dt;
    TakePicture Take;


    public PhotoHandler(Context context) {
        this.dt = null;
        this.context = context;
    }

    public void onPictureTaken(byte[] data, Camera camera) {
        Take = new TakePicture();
        try {
            byte[] bitmapdata = null;
            try {
                int size;
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
                this.dt = new Date();
                String date = dateFormat.format(this.dt);
                int wid = BitmapFactory.decodeByteArray(data, 0, data.length).getWidth();
                if (wid > 1600) {
                    size = 4;
                } else if (wid > 600) {
                    size = 2;
                } else {
                    size = 1;
                }
                Options options = new Options();
                options.inSampleSize = size;
                Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length, options);
                if (bm.getWidth() > bm.getHeight()) {
                    Matrix mat = new Matrix();
                    mat.postRotate(-90.0f);
                    Bitmap bMapRotate = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), mat, true);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bMapRotate.compress(CompressFormat.PNG, 0, bos);
                    bitmapdata = bos.toByteArray();
                }
                //DatabaseHandler databaseHandler = new DatabaseHandler(this.context);
         //       databaseHandler.addImgLog(new TblImgLog(date, bitmapdata));
         //       databaseHandler.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            String filename = getSharedPref("DIRECTORY_DCIM") + File.separator + (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(this.dt) + ".jpg");
            File file = new File(filename);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(bitmapdata);
                fileOutputStream.close();
                intent.setData(Uri.fromFile(file));
                this.context.sendBroadcast(intent);
             //   Toast.makeText(this.context, "SAVED", Toast.LENGTH_SHORT).show();
                Take.finish();
            //    Toast.makeText(this.context, "SAVED111111111", Toast.LENGTH_SHORT).show();
            } catch (Exception error) {
                Log.d("HiddenEye", "File" + filename + "not saved: " + error.getMessage());
                Toast.makeText(this.context, "Image could not be saved.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        storeSharedPref("CAPTURE", "1");
    }

    private void storeSharedPref(String key, String value) {
        Editor prefsEditor = this.context.getSharedPreferences("hEyePref", MODE_PRIVATE).edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    private String getSharedPref(String key) {
        return this.context.getSharedPreferences("hEyePref", MODE_PRIVATE).getString(key, "-1");
    }
}
