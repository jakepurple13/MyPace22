package university.pace.mypace2;

/**
 * Created by Mrgds on 8/5/2016.
 */

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
;

//TODO:   WORK ON GETTING WEB VIEW UP INFO


public class SupportFragment extends Fragment {


    public SupportFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.ssswebpageview, container, false);
        WebView viewfrag = (WebView) rootView.findViewById(R.id.SSSwebPage);
        WebSettings webSettings = viewfrag.getSettings();
        webSettings.setJavaScriptEnabled(true);
        viewfrag.loadUrl(Constants.SSSpage);


        return rootView;


    }
}
