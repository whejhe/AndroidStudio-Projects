package h.mahdi.alarm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by mahdi.h on 20/09/2017.
 */


public class ControlWithSMS   extends AppCompatActivity implements Observer {

    MyFunctions fu;
    private FlowingDrawer mDrawer;
    boolean item_switch_Value;
    Button Activate;
    DBHelper mydb;
    int Used_times, App_payment;
    boolean isFirstRun;
    String[] my_items_name=new String[5];
    public String[] my_items = {"Vibrate",
            "Silent",
            "Sound","Take picture","Show text"
    };
    View v1;
    String TAG="Control with sms";
    SwitchCompat item_switch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mydb = new DBHelper(this);
        fu= new MyFunctions();

        setContentView(R.layout.control_with_sms);
        ObservableObject.getInstance().addObserver(this);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        update_information();
        mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final SharedPreferences shared = getSharedPreferences("Prefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor = shared.edit();
        isFirstRun = shared.getBoolean("FIRSTRUN_CWS", true);
        editor.putBoolean("FIRSTRUN_CWS", false);
        editor.commit();
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionbar.setTitle(R.string.title_activity_CS);
        toolbar.setBackgroundColor(ContextCompat.getColor(ControlWithSMS.this, R.color.green));
        final ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new MyAdapter(this,
                R.layout.sms_activity_list_item,
                R.id.name,
                my_items));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
             //   Toast.makeText(ControlWithSMS.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ControlWithSMS.this, CWM_Setting.class));
                finish();
              //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        fab.hide();
        FragmentManager fm = getSupportFragmentManager();
        MenuListFragment mMenuFragment = (MenuListFragment) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuListFragment();
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }

        if(isFirstRun)
            help();
    }

    void help() {

        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.toolbar), getString(R.string.CWM_help), "")
                        .cancelable(false).targetCircleColor(R.color.green).textColor(R.color.White).outerCircleColor(R.color.green).drawShadow(true).tintTarget(false),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);

                    }

                    @Override
                    public void onOuterCircleClick(TapTargetView view) {
                        super.onOuterCircleClick(view);
                        super.onTargetClick(view);

                    }
                });
    }



    public void update_information(){
        Cursor S1 = mydb.getData("my_values", "App_payment");S1.moveToFirst();
        Cursor S2 = mydb.getData("my_values", "Used_times");S2.moveToFirst();
        App_payment = S1.getInt(S1.getColumnIndex("value"));
        Used_times = S2.getInt(S2.getColumnIndex("value"));
        S1.close();
        S2.close();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mDrawer.toggleMenu();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isMenuVisible()) {
            mDrawer.closeMenu();
        } else {
            super.onBackPressed();

        }
    }

    private View.OnClickListener myButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (Integer) v.getTag();
            boolean isChecked=  ((SwitchCompat) v).isChecked();
            if(isChecked){
                mydb.updateSwitchs("switches", "CS_I_"+my_items_name[position], "1");
            }else {
                mydb.updateSwitchs("switches", "CS_I_"+my_items_name[position], "0");
            }
        }
    };

    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals("upgrade")) {
            Log.e(TAG,"RECEIVED");
            ObservableObject.getInstance().deleteObserver(this);
            finish();
            startActivity(new Intent(this, MovementPhone.class));

        }
    }
    private class MyAdapter extends ArrayAdapter<String>{


        public MyAdapter(Context context, int resource, int textViewResourceId,
                         String[] strings) {
            super(context, resource, textViewResourceId, strings);
            // TODO Auto-generated constructor stub
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.sms_activity_list_item, parent, false);
            String stringName = "My_action_item_" + String.valueOf(position+1);
            int string_res_ID = getResources().getIdentifier(stringName,"array",getPackageName());
            String Item_Array[] = getResources().getStringArray(string_res_ID);
            Cursor S5 = mydb.getData("switches", "CS_I_"+Item_Array[0]);S5.moveToFirst();
            item_switch_Value = fu.getBoolean(S5.getString(S5.getColumnIndex("value")));
            S5.close();
            TextView name = (TextView) row.findViewById(R.id.name);
            name.setText( Item_Array[0]);
            TextView description = (TextView) row.findViewById(R.id.description);
            description.setText( Item_Array[1]);
            TextView code = (TextView) row.findViewById(R.id.code);
            TextView t1 = (TextView) row.findViewById(R.id.help);
            code.setText( "*#"+Item_Array[2]+"#*");
            item_switch = (SwitchCompat) row.findViewById(R.id.item_switch);
            item_switch.setTag(position);
            item_switch.setOnClickListener(myButtonClickListener);
            item_switch.setChecked(item_switch_Value);
            my_items_name[position]=Item_Array[0];
            TextView T8 = (TextView) row.findViewById(R.id.Activate_T);
            if(App_payment==0) {

                if (Used_times > 15) {
                    T8.setVisibility(View.VISIBLE);
                    name.setEnabled(false);description.setEnabled(false);
                    code.setEnabled(false);item_switch.setEnabled(false);
                    t1.setEnabled(false);

                }else{

                    T8.setVisibility(View.INVISIBLE);
                    name.setEnabled(true);description.setEnabled(true);
                    code.setEnabled(true);item_switch.setEnabled(true);
                    t1.setEnabled(true);
                }
            } else {
                T8.setVisibility(View.INVISIBLE);
                name.setEnabled(true);description.setEnabled(true);
                code.setEnabled(true);item_switch.setEnabled(true);
                t1.setEnabled(true);
            }
            return row;
        }


    }

}
