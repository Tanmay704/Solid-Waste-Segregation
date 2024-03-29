package org.pytorch.demo.objectdetection;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;

//piechart libraries
// Dropdown
//Barchart

public class NewAnalysisActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
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

   //locationwise count
    public long l_pl_total = 0;
    public long l_pa_total = 0;
    public long l_c_total = 0;
    public long l_m_total = 0;
    public long l_t_total = 0;
    public long l_g_total = 0;
    //for drop down
    TextView txtMarquee;
    private  DatabaseReference databaseReference;

    List<String> locations = new ArrayList<String>();
    TextView tvCardboard, tvPaper, tvPlastic, tvMetal, tvThermacol, tvGlass;
    PieChart pieChart;


    public NewAnalysisActivity() {
        getAllCount();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analysis_activity);

        // casting of marquee textview
        txtMarquee = (TextView) findViewById(R.id.marqueeText);
        txtMarquee.setSelected(true);


        //chart
        tvCardboard = findViewById(R.id.tvCardboard);
        tvPaper = findViewById(R.id.tvPaper);
        tvPlastic = findViewById(R.id.tvPlastic);
        tvMetal = findViewById(R.id.tvMetal);
        tvThermacol = findViewById(R.id.tvThermacol);
        tvGlass = findViewById(R.id.tvGlass);
        pieChart = findViewById(R.id.piechart);


        //Dropdown for locations
        locations.add("All");

        Spinner spineerLocation = (Spinner) findViewById(R.id.spinner_locations);
        Button button = (Button) findViewById(R.id.chartbutton);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locations);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spineerLocation.setPrompt("Select your Location!");
        spineerLocation.setAdapter(adapter);

        spineerLocation.setOnItemSelectedListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("--------------------"+spineerLocation.getSelectedItem());
                String location = spineerLocation.getSelectedItem().toString();
                getCountbyLocation(location);
//                Intent intent= new Intent(NewAnalysisActivity.this,SecondActivity.class);
//                intent.putExtra("data",String.valueOf(spineerLocation.getSelectedItem()));
//                startActivity(intent);
            }
        });
    }
    // System.out.println("aloalaolaolaolaolaoalllllllllllllllllllllllll");


    @Override
    public void onItemSelected(AdapterView<?> parent, View arg1, int position, long id) {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + locations.get(position));
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(), "please Select location: ", Toast.LENGTH_SHORT).show();
    }
    public void getCountbyLocation(String location){

        databaseReference = FirebaseDatabase.getInstance("https://solid-waste-segregation-default-rtdb.firebaseio.com/").getReference("GarbageCount").child(location);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {


                for (DataSnapshot ds : dataSnapshot1.getChildren()) {


                    l_pl_total = ds.child("Plastic").child("Count").getValue(Long.class);

                    l_pa_total = ds.child("Paper").child("Count").getValue(Long.class);

                    l_c_total = ds.child("Cardboard").child("Count").getValue(Long.class);

                    l_m_total = ds.child("Metal").child("Count").getValue(Long.class);

                    l_t_total = ds.child("Thermocol").child("Count").getValue(Long.class);

                    l_g_total = ds.child("Glass").child("Count").getValue(Long.class);

                    }
         //call the bar graph
                barChart();
              //piechart
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NewAnalysisActivity.this, "Network Error..." + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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

                for (DataSnapshot ds : dataSnapshot1.getChildren()) {

                    locations.add(ds.getKey().toString());
                    for (DataSnapshot ds2 : ds.getChildren()) {

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
//                System.out.println("())))))))))))))))))))))))))))))))))))))))))))))))))))))))))"+cardboard_total);
//                System.out.println("())))))))))))))))))))))))))))))))))))))))))))))))))))))))))"+glass_total);
//                System.out.println("())))))))))))))))))))))))))))))))))))))))))))))))))))))))))"+metal_total);
//                System.out.println("())))))))))))))))))))))))))))))))))))))))))))))))))))))))))"+paper_total);
//                System.out.println("())))))))))))))))))))))))))))))))))))))))))))))))))))))))))"+plastic_total);
//                System.out.println("())))))))))))))))))))))))))))))))))))))))))))))))))))))))))"+thermocol_total);

                //piechart
                //callpiechart();
                setData();
            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(NewAnalysisActivity.this, "Network Error..." + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });
    }
    //Barchart
    public void barChart(){

        BarChart barChart = (BarChart) findViewById(R.id.barchart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        long totalOf = l_c_total + l_g_total + l_m_total + l_pl_total + l_t_total + l_pa_total;
        if(totalOf == 0)
            totalOf = 1;
        entries.add(new BarEntry((l_pl_total*100)/totalOf, 0));
        entries.add(new BarEntry((l_pa_total*100)/totalOf , 1));
        entries.add(new BarEntry((l_c_total*100)/totalOf , 2));
        entries.add(new BarEntry((l_m_total*100)/totalOf , 3));
        entries.add(new BarEntry((l_t_total*100)/totalOf , 4));
        entries.add(new BarEntry((l_g_total*100)/totalOf , 5));

        BarDataSet bardataset = new BarDataSet(entries, "Cells");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Plastic");
        labels.add("Paper");
        labels.add("Cardboard");
        labels.add("Metal");
        labels.add("Thermocol");
        labels.add("Glass");

        BarData data = new BarData(labels, bardataset);
        barChart.setData(data); // set the data and list of labels into chart
        barChart.setDescription("%age of different Wastes");  // set the description
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.animateY(5000);

    }


    public void setData() {
        // Set the percentage of waste
        total = cardboard_total + paper_total + plastic_total + metal_total + thermocol_total + glass_total;
        if(total == 0)
            total = 1;
        c_total = (cardboard_total * 100) / total;
        pa_total = (paper_total * 100) / total;
        pl_total = (plastic_total * 100) / total;
        m_total = (metal_total * 100) / total;
        t_total = (thermocol_total * 100) / total;
        g_total = (glass_total * 100) / total;


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
                        "Thermocol",
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


