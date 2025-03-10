package com.example.tlucontact;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DVFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DVFragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DVFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DVFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DVFragment newInstance(String param1, String param2) {
        DVFragment fragment = new DVFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<Donvi> listDV = new ArrayList<>();
        listDV.add(new Donvi("Cong Nghe Thong Tin","0123456789", "Toa A1"));
        listDV.add(new Donvi("Cong Trinh","0123456789", "Toa A2"));
        listDV.add(new Donvi("Dien Tu","0123456789", "Toa A3"));
        listDV.add(new Donvi("Ngon Ngu Anh","0123456789", "Toa B1"));
        listDV.add(new Donvi("Cong Nghe Sinh Hoc","0123456789", "Toa C1"));
        listDV.add(new Donvi("Hoa Moi Truong","0123456789", "Toa B5"));
        listDV.add(new Donvi("Tai Nguyen Nuoc","0123456789", "Toa B4"));
        listDV.add(new Donvi("Cong Nghe Hoa Hoc","0123456789", "Toa D1"));
        listDV.add(new Donvi("Thuong Mai Dien Tu","0123456789", "Toa D0"));




        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_d_v, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_DV);
        // duong line giua cac item
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        DVAdapter dvAdapter = new DVAdapter(listDV);
        recyclerView.setAdapter(dvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        return view;
    }
}