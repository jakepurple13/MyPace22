package university.pace.mypace2.SSSProgram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import university.pace.mypace2.Constants;
import university.pace.mypace2.R;

public class SSWebView extends AppCompatActivity {
    String newString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssweb_view);
        String linkstring = Constants.SSSPAGE;
        webviewUser(linkstring);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newString = null;
            } else {
                newString = extras.getString("#academicservices");
            }
        } else {
            newString = (String) savedInstanceState.getSerializable("#academicservices");
        }
    }

    public void webviewUser(String LinkTo) {
        WebView viewfrag = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = viewfrag.getSettings();
        webSettings.setJavaScriptEnabled(true);
        viewfrag.loadUrl(LinkTo + newString);

    }



}
