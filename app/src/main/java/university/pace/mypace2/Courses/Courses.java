package university.pace.mypace2.Courses;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.WorkSource;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.services.calendar.model.Event;
import com.google.common.collect.Table;
import com.viethoa.RecyclerViewFastScroller;
import com.viethoa.models.AlphabetItem;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import university.pace.mypace2.R;

public class Courses extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewFastScroller fastScroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_courses);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_course);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        fastScroller = (RecyclerViewFastScroller) findViewById(R.id.fast_scroller_courses);
        fastScroller.setRecyclerView(mRecyclerView);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        /**  fab.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show();
        }
        });
         **/
        List<String> courselist = new ArrayList<String>(); //course list
        /**       **/
        AssetManager am = getAssets();
        InputStream is = null;

        try {
            is = am.open("courses_fa16.xls");
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            Workbook wb = Workbook.getWorkbook(is);
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
                courselist.add(data);

            }
        } catch (BiffException e) {
            e.printStackTrace();
            Log.d("Error", "Bliff");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Error", "While reading" + e);
        }

        ArrayList<AlphabetItem> mAlphabetItems = new ArrayList<>();
        List<String> strAlphabets = new ArrayList<>();
        for (int i = 0; i < courselist.size(); i++) {
            String name = courselist.get(i); //.name
            if (name == null || name.trim().isEmpty())
                continue;

            String word = name.substring(0, 1);
            if (!strAlphabets.contains(word)) {
                strAlphabets.add(word);
                mAlphabetItems.add(new AlphabetItem(i, word, false));
            }
        }

        fastScroller.setUpAlphabet(mAlphabetItems);

    }


    public class Course {
        String campus_description;
        String course_number;
        String course_level;
        String subject_code;
        String subject_description;
        String term_desc;
        String title;

        public Course(String campus_description,
                      String course_number, String course_level, String subject_code, String subject_description, String term_desc,
                      String title) {
            this.campus_description = campus_description;
            this.course_number = course_number;
            this.course_level = course_level;
            this.subject_code = subject_code;
            this.subject_description = subject_description;
            this.term_desc = term_desc;
            this.title = title;
        }

        @Override
        public String toString() {
            return campus_description + "\nCourse Number" + course_number
                    + course_level + subject_code + subject_description + term_desc + title;
        }


    }
}
/**
 * public class InfoCompare implements Comparator<Course> {
 * public int compare(Course e1, Course e2) {
 * return e1.subject_code.compareTo(e2.subject_code);
 * }
 * } }
 **/


// adds in Alphabetical scroller


// adds in Alphabetical scroller end












