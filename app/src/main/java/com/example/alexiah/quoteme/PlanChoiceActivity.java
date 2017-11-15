package com.example.alexiah.quoteme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexiah on 17/07/2017.
 */

public class PlanChoiceActivity extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_choice);

        listView = (ExpandableListView)findViewById(R.id.lvExp);
        initData();
        listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);

        /** Get Person's details **/
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        final Person personPassed = (Person) bundle.getSerializable("value");

        //Define onClick behaviour
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                System.out.println("On click");
                System.out.println(parent.getExpandableListAdapter().getChild(groupPosition,childPosition));

                //Prepare to send email
                Log.i("SendMailActivity", "Send Button Clicked.");

                String fromEmail = "rafaellatrust@gmail.com";
                String fromPassword = "raf99228159";
                String toEmails = "rafaellatrust@gmail.com ";
                System.out.println("toEmails: "+ toEmails);
                List toEmailList = Arrays.asList(toEmails
                        .split("\\s*,\\s*"));
                System.out.println("toEmails: "+ toEmails);

                Log.i("SendMailActivity", "To List: " + toEmailList);
                String emailSubject = "QuoteMe New";

                String emailText = "Personal Details \n Name: "+ personPassed.getName() +"\n Surname: " + personPassed.getSurname() +"\n Email: " + personPassed.getEmail();

                String emailBody = emailText+ "\n Plan: "+ parent.getExpandableListAdapter().getChild(groupPosition,childPosition);
                new SendMailTask(PlanChoiceActivity.this).execute(fromEmail,
                        fromPassword, toEmailList, emailSubject, emailBody);


                return true;
            }
        });
    }



    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("General Insurances");
        listDataHeader.add("Life Insurances");


        List<String> general = new ArrayList<>();
        general.add("Vehicle");
        general.add("Medical");


        List<String> life = new ArrayList<>();
        life.add("Life Invest");

        listHash.put(listDataHeader.get(0),general);
        listHash.put(listDataHeader.get(1),life);

    }
}
