package View;

import Model.Channel;

import java.util.ArrayList;

/**
 * Created by simon on 2017-01-03.
 */
public class ChannelsView {

    private ArrayList<Channel> ac;

    public ChannelsView(ArrayList<Channel> ac){
        this.ac = ac;
    }

    public void printChannels(){

        for (Channel c: ac
                ) {
            System.out.println(c.getName());
        }
    }
}
