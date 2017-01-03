package Model;

/**
 * Created by simon on 2017-01-03.
 */
public class Channel {

private int id;
private String name;
private String imageUrl;
private String imageTemplate;
private String color;
private String siteUrl;
private int liveAudioId ;
private String liveAudioUrl;
private String liveAudioStatKey;
private String scheduleUrl;
private String channelType;

public Channel(int id , String name){

    this.id = id;
    this.name = name;

}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageTemplate() {
        return imageTemplate;
    }

    public void setImageTemplate(String imageTemplate) {
        this.imageTemplate = imageTemplate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public int getLiveAudioId() {
        return liveAudioId;
    }

    public void setLiveAudioId(int liveAudioId) {
        this.liveAudioId = liveAudioId;
    }

    public String getLiveAudioUrl() {
        return liveAudioUrl;
    }

    public void setLiveAudioUrl(String liveAudioUrl) {
        this.liveAudioUrl = liveAudioUrl;
    }

    public String getLiveAudioStatKey() {
        return liveAudioStatKey;
    }

    public void setLiveAudioStatKey(String liveAudioStatKey) {
        this.liveAudioStatKey = liveAudioStatKey;
    }

    public String getScheduleUrl() {
        return scheduleUrl;
    }

    public void setScheduleUrl(String scheduleUrl) {
        this.scheduleUrl = scheduleUrl;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }
}
