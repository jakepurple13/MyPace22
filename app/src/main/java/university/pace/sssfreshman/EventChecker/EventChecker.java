package university.pace.sssfreshman.EventChecker;

import android.Manifest;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import university.pace.sssfreshman.MainActivity;
import university.pace.sssfreshman.R;

public class EventChecker extends AppCompatActivity
        implements EasyPermissions.PermissionCallbacks {
    Button IconChange;
    ImageView usricon;

    EditText usrName, EventCode;
    TextView badgecounter;
    String setusrname;
    AudioManager am;
    private static int count = 1;//count stars at one
    private CheckBox remember;
    private boolean saveMemory;
    GoogleAccountCredential mCredential;
    ProgressDialog mProgress;
    static final int PICK_PHOTO_FOR_AVATAR = 1;
    static final int REQUEST_ACCOUNT_PICKER = 1000;
    static final int REQUEST_AUTHORIZATION = 1001;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;

    private static final String PREF_ACCOUNT_NAME = "accountName";
    private static final String[] SCOPES = {SheetsScopes.SPREADSHEETS_READONLY};

    ArrayList<Eventinfo> events;
    private static ArrayList<String> visted;
    private Tracker mTracker;
    private final String TAG = "";
    private String Screentracker = "EventChecker";
    private String usrcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventbadgedisplay);

        IconChange = (Button) findViewById(R.id.changeicon);
        usricon = (ImageView) findViewById(R.id.usricon);

        usrName = (EditText) findViewById(R.id.usrname);
        EventCode = (EditText) findViewById(R.id.Eventcode);
        badgecounter = (TextView) findViewById(R.id.BadgesEarned);

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
                //if checked
                // disable editing password
                usrName.setFocusable(false);
                usrName.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
                usrName.setClickable(false); // user navigates with wheel and selects widget
            }
        });


        /**Loads name on remember me ******/
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        saveMemory = preferences.getBoolean("save", false);
        remember.setChecked(saveMemory);
        String name = preferences.getString("username", ""); //pulls it on create
        usrName.setText(name, TextView.BufferType.NORMAL); //sets users name

        /**Loads badge count number*******/

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int rememberedcount = prefs.getInt("badgenum", count); //pulls it on create
        Log.i("start==>", String.valueOf(count));//ex> start 1
        badgecounter.setText("x " + String.valueOf(rememberedcount), TextView.BufferType.NORMAL); //sets users name
        ++rememberedcount;// increment remebered by one
        getPreferences(MODE_PRIVATE).edit().putInt("count_key", rememberedcount).commit();//save number in shared prefs
        count = getPreferences(MODE_PRIVATE).getInt("count_key", count); //count becomes that value and is sent to badgeearned()
        Log.i("end==>", String.valueOf(count));//ex> ends 2
        Log.i("numremembered==>", String.valueOf(rememberedcount));





        /**Loads Photo*****/
        SharedPreferences prefphoto = PreferenceManager.getDefaultSharedPreferences(this);
        String savephoto = prefphoto.getString("imagepath", ""); //pulls it on create
        usricon.setImageBitmap(getScaledBitmap(savephoto, 135, 135));//sets users name


        /***allows user to edittext on edittext hold**/
        usrName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

// enable editing of password
                usrName.setFocusable(true);
                usrName.setFocusableInTouchMode(true);
                usrName.setClickable(true);
                Log.i("Pressed usrname", "Long click");
                return false;
            }
        });

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
                        //       Log.d("name saved==>", setusrname);
                    }


                    Log.d("pressed enter", "==>");
                    Log.d("username", usrName.getText().toString());
