package com.jacksen.jackannodemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.Toast;

import com.jacksen.jackanno.JackAnno;
import com.jacksen.jackanno.OnClick;
import com.jacksen.jackanno.ViewInject;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {

    @ViewInject(R.id.ratingBar)
    private RatingBar ratingBar;

    @ViewInject(R.id.datePicker)
    private DatePicker datePicker;

    @ViewInject(R.id.button2)
    private Button button;

    public TestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);

        JackAnno.inject(this, view);

        button.setText("jacksen");

        return view;
    }

    @OnClick(R.id.button2)
    private void onViewClick(View view) {
        Toast.makeText(getActivity(), "button2 click.", Toast.LENGTH_SHORT).show();
    }

}
