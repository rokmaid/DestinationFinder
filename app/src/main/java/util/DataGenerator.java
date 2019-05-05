package util;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class DataGenerator {



    public static List<String> getListOfCityCodes(Context context) {


       List<String> records = new ArrayList<>();


        try (Scanner scanner = new Scanner(context.getAssets().open("AirportCodes.csv"))) {

            while (scanner.hasNextLine()) {
                //records.add(getRecordFromLine(scanner.nextLine()));
               // System.out.println(scanner.nextLine());
            records.add(scanner.nextLine());
            }

        }catch (FileNotFoundException e ){

            e.printStackTrace();

        }catch (IOException e ){
            e.printStackTrace();
        }




        return records ;
    }

    public static String[] getListOfCountryCodes(){


     String[] countryCodes ={"AL", "DZ", "AS", "AD" ,"AO" ,"AI" ,"AQ" ,"AG" ,"AR" ,"AM" ,"AW" ,"AU" ,"AT" ,"AZ" ,"BS" ,"BH" ,"BD" ,"BB" ,"BY", "BE","BZ","BJ"
        ,"BM","BT","BO","BQ", "BA","BW","BV","BR","IO","BN","BG", "BF","BI","CV","KH","CM","CA","KY","CF","TD","CL","CN","CX","CC","CO","KM","CD","CG","CK","CR"
        ,"HR","CU","CW","CY","CZ","CI","DK","DJ","DM","DO","EC","EG","SV","GQ","ER","EE","SZ","ET","FK","FO","FJ","FI","FR","GF","PF","TF","GA","GM","GE","DE","GH","GI"
        ,"GR", "GL","GD","GP","GU","GT","GG","GN","GW","GY","HT","HM","VA","HN","HK","HU","IS","IN","ID","IR","IQ","IE","IM","IL","IT","JM","JP","JE","JO","KZ","KE","KI"
        ,"KP","KR","KW","KG","LA","LV","LB","LS","LR","LY","LI","LT","LU","MO","MK","MG","MW","MY","MV","ML","MT","MH","MQ","MR","MU","YT","MX","FM","MD","MC","MN","ME"
        ,"MS","MA","MZ","MM","NA","NR","NP","NL","NC","NZ","NI","NE","NG","NU","NF","MP","NO","OM","PK","PW","PS","PA","PG","PY","PE","PH","PN","PL","PT","PR","QA","RO"
             ,"RU","RW","RE","BL","SH","KN","LC","MF","PM","VC","WS","SM","ST","SA","SN","RS","SC","SL","SG","SX","SK","SI","SB","SO","ZA","GS","SS","ES", "LK",
               "SD","SR","SJ","SE","CH","SY","TW","TJ","TZ","TH","TL","TG","TK","TO","TT","TN","TR","TM","TC","TV","UG","UA","AE","GB","UM","US","UY","UZ","VU","VE"
                ,"VN","VG","VI","WF","EH","YE","ZM","ZW","AX"};



return  countryCodes ;
    }


}
