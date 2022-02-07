package org.pytorch.demo.objectdetection;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class NewAnalysisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analysis_activity);
    }

    public void getAllCount() {


        DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://solid-waste-segregation-default-rtdb.firebaseio.com/").getReference("GarbageCount");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {

                String itsme = String.valueOf(Objects.requireNonNull(dataSnapshot1.getValue()));
    
                 //     Log.i("child", String.valueOf(itsme));

                int sum = 0;

                for (DataSnapshot ds : dataSnapshot1.getChildren()) {
                    sum = Integer.parseInt(sum + itsme);
                }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }


        };
    }
}