package h.mahdi.alarm.PracticalClass;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.MediaStore;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by mahdi.h on 31/10/2017.
 */

public class GetMusicInformation extends SQLiteOpenHelper {
    int REQUEST_MEDIA = 0;


    SharedPreferences prefs;
    String prefName = "MyPref";
    String artist_name, artist_band;
    Uri MyUri;

    public GetMusicInformation(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void x(String S_uri){
/*

            MyUri = Uri.parse(S_uri);
            String[] proj = { MediaStore.Audio.Media._ID,
                    MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.TITLE,
                    MediaStore.Audio.Artists.ARTIST };

            Cursor tempCursor = managedQuery(MrUri, proj, null, null, null);

            tempCursor.moveToFirst(); //reset the cursor
            int col_index=-1;
            int numSongs=tempCursor.getCount();
            int currentNum=0;
            do{
                col_index = tempCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE);
                artist_name = tempCursor.getString(col_index);
                col_index = tempCursor.getColumnIndexOrThrow(MediaStore.Audio.Artists.ARTIST);
                artist_band = tempCursor.getString(col_index);
                //do something with artist name here
                //we can also move into different columns to fetch the other values

                currentNum++;
            }while(tempCursor.moveToNext());


            prefs = getSharedPreferences(prefName, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("alarm_selected", audioID);
            editor.putString("alarm_name", artist_name);
            editor.putString("alarm_band", artist_band);
            editor.commit();
*/

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
