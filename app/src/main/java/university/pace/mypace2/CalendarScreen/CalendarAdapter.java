package university.pace.mypace2.CalendarScreen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.api.services.calendar.model.Event;

import java.util.ArrayList;

import university.pace.mypace2.R;


/**
 * Created by Jacob on 6/8/16.
 */
public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    private ArrayList<CalendarScreen.CalendarInfo> mDataset;

    CalendarScreen in;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;


        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.calendarTexted);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CalendarAdapter(ArrayList<CalendarScreen.CalendarInfo> myDataset, CalendarScreen in) {
        mDataset = myDataset;
        this.in = in;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CalendarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calendartext, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        String info = "";
        Event event = mDataset.get(position).getEvent();


        //Log.e("EventDateTime line 69", event.getStart().getDateTime() + "");


        //Log.e("Line 78", event.getStart().getDateTime() + "");
        //Log.e("Line 79", event.getStart().getDate() + "");

        //DateTimeFormatter dateFormatter = ISODateTimeFormat.dateTime();
        //org.joda.time.DateTime dateTime = dateFormatter.parseDateTime(event.getStart().getDateTime().toString());
        //Log.e("Line 88", dateTime.toDate() + "");
        //Log.e("Line 81", event.getStart() + "");


        info += event.getSummary() + "\n" +
                "Description: " + event.getDescription() + "\n" +
                "Date: " + event.getStart().getDate() + " - " + event.getEnd().getDate() + "\n" +
                "From: " + event.getStart().getDateTime();


        //TODO: Work here on getting this looking nicer

        //holder.mTextView.setText(mDataset.get(position).toString());
        holder.mTextView.setText(info);


    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}


