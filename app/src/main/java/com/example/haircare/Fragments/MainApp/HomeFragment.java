package com.example.haircare.Fragments.MainApp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.haircare.Activities.Booking.HistoryCutActivity;
import com.example.haircare.Model.Adapter.PhotoAdapter;
import com.example.haircare.Model.Photo;
import com.example.haircare.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment {
    private View view;
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPagerSlider;
    private CircleIndicator circleIndicator;
    // adapter
    private PhotoAdapter photoAdapter;
    // param
    private List<Photo> photoList;
    private Timer timer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initview();
        setListenFragment();
        setSlider();
        autoSlideImage();
        return view;
    }
    private void autoSlideImage() {
        if (photoList == null || photoList.isEmpty() || viewPagerSlider == null) {
            return;
        }

        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPagerSlider.getCurrentItem();
                        int totalItem = photoList.size() - 1;
                        if (currentItem < totalItem) {
                            currentItem++;
                            viewPagerSlider.setCurrentItem(currentItem);
                        } else {
                            viewPagerSlider.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }
    private void setSlider() {
        photoList = getListPhoto();

        photoAdapter = new PhotoAdapter(getContext(), photoList);

        viewPagerSlider.setAdapter(photoAdapter);

        circleIndicator.setViewPager(viewPagerSlider);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
    }

    private List<Photo> getListPhoto() {
        List<Photo> photos = new ArrayList<>();

        photos.add(new Photo(R.drawable.demo1));
        photos.add(new Photo(R.drawable.demo2));
        photos.add(new Photo(R.drawable.demo3));
        photos.add(new Photo(R.drawable.demo4));
        photos.add(new Photo(R.drawable.demo5));

        return photos;
    }
    private void initview() {
        bottomNavigationView = view.findViewById(R.id.bottomNavView);
        viewPagerSlider = view.findViewById(R.id.viewPager_Slider);
        circleIndicator = view.findViewById(R.id.circleIndicator);
    }

    private void setListenFragment() {

        bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_booking:
                        Intent intent = new Intent(getContext(), com.example.haircare.Activities.Booking.BookingActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.menu_history:
                        Intent intent2 = new Intent(getContext(), HistoryCutActivity.class);
                        startActivity(intent2);
                        break;
                    /*case R.id.menu_member:
                        Intent intent3 = new Intent(getContext(), MemberActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.menu_rewards:
                        Intent intent4 = new Intent(getContext(), RewardsActivity.class);
                        startActivity(intent4);
                        break;*/
                }
            }
        });

    }
}