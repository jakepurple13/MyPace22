package university.pace.mypace2.TestingPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;

import university.pace.mypace2.R;

public class CardTest extends AppCompatActivity {

    CardView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_test);

        cv = (CardView) findViewById(R.id.card_view);

    }
}
