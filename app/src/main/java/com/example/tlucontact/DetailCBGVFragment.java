package com.example.tlucontact;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailCBGVFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailCBGVFragment extends Fragment {
    public static final String TAG = DetailCBGVFragment.class.getName();
    private TextView txtName;
    private TextView txtSdt;
    private TextView txtDiaChi;
    private TextView txtEmail;
    private TextView txtDonVi;
    private Button btnBack;

    private View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailCBGVFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailCBGVFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailCBGVFragment newInstance(String param1, String param2) {
        DetailCBGVFragment fragment = new DetailCBGVFragment();
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

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_detail_c_b_g_v, container, false);
        txtName = view.findViewById(R.id.txt_name_detail);
        btnBack=view.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });
        Bundle bundleRecieve = getArguments();
        if (bundleRecieve != null) {
            CBGV cbgv = (CBGV) bundleRecieve.get("object cbgv");
            if(cbgv != null) {
                txtName.setText(cbgv.getTenCB());
            }
        }

        return view;
    }
}