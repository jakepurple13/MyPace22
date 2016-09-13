package university.pace.sssfreshman.EventChecker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import university.pace.sssfreshman.R;

public class EventChecker extends AppCompatActivity {
    Button IconChange;
    ImageView usricon;
    private static int PICK_PHOTO_FOR_AVATAR = 1;
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

        final String setusrname = usrName.getText().toString();//gets user's name

/**Set name with enter******/
        usrName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    /**stores it in memory**/
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(EventChecker.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username", setusrname);
                    editor.apply();
                    usrName.setText(setusrname, TextView.BufferType.EDITABLE); //sets users name
                    /**stores it in memory**/


                    //    Tracks("Entered Search " + location_search, "pressed Enter");
                    //      Log.d("Entered Search", "pressed Enter");
                    return true;

                }
                return false;
            }
        });
/**Set name with enter******/
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(EventChecker.this);
        String name = preferences.getString("username", setusrname); //pulls it on create



        String code = EventCode.getText().toString();
        //TODO: save user name here

        Checkcode(code);
    }


    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }

    public void SetIcon() {
        //     usricon.setBackground();


    }

    public void Checkcode(String code) {
        String keyowrd = "omellte du fromage";

        if (code.equals(keyowrd)) {

        }


    }

    /**
     * sets user icon
     ***/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.usricon);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }

    }
    /**sets user icon***/


}
