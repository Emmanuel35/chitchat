package fr.sodifrance.anteo.chitchat;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;

import java.util.Date;

@Portable
public class Tweet {
    
    @PortableProperty(0)
    private String author;
    
    @PortableProperty(1)
    private String text;
    
    @PortableProperty(2)
    private Integer thread;
    
    @PortableProperty(3)
    private Date createdAt;
    
    public Tweet() {
        super();
    }
    
    public Tweet(String author, String text, Integer thread, Date createdAt) {
        super();
        
        this.author = author;
        this.text = text;
        this.thread = thread;
        this.createdAt = createdAt;
    }
}
