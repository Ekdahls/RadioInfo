package Model;

import java.time.LocalDateTime;

/**
 * Created by simon on 2017-01-03.
 */
public class Episode {

    private String episodeTitle;
    private String description;
    private String startTime;
    private String endTime;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int programId ;
    private String programName;
    private int channelId;
    private String channelName;
    private String imageUrl;
    private String imageUrlTemplate;
    private boolean hasPassed;

    public Episode( String episodeTitle){
        this.episodeTitle = episodeTitle;
        hasPassed = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startTime) {
        String newStartTime = startTime.replace("Z", "");
        startDateTime = LocalDateTime.parse(newStartTime);
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;

    }

    public void setEndDateTime(String endTime) {
        String newEndTime = endTime.replace("Z", "");
        endDateTime = LocalDateTime.parse(newEndTime);
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrlTemplate() {
        return imageUrlTemplate;
    }

    public void setImageUrlTemplate(String imageUrlTemplate) {
        this.imageUrlTemplate = imageUrlTemplate;
    }

    public boolean getHasPassed() {
        return hasPassed;
    }

    public void setHasPassed(boolean hasPassed) {
        this.hasPassed = hasPassed;
    }
}
