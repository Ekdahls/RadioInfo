package View;

import Controller.Collector;
import Model.Channel;
import com.ou2.MainFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Created by simon on 2017-01-03.
 */
public class ChannelsView {

    private JList<String> channelList = new JList<>();
    DefaultListModel<String> listModel = new DefaultListModel<String>();
    private boolean clicked = false;
    private EpisodesView episodesView;
    private Collector collector;

    public ChannelsView(Collector collector){
        this.collector = collector;

        setup();
        episodesView = new EpisodesView(collector.getEpisodeParser().getEpisodeArrayList());
    }

    public void setup(){
        //clear list
        channelList.setListData(new String[0]);
        //Add mouselistener to list
        channelList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 1) {
                    int index = list.locationToIndex(evt.getPoint());

                    System.out.println(index + " : Index was clicked");

                    collector.updateCurrentChannelIdFromListIndex(collector.getChannelArrayList().get(index).getId());
//                    collector.collectEpisodes();
                    MainFrame.panel2.add(episodesView.getEpisodeTable());
                    MainFrame.panel2.revalidate();

                    // Double-click detected
                }else if (evt.getClickCount() == 2) {

                    System.out.println("Double click was detected");
                }
            }
        });

        for (Channel channel : collector.channelArrayList) {
            listModel.addElement(channel.getName());
        }
        channelList.setModel(listModel);
    }

    public JList getChannelList(){
        return channelList;
    }

    public boolean getClicked(){
        return clicked;
    }
    public void setClicked(boolean bool){
        clicked = bool;
    }
}
