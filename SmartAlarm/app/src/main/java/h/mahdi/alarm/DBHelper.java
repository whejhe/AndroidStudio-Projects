package h.mahdi.alarm;

/**
 * Created by mahdi.h on 21/09/2017.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String SWITCHS_COLUMN_VALUE = "value";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table my_values " + "(name text,value text,column1 text,column2 text)");
        db.execSQL("create table switches " + "(name text,value integer)");
        db.execSQL("create table check_boxes " + "(name text,value integer)");
        //   start();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS switches");
        onCreate(db);
    }

    public boolean start () {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "MP_Alarm");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "MP_Picture");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "MP_SMS");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "MP_Notification");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "MP_Video");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "MI_Alarm");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "MI_Picture");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "MI_SMS");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "MI_Notification");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "MI_Video");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "CC_Alarm");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "CC_Picture");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "CC_SMS");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "CC_Notification");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "CC_Video");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);


        contentValues.put("name", "MP_Activation_timer");
        contentValues.put("value", "0");
        db.insert("check_boxes", null, contentValues);
        contentValues.put("name", "MI_Activation_timer");
        contentValues.put("value", "0");
        db.insert("check_boxes", null, contentValues);
        contentValues.put("name", "MP_Audio_Loop");
        contentValues.put("value", "0");
        db.insert("check_boxes", null, contentValues);
        contentValues.put("name", "MI_Audio_Loop");
        contentValues.put("value", "0");
        db.insert("check_boxes", null, contentValues);
        contentValues.put("name", "CC_Audio_Loop");
        contentValues.put("value", "0");
        db.insert("check_boxes", null, contentValues);
        contentValues.put("name", "MP_Activity_Notification");
        contentValues.put("value", "0");
        db.insert("check_boxes", null, contentValues);
        contentValues.put("name", "MI_Activity_Notification");
        contentValues.put("value", "0");
        db.insert("check_boxes", null, contentValues);
        contentValues.put("name", "CC_Activity_Notification");
        contentValues.put("value", "0");
        db.insert("check_boxes", null, contentValues);

        contentValues.put("name", "movement_phone_mode");
        contentValues.put("value", "false");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "charge_check_mode");
        contentValues.put("value", "false");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "control_with_sms_mode");
        contentValues.put("value", "false");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "movement_in_around_mode");
        contentValues.put("value", "false");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "is_alarming");
        contentValues.put("value", "false");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "charge_check_activity_mode");
        contentValues.put("value", "close");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "MP_sensitivity");
        contentValues.put("value", "3");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "MI_sensitivity");
        contentValues.put("value", "3");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "MP_Volume");
        contentValues.put("value", "10");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "MI_Volume");
        contentValues.put("value", "10");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "CC_Volume");
        contentValues.put("value", "10");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "MP_Audio_Location");
        contentValues.put("value", "");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "MI_Audio_Location");
        contentValues.put("value", "");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "CC_Audio_Location");
        contentValues.put("value", "");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "MP_Phone_Number");
        contentValues.put("value", "");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "MI_Phone_Number");
        contentValues.put("value", "");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "CC_Phone_Number");
        contentValues.put("value", "");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "MP_Audio_Name");
        contentValues.put("value", "آژیر پلیس");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "MI_Audio_Name");
        contentValues.put("value", "آژیر پلیس");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "CC_Audio_Name");
        contentValues.put("value", "آژیر پلیس");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "MP_Audio_Duration");
        contentValues.put("value", "32422");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "MI_Audio_Duration");
        contentValues.put("value", "32422");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "CC_Audio_Duration");
        contentValues.put("value", "32422");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "Activity_CC_Mode");
        contentValues.put("value", "close");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "App_payment");
        contentValues.put("value", "0");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "Used_times");
        contentValues.put("value", "0");
        db.insert("my_values", null, contentValues);



        return true;
    }

    public boolean start2 () {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "CS_I_Silent");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "CS_I_Sound");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "CS_I_Vibrate");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "CS_I_Take picture");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "CS_I_Show text");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "CWM_password");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "CWM_receiveNumber");
        contentValues.put("value", "0");
        db.insert("switches", null, contentValues);
        contentValues.put("name", "CWM_phone_from");
        contentValues.put("value", "");
        db.insert("my_values", null, contentValues);
        contentValues.put("name", "CWM_password");
        contentValues.put("value", "");
        db.insert("my_values", null, contentValues);

        return true;
    }

    public Cursor getData(String table,String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+table+" where name='"+name+"'", null );
        return res;
    }


    public boolean updateSwitchs (String table,String name, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("value", value);
        db.update(table, contentValues, "name = ? ", new String[]{name});
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }
    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from my_values", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(SWITCHS_COLUMN_VALUE)));
            res.moveToNext();
        }
        return array_list;
    }


}