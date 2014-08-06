package com.example.yuri.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class TopFragment extends Fragment {

    private TextView textView;

    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top, container, false);
        textView = (TextView) view.findViewById(R.id.text_view);
        view.setBackground(getResources().getDrawable(R.drawable.bg));
        view.getBackground().setAlpha(60);

        return view;
    }
}
