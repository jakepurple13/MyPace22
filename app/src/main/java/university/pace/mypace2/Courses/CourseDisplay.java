package university.pace.mypace2.Courses;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import university.pace.mypace2.R;

public class CourseDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_display);

        ArrayList<String> al = new ArrayList<>();

        AssetManager am = getAssets();
        InputStream is = null;

       /* try {
            is = am.open();
        } catch (IOException e1) {
            e1.printStackTrace();
        }*/

        try {
            Workbook wb = Workbook.getWorkbook(new File(String.valueOf(R.raw.courses_fa16)));
            // Get the first sheet
            // read in rows and colums
            Sheet sheet = wb.getSheet(0);
            int row = sheet.getRows();
            int col = sheet.getColumns();
            // Loop over column and lines
            String data = "";
            /**  goes through the xl sheet*/
            for (int i = 0; i < row; i++) {
                for (int c = 0; c < col; col++) {
                    Cell cursor = sheet.getCell(c, i);
                    data = cursor.getContents();
                }
                al.add(data);

            }
        } catch (BiffException e) {
            e.printStackTrace();
            Log.d("Error", "Bliff");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Error", "While reading" + e);
        }


    }
}
