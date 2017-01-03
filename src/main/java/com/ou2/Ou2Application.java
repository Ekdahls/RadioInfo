package com.ou2;

import Controller.ChannelParser;
import Controller.EpisodeParser;
import Model.Channel;
import View.ChannelsView;
import View.EpisodesView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;


@SpringBootApplication
public class Ou2Application {

	public static void main(String[] args) {

		SpringApplication.run(Ou2Application.class, args);
		ChannelParser channelParser = new ChannelParser();
		channelParser.run();

		ChannelsView channelsView= new ChannelsView(channelParser.getChannelList());
		channelsView.printChannels();

		EpisodeParser episodeParser = new EpisodeParser();
		episodeParser.run();

		EpisodesView episodesView = new EpisodesView(episodeParser.getEpisodeArrayList());
		episodesView.printEpisodes();

	}
}
