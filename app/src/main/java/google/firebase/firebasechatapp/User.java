package google.firebase.firebasechatapp;

/**
 * Created by Vivek on 28-Jul-16.
 */
public class User {
    private String username;
    private String msg;

    public User(){

    }
    public User(String username, String msg){
        this.username = username;
        this.msg= msg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
