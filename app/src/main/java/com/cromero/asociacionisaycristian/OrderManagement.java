package com.cromero.asociacionisaycristian;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cromero.asociacionisaycristian.controllers.AdapterOrder;
import com.cromero.asociacionisaycristian.controllers.AdapterUser;
import com.cromero.asociacionisaycristian.models.Order;
import com.cromero.asociacionisaycristian.models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class OrderManagement extends Fragment {
    private RecyclerView recView;
    //Database variables
    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    //
    private ArrayList<Order> orders;
    public OrderManagement() {
        // Required empty public constructor
    }


    public static OrderManagement newInstance() {
        OrderManagement fragment = new OrderManagement();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_management, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //RecyclerView initialization
        recView = (RecyclerView) view.findViewById(R.id.rv_Order);

        //Assignment of the Layout to the Recycler View
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recView.setLayoutManager(layoutManager);

        orders= new ArrayList<Order>();

        //String orderId, List<OrderLine> orderLines, Date orderDate
        String dateStr = "04/05/2010";

        Date c = Calendar.getInstance().getTime();

        Order order=new Order("01",c);
        orders.add(order);
        order=new Order("02",c);
        orders.add(order);
        order=new Order("03",c);
        orders.add(order);
        order=new Order("04",c);
        orders.add(order);
        order=new Order("05",c);
        orders.add(order);
        order=new Order("06",c);
        orders.add(order);

        //Assignment of the Recycler View adapter with the user list
        AdapterOrder adapter = new AdapterOrder(orders);
        recView.setAdapter(adapter);
    }
}