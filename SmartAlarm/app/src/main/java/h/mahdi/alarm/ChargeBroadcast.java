package h.mahdi.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import h.mahdi.alarm.TakePicture.TakePicture;

import static h.mahdi.alarm.R.id.Activate;

/**
 * Created by mahdi.h on 30/09/2017.
 */

public class ChargeBroadcast extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        ObservableObject.getInstance().updateValue(intent.getAction());

    }

}