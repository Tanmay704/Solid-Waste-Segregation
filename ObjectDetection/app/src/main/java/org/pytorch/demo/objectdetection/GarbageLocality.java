package org.pytorch.demo.objectdetection;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class GarbageLocality {
    boolean flag;

    public void check_locality(String locality,String formattedDate){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://solid-waste-segregation-default-rtdb.firebaseio.com/").getReference("GarbageCount").child(locality);
        DatabaseReference userReference = FirebaseDatabase.getInstance("https://solid-waste-segregation-default-rtdb.firebaseio.com/").getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String newDateStr = null;
                if(snapshot.exists()) {
                    //create new user

                   setValue(true);
                }else {

                 //   System.out.println(":::::::::::::::::::::::::::::::::::::::::"+formattedDate);
                    userReference.child("GarbageCount").child(locality).child(formattedDate).child("Cardboard").child("Count").setValue(0);
                    userReference.child("GarbageCount").child(locality).child(formattedDate).child("Glass").child("Count").setValue(0);
                    userReference.child("GarbageCount").child(locality).child(formattedDate).child("Metal").child("Count").setValue(0);
                    userReference.child("GarbageCount").child(locality).child(formattedDate).child("Paper").child("Count").setValue(0);
                    userReference.child("GarbageCount").child(locality).child(formattedDate).child("Plastic").child("Count").setValue(0);
                    userReference.child("GarbageCount").child(locality).child(formattedDate).child("Thermocol").child("Count").setValue(0);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void setValue(boolean flag){
        this.flag = flag;
    }
    public boolean getValue(){
        return flag;
    }
}

