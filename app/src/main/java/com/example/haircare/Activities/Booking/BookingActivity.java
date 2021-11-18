package com.example.haircare.Activities.Booking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.haircare.HomeActivity;
import com.example.haircare.LoginHuaweiActivity;
import com.example.haircare.R;

public class BookingActivity extends AppCompatActivity {
    private ImageView ImageViewBackHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_booking);
        initview();
        BackToHome();
    }
    private void BackToHome(){
        ImageViewBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initview(){
        ImageViewBackHome = findViewById(R.id.imageView_Home);
    }
}