package university.pace.mypace2.CalendarScreen;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.api.services.calendar.model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import university.pace.mypace2.R;


/**
 * Created by Jacob on 6/8/16.
 */
public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    private ArrayList<CalendarScreen.CalendarInfo> mDataset;
    //private List<com.github.sundeepk.compactcalendarview.domain.Event> mDataset;

    CalendarScreen in;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public Button addToCalendar;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.calendarTexted);
            addToCalendar = (Button) v.findViewById(R.id.addCal);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CalendarAdapter(ArrayList<CalendarScreen.CalendarInfo> myDataset, CalendarScreen in) {
        mDataset = myDataset;
        this.in = in;
    }

    /*// Provide a suitable constructor (depends on the kind of dataset)
    public CalendarAdapter(List<com.github.sundeepk.compactcalendarview.domain.Event> myDataset, CalendarScreen in) {
        mDataset = myDataset;
        this.in = in;
    }*/

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
        final Event event = mDataset.get(position).getEvent();


        //Log.e("EventDateTime line 69", event.getStart().getDateTime() + "");


        //Log.e("Line 78", event.getStart().getDateTime() + "");
        //Log.e("Line 79", event.getStart().getDate() + "");

        //DateTimeFormatter dateFormatter = ISODateTimeFormat.dateTime();
        //org.joda.time.DateTime dateTime = dateFormatter.parseDateTime(event.getStart().getDateTime().toString());
        //Log.e("Line 88", dateTime.toDate() + "");
        //Log.e("Line 81", event.getStart() + "");

        //info += mDataset.get(position).toString();

        info += event.getSummary() + "\n" +
                "Description: " + event.getDescription() + "\n" +
                "Date: " + event.getStart().getDate() + " \n " + event.getEnd().getDate();

        //TODO: Work here on getting this looking nicer

        //holder.mTextView.setText(mDataset.get(position).toString());
        holder.mTextView.setText(info);
        holder.addToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ContentValues events = new ContentValues();
                events.put(CalendarContract.Events.CALENDAR_ID, 1);

                events.put(CalendarContract.Events.TITLE, event.getSummary());
                events.put(CalendarContract.Events.DESCRIPTION, event.getDescription());
                event.setStart(event.getStart());
                events.put(CalendarContract.Events.DTSTART, event.getStart().getDate().getValue());
                events.put(CalendarContract.Events.DTEND, event.getEnd().getDate().getValue());
                String timeZone = TimeZone.getDefault().getID();
                events.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone);

                Uri baseUri;
                if (Build.VERSION.SDK_INT >= 8) {
                    baseUri = Uri.parse("content://com.android.calendar/events");
                } else {
                    baseUri = Uri.parse("content://calendar/events");
                }

                in.getContentResolver().insert(baseUri, events);


            }
        });

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}


