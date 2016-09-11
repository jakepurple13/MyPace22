package university.pace.sssfreshman.EventChecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import university.pace.sssfreshman.R;

public class EventChecker extends AppCompatActivity {
    Button IconChange;
    ImageView usricon;
    int PICK_PHOTO_FOR_AVATAR = 1;
    EditText usrName, EventCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventbadgedisplay);

        IconChange = (Button) findViewById(R.id.changeicon);
        usricon = (ImageView) findViewById(R.id.usricon);

        usrName = (EditText) findViewById(R.id.usrname);
        EventCode = (EditText) findViewById(R.id.Eventcode);

        IconChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });

        usrName.getText().toString();

        //TODO: save user name here


    }


    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }

    public void SetIcon() {
        //     usricon.setBackground();


    }


}
