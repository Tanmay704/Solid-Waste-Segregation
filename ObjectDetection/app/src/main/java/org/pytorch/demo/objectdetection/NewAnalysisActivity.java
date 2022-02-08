package org.pytorch.demo.objectdetection;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class NewAnalysisActivity extends AppCompatActivity {
    public long plastic_total = 0;
    public long paper_total = 0;
    public long cardboard_total = 0;
    public long metal_total = 0;
    public long thermocol_total = 0;
    public long glass_total = 0;
    public NewAnalysisActivity(){
        getAllCount();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analysis_activity);
    }

    public void getAllCount() {


        DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://solid-waste-segregation-default-rtdb.firebaseio.com/").getReference("GarbageCount");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {



                plastic_total = 0;
                paper_total = 0;
                cardboard_total = 0;
                metal_total = 0;
                thermocol_total = 0;
                glass_total = 0;
                for(DataSnapshot ds : dataSnapshot1.getChildren()) {
                    for(DataSnapshot ds2 : ds.getChildren()) {

                        Long amount = ds2.child("Plastic").child("Count").getValue(Long.class);
                        plastic_total += amount;
                        amount = ds2.child("Paper").child("Count").getValue(Long.class);
                        paper_total += amount;
                        amount = ds2.child("Cardboard").child("Count").getValue(Long.class);
                        cardboard_total += amount;
                        amount = ds2.child("Metal").child("Count").getValue(Long.class);
                        metal_total += amount;
                        amount = ds2.child("Thermocol").child("Count").getValue(Long.class);
                        thermocol_total += amount;
                        amount = ds2.child("Glass").child("Count").getValue(Long.class);
                        glass_total += amount;

                   }
                }
                System.out.println("())))))))))))))))))))))))))))))))))))))))))))))))))))))))))"+cardboard_total);
                System.out.println("())))))))))))))))))))))))))))))))))))))))))))))))))))))))))"+glass_total);
                System.out.println("())))))))))))))))))))))))))))))))))))))))))))))))))))))))))"+metal_total);
                System.out.println("())))))))))))))))))))))))))))))))))))))))))))))))))))))))))"+paper_total);
                System.out.println("())))))))))))))))))))))))))))))))))))))))))))))))))))))))))"+plastic_total);
                System.out.println("())))))))))))))))))))))))))))))))))))))))))))))))))))))))))"+thermocol_total);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }


        });
    }
}