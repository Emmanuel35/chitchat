package fr.sodifrance.anteo.chitchat.coherence;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.QueryHelper;
import com.tangosol.util.extractor.ReflectionExtractor;
import com.tangosol.util.filter.LikeFilter;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.jws.WebParam;
import javax.jws.WebService;

@Stateless(name = "ChitChatEJB", mappedName = "ChitChatEJB")
@WebService(targetNamespace="http://www.anteo-consulting.com/chitchat")
public class ChitChatEJB implements IChitChat {
    @Resource
    SessionContext sessionContext;

    private NamedCache cache;
    private NamedCache cacheLatest;
    
    public ChitChatEJB() {
    }

    /**
     * Cache for All objects
     * @return
     */
    private NamedCache getCache() {
        if (cache ==null) {
            cache = CacheFactory.getCache("ChitChat");
            
            // add indexes
            cache.addIndex(new ReflectionExtractor("getThread"), true, null);
            cache.addIndex(new ReflectionExtractor("getAuthor"), true, null);
            cache.addIndex(new ReflectionExtractor("getText"), true, null);
        }
        return cache;
    }
    
    /**
     * Cache for latest tweet of author
     * @return
     */
    private NamedCache getCacheLatest() {
        if (cacheLatest ==null) {          
            cacheLatest = CacheFactory.getCache("Latest");
        }
        return cacheLatest;
    }
    
    @Override
    public void addTweet(@WebParam(name = "tweet") Tweet tweet) {
        // add tweet
        getCache().put(tweet.getKey(), tweet);
        
        // this is latest :-)
        getCacheLatest().put(tweet.getAuthor(), tweet);
    }

    @Override
    public Tweets thread(@WebParam(name = "thread") Integer number) {
        //index on threads
        //cache.addIndex(new ReflectionExtractor("getThread"), true, null);
        
        Set<Map.Entry> entries = getCache().entrySet(
            QueryHelper.createFilter("thread = ?1" , new Object[]{number})
        );
        
        Tweets result = new Tweets();
        for(Map.Entry entry: entries)
            result.getTweets().add((Tweet)entry.getValue());
        
        return result;
    }

    @Override
    public Tweets search(@WebParam(name = "text") String text) {
        Set<Map.Entry> entries = getCache().entrySet(
            //QueryHelper.createFilter("text contains ?1" , new Object[]{text})
            new LikeFilter("getText", "%"+text+"%")
            );

        Tweets result = new Tweets();
        for(Map.Entry entry: entries)
            result.getTweets().add((Tweet)entry.getValue());
        
        return result;
    }

    @Override
    public Tweet latest(@WebParam(name = "author") String author) throws Exception {
        
        return (Tweet) getCacheLatest().get(author);
    }
}
