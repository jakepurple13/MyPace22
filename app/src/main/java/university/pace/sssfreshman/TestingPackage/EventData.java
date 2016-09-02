package university.pace.sssfreshman.TestingPackage;

/**
 * Created by Mrgds on 6/23/2016.
 */

public class EventData {

    int id;

    String eventname, eventtime, eventday, eventid;


    public EventData(String name, String time, String day, String eid) {

        eventname = name;
        eventtime = time;
        eventday = day;
        eventid = eid;

    }


    public void setId(int id) {

    }

    public int getId() {
        return this.id;
    }


    public void setName(String name) {
        this.eventname = name;

    }

    public String getName() {
        return this.eventname;

    }

    public void setEventId(String id) {
        this.eventid = id;

    }

    public String getEventid() {
        return this.eventid;

    }


    public void setTime(String time) {
        this.eventtime = time;

    }

    public String getEventTime() {
        return this.eventtime;

    }

    public void setDay(String eventday) {
        this.eventday = eventday;

    }

    public String getDay() {
        return this.eventday;

    }

}
