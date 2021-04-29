package com.example.recipe.user.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recipe.R;
import com.example.recipe.databinding.FragmentMainBinding;
import com.example.recipe.databinding.FragmentUserBinding;

public class UserFragment extends Fragment {
    FragmentUserBinding binding;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentUserBinding .inflate(inflater);
        view = binding.getRoot();
        return view;
    }

    public void initView(){

    }
}