package org.pytorch.demo.objectdetection;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//piechart libraries
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

// Dropdown
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class NewAnalysisActivity extends AppCompatActivity {
    public long plastic_total = 0;
    public long paper_total = 0;
    public long cardboard_total = 0;
    public long metal_total = 0;
    public long thermocol_total = 0;
    public long glass_total = 0;
    public long total = 0;

    public long pl_total = 0;
    public long pa_total = 0;
    public long c_total = 0;
    public long m_total = 0;
    public long t_total = 0;
    public long g_total = 0;

    TextView tvCardboard, tvPaper, tvPlastic, tvMetal,tvThermacol,tvGlass;
    PieChart pieChart;
    Spinner spinnerLanguages;

    public NewAnalysisActivity(){
        getAllCount();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analysis_activity);

        tvCardboard = findViewById(R.id.tvCardboard);
        tvPaper = findViewById(R.id.tvPaper);
        tvPlastic = findViewById(R.id.tvPlastic);
        tvMetal = findViewById(R.id.tvMetal);
        tvThermacol = findViewById(R.id.tvThermacol);
        tvGlass = findViewById(R.id.tvGlass);
        pieChart = findViewById(R.id.piechart);

        //Dropdown
        spinnerLanguages=findViewById(R.id.spinner_languages);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.Waste, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerLanguages.setAdapter(adapter);
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

                //piechart
                //callpiechart();
                setData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }


        });
    }

/*
    public void callpiechart(){


    }
*/

    public void setData()
    {
        // Set the percentage of language used
        total = cardboard_total + paper_total + plastic_total + metal_total + thermocol_total + glass_total;

        c_total = (cardboard_total * 100) / total;
        pa_total = (paper_total * 100) / total;
        pl_total = (plastic_total * 100) / total;
        m_total = (metal_total * 100) / total;
        t_total = (thermocol_total * 100) / total;
        g_total = (glass_total * 100) / total;
        System.out.println("())))))))))))))))))))))))))))))))))))))))))))))))))))))))))"+c_total);
        System.out.println("())))))))))))))))))))))))))))))))))))))))))))))))))))))))))"+g_total);
        System.out.println("())))))))))))))))))))))))))))))))))))))))))))))))))))))))))"+m_total);
        System.out.println("())))))))))))))))))))))))))))))))))))))))))))))))))))))))))"+pa_total);
        System.out.println("())))))))))))))))))))))))))))))))))))))))))))))))))))))))))"+pl_total);
        System.out.println("())))))))))))))))))))))))))))))))))))))))))))))))))))))))))"+t_total);

        tvCardboard.setText(Integer.toString((int) c_total));
        tvPaper.setText(Integer.toString((int) pa_total));
        tvPlastic.setText(Integer.toString((int) pl_total));
        tvMetal.setText(Integer.toString((int) m_total));
        tvThermacol.setText(Integer.toString((int) t_total));
        tvGlass.setText(Integer.toString((int) g_total));
        // Set the data and color to the pie chart

        pieChart.addPieSlice(
                new PieModel(
                        "Cardboard",
                        Integer.parseInt(tvCardboard.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Paper",
                        Integer.parseInt(tvPaper.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Plastic",
                        Integer.parseInt(tvPlastic.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Metal",
                        Integer.parseInt(tvMetal.getText().toString()),
                        Color.parseColor("#29B6F6")));

        pieChart.addPieSlice(
                new PieModel(
                        "Thermacol",
                        Integer.parseInt(tvThermacol.getText().toString()),
                        Color.parseColor("#000000")));

        pieChart.addPieSlice(
                new PieModel(
                        "Glass",
                        Integer.parseInt(tvGlass.getText().toString()),
                        Color.parseColor("#0FECBF")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}
