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
    public long total = 0;
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



                total = 0;

                for(DataSnapshot ds : dataSnapshot1.getChildren()) {
                    for(DataSnapshot ds2 : ds.getChildren()) {

                        Long amount = ds2.child("Plastic").child("Count").getValue(Long.class);
                            total += amount;

                   }
                }
                System.out.println("())))))))))))))))))))))))))))))))))))))))))))))))))))))))))"+total);
                System.out.println("ABHI_GUNJAL");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }


        });
    }
}