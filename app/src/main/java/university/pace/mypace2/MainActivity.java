package university.pace.mypace2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import university.pace.mypace2.ImportantNumbersScreen.ImportantNumbers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(this, ImportantNumbers.class);
        startActivity(i);
        finish();



    }
}
