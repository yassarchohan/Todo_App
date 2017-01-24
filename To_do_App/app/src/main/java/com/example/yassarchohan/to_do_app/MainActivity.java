package com.example.yassarchohan.to_do_app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String Name;
    private String SecretInfo;
    private String newinput1;
    private String input2;

    private EditText edt1;
    private EditText edt2;
    private Button btn;
    private CheckBox chk;
    private Custom_Adapter customadapter;
    private ListView list;
    private Gettermethods gm;
    private TextView txt;
    private EditText name1;
    private EditText Secret1;



    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference Mreferences;
    private ChildEventListener mchildeventlistener;
    private  AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt1 = (EditText) findViewById(R.id.forname);
        edt2 = (EditText) findViewById(R.id.forinfo);
        btn = (Button) findViewById(R.id.forsending);
        list = (ListView) findViewById(R.id.list_item);
        chk = (CheckBox) findViewById(R.id.forcheck);






        firebaseDatabase = FirebaseDatabase.getInstance();
        Mreferences = firebaseDatabase.getReference().child("secretmessages");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Name = edt1.getText().toString();
                SecretInfo = edt2.getText().toString();
                Boolean state;
                String key = Mreferences.push().getKey();


                if (chk.isChecked()) {
                    state = true;
                } else {
                    state = false;
                }

                if (Name == null && SecretInfo == null) {
                    Toast.makeText(MainActivity.this, "Please enter both fields", Toast.LENGTH_SHORT).show();
                } else {


                     gm = new Gettermethods(Name, SecretInfo, state, key);
                    Mreferences.push().setValue(gm);


                    edt1.setText("");
                    edt2.setText("");

                }

            }
        });








//        btn = (Button) findViewById(R.id.todisplaymsg);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dialogmethod();
//            }
//        });

        final List<Gettermethods> messages = new ArrayList<>();
        customadapter = new Custom_Adapter(this, R.layout.activity_custom_view, messages);
        list.setAdapter(customadapter);

     list.setClickable(true);
     list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             switch (position) {
                 case 0:
                     Dialogmethod(position);
                     break;
                 case 1:
                     Dialogmethod(position);
                     break;
                 default:
                     Dialogmethod(position);
                     break;
             }

         }
     });


        mchildeventlistener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                gm = dataSnapshot.getValue(Gettermethods.class);
                gm.setNodeKey(dataSnapshot.getKey());
                customadapter.notifyDataSetChanged();
                customadapter.add(gm);

            }

            @Override
            public void onChildChanged(final DataSnapshot dataSnapshot, String s) {

                String key = dataSnapshot.getKey();
                Gettermethods getter = dataSnapshot.getValue(Gettermethods.class);
                for(Gettermethods gm1 : messages){
                    if(gm1.getNodeKey().equals(key)){
                        gm1.setvalues(getter);
                    }
                    customadapter.notifyDataSetChanged();
                }



            }

            @Override
            public void onChildRemoved(final DataSnapshot dataSnapshot) {

                 final String key = dataSnapshot.getKey();
                         for (Gettermethods gm : messages) {
                             if (key.equals(gm.getNodeKey())) {
                                 messages.remove(gm);
                                 customadapter.notifyDataSetChanged();
                                 break;
                             }
                         }

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        Mreferences.addChildEventListener(mchildeventlistener);


    }

   public void Dialogmethod(final int no) {

      builder = new AlertDialog.Builder(this);

        // Get the layout inflater

            final LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

       final LayoutInflater factory = LayoutInflater.from(MainActivity.this);
       final View view = factory.inflate(R.layout.activity_dialog_message, null);
       name1 = (EditText) view.findViewById(R.id.dialog_Name);
       Secret1 = (EditText) view.findViewById(R.id.dialog_info);





       builder.setView(view)
        // Add action buttons
               .setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int id) {



                       // sign in the user ...
                       Gettermethods message = customadapter.getItem(no);

                       name1.setText(message.getName());
                       Secret1.setText(message.getSecretcode());

                       edt1.setText(message.getName());

                       newinput1 = name1.getText().toString();
                       input2 = Secret1.getText().toString();

                       Toast.makeText(MainActivity.this, "updated successfully" + newinput1 + input2, Toast.LENGTH_LONG).show();

                       update(message,newinput1,input2);


                }
                 })
                .setNegativeButton(R.string.oncancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Gettermethods gm2 = customadapter.getItem(no);
                        delete(gm2);
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "closed transaction", Toast.LENGTH_SHORT).show();

                               }
                           });
                builder.create();
                builder.show();
       }


    public void update(Gettermethods gm , String value1 , String value2){

        gm.setName(value1);
        gm.setSecretcode(value2);
        Mreferences.child(gm.getNodeKey()).setValue(gm);
    }

    public void delete(Gettermethods gm1){
        Mreferences.child(gm1.getNodeKey()).removeValue();
    }


}
















