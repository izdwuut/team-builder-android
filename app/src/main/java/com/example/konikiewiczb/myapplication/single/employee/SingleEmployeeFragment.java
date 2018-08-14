package com.example.konikiewiczb.myapplication.single.employee;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.konikiewiczb.myapplication.R;

public class SingleEmployeeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Pojedyńczy pracownik");
        View view = inflater.inflate(R.layout.fragment_single_employy, container, false);

        return view;
    }
}
