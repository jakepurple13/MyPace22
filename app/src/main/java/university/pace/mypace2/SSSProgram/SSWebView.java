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
    String linkstring = Constants.SSSPAGE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssweb_view);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        WebView viewfrag = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = viewfrag.getSettings();
        webSettings.setJavaScriptEnabled(true);
        viewfrag.loadUrl(linkstring + b.get("tag"));
    }


}
