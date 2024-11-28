package h.mahdi.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by mahdi.h on 30/09/2017.
 */

public class BroadCasts extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        ObservableObject.getInstance().updateValue(intent.getAction());

    }

}