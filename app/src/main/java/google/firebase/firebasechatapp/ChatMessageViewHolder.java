package google.firebase.firebasechatapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Vivek on 01-Aug-16.
 */
public class ChatMessageViewHolder extends RecyclerView.ViewHolder {

    TextView sender, msg;
    public ChatMessageViewHolder(View itemView) {
        super(itemView);
        sender = (TextView) itemView.findViewById(R.id.sender);
        msg = (TextView) itemView.findViewById(R.id.msg);
    }
}
