package com.example.fragmenttofragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment1 extends Fragment {
    private Button button;
    private TextView textView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment1 newInstance(String param1, String param2) {
        BlankFragment1 fragment = new BlankFragment1();
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
        View view = inflater.inflate(R.layout.fragment_blank1, container, false);
        button = view.findViewById(R.id.button);
        textView = view.findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第一种方式
//                BlankFragment2 blankFragment2 = new BlankFragment2();
//                FragmentManager manager = getFragmentManager();
//                FragmentTransaction transaction = manager.beginTransaction();
//                Bundle bundle = new Bundle();
//                bundle.putString("text","我是fragment1的消息");
//                blankFragment2.setArguments(bundle);
//                transaction.add(R.id.frame_layout2,blankFragment2);
//                transaction.commit();
                //第二种方式
                Bundle bundle = getArguments();
                if (bundle != null) {
                    String text = bundle.getString("text1");
                    TextView tv = getFragmentManager().findFragmentById(R.id.frame_layout2).getView().findViewById(R.id.textView2);
                    tv.setText(text);
                }


            }
        });
//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            String text = bundle.getString("text1");
//            textView.setText(text);
//        }
        return view;
    }
}















