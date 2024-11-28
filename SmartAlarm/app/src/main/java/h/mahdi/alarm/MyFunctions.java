package h.mahdi.alarm;

/**
 * Created by mahdi.h on 10/06/2018.
 */

public class MyFunctions {


    public boolean getBoolean(String string) {
        boolean b=true;
        if(string.equals("0")){
            b=false;
        }else if(string.equals("1")){
            b=true;
        }
        return b;
    }

    public static String arrayToString(String[] ar){
        String ar_s="";
        for(int i=0;i<ar.length;i++){

            ar_s+=ar[i];

        }

        return ar_s;
    }
    public static String cutString(String st,int number) {
        String[] ar=st.split("(?!^)");
        String ar_s = "";
        if (number > 0) {
            for (int i = 0+number; i < ar.length; i++) {

                ar_s += ar[i];

            }
            return ar_s;
        } else {
            for (int i = 0; i < ar.length+number; i++) {

                ar_s += ar[i];

            }
            return ar_s;
        }
    }

    public static String numberToStandard(String st) {
        String st_a[]=st.split("(?!^)");
        String string="";
        if(st_a.length!=10) {
            int x=st_a.length-10;
            string=cutString(st, x);
        }else {
            string=arrayToString(st_a);
        }


        return string;
    }
    public static String cutBetween(String st,int i1,int i2) {
        String[] ar=st.split("(?!^)");
        String ar_s = "";

            for (int i = 0+i1; i < ar.length-i2; i++) {

                ar_s += ar[i];

            }
            return ar_s;

    }

}
