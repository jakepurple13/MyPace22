package university.pace.mypace2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ImportantNumbers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_numbers);

        ArrayList<ImportantInfo> al = new ArrayList<>();

        InputStreamReader is = new InputStreamReader(getResources().openRawResource(R.raw.important_numbers));

        BufferedReader br = new BufferedReader(is);

        String line = " ";
        String number = " ";
        while(line!=null) {
            try {
                line = br.readLine();
                number = br.readLine();

                ImportantInfo ii = new ImportantInfo(line, number);
                al.add(ii);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("asdfkjh", line);
            
        }

    }







    public class ImportantInfo {
        String name;
        String number;

        public ImportantInfo(String name, String number) {
            this.name = name;
            this.number = number;
        }

    }
}
