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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SessionCreate extends AppCompatActivity {

    private DatabaseReference myDatabase;
    private String email,s,Key;
    String BarInputcolor="", TextInputcolor="",BgInputcolor="",UserColorStatus,StatusBar,ci;
    View view;
    TextView id,key;

    EditText childkey,mySub;
    Button sub;
    ImageView bview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_create);

        bview=findViewById(R.id.view_topic);

        id=findViewById(R.id.eid);
        key=findViewById(R.id.createkey);


        mySub=findViewById(R.id.subject);

        childkey=findViewById(R.id.childkey);
        sub=findViewById(R.id.button12);

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


        SharedPreferences sharedPreferences = getSharedPreferences("eKey", MODE_PRIVATE);
        email = sharedPreferences.getString("email", "");
        email = email.replaceAll("@gmail.com", "");




        SharedPreferences SharedPreferencesk = getSharedPreferences("key", MODE_PRIVATE);
        Key = SharedPreferencesk.getString("key", "");


        id.setText("Id:-" + email);
        key.setText("Key:-" + Key);



        if(UserColorStatus.equals("1")) {
            getWindow().setStatusBarColor(Color.parseColor(StatusBar));
            this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(BarInputcolor)));
            getSupportActionBar().setTitle(Html.fromHtml("<font color=" + TextInputcolor + ">" + getString(R.string.app_name) + "</font>"));
            view.setBackgroundColor(Color.parseColor(BgInputcolor));



            SharedPreferences SharedPreferencesb = getSharedPreferences("ci", MODE_PRIVATE);
            ci = SharedPreferencesb.getString("ci", "");














            id.setTextColor(Color.parseColor(TextInputcolor));
            key.setTextColor(Color.parseColor(TextInputcolor));











            if (ci.equals("1")) {

                childkey.setBackgroundResource(R.drawable.black_b);
                sub.setBackgroundResource(R.drawable.black_b);
                mySub.setBackgroundResource(R.drawable.black_b);
                childkey.setTextColor(Color.parseColor(TextInputcolor));
                sub.setTextColor(Color.parseColor(TextInputcolor));
                mySub.setTextColor(Color.parseColor(TextInputcolor));

            }
            if (ci.equals("2")) {

                childkey.setBackgroundResource(R.drawable.grey_b);
                sub.setBackgroundResource(R.drawable.grey_b);
                mySub.setBackgroundResource(R.drawable.grey_b);

                childkey.setTextColor(Color.parseColor(TextInputcolor));
                sub.setTextColor(Color.parseColor(TextInputcolor));
                mySub.setTextColor(Color.parseColor(TextInputcolor));



            }
            if (ci.equals("3")) {

                childkey.setBackgroundResource(R.drawable.white_b);
                sub.setBackgroundResource(R.drawable.white_b);
                mySub.setBackgroundResource(R.drawable.white_b);

                childkey.setTextColor(Color.parseColor(TextInputcolor));
                sub.setTextColor(Color.parseColor(TextInputcolor));
                mySub.setTextColor(Color.parseColor(TextInputcolor));


            }
            if (ci.equals("4")) {


                childkey.setBackgroundResource(R.drawable.red_b);
                sub.setBackgroundResource(R.drawable.red_b);
                mySub.setBackgroundResource(R.drawable.red_b);

                childkey.setTextColor(Color.parseColor(TextInputcolor));
                sub.setTextColor(Color.parseColor(TextInputcolor));
                mySub.setTextColor(Color.parseColor(TextInputcolor));
            }




        }else
        {
            Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }
    }

    public void view_topic(View v){
        startActivity(new Intent(SessionCreate.this, Chat.class));
        finish();
    }



    public void chat(View v)
    {

        myDatabase = FirebaseDatabase.getInstance().getReference(email).child("chat");
        myDatabase.child(mySub.getText().toString()).child(email).setValue("Created this account ");
        myDatabase = FirebaseDatabase.getInstance().getReference(email);
        EditText childkey = findViewById(R.id.childkey);
        String key=childkey.getText().toString().trim();


        SharedPreferences sharedPref = getSharedPreferences("key", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("key", key);
        editor.apply();




        if(key!=null){

            myDatabase.child("keys").child("Ckey").setValue(key);


            startActivity(new Intent(SessionCreate.this, Chat.class));
            finish();
        }
        else{
            Toast.makeText(SessionCreate.this, "enter the password", Toast.LENGTH_SHORT).show();
        }




    }


}