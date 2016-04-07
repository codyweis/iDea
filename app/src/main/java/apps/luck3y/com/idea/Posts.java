package apps.luck3y.com.idea;

/**
 * Created by Cody Weisenberger on 4/6/2016.
 */
public class Posts {

    public String content;
    public String user;
    public String date;
    public String topic;

    public Posts(String content, String user, String topic, String date){
        this.content = content;
        this.user = user;
        this.topic = topic;
        this.date = date;
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
}
