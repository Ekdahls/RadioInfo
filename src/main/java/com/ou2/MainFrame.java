package com.ou2;

import Controller.ChannelParser;
import Controller.Collector;
import Model.Channel;
import Model.ImagePanel;
import View.ChannelsView;
import View.EpisodesView;
import View.MainView;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by simon on 2017-01-09.
 */
public class MainFrame  {

    private JFrame frame;
    public static JPanel panel;
    public static JPanel panel2;
    public static JPanel panel3;
    public static JLabel title, title2;
    public static JTextArea description;
    public JPanel imgPanel;
    public Image image;
    private Collector collector;

    private JScrollPane scrollPane, scrollPane2, scrollPane3;

    Thread collectorThread;


    public MainFrame(Collector collector){
        this.collector = collector;

        frame = new JFrame("Sveriges Radio");
        frame.setPreferredSize(new Dimension(800,500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loadInterface();
    }


    public void loadInterface(){

        MainView mv = new MainView(collector);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));
        panel2 = new JPanel();
        panel3 = new JPanel();

        title = new JLabel(collector.getEpisodeArrayList().get(mv.getCurrentEpisodeIndex()).getProgramName());
        title2 = new JLabel(collector.getEpisodeArrayList().get(mv.getCurrentEpisodeIndex()).getChannelName());

        scrollPane = new JScrollPane(mv.getChannelList());
        scrollPane2 = new JScrollPane(mv.getEpisodesTable());


//        ImageIcon icon = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("placeholder.png"));

//        ImageIcon icon = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("placeholder.png"));
//
//
//        Image image = icon.getImage(); // transform it
//        Image newimg = image.getScaledInstance(200,200 ,  Image.SCALE_DEFAULT); // scale it the smooth way
//        icon = new ImageIcon(newimg);
//        JLabel thumb = new JLabel();
//        thumb.setIcon(icon);

        description = new JTextArea();
        description.setPreferredSize(new Dimension(200,0));
        description.setLineWrap(true);
        description.append(collector.getEpisodeArrayList().get(mv.getCurrentEpisodeIndex()).getDescription());
        description.setEditable(false);
        panel.add(title);
        panel.add(title2);
//        panel.add(thumb);
        panel.add(description);

//        panel.add(panel2);
//        panel.add(panel3);

        //scrollPane3 = new JScrollPane(mv.getEpisodeList());
        frame.add(scrollPane, BorderLayout.WEST);
        frame.add(scrollPane2, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.EAST);

        //frame.add(scrollPane3, BorderLayout.EAST);
    }

//    public void setup(){
//        //ChannelsView cv = new ChannelsView(collector);
//
//        MainView mv = new MainView(collector);
//        panel.add(mv.getChannelList());
//        scrollPane2 = new JScrollPane(mv.getEpisodesTable());
//        frame.add(scrollPane2, BorderLayout.CENTER);
//        panel3.add(mv.getEpisodeList());
//    }


    public void updateView(){

        EpisodesView ev = new EpisodesView();
        panel.add(ev.getEpisodeTable(), BorderLayout.CENTER);
        scrollPane = ev.getNewScrollPane();
        frame.add(scrollPane);
        frame.pack();

    }
    private void addMenuBar(){
        JMenu menuBar = new JMenu();
    }

    public void show(){
        frame.pack();
        frame.setVisible(true);
    }

}
