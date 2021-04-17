package com.example.android.splashscreen.ui.buybooks;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.android.splashscreen.R;
public class BuybooksFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_buybooks, container, false);
        System.out.println("Buy Books");
        return root;
    }
}
