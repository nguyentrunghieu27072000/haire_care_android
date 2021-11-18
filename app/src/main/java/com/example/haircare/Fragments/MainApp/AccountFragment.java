package com.example.haircare.Fragments.MainApp;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haircare.LoginActivity;
import com.example.haircare.R;
import com.example.haircare.Share.DataLocalManager;

public class AccountFragment extends Fragment {

    private View view;
    private CardView cardViewLogin_Logout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_account, container, false);
        initView();
        listFragment();
        return view;
    }
    private void listFragment() {
        cardViewLogin_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Đăng xuất", Toast.LENGTH_SHORT).show();

                //Reset Shared Preferences
                DataLocalManager.resetSharaPreferences();

                getActivity().finish();
                getActivity().startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finishAffinity();
            }
        });
    }
    private void initView() {
        cardViewLogin_Logout = view.findViewById(R.id.cardView_Login_Logout);
    }
}