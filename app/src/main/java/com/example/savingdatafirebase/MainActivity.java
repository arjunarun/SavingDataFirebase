package com.example.savingdatafirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText name;
    Button b, b2;
    Spinner s;
    TextView t1, t2;
    String value1="";

    // Database retrieval
    String userKey = "";

    DatabaseReference databseHero;

   // ListView listHeroes;
    List<Artist> heroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databseHero = FirebaseDatabase.getInstance().getReference("superheroes");

        name = (EditText)findViewById(R.id.t1);
        b = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.myButton);
        s = (Spinner)findViewById(R.id.comics);
        t1 = (TextView)findViewById(R.id.text1);
        t2 = (TextView)findViewById(R.id.text2);
        //listHeroes = (ListView)findViewById(R.id.listheroes);
        heroList = new ArrayList<>();

        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addArtist();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrieveArtist();
            }
        });
    }


    private void addArtist(){
        String name1 = name.getText().toString().trim();
        String comic1 = s.getSelectedItem().toString();
        
        if(!TextUtils.isEmpty(name1)){
            String id = databseHero.push().getKey();
            Artist artist = new Artist(id, name1, comic1);
            databseHero.child(id).setValue(artist);
            Toast.makeText(MainActivity.this, "Superhero Added", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Fields can't be empty", Toast.LENGTH_SHORT).show();
        }
    }
    public void retrieveArtist() {
        databseHero.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    userKey = userSnapshot.getKey();
                    if (!userKey.isEmpty()) {
                        if((userSnapshot.child("heroName").getValue().toString()).equals("Spiderman")) {
                            t1.setText(userSnapshot.child("heroName").getValue().toString());
                            break;
                        }
                        //t1.setText(userSnapshot.child("heroComic").getValue().toString());
                        //String m1 = userSnapshot.child("heroComic").getValue().toString();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
