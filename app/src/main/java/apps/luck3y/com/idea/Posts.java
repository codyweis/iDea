package apps.luck3y.com.idea;

/**
 * Created by Cody Weisenberger on 4/6/2016.
 */
public class Posts {

    public String content;
    public String user;

    public Posts(String content, String user){
        this.content = content;
        this.user = user;
    }

    public String getContent(){
        return content;
    }

    public String getUser(){
        return user;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
