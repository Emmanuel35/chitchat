package fr.sodifrance.anteo.chitchat.coherence;

import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;

import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="tweet")
@XmlAccessorType(XmlAccessType.FIELD)
@Portable
public class Tweet implements java.io.Serializable {
    
    @XmlElement
    @PortableProperty(0)
    private String author;
    
    @XmlElement
    @PortableProperty(1)
    private String text;
    
    @XmlElement
    @PortableProperty(2)
    private Integer thread;
    
    @XmlElement
    @PortableProperty(3)
    private Date createdAt;
    
    public Tweet() {
        super();
        
        createdAt = Calendar.getInstance().getTime();
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setThread(Integer thread) {
        this.thread = thread;
    }

    public Integer getThread() {
        return thread;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    
    public Tweet(String author, String text, Integer thread) {
        super();
        
        this.author = author;
        this.text = text;
        this.thread = thread;
        this.createdAt = Calendar.getInstance().getTime();
    }
    
    public String getKey() {
        return author+"-"+thread+"-"+createdAt.toString();
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Tweet)) {
            return false;
        }
        final Tweet other = (Tweet) object;
        if (!(author == null ? other.author == null : author.equals(other.author))) {
            return false;
        }
        if (!(thread == null ? other.thread == null : thread.equals(other.thread))) {
            return false;
        }
        if (!(createdAt == null ? other.createdAt == null : createdAt.equals(other.createdAt))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 37;
        int result = 1;
        result = PRIME * result + ((author == null) ? 0 : author.hashCode());
        result = PRIME * result + ((thread == null) ? 0 : thread.hashCode());
        result = PRIME * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        return result;
    }
}
