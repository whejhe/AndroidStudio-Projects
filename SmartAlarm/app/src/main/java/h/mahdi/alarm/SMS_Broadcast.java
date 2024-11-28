package h.mahdi.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by mahdi.h on 26/05/2018.
 */

public class SMS_Broadcast extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    String TAG = "SMS_Broadcast";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED)) {
            try {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    // get sms objects
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    if (pdus.length == 0) {
                        return;
                    }
                    // large message might be broken into many
                    SmsMessage[] messages = new SmsMessage[pdus.length];
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < pdus.length; i++) {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        sb.append(messages[i].getMessageBody());
                    }
                    String sender = messages[0].getOriginatingAddress();
                    String message = sb.toString();
                    Log.i(TAG, "From:" + sender + " body:" + message);

                    Intent intent1 = new Intent(context, SMS_R_Service.class);
                    intent1.putExtra("From", sender);
                    intent1.putExtra("Body", message);
                    context.startService(intent1);
                    // prevent any other broadcast receivers from receiving broadcast
                    // abortBroadcast();
                }
            } catch (Exception err) {

            }

        }
    }
}