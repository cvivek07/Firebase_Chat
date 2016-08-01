package google.firebase.firebasechatapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends Activity {
    private List<User> userList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mAdapter = new MyAdapter1(userList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        mRecyclerView.setLayoutManager(mLayoutManager);
       mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        prepareData();
    }
    void prepareData()
    {
        User user = new User("Vivek: ", "Fuck you");
        userList.add(user);
        User user1 = new User("Sneh: ", "Fuck you too");
        userList.add(user1);
        mAdapter.notifyDataSetChanged();
    }
}
class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder>{
private List<User> userList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username, msg;

        public MyViewHolder(View view) {
            super(view);
            username = (TextView) view.findViewById(R.id.sender);
            msg = (TextView) view.findViewById(R.id.msg);

        }
    }
    public MyAdapter1(List<User> userList) {
        this.userList = userList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.textview, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = userList.get(position);
        holder.username.setText(user.getUsername());
        holder.msg.setText(user.getMsg());

    }
    @Override
    public int getItemCount() {
        return userList.size();
    }


}