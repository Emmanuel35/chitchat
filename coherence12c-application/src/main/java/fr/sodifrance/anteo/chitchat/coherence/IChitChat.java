package fr.sodifrance.anteo.chitchat.coherence;

import javax.ejb.Local;

@Local
public interface IChitChat {
    
    /**
     * add a tweet
     */
    void addTweet(Tweet tweet);

    /**
     * Find tweets of a thread
     * @param number
     * @return
     */
    Tweets thread(Integer number);

    /**
     * Search tweet with text in text
     * @param text
     * @return
     */
    Tweets search(String text);

    /**
     * Search latest tweet of this author
     * @param author
     * @return
     */
    Tweet latest(String author) throws Exception;
    
}
