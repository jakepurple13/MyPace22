package university.pace.mypace2.SSSProgram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import university.pace.mypace2.Constants;
import university.pace.mypace2.R;

public class SSWebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssweb_view);
        String linkstring = Constants.SSSPAGE + "#eli";
        webviewUser(linkstring);

    }

    public void webviewUser(String LinkTo) {
        WebView viewfrag = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = viewfrag.getSettings();
        webSettings.setJavaScriptEnabled(true);
        viewfrag.loadUrl(LinkTo);

    }



}
