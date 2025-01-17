/*
 * Copyright 2015 Udacity, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.udacity.md_typograhical_scale;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.udacity.md_typograhical_scale.databinding.FragmentMainBinding;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

//    @InjectView(R.id.display4)
//    TextView display4View;
//
//    @InjectView(R.id.headline)
//    TextView headlineView;



    Typeface courgette;


    FragmentMainBinding binding;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        View view =  inflater.inflate(R.layout.fragment_main, container, false);
//        ButterKnife.inject(this, view);

        /*
        display4View = (TextView)getActivity().findViewById(R.id.display4);
        headlineView = (TextView)getActivity().findViewById(R.id.headline);
         */

        binding.display4.setTypeface(courgette);
        binding.headline.setTypeface(courgette);
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        courgette = Typeface.createFromAsset(getActivity().getAssets(), "Courgette-Regular.ttf");
    }
}
