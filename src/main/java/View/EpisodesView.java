package View;

import Model.Episode;

import java.util.ArrayList;

/**
 * Created by simon on 2017-01-03.
 */
public class EpisodesView {

    private ArrayList<Episode> ae;

    public EpisodesView(ArrayList<Episode> ae){
        this.ae = ae;
    }

    public void printEpisodes(){

        for (Episode e: ae
                ) {
            System.out.println(e.getProgramName());

            System.out.println("Starts: " + e.getStartDateTime());
            System.out.println("Ends: " + e.getEndDateTime());
            System.out.println("Has Passed = " + e.getHasPassed());
        }
    }
}
