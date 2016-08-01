package google.firebase.firebasechatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private FirebaseListAdapter<Message> mFirebaseAdapter= null;
    TextView textView;
    EditText editText;
    Button button;
    //ListView listView;
    ArrayList<String> arrayList = new ArrayList<String>();
    List<Message> messageList = new ArrayList<Message>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        final Firebase firebase_ref = new Firebase("https://fir-demo-d7354.firebaseio.com/message/");

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new MyAdapter(messageList);
        RecyclerView.LayoutManager layoutmgr = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutmgr);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // FirebaseListAdapter



/*Message initialdataset = new Message("Vivek", "Welcome bro");

        messageList.add(initialdataset);
        mAdapter.notifyDataSetChanged();
        Message initialdataset2 = new Message("Sneha", "Thank you");
        messageList.add(initialdataset2);
        mAdapter.notifyDataSetChanged();*/


        /*final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);*/
        //textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String message = editText.getText().toString().trim();
                Message messageobj = new Message();
                messageobj.setMessage(message);
                messageobj.setSender(message);

                firebase_ref.child("text").setValue(messageobj);
// for getting the FCM registration token for the device.[start]
                String token = FirebaseInstanceId.getInstance().getToken();
                String msg = getString(R.string.msg_token_fmt, token);
                Log.v("Main Activity", msg);
                // for getting the FCM registration token for the device.[end]

                Log.v("MainActivity", "Value -set");


                firebase_ref.addListenerForSingleValueEvent(new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.v("MainActivity", "Single value event");
                        for (DataSnapshot snap : dataSnapshot.getChildren()) {

                            Message mObj = snap.getValue(Message.class);
                            String messagevalue = mObj.getMessage();
                            String sendervalue = mObj.getSender();
                            //String s =snap.getValue(String.class);
                            if (messagevalue != null)Log.v("MainActivity", "s is not null: " + messagevalue);
Message msg = new Message(messagevalue,sendervalue);
                            messageList.add(msg);

                            for (Message mListItem : messageList){
                                Log.v("messageList: Message ",  mListItem.getMessage());
                                Log.v("messageList: Sender ",  mListItem.getSender());
                            }
                            mAdapter.notifyDataSetChanged();

                            Log.v("MainActivity", "notfidatachanged " + mAdapter);
                            editText.setText("");
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });


            }
        });

    }


}

