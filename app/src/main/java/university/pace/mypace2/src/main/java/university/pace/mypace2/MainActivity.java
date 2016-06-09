package university.pace.mypace2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
 Button CampusMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
CampusMap= (Button) findViewById(R.id.campusmap);

        CampusMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PaceMaps.class);
                startActivity(i);

            }
        });
      /**  Intent i = new Intent(this, ImportantNumbers.class);
        startActivity(i);
        finish();
       **/
    }
}
