package org.pytorch.demo.objectdetection;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.Locale;


public class FrontPageActivity extends AppCompatActivity implements LocationListener{
    public Button button_location;
    public Button button_analysis;
    TextView textView_location;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        button_location = (Button) findViewById(R.id.button_location);
//        textView_location = findViewById(R.id.text_location);
        //Runtime permissions
        if (ContextCompat.checkSelfPermission(FrontPageActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(FrontPageActivity.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }


        button_location.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getLocation();



            }
        });





        //analysis button code
        button_analysis = (Button) findViewById(R.id.button_analysis);
//        textView_location = findViewById(R.id.text_location);

        button_analysis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openAnalysisActivity();
            }
        });


    }

    public void openAnalysisActivity(){
        Intent intent = new Intent(FrontPageActivity.this, NewAnalysisActivity.class);
        startActivity(intent);
    }
    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
         //   locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,FrontPageActivity.this);

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, FrontPageActivity.this);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, FrontPageActivity.this);


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {

            Toast.makeText(this, "" + location.getLatitude() + "," + location.getLongitude(), Toast.LENGTH_SHORT).show();
            try {
                Geocoder geocoder = new Geocoder(FrontPageActivity.this, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                String address = addresses.get(0).getAddressLine(0);

                Intent intent = new Intent(FrontPageActivity.this, MainActivity.class);

                intent.putExtra("Latitude", location.getLatitude());
                intent.putExtra("Longitude", location.getLongitude());

                startActivity(intent);
                //    textView_location.setText(address);
                locationManager.removeUpdates(this);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}