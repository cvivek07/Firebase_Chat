package google.firebase.firebasechatapp;

import android.content.Intent;
import android.database.DataSetObserver;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class OnlineUsers extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> mAdapter = null;
    ArrayList<String> usersList = new ArrayList<String>();
    Firebase firebase_onlineusers = new Firebase("https://fir-demo-d7354.firebaseio.com/Online_Users/");
    String LoggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_users);
        listView = (ListView) findViewById(R.id.listview);
        Firebase.setAndroidContext(this);
        ;
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, usersList);
        listView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebase_onlineusers.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                s = dataSnapshot.getValue(String.class);
                Log.v("VALUE", s);
                usersList.add(s);
                mAdapter.notifyDataSetChanged();
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
        onListItemClick();
    }

    private void onListItemClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String to_user = (String) listView.getItemAtPosition(i);
                Intent lastintent = getIntent();
                String from_user = lastintent.getStringExtra("FROM_USER");
                LoggedInUser = lastintent.getStringExtra("LOG_IN_USER");
                Log.v("from_user", from_user);
                Intent intent = new Intent(OnlineUsers.this, ChatPage.class);
                intent.putExtra("FROM_USER", from_user);
                intent.putExtra("TO_USER", to_user);
                intent.putExtra("LOG_IN_USER", LoggedInUser);
                startActivity(intent);


            }
        });
    }
}
