package com.android.location_alarm;


import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Chat extends AppCompatActivity {

    ListView lvDiscussionTopics;
    ArrayList<String> listOfDiscussion = new ArrayList<String>();
    ArrayAdapter arrayAdpt;
    String UserName,email;
    private DatabaseReference dbr;


    String BarInputcolor="", TextInputcolor="",BgInputcolor="",UserColorStatus,StatusBar;
    View view;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);



        SharedPreferences sharedPreferences = getSharedPreferences("eKey", MODE_PRIVATE);
        email = sharedPreferences.getString("email", "");
        email = email.replaceAll("@gmail.com", "");
        dbr = FirebaseDatabase.getInstance().getReference(email).child("chat");
        lvDiscussionTopics = (ListView) findViewById(R.id.lvDiscussionTopics);
        ListView lvDiscussionTopics = (ListView) findViewById(R.id.lvDiscussionTopics);
        arrayAdpt = new MyListAdaper(this,R.layout.activity_list_item, listOfDiscussion);
        lvDiscussionTopics.setAdapter(arrayAdpt);
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while(i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }

                arrayAdpt.clear();
                arrayAdpt.addAll(set);
                arrayAdpt.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        lvDiscussionTopics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = (String) parent.getItemAtPosition(position);
                Log.d("selectedItem",selectedItem);


                Intent i = new Intent(getApplicationContext(), DiscussionActivity.class);
                i.putExtra("selected_topic",selectedItem);

                i.putExtra("user_name", email);
                startActivity(i);
            }
        });








        SharedPreferences SharedPreferencesc = getSharedPreferences("usc", MODE_PRIVATE);
        UserColorStatus = SharedPreferencesc.getString("usc", "");
        Log.d("UserColorStatus",UserColorStatus);
        view=(View)findViewById(R.id.bg);
        SharedPreferences SharedPreferences0 = getSharedPreferences("bcolor", MODE_PRIVATE);
        BarInputcolor = SharedPreferences0.getString("bcolor", "");

        SharedPreferences SharedPreferences1 = getSharedPreferences("tcolor", MODE_PRIVATE);
        TextInputcolor= SharedPreferences1.getString("tcolor", "");

        SharedPreferences SharedPreferences2 = getSharedPreferences("bgcolor", MODE_PRIVATE);
        BgInputcolor= SharedPreferences2.getString("bgcolor", "");

        SharedPreferences SharedPreferences3 = getSharedPreferences("Scolor", MODE_PRIVATE);
        StatusBar= SharedPreferences3.getString("Scolor", "");










        if(UserColorStatus.equals("1")) {
            getWindow().setStatusBarColor(Color.parseColor(StatusBar));
            this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(BarInputcolor)));
            getSupportActionBar().setTitle(Html.fromHtml("<font color=" + TextInputcolor + ">" + getString(R.string.app_name) + "</font>"));
            view.setBackgroundColor(Color.parseColor(BgInputcolor));




        }else
        {
            Toast.makeText(Chat.this, "default", Toast.LENGTH_SHORT).show();
        }















    }
    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> mObjects;
        private MyListAdaper(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
               // viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.list_item_thumbnail);
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_text);







                ///
                if(UserColorStatus.equals("1")) {
                    viewHolder.title.setTextColor(Color.parseColor(TextInputcolor));
                }





                ///









                viewHolder.button = (ImageView) convertView.findViewById(R.id.list_item_btn);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Button was clicked for list item " + position, Toast.LENGTH_SHORT).show();
                    Log.d("sffsfd",getItem(position));

                    dbr = FirebaseDatabase.getInstance().getReference(email).child("chat").child(getItem(position));
                    dbr.removeValue();
                }
            });
            mainViewholder.title.setText(getItem(position));
            return convertView;
        }
    }
    public class ViewHolder {

      //  ImageView thumbnail;
        TextView title;


       // Button button;
       ImageView button;
    }

        private void getUserName(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText userName = new EditText(this);

        builder.setView(userName);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserName = userName.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getUserName();
            }
        });
        builder.show();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            startActivity(new Intent(getApplicationContext(), SessionCreate.class));
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}