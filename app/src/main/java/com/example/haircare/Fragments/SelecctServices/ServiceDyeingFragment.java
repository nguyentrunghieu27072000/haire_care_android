package com.example.haircare.Fragments.SelecctServices;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.haircare.Activities.Booking.SelectServiceActivity;
import com.example.haircare.Model.Adapter.ServiceAdapter;
import com.example.haircare.Model.Config;
import com.example.haircare.Model.ServicesHairSalon;
import com.example.haircare.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ServiceDyeingFragment extends Fragment {

    /*
     * android:descendantFocusability="blocksDescendants"
     * phần này phải dùng để nó bắt đk sự kiện nhé.
     * chưa hiểu lắm nhưng thôi kệ đi
     *
     * đặt thêm đoạn này vào checkbox để tránh xung đột
     * android:clickable="false"
     *android:focusable="false"
     *android:focusableInTouchMode="false"
     *https://stackoverflow.com/questions/5551042/onitemclicklistener-not-working-in-listview
     *
     * */
    //View
    private RecyclerView recyclerViewServiceCut;
    private View view;

    //Adapter
    private ServiceAdapter adapter;

    //List
    private List<ServicesHairSalon> servicesList;

    //param
    private static final String TAG = ServiceDyeingFragment.class.getName();
    private SelectServiceActivity activity;
    private final String ID_Species = "3";
    private String Url = Config.LOCALHOST + "GetService.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_service_dyeing, container, false);

        initView();

        SetViewService();

        SetDataService(Url);

        return view;
    }

    private void SetDataService(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject service = response.getJSONObject(i);
                        if (service.getString("ID_Species").equals(ID_Species)) {
                            servicesList.add(new ServicesHairSalon(Integer.parseInt(service.getString("ID_Service"))
                                    , service.getString("Name_Service")
                                    , service.getString("Description_Service")
                                    , false
                                    , Integer.parseInt(service.getString("Price_Service"))));
                            adapter.notifyDataSetChanged();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        });

        requestQueue.add(arrayRequest);

    }

    private void SetViewService() {
        servicesList = new ArrayList<>();
        adapter = new ServiceAdapter(activity, 3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL
                , false);
        recyclerViewServiceCut.setLayoutManager(linearLayoutManager);
        adapter.setData(servicesList);
        recyclerViewServiceCut.setAdapter(adapter);
    }

    private void initView() {
        activity = (SelectServiceActivity) getActivity();

        recyclerViewServiceCut = view.findViewById(R.id.recyclerView_Service);
    }
}