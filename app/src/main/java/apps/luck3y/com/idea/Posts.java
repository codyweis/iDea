package apps.luck3y.com.idea;

/**
 * Created by Cody Weisenberger on 4/6/2016.
 */
public class Posts {

    public String id;
    public String content;
    public String user;
    public String date;
    public String topic;
    public String likes;

    public Posts(String id, String content, String user, String topic, String date, String likes){
        this.id = id;
        this.content = content;
        this.user = user;
        this.topic = topic;
        this.date = date;
        this.likes = likes;
    }

    public String getContent(){
        return content;
    }
    public String getUser(){
        return user;
    }
    public String getDate(){
        return date;
    }
    public String getTopic(){
        return topic;
    }
    public String getLikes(){
        return likes;
    }
    public String getId(){
        return id;
    }

    public void setContent(String content){
        this.content = content;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public void setLikes(String likes) {
        this.likes = likes;
    }
    public void setId(String id) {
        this.id = id;
    }
}
