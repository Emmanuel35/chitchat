package fr.sodifrance.anteo.chitchat.coherence;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * List of tweet
 */
@XmlRootElement(name="tweets")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tweets {
    
    @XmlElement
    private List<Tweet> tweets = new ArrayList<Tweet>();
    
    public Tweets() {
        super();
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }
}
