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

import university.pace.mypace2.CalendarScreen.CalendarScreen;
import university.pace.mypace2.ImportantNumbersScreen.ImportantNumbers;
import university.pace.mypace2.PaceMaps.PaceMaps;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.campusmap:
                changeScreen(PaceMaps.class);
                break;


            case R.id.numbers:

                changeScreen(ImportantNumbers.class);

                break;

            case R.id.calBut:

                Intent intent = new Intent(this, CalendarScreen.class);
                startActivity(intent);
                //changeScreen(CalendarScreen.class);

                break;

            default:
                break;
        }


    }


    public void changeScreen(Class cl) {
        Intent intent = new Intent(this, cl);
        startActivity(intent);
    }

}

