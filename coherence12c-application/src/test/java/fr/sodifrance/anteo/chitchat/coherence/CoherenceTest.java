package fr.sodifrance.anteo.chitchat.coherence;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.QueryHelper;
import com.tangosol.util.extractor.ReflectionExtractor;
import com.tangosol.util.filter.EqualsFilter;
import com.tangosol.util.filter.LikeFilter;

import java.util.Map;
import java.util.Set;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class CoherenceTest {
    
    private NamedCache cache;
    private NamedCache cache2;
    
    public CoherenceTest() {
    }
    
    @Before
    public void initTest() {
        cache = CacheFactory.getCache("ChitChat");
         
        cache.addIndex(new ReflectionExtractor("getThread"), true, null);
        cache.addIndex(new ReflectionExtractor("getText"), true, null);
        
        cache2 = CacheFactory.getCache("Latest");
    }
    
    @After
    public void cleanCache() {
        if (cache != null)
            cache.clear();
    }
    
    @Test
    public void put() {
        Tweet t1 = new Tweet("Loki", "I have an army !", 3);
        cache.put(t1.getKey(), t1);
        
        assertEquals(t1.getText(), ((Tweet)cache.get(t1.getKey())).getText());
    }
    
    @Test
    public void thread() {
        Tweet t1 = new Tweet("Loki", "I have an army !", 3);
        Tweet t2 = new Tweet("Iron Man", "I am an army", 3);
        Tweet t3 = new Tweet("Hulk", "I will destroy all armies", 3);
        
        cache.put(t1.getKey(), t1);
        cache.put(t2.getKey(), t2);
        cache.put(t3.getKey(), t3);
        
        Set<Map.Entry> entries = cache.entrySet(
            QueryHelper.createFilter("thread = ?1" , new Object[]{3})
            );

        assertNotNull(entries);
        assertEquals(3, entries.size());
        
        for(Map.Entry entry: entries) {
            Tweet tweet = (Tweet) entry.getValue();
            assertEquals(new Integer(3), tweet.getThread());
        }
    }
    
    @Test
    public void threadWithIndex() {
        for(int i=0; i<100; i++) {
            Tweet t1 = new Tweet("Loki", "I have an army !", i);
            Tweet t2 = new Tweet("Iron Man", "I am an army", i);
            Tweet t3 = new Tweet("Hulk", "I will destroy all armies", i);
            
            cache.put(t1.getKey(), t1);
            cache.put(t2.getKey(), t2);
            cache.put(t3.getKey(), t3);
        }
        
        Set<Map.Entry> entries = cache.entrySet(
            QueryHelper.createFilter("thread = ?1" , new Object[]{3})
        );

        assertNotNull(entries);
        assertEquals(3, entries.size());
        
        for(Map.Entry entry: entries) {
            Tweet tweet = (Tweet) entry.getValue();
            assertEquals(new Integer(3), tweet.getThread());
        }
    }
    
    @Test
    public void search() {
        Tweet t1 = new Tweet("Loki", "I have an army !", 1);
        Tweet t2 = new Tweet("Iron Man", "I am an army", 2);
        Tweet t3 = new Tweet("Hulk", "I will destroy all armies", 3);
        
        cache.put(t1.getKey(), t1);
        cache.put(t2.getKey(), t2);
        cache.put(t3.getKey(), t3);
        
        Set<Map.Entry> entries = cache.entrySet(
            //QueryHelper.createFilter("text contains ?1" , new Object[]{"destroy"})
            new LikeFilter("getText", "%destroy%")
            );

        assertNotNull(entries);
        assertEquals(1, entries.size());
        
        for(Map.Entry entry: entries) {
            Tweet tweet = (Tweet) entry.getValue();
            assertEquals("Hulk", tweet.getAuthor());
        }
    }
    
    @Test
    public void latest() {
        Tweet t1 = new Tweet("Loki", "I have an army !", 1);
        Tweet t2 = new Tweet("Loki", "I am an army", 2);
        Tweet t3 = new Tweet("Loki", "I will destroy all armies", 3);
        
        cache2.put(t1.getAuthor(), t1);
        cache2.put(t2.getAuthor(), t2);
        cache2.put(t3.getAuthor(), t3);
        
        Set<Map.Entry> entries = cache2.entrySet(
            //QueryHelper.createFilter("text contains ?1" , new Object[]{"destroy"})
            new EqualsFilter("getAuthor", "Loki")
            );

        assertNotNull(entries);
        assertEquals(1, entries.size());
        
        for(Map.Entry entry: entries) {
            Tweet tweet = (Tweet) entry.getValue();
            assertEquals("Loki", tweet.getAuthor());
            assertEquals("I will destroy all armies", tweet.getText());
        }
    }
}
