package university.pace.mypace2.PaceMaps;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import university.pace.mypace2.R;

/**
 * Created by Mrgds on 6/22/2016.
 */
public class NycBuildings {

    ArrayList<Building> nyc = new ArrayList<>();
    PaceMaps PaceBuilding;

    public NycBuildings(Context c) {
    /*Pass context of current Activity*/
        InputStream in = c.getResources().openRawResource(R.raw.nyc_buildings);


    /*Read in from text file all PLV Building names*/
        InputStreamReader read = new InputStreamReader(in);

        BufferedReader br = new BufferedReader(read);


        String line = " ";


        while (line != null) {
            try {
                line = br.readLine();

                Building pace = new Building(line);
                nyc.add(pace);
                if (line == null)
                    break;


            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("read in", line + "\t");
            Log.d("read in", nyc + "\t");

        }

    }

    public class Building {
        String name;


        public Building(String line) {

            this.name = line;
        }


        @Override
        public String toString() {
            return name;

        }


        public boolean isNYCBuilding(String find) {
                /*For-eachLOOP!     */
            for (Building item : nyc) {
                if (name.equalsIgnoreCase(find)) {
                    return true;
                }
            }
            return false;
        }
        //  i.equalsIgnoreCase(find);


        public LatLng NYClocation(String buildingName) {
            LatLng latlngSpot = null;
            PaceBuilding = new PaceMaps();
            Log.d("user read in", buildingName + "\t");

            if (isNYCBuilding(buildingName)) {

                switch (buildingName.toLowerCase()) {

                    case "182 broadway":
                        latlngSpot = PaceBuilding.PaceUniNYC_Broadway;
                        break;
                    case "broadway":
                        latlngSpot = PaceBuilding.PaceUniNYC_Broadway;
                        break;
                    case "106 fulton street":
                        latlngSpot = PaceBuilding.PaceUniNYC_Fulton;
                        break;
                    case "fulton street":
                        latlngSpot = PaceBuilding.PaceUniNYC_Fulton;
                        break;
                    case "163 william street":
                        latlngSpot = PaceBuilding.PaceUniNYC_William163;
                        break;
                    case "156 william street":
                        latlngSpot = PaceBuilding.PaceUniNYC_William156;
                        break;
                    case "55 john street":
                        latlngSpot = PaceBuilding.PaceUniNYC_JohnStreet;
                        break;
                    case "john street":
                        latlngSpot = PaceBuilding.PaceUniNYC_JohnStreet;
                        break;
                    case "maria's tower":
                        latlngSpot = PaceBuilding.PaceUniNYC_Maria;
                        break;
                    case "maria":
                        latlngSpot = PaceBuilding.PaceUniNYC_Maria;
                        break;
                    case "one pace plaza":
                        latlngSpot = PaceBuilding.PaceUniNYC_OnePacePlaza;
                        break;
                    case "one pace":
                        latlngSpot = PaceBuilding.PaceUniNYC_OnePacePlaza;
                        break;
                    case "41 parks row ":
                        latlngSpot = PaceBuilding.PaceUniNYC_ParksRow;
                        break;
                    case "parks row":
                        latlngSpot = PaceBuilding.PaceUniNYC_ParksRow;
                        break;

                    case "schimmel center":
                        latlngSpot = PaceBuilding.PaceUniNYC_Schimmel;
                        break;
                    case "schimmel":
                        latlngSpot = PaceBuilding.PaceUniNYC_Schimmel;
                        break;
                    case "henry birnbaum library":
                        latlngSpot = PaceBuilding.PaceUniNYC_Library;
                        break;
                    case "henry birnbaum":
                        latlngSpot = PaceBuilding.PaceUniNYC_Library;
                        break;
                    case "library":
                        latlngSpot = PaceBuilding.PaceUniNYC_Library;
                        break;
                    case "Barns & Nobles Bookstore":
                        latlngSpot = PaceBuilding.PaceUniNYC_Bookstore;
                        break;
                    case "Barns & Nobles":
                        latlngSpot = PaceBuilding.PaceUniNYC_Bookstore;
                        break;
                    case "the confucius institute":
                        latlngSpot = PaceBuilding.PaceUniNYC_Confucius;
                        break;
                    case "confucius institute":
                        latlngSpot = PaceBuilding.PaceUniNYC_Confucius;
                        break;
                    case "food":
                        latlngSpot = PaceBuilding.PaceUniNYC_Cafe101;
                     break;
                    case "Cafe 101":
                        latlngSpot = PaceBuilding.PaceUniNYC_Cafe101;
                        break;
                    case "Cafe":
                        latlngSpot = PaceBuilding.PaceUniNYC_Cafe101;
                        break;
                    case "office of student assistance":
                        latlngSpot = PaceBuilding.PaceUniPLV_OSA;
                        break;
                    case "OSA":
                        latlngSpot = PaceBuilding.PaceUniPLV_OSA;
                        break;
                     case "Tech Support":
                         latlngSpot = PaceBuilding.PaceUniNYC_IT;
                     break;
                    case "health center":
                        latlngSpot = PaceBuilding.PaceUniNYC_Health;
                        break;
                    case "nurse":
                        latlngSpot = PaceBuilding.PaceUniNYC_Health;
                        break;
                    case "SSS":
                        latlngSpot = PaceBuilding.PaceUniNYC_SSS;
                        break;
                    case "Student Suport Services":
                        latlngSpot = PaceBuilding.PaceUniNYC_SSS;
                        break;
                    case "court yard":
                        latlngSpot = PaceBuilding.PaceUniNYC_OnePace_Courtyard;
                        break;
                    case "one pace court yard":
                        latlngSpot = PaceBuilding.PaceUniNYC_OnePace_Courtyard;
                        break;

                    case " Center for Undergraduate Research Experiences":
                        latlngSpot = PaceBuilding.PaceUniNYC_SSS;
                        break;

                    case "CURE":
                        latlngSpot = PaceBuilding.PaceUniNYC_SSS;
                        break;

                    case "Gym":
                        latlngSpot = PaceBuilding.PaceUniNYC_OnePacePlaza;
                        break;
                    case " Cardio Room":
                        latlngSpot = PaceBuilding.PaceUniNYC_OnePacePlaza;
                        break;
                    case "Weight Room":
                        latlngSpot = PaceBuilding.PaceUniNYC_OnePacePlaza;
                        break;

                    case "Financial Aid":
                        latlngSpot = PaceBuilding.PaceUniNYC_OSA;
                        break;
                    case "Pace ID Card":
                        latlngSpot = PaceBuilding.PaceUniNYC_OSA;
                        break;

                    case "Starbucks":
                        latlngSpot = PaceBuilding.PaceUniNYC_Cafe101;
                        break;
                    case "Pforzheimer Honors College":
                        latlngSpot = PaceBuilding.PaceUniNYC_Honors;
                        break;
                    case "Center for Community Action and Research":
                        latlngSpot = PaceBuilding.PaceUniNYC_CCAR;
                        break;
                    case "CCAR":
                        latlngSpot = PaceBuilding.PaceUniNYC_CCAR;
                        break;
                    case "Science Departments":
                        latlngSpot = PaceBuilding.PaceUniNYC_OnePacePlaza;
                        break;
                    case "labs":
                        latlngSpot = PaceBuilding.PaceUniNYC_OnePacePlaza;
                        break;
                    case "IT":
                        latlngSpot = PaceBuilding.PaceUniNYC_IT;
                        break;
                    case "HR":
                        latlngSpot = PaceBuilding.PaceUniNYC_William156;
                        break;
                    case "Human Resources":
                        latlngSpot = PaceBuilding.PaceUniNYC_William156;
                        break;
                    case "Tutoring":
                        latlngSpot = PaceBuilding.PaceUniNYC_Tutoring;
                        break;

                    case "Tutoring Center":
                        latlngSpot = PaceBuilding.PaceUniNYC_Tutoring;
                        break;

                    case "Dean for Students NYC":
                        latlngSpot = PaceBuilding.PaceUniNYC_Confucius;
                        break;
                    case "LGBTQA":
                        latlngSpot = PaceBuilding.PaceUniNYC_Confucius;
                        break;

                    case "Social Justice Center":
                        latlngSpot = PaceBuilding.PaceUniNYC_Confucius;
                        break;
                    case "Office of Multicultural Affairs":
                        latlngSpot = PaceBuilding.PaceUniNYC_Confucius;
                        break;
                    case "Student Government Association":
                        latlngSpot = PaceBuilding.PaceUniNYC_Confucius;
                        break;
                    case "Career Services":
                        latlngSpot = PaceBuilding.PaceUniNYC_ParksRow;
                        break;
                    case "Dyson Administration and Advisement":
                        latlngSpot = PaceBuilding.PaceUniNYC_ParksRow;
                        break;
                    case "Dyson":
                        latlngSpot = PaceBuilding.PaceUniNYC_ParksRow;
                        break;
                    case "Seidenberg":
                        latlngSpot = PaceBuilding.PaceUniNYC_William163;
                        break;

                    case "International Programs and Services":
                        latlngSpot = PaceBuilding.PaceUniNYC_William163;
                        break;
                    case "Counseling Center":
                        latlngSpot = PaceBuilding.PaceUniNYC_William156;
                        break;

                    case "140 William Street":
                        latlngSpot = PaceBuilding.PaceUniNYC_William140;
                        break;

                    case "33 Beekman":
                        latlngSpot = PaceBuilding.PaceUniNYC_BEEKMAN;
                        break;







                }


            }
            Log.d("return a spot ", "NYC");
            return latlngSpot;

        }
    }

}
