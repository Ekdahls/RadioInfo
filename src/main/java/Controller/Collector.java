package Controller;

import Model.Channel;
import Model.Episode;

import javax.xml.xpath.XPathExpressionException;
import java.util.ArrayList;

/**
 * Created by simon on 2017-01-09.
 */
public class Collector implements Runnable {

    private ChannelParser channelParser;
    private EpisodeParser episodeParser;

    public  ArrayList<Channel> channelArrayList;
    public  ArrayList<Episode> episodeArrayList;

    private int currentChannelId = 0;

    public static boolean running = true;

    public Collector(){

        channelParser = new ChannelParser();
        channelParser.init();
        channelArrayList = channelParser.getChannelList();
        episodeParser = new EpisodeParser();
        try {
            episodeParser.init(channelArrayList.get(0).getId());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
//        collectEpisodes();
        episodeArrayList = episodeParser.getEpisodeArrayList();
    }


    public void updateCurrentChannelIdFromListIndex(int index){

        currentChannelId = channelArrayList.get(index).getId();

    }

    public void updateEpisodeParser(){
        episodeParser.updateChannelIndex(currentChannelId);
        try {
            episodeParser.init(currentChannelId);
            episodeArrayList = episodeParser.getEpisodeArrayList();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    public EpisodeParser getEpisodeParser(){
        return episodeParser;
    }

    public ArrayList<Channel> getChannelArrayList() {
        return channelArrayList;
    }

    public ArrayList<Episode> getEpisodeArrayList() {
        return episodeArrayList;
    }

    @Override
    public void run() {

        while (running) {

//            collectEpisodes();
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
