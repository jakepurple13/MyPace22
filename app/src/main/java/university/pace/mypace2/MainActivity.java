package university.pace.mypace2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import university.pace.mypace2.ImportantNumbersScreen.ImportantNumbers;
import university.pace.mypace2.PaceMaps.PaceMaps;


public class MainActivity extends  AppCompatActivity implements View.OnClickListener {
    Button CampusMap,ImportantNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CampusMap= (Button) findViewById(R.id.campusmap);
        ImportantNum =(Button) findViewById(R.id.numbers);


        /**      LocationManager gps = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
         boolean gps_enabled = false;
         boolean network_enabled = false;

         try {
         gps_enabled = gps.isProviderEnabled(LocationManager.GPS_PROVIDER);
         } catch (Exception ex) {
         Toast.makeText(MainActivity.this,"Gps is off",Toast.LENGTH_SHORT).show();
         Log.d("GPS is off","try1");
         }

         try {
         network_enabled = gps.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
         } catch (Exception ex) {

         Toast.makeText(MainActivity.this,"Wifi is off",Toast.LENGTH_SHORT).show();
         Log.d("Wifi is off","try2");
         }

         if (!gps_enabled && !network_enabled) {
         // notify user
         AlertDialog ad = new AlertDialog.Builder(getApplicationContext()).setMessage(
         R.string.gps_network_not_enabled).setTitle(
         R.string.gps_network_enabled).setCancelable(false)
         .setPositiveButton(android.R.string.ok,
         new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog,
         int whichButton) {
         Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
         getApplicationContext().startActivity(myIntent);



         }
         }).setNeutralButton(android.R.string.cancel,
         new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog,
         int whichButton) {
         // User selects Cancel, show default map

         }
         }).show();
         }

         /*Creates Google maps view*/




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.campusmap:
               changeScreen(PaceMaps.class);
                break;


    case R.id.numbers:

    changeScreen(ImportantNumbers.class);

    break;
}


}


public void changeScreen(Class cl) {
    Intent intent = new Intent(this, cl);
    startActivity(intent);
    }

}

