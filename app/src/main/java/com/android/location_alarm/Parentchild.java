package com.android.location_alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
import java.util.Set;

public class Parentchild extends AppCompatActivity {




    /*
    private DatabaseReference myDatabase;
    private int i;
    private String cpeemail;
    private String email;

    String BarInputcolor="", TextInputcolor="",BgInputcolor="",UserColorStatus,StatusBar;
    View view;

     */
    String BarInputcolor="", TextInputcolor="",BgInputcolor="",UserColorStatus,StatusBar;
    View view;
    ListView lvDiscussionTopics;
    ArrayList<String> listOfDiscussion = new ArrayList<String>();
    ArrayAdapter arrayAdpt;

    String UserName,email,SelectedTopic,cpeemail;


    // private DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().getRoot();
    private DatabaseReference dbr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parentchild);

        SharedPreferences sharedPreferencesc = getSharedPreferences("cKey", MODE_PRIVATE);
        cpeemail = sharedPreferencesc.getString("cpeemail", "");





        SharedPreferences sharedPreferences = getSharedPreferences("eKey", MODE_PRIVATE);
        email = sharedPreferences.getString("email", "");
        email = email.replaceAll("@gmail.com", "");

        dbr = FirebaseDatabase.getInstance().getReference(cpeemail).child("chat");



        lvDiscussionTopics = (ListView) findViewById(R.id.lvDiscussionTopics);
        arrayAdpt = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listOfDiscussion) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);





                if(UserColorStatus.equals("1")) {
                    textView.setTextColor(Color.parseColor(TextInputcolor));
                }


                return view;
            }
        };









        lvDiscussionTopics.setAdapter(arrayAdpt);


        //getUserName();


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
                Intent i = new Intent(getApplicationContext(), DiscussionActivity.class);
                i.putExtra("selected_topic", ((TextView)view).getText().toString());
                i.putExtra("user_name", cpeemail);
                startActivity(i);
            }
        });












        SharedPreferences SharedPreferencesc = getSharedPreferences("usc", MODE_PRIVATE);
        UserColorStatus = SharedPreferencesc.getString("usc", "");

        Log.d("UserColorStatus", UserColorStatus);


        view = (View) findViewById(R.id.bg);


        SharedPreferences SharedPreferences0 = getSharedPreferences("bcolor", MODE_PRIVATE);
        BarInputcolor = SharedPreferences0.getString("bcolor", "");

        SharedPreferences SharedPreferences1 = getSharedPreferences("tcolor", MODE_PRIVATE);
        TextInputcolor = SharedPreferences1.getString("tcolor", "");

        SharedPreferences SharedPreferences2 = getSharedPreferences("bgcolor", MODE_PRIVATE);
        BgInputcolor = SharedPreferences2.getString("bgcolor", "");

        SharedPreferences SharedPreferences3 = getSharedPreferences("Scolor", MODE_PRIVATE);
        StatusBar = SharedPreferences3.getString("Scolor", "");


        if (UserColorStatus.equals("1")) {
            getWindow().setStatusBarColor(Color.parseColor(StatusBar));
            this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(BarInputcolor)));
            getSupportActionBar().setTitle(Html.fromHtml("<font color=" + TextInputcolor + ">" + getString(R.string.app_name) + "</font>"));
            view.setBackgroundColor(Color.parseColor(BgInputcolor));








        } else {
            Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }













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
}


        /*



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
            Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }














        SharedPreferences sharedPreferences1 = getSharedPreferences("eKey", MODE_PRIVATE);
        email = sharedPreferences1.getString("email", "");



        SharedPreferences sharedPreferences = getSharedPreferences("cKey", MODE_PRIVATE);
        cpeemail = sharedPreferences.getString("cpeemail", "");
        cpeemail = cpeemail.replaceAll("@gmail.com", "");
        Log.d("process",cpeemail);
        myDatabase = FirebaseDatabase.getInstance().getReference(cpeemail).child("chat");
        TextView  myText = findViewById(R.id.ptextview);
        myDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(myText!=null) {
                    try {
                        myText.setText(dataSnapshot.getValue().toString());
                    } catch (Exception e) {
                        Toast.makeText(Parentchild.this, "created", Toast.LENGTH_SHORT).show();
                    }
                }
                     else{
                        myText.setText(dataSnapshot.getValue().toString());
                    }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                myText.setText("CANCEL");
            }
        });
    }
    public void csendMessage(View v) {
        SharedPreferences sharedPreferences1 = getSharedPreferences("eKey", MODE_PRIVATE);
        email = sharedPreferences1.getString("email", "");
        email = email.replaceAll("@gmail.com", "");
        EditText myEditText = findViewById(R.id.PeditText);
        myDatabase.child(i+email).setValue(myEditText.getText().toString());
        myEditText.setText("");
        i++;
    }
}


         */