//                    Log.d("setname", setusrname);

                    return true;
                }
                return false;


            }
        });


        //    Tracks("Entered Search " + location_search, "pressed Enter");
        //      Log.d("Entered Search", "pressed Enter");

        EventCode.setOnKeyListener(new View.OnKeyListener() {

            @TargetApi(Build.VERSION_CODES.M)
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_ENTER) {
                    usrcode = EventCode.getText().toString();
                    Log.d("usrcode===>", usrcode);  //on enter pass text to check with google
                    Checkcode(usrcode);
                    return true;
                }
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    Log.d("Delete it===>", "backspace");
                    EventCode.setBackgroundColor(getResources().getColor(R.color.white, null));
                    EventCode.setTextColor(getResources().getColor(R.color.wrong, null));//color black
                    badgecounter.setBackgroundColor(getResources().getColor(R.color.white, null));


                }
                return false;
            }

        });


        // Initialize credentials and service object.
        mCredential = GoogleAccountCredential.usingOAuth2(
                getApplicationContext(), Arrays.asList(SCOPES))
                .setBackOff(new ExponentialBackOff());

        mProgress = new ProgressDialog(this);
        mProgress.setMessage("Loading please wait...");


        events = new ArrayList<>();
        visted = new ArrayList<>();

        loadArray(this);//LoadsArray
        Log.i("Visted==>loaded", visted.toString());



        getResultsFromApi();




    }


    public void pickImage() {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, PICK_PHOTO_FOR_AVATAR);
    }

    public void SetIcon() {
        //     usricon.setBackground();


    }

    public void Checkcode(String code) {
        Log.d("Checkcode==>", code);
        Log.d("arrayevents", events.toString());
        Log.d("vistedList==>", visted.toString());

//TODO:FIXED // FIXedME: 9/16/2016
        for (Eventinfo e : events) //for-each loop looks through Event list
        {

            Log.d("mylist==>", e.toString());
            Log.d("mycodes==>", e.eventcode);
            Log.d("myeventname==>", e.name);
            //Log.d("notfound()===>",String.valueOf(notfound(code)) );

            if (code.equals(e.eventcode) && !VistedEvent(code)) {
                //Code correct
                Log.d("Code Correct==>", code);
                Log.d("Match==>", code + e.eventcode);

                BadgeEarned(badgecounter, e.name); //adds badge in view
                visted.add(e.eventcode);

//store in ArrayList visted  *user attended event*
                Log.d("REMOVED", visted.toString());

            }
            saveArray(); //save visted array
        }
    }
    public class Eventinfo {
        String name;
        String eventcode;


        public Eventinfo(String name, String eventcode) {
            this.name = name;
            this.eventcode = eventcode;

        }

        @Override
        public String toString() {
            return "Event Name:" + "\n" + name + "\n" + "EventCode:" + "\n" + eventcode;
        }
    }

    /**
     * Attempt to call the API, after verifying that all the preconditions are
     * satisfied. The preconditions are: Google Play Services installed, an
     * account was selected and the device currently has online access. If any
     * of the preconditions are not satisfied, the app will prompt the user as
     * appropriate.
     */
    private void getResultsFromApi() {
        if (!isGooglePlayServicesAvailable()) {
            acquireGooglePlayServices();
        } else if (mCredential.getSelectedAccountName() == null) {
            chooseAccount();
        } else if (!isDeviceOnline()) {
            //mOutputText.setText("No network connection available.");

        } else {
            new MakeRequestTask(mCredential, EventChecker.this).execute();
        }


    }

    /**
     * Attempts to set the account used with the API credentials. If an account
     * name was previously saved it will use that one; otherwise an account
     * picker dialog will be shown to the user. Note that the setting the
     * account to use with the credentials object requires the app to have the
     * GET_ACCOUNTS permission, which is requested here if it is not already
     * present. The AfterPermissionGranted annotation indicates that this
     * function will be rerun automatically whenever the GET_ACCOUNTS permission
     * is granted.
     */
    @AfterPermissionGranted(REQUEST_PERMISSION_GET_ACCOUNTS)
    private void chooseAccount() {
        if (EasyPermissions.hasPermissions(
                EventChecker.this, Manifest.permission.GET_ACCOUNTS)) {
            String accountName = getPreferences(Context.MODE_PRIVATE)
                    .getString(PREF_ACCOUNT_NAME, null);
            if (accountName != null) {
                mCredential.setSelectedAccountName(accountName);
                getResultsFromApi();
            } else {
                // Start a dialog from which the user can choose an account
                startActivityForResult(
                        mCredential.newChooseAccountIntent(),
                        REQUEST_ACCOUNT_PICKER);
            }
        } else {
            // Request the GET_ACCOUNTS permission via a user dialog
            EasyPermissions.requestPermissions(
                    this,
                    "This app needs to access your Google account (via Contacts).",
                    REQUEST_PERMISSION_GET_ACCOUNTS,
                    Manifest.permission.GET_ACCOUNTS);
        }
    }

    /**
     * Called when an activity launched here (specifically, AccountPicker
     * and authorization) exits, giving you the requestCode you started it with,
     * the resultCode it returned, and any additional data from it.
     *
     * @param requestCode code indicating which activity result is incoming.
     * @param resultCode  code indicating the result of the incoming
     *                    activity result.
     * @param data        Intent (containing result data) returned by incoming
     *                    activity result.
     */
    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_GOOGLE_PLAY_SERVICES:
                if (resultCode != RESULT_OK) {
                    //mOutputText.setText(
                    //      "This app requires Google Play Services. Please install " +
                    //            "Google Play Services on your device and relaunch this app.");
                } else {
                    getResultsFromApi();
                }
                break;
            case REQUEST_ACCOUNT_PICKER:
                if (resultCode == RESULT_OK && data != null &&
                        data.getExtras() != null) {
                    String accountName =
                            data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        SharedPreferences settings =
                                getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(PREF_ACCOUNT_NAME, accountName);
                        editor.apply();
                        mCredential.setSelectedAccountName(accountName);
                        getResultsFromApi();
                    }
                }
                break;
            case REQUEST_AUTHORIZATION:
                if (resultCode == RESULT_OK) {
                    getResultsFromApi();
                }
                break;


            case PICK_PHOTO_FOR_AVATAR:
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
                    if (imageView != null) {
                        imageView.setImageBitmap(getScaledBitmap(picturePath, 135, 135));
                        Log.d("Image Loading==>", "Imageview");
                        SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(this);
                        SharedPreferences.Editor edit = shre.edit();
                        edit.putString("imagepath", picturePath);
                        edit.commit();
                    } else
                        Log.e("Failed Image Loading", "Imageview");
                    //  imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

                }
                /**
                 * sets user icon
                 ***/

                /**sets user icon***/
                break;
        }
    }

    /**
     * Respond to requests for permissions at runtime for API 23 and above.
     *
     * @param requestCode  The request code passed in
     *                     requestPermissions(android.app.Activity, String, int, String[])
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either PERMISSION_GRANTED or PERMISSION_DENIED. Never null.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(
                requestCode, permissions, grantResults, this);
    }

    /**
     * Callback for when a permission is granted using the EasyPermissions
     * library.
     *
     * @param requestCode The request code associated with the requested
     *                    permission
     * @param list        The requested permission list. Never null.
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Do nothing.
    }

    /**
     * Callback for when a permission is denied using the EasyPermissions
     * library.
     *
     * @param requestCode The request code associated with the requested
     *                    permission
     * @param list        The requested permission list. Never null.
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Do nothing.
    }

    /**
     * Checks whether the device currently has a network connection.
     *
     * @return true if the device has a network connection, false otherwise.
     */

    private boolean isDeviceOnline() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * Check that Google Play services APK is installed and up to date.
     *
     * @return true if Google Play Services is available and up to
     * date on this device; false otherwise.
     */
    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(this);
        return connectionStatusCode == ConnectionResult.SUCCESS;
    }

    /**
     * Attempt to resolve a missing, out-of-date, invalid or disabled Google
     * Play Services installation via a user dialog, if possible.
     */
    private void acquireGooglePlayServices() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(this);
        if (apiAvailability.isUserResolvableError(connectionStatusCode)) {
            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
        }
    }


    /**
     * Display an error dialog showing that Google Play Services is missing
     * or out of date.
     *
     * @param connectionStatusCode code describing the presence (or lack of)
     *                             Google Play Services on this device.
     */
    void showGooglePlayServicesAvailabilityErrorDialog(
            final int connectionStatusCode) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        Dialog dialog = apiAvailability.getErrorDialog(
                this,
                connectionStatusCode,
                REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }

    /**
     * An asynchronous task that handles the Google Sheets API call.
     * Placing the API calls in their own task ensures the UI stays responsive.
     */
    private class MakeRequestTask extends AsyncTask<Void, Void, List<String>> {
        private com.google.api.services.sheets.v4.Sheets mService = null;
        private Exception mLastError = null;
        EventChecker ec;

        public MakeRequestTask(GoogleAccountCredential credential, EventChecker ec) {
            this.ec = ec;
            HttpTransport transport = AndroidHttp.newCompatibleTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            mService = new com.google.api.services.sheets.v4.Sheets.Builder(
                    transport, jsonFactory, credential)
                    .setApplicationName("SSS Freshman App")
                    .build();
        }

        /**
         * Background task to call Google Sheets API.
         *
         * @param params no parameters needed for this task.
         */
        @Override
        protected List<String> doInBackground(Void... params) {
            try {
                return getDataFromApi();
            } catch (Exception e) {
                mLastError = e;
                cancel(true);
                return null;
            }
        }

        /**
         * Fetch a list of names and majors of students in a sample spreadsheet:
         * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
         *
         * @return List of names and majors
         * @throws IOException
         */

        private List<String> getDataFromApi() throws IOException {


            String spreadsheetId = "1oZFwUEg93tsiw1G4M9RHEv7-qObeMNN1XZqsMG0Hmzw";//"1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms";
            String range = "Events!A2:E";
            //ArrayList<MentorInfo> al = new ArrayList<>();
            List<String> results = new ArrayList<String>();
            ValueRange response = this.mService.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            List<List<Object>> values = response.getValues();
            if (values != null) {
                results.add("Name, Major");
                for (List row : values) {

                    Eventinfo eventinfo = new Eventinfo((String) row.get(3), (String) row.get(4));
                    events.add(eventinfo);
                    Log.e("eventslist", events.toString());
                    //Log.i("getdatafromapi",eventinfo.eventcode);


                    for (int i = 0; i < row.size(); i++) {
                        Log.e("Row number: " + i, (String) row.get(i));
                    }

                    Log.e("EventData==>", eventinfo.eventcode);
                    results.add(row.get(0) + ", " + row.get(3));
                }
            }
            return results;
        }

        @Override
        protected void onPreExecute() {
            //mOutputText.setText("");
            mProgress.show();
        }

        @Override
        protected void onPostExecute(List<String> output) {
            mProgress.hide();
            if (output == null || output.size() == 0) {
                Log.e("Nothing returned", "No results returned.");
            } else {
                output.add(0, "Data retrieved using the Google Sheets API:");

                Log.w("onPost", TextUtils.join("\n", output));


            }
            Log.d("events", events.toString());
            //       Checkcode()
        }

        @Override
        protected void onCancelled() {
            //mProgress.hide();
            if (mLastError != null) {
                if (mLastError instanceof GooglePlayServicesAvailabilityIOException) {
                    showGooglePlayServicesAvailabilityErrorDialog(
                            ((GooglePlayServicesAvailabilityIOException) mLastError)
                                    .getConnectionStatusCode());
                } else if (mLastError instanceof UserRecoverableAuthIOException) {
                    startActivityForResult(
                            ((UserRecoverableAuthIOException) mLastError).getIntent(),
                            EventChecker.REQUEST_AUTHORIZATION);
                } else {
                    Log.w("onCancelled", "The following error occurred:\n" + mLastError.getMessage());
                }
            } else {
                Log.e("cancelled", "Request cancelled.");
            }
        }
        /***read data from different sheet**/


    }

    public void CodeSound(int sound) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, sound);
        mediaPlayer.start();


    }

    @TargetApi(Build.VERSION_CODES.M)
    public void FindTextColor(EditText et, int textcolor, int bgcolor) {


        et.setTextColor(getResources().getColor(textcolor, null));
        et.setBackgroundColor(getResources().getColor(bgcolor, null));
    }


    @TargetApi(Build.VERSION_CODES.M)
    public void BadgeEarned(TextView badgecount, String name) { //only if user gets eventcode correct


        //  count++;
        CodeSound(R.raw.correctsoundeffect);
        FindTextColor(EventCode, R.color.Succuess, R.color.Succuess_bg);
        Toast.makeText(EventChecker.this, "Welcome to the " + name + " event", Toast.LENGTH_LONG).show();
        SharedPreferences badgestored = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = badgestored.edit();
        edit.putInt("badgenum", count);
        edit.commit();
        Log.i("numberofbadges==>", String.valueOf(count));
        badgecount.setText("x " + String.valueOf(count++));
        badgecount.setBackgroundColor(getResources().getColor(R.color.Succuess_bg, null));
        //PutExtra(count);
//TODO: provide better counting // FIXME: 9/17/2016


    }

    public boolean VistedEvent(String code) {


        for (String v : visted) {
            if (code.equals(v)) {

                //code used <code>
                CodeSound(R.raw.wrongsoundeffect);
                FindTextColor(EventCode, R.color.wrong, R.color.wrong_bg);
                Toast.makeText(EventChecker.this, "You've already stored this code " + v, Toast.LENGTH_LONG).show();

                return true;
            }

        }
        return false;
    }

    public void PutExtra(int put) {
        Intent ii = new Intent(EventChecker.this, MainActivity.class);
        ii.putExtra("num", put);

        //   startActivity(ii);
    }


    /**
     * Saves visted array to shared prefrences
     **/
    public boolean saveArray() {


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(EventChecker.this);
        SharedPreferences.Editor mEdit1 = sp.edit();
    /* visted is an array */
        mEdit1.putInt("visted_size", visted.size());

        for (int i = 0; i < visted.size(); i++) {
            mEdit1.remove("visted_" + i);
            mEdit1.putString("visted_" + i, visted.get(i));
        }

        return mEdit1.commit();
    }

    /**
     * loads Array from shared prefrences
     **/
    public static void loadArray(Context mContext) {
        SharedPreferences mSharedPreference1 = PreferenceManager.getDefaultSharedPreferences(mContext);
        visted.clear();
        int size = mSharedPreference1.getInt("visted_size", 0);

        for (int i = 0; i < size; i++) {
            visted.add(mSharedPreference1.getString("visted_" + i, null));
        }

    }

    /**
     * Loads Large Images from gallery
     ***/
    private Bitmap getScaledBitmap(String picturePath, int width, int height) {
        BitmapFactory.Options sizeOptions = new BitmapFactory.Options();
        sizeOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(picturePath, sizeOptions);

        int inSampleSize = calculateInSampleSize(sizeOptions, width, height);

        sizeOptions.inJustDecodeBounds = false;
        sizeOptions.inSampleSize = inSampleSize;

        return BitmapFactory.decodeFile(picturePath, sizeOptions);
    }

    /**
     * Loads Large Images from gallery
     ***/
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }
}

