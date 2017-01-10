package Controller;


import Model.Channel;
import org.w3c.dom.Document;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import java.util.ArrayList;

/**
 * Created by simon on 2017-01-03.
 */
public class ChannelParser extends DocumentParser {
    //    public int totalHits, totalPages, page, size;
    private ArrayList<Channel> channelList = new ArrayList<>();
    private Document doc;
    private String uri = "http://api.sr.se/api/v2/channels/?page={page}";

    public ChannelParser(){
//        XPathFactory xPathFactory = XPathFactory.newInstance();
//        xPath = xPathFactory.newXPath();
        setupRestTemplate();
    }


    public void init(){

        try {
            //Setup doc + header to get total pages
            setupDocument(1, uri);
            doc = getDoc();
            getHeader();
            //collect the data
            collectData();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }


    public void collectData() throws XPathExpressionException {

        for (int i = 1; i <= getTotalPages(); i++) {
            if(i!=1) {
                setupDocument(i, uri);
                doc = getDoc();
                getHeader();
            }

            if(getPage() == getTotalPages()){
                setSize(getTotalHits() % getSize());
            }

            for (int j = 0; j < getSize(); j++) {

                try {
                    XPath xPath = getxPath();
                    int id = Integer.parseInt(xPath.evaluate("/sr/channels/channel["+(j+1)+"]/@id", doc));
                    String name = (xPath.evaluate("/sr/channels/channel["+(j+1)+"]/@name", doc));
                    Channel c = new Channel(id, name);
                    c.setImageUrl(xPath.evaluate("/sr/channels/channel["+(j+1)+"]/image", doc));
                    c.setImageTemplate(xPath.evaluate("/sr/channels/channel["+(j+1)+"]/imagetemplate", doc));
                    c.setColor(xPath.evaluate("/sr/channels/channel["+(j+1)+"]/color", doc));
                    c.setSiteUrl(xPath.evaluate("/sr/channels/channel["+(j+1)+"]/siteurl", doc));
                    c.setScheduleUrl(xPath.evaluate("/sr/channels/channel["+(j+1)+"]/scheduleurl", doc));
                    c.setChannelType(xPath.evaluate("/sr/channels/channel["+(j+1)+"]/channeltype", doc));
                    channelList.add(c);
                } catch (XPathExpressionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<Channel> getChannelList() {
        return channelList;
    }

    }





