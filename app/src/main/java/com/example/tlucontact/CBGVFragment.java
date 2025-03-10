package com.example.tlucontact;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CBGVFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CBGVFragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CBGVFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CBDVFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CBGVFragment newInstance(String param1, String param2) {
        CBGVFragment fragment = new CBGVFragment();
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
        List<CBGV> listCB = new ArrayList<>();
        listCB.add(new CBGV("Nguyen Van A","0123456789","Ha Noi","A@gmail.com","Cong Nghe Thong Tin"));
        listCB.add(new CBGV("Nguyen Van B","0123456789","Ha Noi","B@gmail.com","Cong Trinh"));
        listCB.add(new CBGV("Nguyen Van C","0123456789","Ha Noi","C@gmail.com","Cong Nghe Hoa Hoc"));
        listCB.add(new CBGV("Nguyen Van D","0123456789","Ha Noi","D@gmail.com","Dien Tu"));
        listCB.add(new CBGV("Nguyen Van E","0123456789","Ha Noi","E@gmail.com","Cong Nghe Thong Tin"));
        listCB.add(new CBGV("Nguyen Van F","0123456789","Ha Noi","F@gmail.com","Ky Thuat Tai Nguyen Nuoc"));
        listCB.add(new CBGV("Nguyen Van G","0123456789","Ha Noi","G@gmail.com","Ngon Ngu Anh"));
        listCB.add(new CBGV("Nguyen Van H","0123456789","Ha Noi","H@gmail.com","Cong Nghe Sinh Hoc"));
        listCB.add(new CBGV("Nguyen Van I","0123456789","Ha Noi","I@gmail.com","Ke Toan"));
        listCB.add(new CBGV("Nguyen Van K","0123456789","Ha Noi","K@gmail.com","Cong Nghe Thong Tin"));
        listCB.add(new CBGV("Nguyen Van L","0123456789","Ha Noi","L@gmail.com","Thuong Mai Dien Tu"));

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_c_b_d_v, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_CBGV);
        // duong line giua cac item
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        CBGVAdapter cbgvAdapter = new CBGVAdapter(listCB, new CBGVAdapter.IClickItemCBGV() {
            @Override
            public void onClickItemCBGV(CBGV cbgv) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                DetailCBGVFragment detailCBGVFragment = new DetailCBGVFragment();

                Bundle bundle = new Bundle();
                bundle.putSerializable("object cbgv", (Serializable) cbgv);
                detailCBGVFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frm_cbgv, detailCBGVFragment);
                fragmentTransaction.addToBackStack(DetailCBGVFragment.TAG);
                fragmentTransaction.commit();
            }
        });
        recyclerView.setAdapter(cbgvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        return view;
    }
}