package com.android.location_alarm;

import androidx.annotation.NonNull;
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
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Info extends AppCompatActivity {

    TextView emailid;
    String email;
    String BarInputcolor="", TextInputcolor="",BgInputcolor="",UserColorStatus,StatusBar,ci;
    View view;
    Button log,clr;
    private DatabaseReference Reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        log=findViewById(R.id.button24);
        clr=findViewById(R.id.button25);


        SharedPreferences sharedPreferences = getSharedPreferences("eKey", MODE_PRIVATE);
        email = sharedPreferences.getString("email", "");
        emailid=findViewById(R.id.emailid);
        emailid.setText("Logded in Id :"+email);








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

            emailid.setTextColor(Color.parseColor(TextInputcolor));




            SharedPreferences SharedPreferencesb = getSharedPreferences("ci", MODE_PRIVATE);
            ci = SharedPreferencesb.getString("ci", "");
            if (ci.equals("1")) {




                log.setBackgroundResource(R.drawable.black_b);
                clr.setBackgroundResource(R.drawable.black_b);


                log.setTextColor(Color.parseColor(TextInputcolor));
                clr.setTextColor(Color.parseColor(TextInputcolor));
            }
            if (ci.equals("2")) {
                log.setBackgroundResource(R.drawable.grey_b);
                clr.setBackgroundResource(R.drawable.grey_b);

                log.setTextColor(Color.parseColor(TextInputcolor));
                clr.setTextColor(Color.parseColor(TextInputcolor));
            }
            if (ci.equals("3")) {
                log.setBackgroundResource(R.drawable.white_b);
                clr.setBackgroundResource(R.drawable.white_b);


                log.setTextColor(Color.parseColor(TextInputcolor));
                clr.setTextColor(Color.parseColor(TextInputcolor));
            }
            if (ci.equals("4")) {
                log.setBackgroundResource(R.drawable.red_b);
                clr.setBackgroundResource(R.drawable.red_b);


                log.setTextColor(Color.parseColor(TextInputcolor));
                clr.setTextColor(Color.parseColor(TextInputcolor));
            }








        }else
        {
            Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }

















    }
    public void logout(View v) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void delete(View v){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to remove all the data ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {


                        email = email.replaceAll("@gmail.com", "");

                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        FirebaseUser currentUser = firebaseAuth.getCurrentUser();


                        Log.d("emfdggfgail", email);

                        currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Info.this, "exit successfully", Toast.LENGTH_SHORT).show();


                                    Reference = FirebaseDatabase.getInstance().getReference(email);
                                    Reference.removeValue();

                                    startActivity(new Intent(getApplicationContext(), Register.class));
                                    finish();
                                }
                            }

                        });
                    /*
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(Info.this, "failed to remove", Toast.LENGTH_SHORT).show();
                                }
                            });

                     */
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                        Toast.makeText(Info.this, "continue", Toast.LENGTH_SHORT).show();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
}