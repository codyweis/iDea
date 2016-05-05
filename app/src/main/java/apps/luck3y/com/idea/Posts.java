package apps.luck3y.com.idea;

import java.util.HashMap;

/**
 * Created by Cody Weisenberger on 4/6/2016.
 */
public class Posts extends HashMap<String, String> {

    public String id;
    public String content;
    public String user;
    public String date;
    public String topic;
    public String likes;
    public String btnTxt;

    public Posts(String id, String content, String user, String topic, String date, String likes, String btnTxt){
        this.id = id;
        this.content = content;
        this.user = user;
        this.topic = topic;
        this.date = date;
        this.likes = likes;
        this.btnTxt = btnTxt;
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
    public String getLikes(String likes){
        return likes;
    }
    public String getId(){
        return id;
    }
    public String getBtnTxt(){
        return btnTxt;
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
    public void setBtnTxt(String btnTxt){
        this.btnTxt = btnTxt;
    }
}
