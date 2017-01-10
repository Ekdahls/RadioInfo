package com.ou2;

import Controller.ChannelParser;
import Controller.Collector;
import Model.Channel;
import View.ChannelsView;
import View.EpisodesView;
import View.MainView;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by simon on 2017-01-09.
 */
public class MainFrame  {

    private JFrame frame;
    public static JPanel panel;
    public static JPanel panel2;
    public static JPanel panel3;
    private Collector collector;

    private JScrollPane scrollPane, scrollPane2, scrollPane3;


    public MainFrame(){
        collector = new Collector();
        frame = new JFrame("Sveriges Radio");
        frame.setPreferredSize(new Dimension(800,500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loadInterface();
    }


    public void loadInterface(){

        MainView mv = new MainView(collector);

        panel = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();

        scrollPane = new JScrollPane(mv.getChannelList());
        scrollPane2 = new JScrollPane(mv.getEpisodesTable());
        scrollPane3 = new JScrollPane(mv.getEpisodeList());
        frame.add(scrollPane, BorderLayout.WEST);
        frame.add(scrollPane2, BorderLayout.CENTER);
        frame.add(scrollPane3, BorderLayout.EAST);
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
