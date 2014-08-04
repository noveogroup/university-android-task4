package com.noveogroup.task4.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noveogroup.task4.R;
import com.noveogroup.task4.listener.OnFragmentClickListener;

public final class ColorFragment extends Fragment {

    public final static String COLOR = "com.noveogroup.task4.COLOR";

    public static ColorFragment newInstance(int color) {
        ColorFragment fragment = new ColorFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(COLOR, color);
        fragment.setArguments(bundle);

        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_color, container, false);

//        Bundle bundle = savedInstanceState != null ? savedInstanceState : getArguments();
        Bundle bundle = getArguments();
        int color = bundle != null ? bundle.getInt(COLOR) : Color.WHITE;
        view.setBackgroundColor(color);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getActivity() instanceof OnFragmentClickListener) {
                    ((OnFragmentClickListener) getActivity()).onFragmentClick(getTag(), null);
                }
            }
        });

        return view;
    }
}
