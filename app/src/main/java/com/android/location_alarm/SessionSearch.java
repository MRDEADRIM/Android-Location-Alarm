package com.android.location_alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SessionSearch extends AppCompatActivity {

    private String email, ci;
    private DatabaseReference myDatabase;
    EditText CpeeMail, cpeepass;

    Button join;
    int i = 0;
    String BarInputcolor = "", TextInputcolor = "", BgInputcolor = "", UserColorStatus, StatusBar, pass = "0";
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_search);


        CpeeMail = findViewById(R.id.cpeemail);
        cpeepass = findViewById(R.id.childskey);
        join = findViewById(R.id.button13);


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


            SharedPreferences SharedPreferencesb = getSharedPreferences("ci", MODE_PRIVATE);
            ci = SharedPreferencesb.getString("ci", "");


            if (ci.equals("1")) {

                CpeeMail.setBackgroundResource(R.drawable.black_b);
                cpeepass.setBackgroundResource(R.drawable.black_b);
                join.setBackgroundResource(R.drawable.black_b);

                CpeeMail.setTextColor(Color.parseColor(TextInputcolor));
                cpeepass.setTextColor(Color.parseColor(TextInputcolor));
                join.setTextColor(Color.parseColor(TextInputcolor));

            }
            if (ci.equals("2")) {

                CpeeMail.setBackgroundResource(R.drawable.grey_b);
                cpeepass.setBackgroundResource(R.drawable.grey_b);
                join.setBackgroundResource(R.drawable.grey_b);

                CpeeMail.setTextColor(Color.parseColor(TextInputcolor));
                cpeepass.setTextColor(Color.parseColor(TextInputcolor));
                join.setTextColor(Color.parseColor(TextInputcolor));


            }
            if (ci.equals("3")) {

                CpeeMail.setBackgroundResource(R.drawable.white_b);
                cpeepass.setBackgroundResource(R.drawable.white_b);
                join.setBackgroundResource(R.drawable.white_b);

                CpeeMail.setTextColor(Color.parseColor(TextInputcolor));
                cpeepass.setTextColor(Color.parseColor(TextInputcolor));
                join.setTextColor(Color.parseColor(TextInputcolor));


            }
            if (ci.equals("4")) {


                CpeeMail.setBackgroundResource(R.drawable.red_b);
                cpeepass.setBackgroundResource(R.drawable.red_b);
                join.setBackgroundResource(R.drawable.red_b);

                CpeeMail.setTextColor(Color.parseColor(TextInputcolor));
                cpeepass.setTextColor(Color.parseColor(TextInputcolor));
                join.setTextColor(Color.parseColor(TextInputcolor));

            }
        } else {
            Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }
        SharedPreferences sharedPreferences = getSharedPreferences("eKey", MODE_PRIVATE);
        email = sharedPreferences.getString("email", "");
        email = email.replaceAll("@gmail.com", "");


    }

    public void parentchild(View v) {
        i=1;

        String cpeemail = CpeeMail.getText().toString().trim();

        String Ckey = cpeepass.getText().toString().trim();
        SharedPreferences sharedPref = getSharedPreferences("cKey", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        cpeemail = cpeemail.replaceAll("@gmail.com", "");
        editor.putString("cpeemail", cpeemail);
        editor.apply();
        Log.d("cpeemail", cpeemail);


        try {
            myDatabase = FirebaseDatabase.getInstance().getReference(cpeemail);
        }catch(Exception e){
            Toast.makeText(SessionSearch.this, "plea check the id", Toast.LENGTH_SHORT).show();
        }

         myDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(i==1) {


                    try {
                        String password2 = dataSnapshot.child("keys").child("Ckey").getValue().toString();
                        if (Ckey.equals(password2)) {


                            Toast.makeText(SessionSearch.this, "Login sucessfully", Toast.LENGTH_SHORT).show();

                            i=0;
                            startActivity(new Intent(getApplicationContext(), Parentchild.class));



                        } else {
                            Toast.makeText(SessionSearch.this, "plea check the password", Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {
                        Toast.makeText(SessionSearch.this, "plea check the id", Toast.LENGTH_SHORT).show();
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(SessionSearch.this, "database error...", Toast.LENGTH_SHORT).show();
            }
        });




    }
}










