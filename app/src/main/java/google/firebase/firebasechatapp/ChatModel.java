package google.firebase.firebasechatapp;

/**
 * Created by Vivek on 26-Jul-16.
 */
public class ChatModel {
    private String message;
    private String Sender;
    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




    public ChatModel(){

    }
    public ChatModel(String s, String m){
        this.Sender =s ;
        this.message =m;
    }









}
