package google.firebase.firebasechatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.FirebaseRecyclerAdapter;

import java.util.ArrayList;

public class ChatPage extends AppCompatActivity {

    EditText editText;
    Button sendbutton;

    ArrayList<ChatModel> chatmsgsList = new ArrayList<ChatModel>();
    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter<ChatModel, ChatMessageViewHolder> mFirebaseAdapter1 = null;
    private FirebaseRecyclerAdapter<ChatModel, ChatMessageViewHolder> mFirebaseAdapter2 = null;
    Firebase firebase_chatnode = new Firebase("https://fir-demo-d7354.firebaseio.com/Chats");
    Firebase ref_chatchildnode1 = null;
    Firebase ref_chatchildnode2 = null;
    String from_user, to_user, newmsg, LoggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);
        Firebase.setAndroidContext(this);


        editText = (EditText) findViewById(R.id.editText);

        sendbutton = (Button) findViewById(R.id.button);
        Intent startingintent = getIntent();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutmgr = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutmgr);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        from_user = startingintent.getStringExtra("FROM_USER");
        to_user = startingintent.getStringExtra("TO_USER");
        LoggedInUser = startingintent.getStringExtra("LOG_IN_USER");

        setTitle(to_user);



        Log.v("NODE CREATED:", from_user + " " + to_user);


        ref_chatchildnode1 = firebase_chatnode.child(from_user + " " + to_user);

        ref_chatchildnode2 = firebase_chatnode.child(to_user + " " + from_user);
        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newmsg = editText.getText().toString().trim();
                ChatModel m = new ChatModel();
                m.setSender(LoggedInUser);
                m.setMessage(newmsg);
                ref_chatchildnode1.push().setValue(m);
                ref_chatchildnode2.push().setValue(m);
                editText.setText("");


            }
        });
        ref_chatchildnode1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatModel chatmsg = dataSnapshot.getValue(ChatModel.class);
                chatmsgsList.add(chatmsg);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        ref_chatchildnode2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatModel chatmsg = dataSnapshot.getValue(ChatModel.class);
                chatmsgsList.add(chatmsg);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter1 = new FirebaseRecyclerAdapter<ChatModel, ChatMessageViewHolder>(ChatModel.class,
                R.layout.textview,
                ChatMessageViewHolder.class,
                ref_chatchildnode1) {
            @Override
            protected void populateViewHolder(ChatMessageViewHolder chatMessageViewHolder, ChatModel m, int i) {



                chatMessageViewHolder.sender.setText(m.getSender());
                chatMessageViewHolder.msg.setText(m.getMessage());
            }
        };
        mFirebaseAdapter2 = new FirebaseRecyclerAdapter<ChatModel, ChatMessageViewHolder>(ChatModel.class,
                R.layout.textview,
                ChatMessageViewHolder.class,
                ref_chatchildnode2) {
            @Override
            protected void populateViewHolder(ChatMessageViewHolder chatMessageViewHolder, ChatModel m, int i) {


                chatMessageViewHolder.sender.setText(m.getSender());
                chatMessageViewHolder.msg.setText(m.getMessage());
            }
        };

        mRecyclerView.setAdapter(mFirebaseAdapter1);

        mRecyclerView.setAdapter(mFirebaseAdapter2);
    }


}
