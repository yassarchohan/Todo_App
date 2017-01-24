package com.example.yassarchohan.to_do_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Dialog_message extends AppCompatActivity {

    private Button btn;
    private EditText edt;
    private EditText edt2;


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference Mreferences;
    private ChildEventListener mchildeventlistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_message);

    }
}
