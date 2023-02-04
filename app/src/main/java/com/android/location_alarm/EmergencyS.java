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
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class EmergencyS extends AppCompatActivity {

    EditText ename,enumber;
    Button btn_eview,btn_eadd,btn;
    ListView elist;
    ArrayAdapter E_ArrayAdapter;
    EmergencySDatabaseHelper emergencySDatabaseHelper;
    String BarInputcolor="", TextInputcolor="",BgInputcolor="",UserColorStatus,StatusBar,ci;
    View view,linearLayout;
    private final static int CONTACT_PERMISSION_REQ =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_s);
        ename = findViewById(R.id.ename);
        enumber = findViewById(R.id.enumber);
        btn_eview=findViewById(R.id.btn_eview);
        btn_eadd = findViewById(R.id.btn_eadd);
        btn=findViewById(R.id.button8);
        elist = findViewById(R.id.elist);
        linearLayout = findViewById(R.id.linearLayout);
        emergencySDatabaseHelper = new EmergencySDatabaseHelper(EmergencyS.this);
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
            linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

            if (ci.equals("1")) {


                linearLayout.setBackgroundResource(R.drawable.black_b);

                btn_eview.setBackgroundResource(R.drawable.black_b);
                btn.setBackgroundResource(R.drawable.black_b);
                btn_eadd.setBackgroundResource(R.drawable.black_b);
                ename.setBackgroundResource(R.drawable.black_b);
                enumber.setBackgroundResource(R.drawable.black_b);
                btn_eview.setTextColor(Color.parseColor(TextInputcolor));
                btn.setTextColor(Color.parseColor(TextInputcolor));
                btn_eadd.setTextColor(Color.parseColor(TextInputcolor));
                ename.setTextColor(Color.parseColor(TextInputcolor));
                enumber.setTextColor(Color.parseColor(TextInputcolor));
            }
            if (ci.equals("2")) {
                linearLayout.setBackgroundResource(R.drawable.grey_b);

                btn_eview.setBackgroundResource(R.drawable.grey_b);
                btn.setBackgroundResource(R.drawable.grey_b);
                btn_eadd.setBackgroundResource(R.drawable.grey_b);
                ename.setBackgroundResource(R.drawable.grey_b);
                enumber.setBackgroundResource(R.drawable.grey_b);
                btn_eview.setTextColor(Color.parseColor(TextInputcolor));
                btn.setTextColor(Color.parseColor(TextInputcolor));
                btn_eadd.setTextColor(Color.parseColor(TextInputcolor));
                ename.setTextColor(Color.parseColor(TextInputcolor));
                enumber.setTextColor(Color.parseColor(TextInputcolor));
            }
            if (ci.equals("3")) {

                linearLayout.setBackgroundResource(R.drawable.white_b);

                btn_eview.setBackgroundResource(R.drawable.white_b);
                btn.setBackgroundResource(R.drawable.white_b);
                btn_eadd.setBackgroundResource(R.drawable.white_b);
                ename.setBackgroundResource(R.drawable.white_b);
                enumber.setBackgroundResource(R.drawable.white_b);
                btn_eview.setTextColor(Color.parseColor(TextInputcolor));
                btn.setTextColor(Color.parseColor(TextInputcolor));
                btn_eadd.setTextColor(Color.parseColor(TextInputcolor));
                ename.setTextColor(Color.parseColor(TextInputcolor));
                enumber.setTextColor(Color.parseColor(TextInputcolor));
            }
            if (ci.equals("4")) {
                linearLayout.setBackgroundResource(R.drawable.red_b);


                btn_eview.setBackgroundResource(R.drawable.red_b);
                btn.setBackgroundResource(R.drawable.red_b);
                btn_eadd.setBackgroundResource(R.drawable.red_b);
                ename.setBackgroundResource(R.drawable.red_b);
                enumber.setBackgroundResource(R.drawable.red_b);
                btn_eview.setTextColor(Color.parseColor(TextInputcolor));
                btn.setTextColor(Color.parseColor(TextInputcolor));
                btn_eadd.setTextColor(Color.parseColor(TextInputcolor));
                ename.setTextColor(Color.parseColor(TextInputcolor));
                enumber.setTextColor(Color.parseColor(TextInputcolor));
            }
        }else
        {
            Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }
        //E_ArrayAdapter = new ArrayAdapter<EmergencyContact>(EmergencyS.this, android.R.layout.simple_list_item_1, emergencySDatabaseHelper.getEveryone());//hidden user view database wile startup ----******
        //elist.setAdapter(E_ArrayAdapter);
        btn_eadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmergencyContact emergencyContact;
                try {
                    emergencyContact = new EmergencyContact(-1, ename.getText().toString(), enumber.getText().toString());
                    Toast.makeText(EmergencyS.this, emergencyContact.toString(), Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    Toast.makeText(EmergencyS.this, "ERROR", Toast.LENGTH_SHORT).show();
                    emergencyContact = new EmergencyContact (-1,"error","0");
                }
                EmergencySDatabaseHelper emergencySDatabaseHelper = new EmergencySDatabaseHelper(EmergencyS.this);
                boolean success = emergencySDatabaseHelper.addOne(emergencyContact);
                Toast.makeText(EmergencyS.this, "success ="+ success, Toast.LENGTH_SHORT).show();
                E_ArrayAdapter = new ArrayAdapter<EmergencyContact>(EmergencyS.this, android.R.layout.simple_list_item_1, emergencySDatabaseHelper.getEveryone()){

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view =super.getView(position, convertView, parent);

                    TextView textView=(TextView) view.findViewById(android.R.id.text1);

                    /*YOUR CHOICE OF COLOR*/
                    if(UserColorStatus.equals("1")) {
                        textView.setTextColor(Color.parseColor(TextInputcolor));
                    }

                    return view;
                }
            };




                //user view on add database-----****
                elist.setAdapter(E_ArrayAdapter);
                //String name = getColoredSpanned("Hiren", "#800000");
            }
        });
        btn_eview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmergencySDatabaseHelper emergencySDatabaseHelper = new EmergencySDatabaseHelper(EmergencyS.this);
                E_ArrayAdapter = new ArrayAdapter<EmergencyContact>(EmergencyS.this, android.R.layout.simple_list_item_1, emergencySDatabaseHelper.getEveryone()){

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view =super.getView(position, convertView, parent);

                        TextView textView=(TextView) view.findViewById(android.R.id.text1);

                        /*YOUR CHOICE OF COLOR*/
                        if(UserColorStatus.equals("1")) {
                            textView.setTextColor(Color.parseColor(TextInputcolor));
                        }

                        return view;
                    }
                };


                //user view on click view database------*******
                elist.setAdapter(E_ArrayAdapter);
            }
        });
        elist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EmergencyContact clickedPeople = (EmergencyContact)parent.getItemAtPosition(position);
                emergencySDatabaseHelper.deleateOne(clickedPeople);
                E_ArrayAdapter = new ArrayAdapter<EmergencyContact>(EmergencyS.this, android.R.layout.simple_list_item_1, emergencySDatabaseHelper.getEveryone()){

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view =super.getView(position, convertView, parent);

                        TextView textView=(TextView) view.findViewById(android.R.id.text1);

                        /*YOUR CHOICE OF COLOR*/


                        if(UserColorStatus.equals("1")) {
                            textView.setTextColor(Color.parseColor(TextInputcolor));
                        }


                        return view;
                    }
                };




                elist.setAdapter(E_ArrayAdapter);
                Toast.makeText(EmergencyS.this, "Deleted "+clickedPeople.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Text(View v){
        startActivity(new Intent(getApplicationContext(), Text.class));
    }
    public void show_con(View v){
        if(checkPermission(Manifest.permission.READ_CONTACTS))
        {
            Toast.makeText(EmergencyS.this, "Permission grand..", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(intent, 1);
        }
        else
        {
            ActivityCompat.requestPermissions(EmergencyS.this, new String[]{Manifest.permission.READ_CONTACTS}, CONTACT_PERMISSION_REQ);
        }
    }
    private boolean checkPermission(String readContacts) {
        int checkpermission= ContextCompat.checkSelfPermission(this,readContacts);
        return checkpermission == PackageManager.PERMISSION_GRANTED;
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
                enumber.setText(phoneNumber);

                if(!name.equals("") && !phoneNumber.equals("")) {
                    ename.setText(name);
                }else {
                    Toast.makeText(this, "can't add this person ", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "change the person or add info manually", Toast.LENGTH_SHORT).show();
                    ename.setText("");
                }
            }
            c.close();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case CONTACT_PERMISSION_REQ:
                if(grantResults.length>0 &&(grantResults[0]==PackageManager.PERMISSION_GRANTED))
                {
                    Toast.makeText(EmergencyS.this, "Permission grand..", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, 1);
                }
                break;
        }
    }
}