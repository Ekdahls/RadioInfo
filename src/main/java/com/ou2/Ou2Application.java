package com.ou2;

import Controller.ChannelParser;
import Controller.Collector;
import Controller.EpisodeParser;
import Model.Channel;
import View.ChannelsView;
import View.EpisodesView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.applet.Main;

import javax.swing.*;
import java.util.ArrayList;


@SpringBootApplication
public class Ou2Application {

    public static void main(String[] args) {

        Collector collector= new Collector();
        Thread collectorThread = new Thread(collector);
        collectorThread.start();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame mainFrame = new MainFrame(collector);
                mainFrame.show();
            }
        });

	}
}
