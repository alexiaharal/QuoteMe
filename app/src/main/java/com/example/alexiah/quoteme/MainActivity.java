package com.example.alexiah.quoteme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

import static android.R.attr.data;
import static com.example.alexiah.quoteme.R.id.nameForm;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private SharedPreferences preferences;
    private DatabaseReference mDatabase;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.getQuoteTitle);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        /** Set objects **/
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        final EditText name = (EditText) findViewById(R.id.nameForm);
        final EditText surname = (EditText) findViewById(R.id.surnameForm);
        final EditText dateOfBirth = (EditText) findViewById(R.id.dobForm);
        final EditText phone = (EditText) findViewById(R.id.phoneForm);
        final EditText email = (EditText) findViewById(R.id.emailForm);





        // Trying database
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //get database reference
        mDatabase = FirebaseDatabase.getInstance()
                .getReference();
/*        System.out.println("--Printing Database");
        System.out.println(mDatabase);
        mDatabase.child("customer").child("937495").child("name").setValue("Alex");
        mDatabase.child("customer").child("937495").child("surname").setValue("Haralambous");

        mDatabase.child("customer").child("937496").child("name").setValue("Rafaella");
        mDatabase.child("customer").child("937496").child("surname").setValue("Haralambous");*/

    /** Get Personal Details and Add them in Firebase **/
        System.out.println("---"+TextUtils.isEmpty(name.getText().toString()));
        Button next_btn = (Button) findViewById(R.id.buttonNext);
        next_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString())|| TextUtils.isEmpty(surname.getText().toString())|| TextUtils.isEmpty(dateOfBirth.getText().toString()) || TextUtils.isEmpty(phone.getText().toString())|| TextUtils.isEmpty(name.getText().toString())){
                    Toast.makeText(MainActivity.this,
                            "Please fill in all details!", Toast.LENGTH_LONG).show();

                }else{
                    final Person newPerson = new Person(name.getText().toString(), surname.getText().toString(), dateOfBirth.getText().toString(), phone.getText().toString(),email.getText().toString());

                    // Add data to Firebase
                    mDatabase.child("customer").child(newPerson.getPersonId()).child("name").setValue(newPerson.getName());
                    mDatabase.child("customer").child(newPerson.getPersonId()).child("surname").setValue(newPerson.getSurname());
                    mDatabase.child("customer").child(newPerson.getPersonId()).child("dob").setValue(newPerson.getDateOfBirth());
                    mDatabase.child("customer").child(newPerson.getPersonId()).child("phone").setValue(newPerson.getPhone());
                    mDatabase.child("customer").child(newPerson.getPersonId()).child("email").setValue(newPerson.getEmail());


                    //Prepare intent and pass data to PlanChoiceActivity
                    Intent next_intent = new Intent(v.getContext(), PlanChoiceActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("value", newPerson);
                    next_intent.putExtras(bundle);
                    startActivity(next_intent);
                }


            }
        });


    }

}
