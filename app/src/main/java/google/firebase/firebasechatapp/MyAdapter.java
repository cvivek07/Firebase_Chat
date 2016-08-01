package google.firebase.firebasechatapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vivek on 29-Jul-16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
private List<Message> msgList;
    public static class ViewHolder extends RecyclerView.ViewHolder{
public TextView sender, msg;
        public ViewHolder(View itemView) {
            super(itemView);
            sender = (TextView) itemView.findViewById(R.id.sender);
            msg =(TextView) itemView.findViewById(R.id.msg);
        }
    }
    public MyAdapter(List<Message> messages){
        this.msgList=messages;

    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.textview,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
Message msg = msgList.get(position);
        holder.sender.setText(msg.getSender());
        holder.msg.setText(msg.getMessage());
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }
}
