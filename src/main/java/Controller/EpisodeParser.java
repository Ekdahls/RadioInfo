package Controller;

import Model.Episode;
import org.w3c.dom.Document;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by simon on 2017-01-03.
 */
public class EpisodeParser extends DocumentParser{

    private XPath xPath;
    private Document doc;
    private ArrayList<Episode> episodeArrayList = new ArrayList<>();
    private final String schedule = "/sr/schedule/scheduledepisode";

    private String uri = "http://api.sr.se/v2/scheduledepisodes?channelid=132&page={page}";


    public EpisodeParser(){
        setupRestTemplate();
        xPath = getxPath();

    }

    public void run(){

        try {
            //Setup doc + header to get total pages
            setupDocument(1, uri);
            doc = getDoc();
            getHeader();
            //collect the data
            collectData();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Episode> getEpisodeArrayList() {
        return episodeArrayList;
    }

    public LocalDateTime convertToDateTime(String s){
        String time = s.replace("Z", "");
        return LocalDateTime.parse(time);
    }

    public void collectData() throws XPathExpressionException {

        LocalDateTime timeNow = LocalDateTime.now();

        for (int i = 1; i <= getTotalPages(); i++) {
            if(i!=1) {
                setupDocument(i, uri);
                doc = getDoc();
                getHeader();
            }

            if(getPage() == getTotalPages()){
                setSize(getTotalHits() % getSize());
            }

            int tempSize = getSize();

            for (int j = 0; j < tempSize; j++) {

                LocalDateTime startTime = convertToDateTime((xPath.evaluate(schedule+"[" + (j + 1) + "]/starttimeutc", doc)));
                LocalDateTime endTime = convertToDateTime((xPath.evaluate(schedule+"[" + (j + 1) + "]/endtimeutc", doc)));

                //check to see if program is withing 12 hours of timeNow.
                if(timeNow.minusHours(12).isBefore(startTime) && timeNow.plusHours(12).isAfter(endTime)) {

                    String title = (xPath.evaluate(schedule + "[" + (j + 1) + "]/title", doc));
                    Episode e = new Episode(title);
                    e.setDescription(xPath.evaluate(schedule + "[" + (j + 1) + "]/description", doc));
                    e.setStartDateTime(xPath.evaluate(schedule + "[" + (j + 1) + "]/starttimeutc", doc));
                    e.setEndDateTime(xPath.evaluate(schedule + "[" + (j + 1) + "]/endtimeutc", doc));
                    e.setProgramId(Integer.parseInt(xPath.evaluate(schedule + "[" + (j + 1) + "]/program/@id", doc)));
                    e.setProgramName(xPath.evaluate(schedule + "[" + (j + 1) + "]/program/@name", doc));
                    e.setChannelId(Integer.parseInt(xPath.evaluate(schedule + "[" + (j + 1) + "]/channel/@id", doc)));
                    e.setChannelName(xPath.evaluate(schedule + "[" + (j + 1) + "]/channel/@name", doc));
                    e.setImageUrl(xPath.evaluate(schedule + "[" + (j + 1) + "]/imageurl", doc));
                    e.setImageUrlTemplate(xPath.evaluate(schedule + "[" + (j + 1) + "]/imageurltemplate", doc));

                    if(timeNow.isAfter(endTime)){
                        e.setHasPassed(true);
                    }

                    //Add episodes to ArrayList
                    episodeArrayList.add(e);
                }
            }
        }


    }


}
