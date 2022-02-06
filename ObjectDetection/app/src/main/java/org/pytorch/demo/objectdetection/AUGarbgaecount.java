package org.pytorch.demo.objectdetection;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AUGarbgaecount {

    public Task<Void> update(String locality,String formattedDate,long count1, long count2, long count3, long count4, long count5, long count6) {


        Task t;
//        System.out.println("/////////////////////////////////////////////////////////////////////");
//        System.out.println(count1);
//        System.out.println(count2);
//        System.out.println(count3);
//        System.out.println(count4);
//        System.out.println(count5);
//        System.out.println(count6);
//        System.out.println("/////////////////////////////////////////////////////////////////////");
//



        DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://solid-waste-segregation-default-rtdb.firebaseio.com/").getReference("GarbageCount").child(locality).child(formattedDate);

        t = databaseReference.child("Cardboard").child("Count").setValue(ServerValue.increment(count1));
        t = databaseReference.child("Glass").child("Count").setValue(ServerValue.increment(count2));
        t = databaseReference.child("Metal").child("Count").setValue(ServerValue.increment(count3));
        t = databaseReference.child("Paper").child("Count").setValue(ServerValue.increment(count4));
        t = databaseReference.child("Plastic").child("Count").setValue(ServerValue.increment(count5));
        t = databaseReference.child("Thermocol").child("Count").setValue(ServerValue.increment(count6 ));


    return t;
}
}