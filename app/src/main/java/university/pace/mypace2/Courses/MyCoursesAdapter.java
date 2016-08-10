package university.pace.mypace2.Courses;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.viethoa.RecyclerViewFastScroller;

import java.util.ArrayList;

import university.pace.mypace2.ImportantNumbersScreen.ImportantNumbers;
import university.pace.mypace2.R;

/**
 * Created by Mrgds on 8/10/2016.
 */
public class MyCoursesAdapter extends RecyclerView.Adapter<MyCoursesAdapter.ViewHolder> implements RecyclerViewFastScroller.BubbleTextGetter {
    private ArrayList<Courses.Course> mDataset;
    Courses pacecourse;
    Courses.Course course;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item pacecourse a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a strpacecourseg in this case
        public TextView mTextView;
        public ImageView iv;


        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textView);
            iv = (ImageView) v.findViewById(R.id.callArrow);


        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyCoursesAdapter(ArrayList<Courses.Course> myDataset, Courses pacecourse) {
        mDataset = myDataset;
        this.pacecourse = pacecourse;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyCoursesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.courses_textview, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).toString());

        View.OnClickListener von = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //something

            }
        };

        holder.mTextView.setOnClickListener(von);
        holder.iv.setOnClickListener(von);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    @Override
    public String getTextToShowInBubble(int pos) {
        if (pos < 0 || pos >= mDataset.size())
            return null;

        String name = mDataset.get(pos).subject_code;
        if (name == null || name.length() < 1)
            return null;

        return mDataset.get(pos).subject_code.substring(0, 1);
    }

}
