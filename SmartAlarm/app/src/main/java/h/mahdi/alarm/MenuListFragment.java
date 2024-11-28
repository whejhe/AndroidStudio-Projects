
package h.mahdi.alarm;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by mxn on 2016/12/13.
 * MenuListFragment
 */

public class MenuListFragment extends Fragment {

    private ImageView ivMenuUserProfilePhoto;
    MovementPhone MP;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container,
                false);
        ivMenuUserProfilePhoto = (ImageView) view.findViewById(R.id.ivMenuUserProfilePhoto);
        NavigationView vNavigation = (NavigationView) view.findViewById(R.id.vNavigation);
        vNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

           //     menuItem.getItemId(R.id.movement_phone).setEnable(false);


                switch (menuItem.getItemId()){
                    case R.id.movement_phone:
                        startActivity(new Intent(getActivity(), MovementPhone.class));
                        getActivity().finish();
                        break;
                    case R.id.movement_in_around:
                        startActivity(new Intent(getActivity(), MovementInAround.class));
                        getActivity().finish();
                        break;
                    case R.id.charge_check:
                        startActivity(new Intent(getActivity(), ChargeCheck.class));
                        getActivity().finish();
                        break;
                    case R.id.control_with_sms:
                    //    Toast.makeText(getActivity(), R.string.enabled_next_version, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), ControlWithSMS.class));
                        getActivity().finish();
                        break;
                    case R.id.help:
                        startActivity(new Intent(getActivity(), help.class));
                        getActivity().finish();
                        break;
                    case R.id.another_app:
                        AnotherApp();
                        break;
                    case R.id.about:
                       about();
                        break;
                    case R.id.upgrade:

                        ObservableObject.getInstance().updateValue("upgrade");
                        break;
                }
                return false;
            }
        }) ;

        return  view ;
    }
    public void about(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                getActivity());
        alertDialog.setTitle(getString(R.string.About_us));
        alertDialog.setMessage(getString(R.string.About_texts));
        alertDialog.setPositiveButton(getString(R.string.Thanks),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }
    public void AnotherApp(){
        try{
            //Send comment

            //String url= "myket://comment?id=h.mahdi.alarm";
            //Intent intent = new Intent();
            //intent.setAction(Intent.ACTION_VIEW);
            //intent.setData(Uri.parse(url));
            //startActivity(intent);

            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setData(Uri.parse("bazaar://details?id=" + "h.mahdi.alarm"));
            intent.setPackage("com.farsitel.bazaar");
            startActivity(intent);

            //Developer apps

            //  Intent intent = new Intent(Intent.ACTION_VIEW);
            //   intent.setData(Uri.parse("bazaar://collection?slug=by_author&aid=" +"543783626527"));
            //   intent.setPackage("com.farsitel.bazaar");
            //   startActivity(intent);

            //	String url= "http://myket.ir/DeveloperApps.aspx?Packagename=h.mahdi.alarm";
            //	Intent intent = new Intent();
            //	intent.setAction(Intent.ACTION_VIEW);
            //	intent.setData(Uri.parse(url));
            //	startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getActivity(), R.string.Bazzar_app_is_not_installed_on_your_phone, Toast.LENGTH_SHORT).show();
            //	 Toast.makeText(getApplicationContext(), R.string.Myket_app_is_not_installed_on_your_phone, Toast.LENGTH_SHORT).show();
        }
    }


}
