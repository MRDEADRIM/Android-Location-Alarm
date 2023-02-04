package com.android.location_alarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SmsInput extends AppCompatActivity {

    EditText phonenum,text;
    ImageView send;
    private final static int SEND_SMS_PERMISSION_REQ=1;
    private final static int CONTACT_PERMISSION_REQ =2;
    String BarInputcolor="", TextInputcolor="",BgInputcolor="",UserColorStatus,StatusBar,ci;
    View view;
    TextView Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_input);
        Name=findViewById(R.id.c_name);
        phonenum =findViewById(R.id.phonenum);
        text =findViewById(R.id.s_text);
        send =findViewById(R.id.btn_send);
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
            SharedPreferences SharedPreferencesb = getSharedPreferences("ci", MODE_PRIVATE);
            ci = SharedPreferencesb.getString("ci", "");
            if (ci.equals("1")) {

                phonenum.setBackgroundResource(R.drawable.black_b);
                text.setBackgroundResource(R.drawable.black_b);
                phonenum.setTextColor(Color.parseColor(TextInputcolor));
                text.setTextColor(Color.parseColor(TextInputcolor));
            }
            if (ci.equals("2")) {

                phonenum.setBackgroundResource(R.drawable.grey_b);
                text.setBackgroundResource(R.drawable.grey_b);
                phonenum.setTextColor(Color.parseColor(TextInputcolor));
                text.setTextColor(Color.parseColor(TextInputcolor));
            }
            if (ci.equals("3")) {


                phonenum.setBackgroundResource(R.drawable.white_b);
                text.setBackgroundResource(R.drawable.white_b);
                phonenum.setTextColor(Color.parseColor(TextInputcolor));
                text.setTextColor(Color.parseColor(TextInputcolor));
            }
            if (ci.equals("4")) {

                phonenum.setBackgroundResource(R.drawable.red_b);
                text.setBackgroundResource(R.drawable.red_b);
                phonenum.setTextColor(Color.parseColor(TextInputcolor));
                text.setTextColor(Color.parseColor(TextInputcolor));
            }
        }else
        {
            Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPermission(Manifest.permission.SEND_SMS))
                {
                    Toast.makeText(SmsInput.this, "checking for permission", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ActivityCompat.requestPermissions(SmsInput.this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQ);
                }
                String Phonenum= phonenum.getText().toString();
                String Text= text.getText().toString();
                if(!TextUtils.isEmpty(Phonenum)&&!TextUtils.isEmpty(Text))
                {
                    if(checkPermission(Manifest.permission.SEND_SMS))
                    {
                        try {
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(Phonenum, null, Text, null, null);
                            Toast.makeText(SmsInput.this, "loading...", Toast.LENGTH_SHORT).show();
                        }
                        catch(Exception w){
                            Toast.makeText(SmsInput.this, "something went wrong check the inputs", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(SmsInput.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(SmsInput.this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void show_c(View v){
        if(checkPermission(Manifest.permission.READ_CONTACTS))
        {
            Toast.makeText(SmsInput.this, "Permission grand..", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(intent, 1);
        }
        else
        {
            ActivityCompat.requestPermissions(SmsInput.this, new String[]{Manifest.permission.READ_CONTACTS}, CONTACT_PERMISSION_REQ);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Uri contactData = data.getData();
            Cursor c =  getContentResolver().query(contactData, null, null, null, null);
            if (c.moveToFirst()) {
                String phoneNumber="";
                String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                String hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                if ( hasPhone.equalsIgnoreCase("1"))
                    hasPhone = "true";
                else
                    hasPhone = "false" ;
                if (Boolean.parseBoolean(hasPhone))
                {
                    Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId,null, null);
                    while (phones.moveToNext())
                    {
                        phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                    phones.close();
                }
                phonenum.setText(phoneNumber);
                if(!name.equals("") && !phoneNumber.equals("")) {
                    Name.setText("Name: " + name);
                }else {
                    Toast.makeText(this, "can't add this person ", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "change the person or add info manually", Toast.LENGTH_SHORT).show();
                    Name.setText("");
                }

            }
            c.close();
        }
    }
    private boolean checkPermission(String sendSms) {
        int checkpermission= ContextCompat.checkSelfPermission(this,sendSms);
        return checkpermission == PackageManager.PERMISSION_GRANTED;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case SEND_SMS_PERMISSION_REQ:
                if(grantResults.length>0 &&(grantResults[0]==PackageManager.PERMISSION_GRANTED))
                {
                    Toast.makeText(SmsInput.this, "sending...", Toast.LENGTH_SHORT).show();
                }
                break;
//contact and sms permission
            case CONTACT_PERMISSION_REQ:
                if(grantResults.length>0 &&(grantResults[0]==PackageManager.PERMISSION_GRANTED))
                {
                    Toast.makeText(SmsInput.this, "Permission grand..", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, 1);


                }
                break;
        }
    }
}
