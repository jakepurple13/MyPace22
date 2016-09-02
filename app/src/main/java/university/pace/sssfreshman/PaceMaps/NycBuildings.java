package university.pace.sssfreshman.PaceMaps;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import university.pace.sssfreshman.R;

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
                    case "barns & nobles bookstore":
                        latlngSpot = PaceBuilding.PaceUniNYC_Bookstore;
                        break;
                    case "barns & nobles":
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
                    case "cafe 101":
                        latlngSpot = PaceBuilding.PaceUniNYC_Cafe101;
                        break;
                    case "cafe":
                        latlngSpot = PaceBuilding.PaceUniNYC_Cafe101;
                        break;
                    case "office of student assistance":
                        latlngSpot = PaceBuilding.PaceUniPLV_OSA;
                        break;
                    case "osa":
                        latlngSpot = PaceBuilding.PaceUniPLV_OSA;
                        break;
                    case "tech support":
                         latlngSpot = PaceBuilding.PaceUniNYC_IT;
                     break;
                    case "health center":
                        latlngSpot = PaceBuilding.PaceUniNYC_Health;
                        break;
                    case "nurse":
                        latlngSpot = PaceBuilding.PaceUniNYC_Health;
                        break;
                    case "sss":
                        latlngSpot = PaceBuilding.PaceUniNYC_SSS;
                        break;
                    case "student suport services":
                        latlngSpot = PaceBuilding.PaceUniNYC_SSS;
                        break;
                    case "court yard":
                        latlngSpot = PaceBuilding.PaceUniNYC_OnePace_Courtyard;
                        break;
                    case "one pace court yard":
                        latlngSpot = PaceBuilding.PaceUniNYC_OnePace_Courtyard;
                        break;

                    case "cure":
                        latlngSpot = PaceBuilding.PaceUniNYC_SSS;
                        break;

                    case "gym":
                        latlngSpot = PaceBuilding.PaceUniNYC_OnePacePlaza;
                        break;
                    case "cardio room":
                        latlngSpot = PaceBuilding.PaceUniNYC_OnePacePlaza;
                        break;
                    case "weight room":
                        latlngSpot = PaceBuilding.PaceUniNYC_OnePacePlaza;
                        break;

                    case "financial aid":
                        latlngSpot = PaceBuilding.PaceUniNYC_OSA;
                        break;
                    case "pace id card":
                        latlngSpot = PaceBuilding.PaceUniNYC_OSA;
                        break;

                    case "starbucks":
                        latlngSpot = PaceBuilding.PaceUniNYC_Cafe101;
                        break;
                    case "pforzheimer honors college":
                        latlngSpot = PaceBuilding.PaceUniNYC_Honors;
                        break;
                    case "center for community action and research":
                        latlngSpot = PaceBuilding.PaceUniNYC_CCAR;
                        break;
                    case "ccar":
                        latlngSpot = PaceBuilding.PaceUniNYC_CCAR;
                        break;
                    case "science departments":
                        latlngSpot = PaceBuilding.PaceUniNYC_OnePacePlaza;
                        break;
                    case "labs":
                        latlngSpot = PaceBuilding.PaceUniNYC_OnePacePlaza;
                        break;
                    case "it":
                        latlngSpot = PaceBuilding.PaceUniNYC_IT;
                        break;
                    case "hr":
                        latlngSpot = PaceBuilding.PaceUniNYC_William156;
                        break;
                    case "human resources":
                        latlngSpot = PaceBuilding.PaceUniNYC_William156;
                        break;
                    case "tutoring":
                        latlngSpot = PaceBuilding.PaceUniNYC_Tutoring;
                        break;

                    case "tutoring center":
                        latlngSpot = PaceBuilding.PaceUniNYC_Tutoring;
                        break;

                    case "dean for students nyc":
                        latlngSpot = PaceBuilding.PaceUniNYC_Confucius;
                        break;
                    case "lgbtqa":
                        latlngSpot = PaceBuilding.PaceUniNYC_Confucius;
                        break;

                    case "social justice center":
                        latlngSpot = PaceBuilding.PaceUniNYC_Confucius;
                        break;
                    case "office of multicultural affairs":
                        latlngSpot = PaceBuilding.PaceUniNYC_Confucius;
                        break;
                    case "student government association":
                        latlngSpot = PaceBuilding.PaceUniNYC_Confucius;
                        break;
                    case "career services":
                        latlngSpot = PaceBuilding.PaceUniNYC_ParksRow;
                        break;
                    case "dyson administration and advisement":
                        latlngSpot = PaceBuilding.PaceUniNYC_ParksRow;
                        break;
                    case "dyson":
                        latlngSpot = PaceBuilding.PaceUniNYC_ParksRow;
                        break;
                    case "seidenberg":
                        latlngSpot = PaceBuilding.PaceUniNYC_William163;
                        break;

                    case "international programs and services":
                        latlngSpot = PaceBuilding.PaceUniNYC_William163;
                        break;
                    case "counseling center":
                        latlngSpot = PaceBuilding.PaceUniNYC_William156;
                        break;

                    case "140 william street":
                        latlngSpot = PaceBuilding.PaceUniNYC_William140;
                        break;

                    case "33 beekman":
                        latlngSpot = PaceBuilding.PaceUniNYC_BEEKMAN;
                        break;

                    case "beekman":
                        latlngSpot = PaceBuilding.PaceUniNYC_BEEKMAN;
                        break;






                }


            }
            Log.d("return a spot ", "NYC");
            return latlngSpot;

        }
    }

}
