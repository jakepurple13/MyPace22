package university.pace.mypace2.PaceMaps;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.viethoa.models.AlphabetItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import university.pace.mypace2.R;

/**
 * Created by Mrgds on 6/21/2016.
 */
public class Buildings {
    PaceMaps PaceBuilding;
    ArrayList<Building> plv = new ArrayList<>();

    public Buildings(Context context) {
  /*Pass context of current Activity*/
        InputStream in = context.getResources().openRawResource(R.raw.plv_buildings);


        /*Read in from text file all PLV Building names*/
        InputStreamReader read = new InputStreamReader(in);

        BufferedReader br = new BufferedReader(read);


        String line = " ";


        while (line != null) {
            try {
                line = br.readLine();

                Building pace = new Building(line);
                plv.add(pace);
                if (line == null)
                    break;


            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("read in", line + "\t");
            Log.d("read in", plv + "\t");

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

        public boolean isPLVBuilding(String find) {
                /*For-eachLOOP!     */
            for (Building item : plv) {
                if (name.equalsIgnoreCase(find)) {
                    return true;
                }
            }
            return false;
        }


        public LatLng PaceLocation(String buildingName) {

            PaceBuilding = new PaceMaps();
            LatLng latlngSpot = null;
            Log.d("user read in", buildingName + "\t");

            if (isPLVBuilding(buildingName)) {

                switch (buildingName.toLowerCase()) {

                    case "miller":
                        latlngSpot = PaceBuilding.PaceUniPLV_Miller;

                        break;
                    case "miller hall":
                        latlngSpot = PaceBuilding.PaceUniPLV_Miller;
                        break;
                    case "lienhard hall":
                        latlngSpot = PaceBuilding.PaceUniPLV_Lienhard;
                        break;
                    case "lienhard":
                        latlngSpot = PaceBuilding.PaceUniPLV_Lienhard;
                        break;
                    case "nursing":
                        latlngSpot = PaceBuilding.PaceUniPLV_Lienhard;
                        break;
                    case "marks hall":
                        latlngSpot = PaceBuilding.PaceUniPLV_Marks;
                        break;
                    case "marks":
                        latlngSpot = PaceBuilding.PaceUniPLV_Marks;
                        break;
                    case "math department":
                        latlngSpot = PaceBuilding.PaceUniPLV_Marks;
                        break;
                    case "dyson hall":
                        latlngSpot = PaceBuilding.PaceUniPLV_Dyson;
                        break;
                    case "dyson":
                        latlngSpot = PaceBuilding.PaceUniPLV_Dyson;
                        Log.d("latlngSpot", PaceBuilding.PaceUniPLV_Dyson.toString() + "2");
                        break;
                    case "science department":
                        latlngSpot = PaceBuilding.PaceUniPLV_Dyson;
                        break;
                    case "wilcox hall":
                        latlngSpot = PaceBuilding.PaceUniPLV_Wilcox;
                        break;
                    case "goldstein academic center":
                        latlngSpot = PaceBuilding.PaceUniPLV_Goldstein;
                        break;
                    case "goldstein ":
                        latlngSpot = PaceBuilding.PaceUniPLV_Goldstein;
                        break;
                    case "goldstein academic":
                        latlngSpot = PaceBuilding.PaceUniPLV_Goldstein;
                        break;

                    case "lubin":
                        latlngSpot = PaceBuilding.PaceUniPLV_GoldsteinGym;
                        break;
                    case "seidenberg":
                        latlngSpot = PaceBuilding.PaceUniPLV_GoldsteinGym;
                        break;
                    case "goldstein fitness center":
                        latlngSpot = PaceBuilding.PaceUniPLV_GoldsteinGym;
                        break;
                    case "gym":
                        latlngSpot = PaceBuilding.PaceUniPLV_GoldsteinGym;
                        break;
                    case "kessel":
                        latlngSpot = PaceBuilding.PaceUniPLV_Kessel;
                        break;
                    case "kessel center":
                        latlngSpot = PaceBuilding.PaceUniPLV_Kessel;
                        break;
                    case "mortola library":
                        latlngSpot = PaceBuilding.PaceUniPLV_Library;
                        break;
                    case "mortola":
                        latlngSpot = PaceBuilding.PaceUniPLV_Library;
                        break;
                    case "library":
                        latlngSpot = PaceBuilding.PaceUniPLV_Library;
                        break;
                    case "north hall":
                        latlngSpot = PaceBuilding.PaceUniPLV_North;
                        break;
                    case "north":
                        latlngSpot = PaceBuilding.PaceUniPLV_North;
                        break;

                    case "martin hall":
                        latlngSpot = PaceBuilding.PaceUniPLV_Martin;
                        break;
                    case "martin":
                        latlngSpot = PaceBuilding.PaceUniPLV_Martin;
                        break;
                    case "elm hall":

                        latlngSpot = PaceBuilding.PaceUniPLV_Elm;
                        break;
                    case "elm":
                        latlngSpot = PaceBuilding.PaceUniPLV_Elm;
                        break;

                    case "alumni hall":
                        latlngSpot = PaceBuilding.PaceUniPLV_Alumni;
                        break;
                    case "alumni":
                        latlngSpot = PaceBuilding.PaceUniPLV_Alumni;
                        break;
                    case "choate house":
                        latlngSpot = PaceBuilding.PaceUniPLV_Choate;
                        break;
                    case "choate":
                        latlngSpot = PaceBuilding.PaceUniPLV_Choate;
                        break;
                    case "pink house":
                        latlngSpot = PaceBuilding.PaceUniPLV_Choate;
                        break;
                    case "osa":
                        latlngSpot = PaceBuilding.PaceUniPLV_OSA;
                        break;
                    case "office of student assistance":
                        latlngSpot = PaceBuilding.PaceUniPLV_OSA;
                        break;
                    case "choate pond":
                        latlngSpot = PaceBuilding.PaceUniPLV_Pond;
                        break;
                    case "pond":
                        latlngSpot = PaceBuilding.PaceUniPLV_Pond;
                        break;
                    case "food":
                        latlngSpot = PaceBuilding.PaceUniPLV_Martin;
                        break;
                    case "class":
                        latlngSpot = PaceBuilding.PaceUniPLV_Miller;
                        break;
                    case "Goldstein":
                        latlngSpot = PaceBuilding.PaceUniPLV_GoldsteinGym;
                        break;
                    case "bookstore":
                        latlngSpot = PaceBuilding.PaceUniPLV_Kessel;
                        break;
                    case "health center":
                        latlngSpot = PaceBuilding.PaceUniPLV_GoldsteinGym;
                        break;
                    case "tech support":
                        latlngSpot = PaceBuilding.PaceUniPLV_Wilcox;
                        break;
                    case "it":
                        latlngSpot = PaceBuilding.PaceUniPLV_Wilcox;
                        break;
                    case "computer science department":
                        latlngSpot = PaceBuilding.PaceUniPLV_Goldstein;
                        break;
                    case "english department":
                        latlngSpot = PaceBuilding.PaceUniPLV_Choate;
                        break;

                    case "basketball courts":
                        latlngSpot = PaceBuilding.PaceUniPLV_GoldsteinGym;
                        break;

                    case "baseball field":
                        latlngSpot = PaceBuilding.PaceUniPLV_Baseball;
                        break;

                    case "football field":
                        latlngSpot = PaceBuilding.PaceUniPLV_Football;
                        break;
                    case "fit trail":
                        latlngSpot = PaceBuilding.PaceUniPLV_FITNESSTRAIL;
                        break;

                    case "bus stop":
                        latlngSpot = PaceBuilding.PaceUniPLV_BUS_WILCOX;
                        break;
                    case "bus":
                        latlngSpot = PaceBuilding.PaceUniPLV_BUS_WILCOX;
                        break;
                    case "shuttle":
                        latlngSpot = PaceBuilding.PaceUniPLV_BUS_MILLER;
                        break;


                }


            }

            Log.d("return a spot ", "PLV");
            return latlngSpot;

        }


    }



    }

