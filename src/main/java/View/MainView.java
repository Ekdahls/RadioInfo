package View;

import Controller.Collector;
import Model.Channel;
import Model.Episode;
import com.ou2.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


/**
 * Created by simon on 2017-01-03.
 */
public class MainView {

    private JList<String> channelList = new JList<>();
    private JList<String> episodeList = new JList<>();
    DefaultListModel<String> listModel = new DefaultListModel<String>();
    DefaultListModel<String> listModel2 = new DefaultListModel<String>();
    private boolean clicked = false;
    private EpisodesView episodesView;
    private Collector collector;

    private JTable episodeTable;
    DefaultTableModel tableModel = new DefaultTableModel();
    private ArrayList<Episode> ae;

    public MainView(Collector collector){
        this.collector = collector;
        setup();
//        episodesView = new EpisodesView(collector.getEpisodeParser().getEpisodeArrayList());
    }

    public void setup(){
        setupChannelList();
        updateChannelList();

        setupTable();
        updateEpisodesTable();

        setupEpisodeList();
    }

    public void setupChannelList(){
        channelList.setListData(new String[0]);
        //Add mouselistener to list
        channelList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 1) {
                    int index = list.locationToIndex(evt.getPoint());

                    System.out.println(index + " : Index was clicked");

                    collector.updateCurrentChannelIdFromListIndex(index);
                    collector.updateEpisodeParser();
                    updateEpisodesTable();
                    MainFrame.panel2.revalidate();

//                    collector.updateCurrentChannelIdFromListIndex(ac.get(index).getId());
////                    collector.collectEpisodes();
//
//                    MainFrame.panel2.add(episodesView.getEpisodeTable());
//                    MainFrame.panel2.revalidate();

                    // Double-click detected
                }else if (evt.getClickCount() == 2) {

                    System.out.println("Double click was detected");
                }
            }
        });
    }

    public void updateChannelList(){

        for (Channel channel : collector.getChannelArrayList()) {
            listModel.addElement(channel.getName());
        }
        channelList.setModel(listModel);
    }

   public void setupEpisodeList(){

        listModel2.addElement("Hello world");
        episodeList.setModel(listModel2);
    }

    public void setupTable(){

        //create edited table model
        tableModel = new DefaultTableModel() {

            String[] columnNames = new String[] {
                    "Name", "Start time", "End time", "Description"};

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }

            @Override
            public int getColumnCount() {
                return columnNames.length;
            }

            @Override
            public String getColumnName(int index) {
                return columnNames[index];
            }
        };

        episodeTable = new JTable(tableModel);

        //Add mouselistener
        episodeTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JTable table = (JTable) evt.getSource();
                if (evt.getClickCount() == 1) {

                    int index = table.rowAtPoint(evt.getPoint());

                    System.out.println(index + " : Index was clicked");
                    // Double-click detected
                }else if (evt.getClickCount() == 2) {

                    System.out.println("Double click was detected");
                }
            }
        });

    }


    public void updateEpisodesTable(){

        episodeTable.clearSelection();
        tableModel.setRowCount(0);

        for (Episode epsode: collector.getEpisodeArrayList()) {
            String channelName = epsode.getProgramName();
            String startTime = epsode.getStartDateTime().toString();
            String endTime = epsode.getEndDateTime().toString();
            Object[] data = {channelName, startTime, endTime};
            tableModel.addRow(data);
        }
    }

    public JList getChannelList(){
        return channelList;
    }

    public JTable getEpisodesTable(){
        return episodeTable;
    }

    public JList getEpisodeList(){
        return episodeList;
    }

    public ArrayList<Episode> getEpisodeArray(){
        return ae;
    }


    public boolean getClicked(){
        return clicked;
    }
    public void setClicked(boolean bool){
        clicked = bool;
    }
}
