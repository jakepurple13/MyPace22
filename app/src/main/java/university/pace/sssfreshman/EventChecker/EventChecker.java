package university.pace.sssfreshman.EventChecker;

import android.content.Context;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import university.pace.sssfreshman.R;

public class EventChecker extends AppCompatActivity {
    Button IconChange;
    ImageView usricon;
    private static int PICK_PHOTO_FOR_AVATAR = 1;
    EditText usrName, EventCode;
    String setusrname;
    private CheckBox remember;
    private boolean saveMemory;

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



/*   *****************When remember me is checked it save the users data on return**** */
        remember = (CheckBox) findViewById(R.id.checkBox);
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveMemory = isChecked;

            }
        });
/**Loads name on remember me ******/
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        saveMemory = preferences.getBoolean("save", false);
        remember.setChecked(saveMemory);
        String name = preferences.getString("username", ""); //pulls it on create
        usrName.setText(name, TextView.BufferType.NORMAL); //sets users name

        if (saveMemory)//if checked
        {

            // disable editing password
            usrName.setFocusable(false);
            usrName.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
            usrName.setClickable(false); // user navigates with wheel and selects widget
        } else { // enable editing of password
            usrName.setFocusable(true);
            usrName.setFocusableInTouchMode(true);
            usrName.setClickable(true);
        }


/**Set name with enter******/
        usrName.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    /**stores it in memory**/
                    if (saveMemory) {
                        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        setusrname = usrName.getText().toString();//gets user's name
                        editor.putString("username", setusrname);
                        editor.putBoolean("save", true);
                        editor.apply();
                        Log.d("name saved==>", setusrname);
                    }


                    Log.d("pressed enter", "==>");
                    Log.d("username", usrName.getText().toString());
                    Log.d("setname", setusrname);

                    return true;
                }
                return false;
            }
        });


        //    Tracks("Entered Search " + location_search, "pressed Enter");
        //      Log.d("Entered Search", "pressed Enter");



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
