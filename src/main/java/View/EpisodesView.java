package View;

import Controller.Collector;
import Model.Episode;
import com.ou2.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by simon on 2017-01-03.
 */
public class EpisodesView {


    private Object[][] data;
    private JTable episodeTable;
    DefaultTableModel tableModel = new DefaultTableModel();
    private ArrayList<Episode> ae;

    public EpisodesView(ArrayList<Episode> ae){
        this.ae = ae;
        setup();
    }

    public EpisodesView(){
        setup();
    }

    public void setup(){

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


    updateEpisodesTable();

    }

    public void setEpisodeTable(ArrayList<Episode> ae){

        this.ae = ae;
    }

    public void updateEpisodesTable(){

        episodeTable.clearSelection();

        for (Episode epsode: ae) {
            String channelName = epsode.getProgramName();
            String startTime = epsode.getStartDateTime().toString();
            String endTime = epsode.getEndDateTime().toString();
            Object[] data = {channelName, startTime, endTime};
            tableModel.addRow(data);
        }
    }

    public JTable getEpisodeTable(){

        return episodeTable;
    }

    public JScrollPane getNewScrollPane(){

         JScrollPane scrollpane = new JScrollPane(episodeTable);

         return scrollpane;
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
