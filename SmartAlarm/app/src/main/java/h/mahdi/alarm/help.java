package h.mahdi.alarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import java.util.Observable;
import java.util.Observer;

public class help extends AppCompatActivity implements Observer,View.OnClickListener {
    private BottomSheetBehavior bottomSheetBehavior;

    private TextView bottomSheetHeading,bottomSheetContent;
    ScrollView scrollView;
    private Button expandBottomSheetButton;
    private Button collapseBottomSheetButton;
    private Button hideBottomSheetButton;
    private FlowingDrawer mDrawer;
    Toolbar toolbar;
    String TAG="help";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        ObservableObject.getInstance().addObserver(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        scrollView = (ScrollView) findViewById(R.id.s1);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

        initViews();
        initListeners();

        FragmentManager fm = getSupportFragmentManager();
        MenuListFragment mMenuFragment = (MenuListFragment) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuListFragment();
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isMenuVisible()) {
            mDrawer.closeMenu();
        } else {
            super.onBackPressed();

        }
    }
    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottomSheetLayout));
        bottomSheetHeading = (TextView) findViewById(R.id.bottomSheetHeading);
        bottomSheetContent = (TextView) findViewById(R.id.bottomSheetContent);

        expandBottomSheetButton = (Button) findViewById(R.id.expand_bottom_sheet_button);
        collapseBottomSheetButton = (Button) findViewById(R.id.collapse_bottom_sheet_button);
        hideBottomSheetButton = (Button) findViewById(R.id.hide_bottom_sheet_button);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);


    }
    private void initListeners() {
        // register the listener for button click
        expandBottomSheetButton.setOnClickListener(this);
        collapseBottomSheetButton.setOnClickListener(this);
        hideBottomSheetButton.setOnClickListener(this);
     //   showBottomSheetDialogButton.setOnClickListener(this);

        // Capturing the callbacks for bottom sheet
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {

                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
               //     bottomSheetHeading.setText("text_collapse_me");
                } else {
              //      bottomSheetHeading.setText("text_expand_me");
                }

                // Check Logs to see how bottom sheets behaves
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.e("Bottom Sheet Behaviour", "STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.e("Bottom Sheet Behaviour", "STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e("Bottom Sheet Behaviour", "STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.e("Bottom Sheet Behaviour", "STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.e("Bottom Sheet Behaviour", "STATE_SETTLING");
                        break;
                }
            }


            @Override
            public void onSlide(View bottomSheet, float slideOffset) {

            }
        });


    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals("upgrade")) {
            Log.e(TAG,"RECEIVED");
            ObservableObject.getInstance().deleteObserver(this);
            finish();
            startActivity(new Intent(this, MovementPhone.class));

        }
    }

    public void onClick(View v) {
        String myText2 = "<h4>\n" +
                "\tحساسیت:</h4>\n" +
                "<p>در این قسمت میتوانید میزان دقت و حساسیت سنسور های گوشسی را تغییر دهید</p>\n" +
                "<h4>\n" +
                "\tمیزان صدای آژیر:</h4>\n" +
                "<p>کاهش یا افزایش صدای آژیر یا موسیقی انتخابی</p>\n" +
                "<h4>\n" +
                "\tشماره تلفن همراه:</h4>\n" +
                "<p>شماره ای که پیامک برای آن ارسال می شود مثلا در زمان جدا شدن گوشی از شارژر</p>\n" +
                "<h4>\n" +
                "\tتغییر صدای آژیر:</h4>\n" +
                "<p>میتوانید به جای صدای آژیر یک موسیقی از دستگاه خود انتخاب کنید یا صدایی که ضبط کرده اید</p>\n";
        String myText = "<h3>\n" +
                "\tشناسایی جابجایی گوشی:</h3>\n" +
                "<p>این بخش میتواند حرکت کوشی را تشخیص داده و پس از آن آیتم هایی مثل آژیر زدن را اجرا کند به طور مثال توسط این بخش میتوان افرادی که بی اجازه به گوشی شما دست می زنند را غافلگیر کرد.</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<h3>\n" +
                "\tشناسایی حرکت در محیط:</h3>\n" +
                "<p>این بخش توسط دوربین جلوی گوشی حرکت اشیا از جلوی دوربین راتشخیص می دهد به طور مثال میتوان از این بخش برای تشخیص رفت و آمد در اتاق خود استفاده کنید</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<h3>\n" +
                "\tشناسایی اتصال به شارژر:</h3>\n" +
                "<p>حتما برای شما پیش آمده که گوشی خود را در یک مکان عمومی در شارژ قرار داده و نگران آن هستید توسط این قسمت میتوانید در صورت جدا شدن گوشی از شارژ خبر دار شوید</p>\n";
        Spanned sp = Html.fromHtml(myText);
        Spanned sp2 = Html.fromHtml(myText2);
        switch (v.getId()) {
            case R.id.collapse_bottom_sheet_button:
                scrollView.scrollTo(0, 0);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                bottomSheetHeading.setText("کاربرد های هر بخش");
                bottomSheetContent.setText(sp);

                break;
            case R.id.expand_bottom_sheet_button:
                scrollView.scrollTo(0, 0);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                bottomSheetHeading.setText("محل ذخیره فیلم ها و تصاویر گرفته شده");
                bottomSheetContent.setText("پوشه: \n  DCIM/دزدگیر هوشمند/");
                // Expanding the bottom sheet
             //   bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.hide_bottom_sheet_button:
                scrollView.scrollTo(0, 0);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                bottomSheetHeading.setText("کاربرد آیتم های قسمت تنظیمات");
                bottomSheetContent.setText(sp2);
                break;
      //      case R.id.show_bottom_sheet_dialog_button:
                // Opening the Dialog Bottom Sheet
                //new CustomBottomSheetDialogFragment().show(getSupportFragmentManager(), "Dialog");
         //       break;

        }
    }
}
