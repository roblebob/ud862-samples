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
package com.udacity.interpolationdemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.udacity.interpolationdemo.databinding.ActivityMainBinding;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

//    @BindView(R.id.interpolator_spinner)Spinner interpolatorSpinner;
//    @BindView(R.id.duration_spinner)Spinner duratorSpinner;
//    @BindView(R.id.textView)TextView textView;


    private static final String PACKAGE = "android.view.animation.";
    //private static final String PACKAGE_V4 = "android.support.v4.view.animation.";
    private static final String PACKAGE_V4 = "androidx.interpolator.view.animation.";

    private int duration;
    private Interpolator interpolator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);
//        ButterKnife.bind(this);


        binding.durationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("durationSpinner", "onItemSelected(..., " + i + " ,... )");
                MainActivity.this.durationSelected( (Spinner) adapterView, i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.e("durationSpinner", "onNothingSelected(...)");
            }
        });


        binding.interpolatorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("interpolatorSpinner", "onItemSelected(..., " + i + " ,... )");
                MainActivity.this.onItemSelected((Spinner) adapterView, i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.e("interpolatorSpinner", "onNothingSelected(...)");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //@OnItemSelected({R.id.duration_spinner})
    void durationSelected(Spinner spinner, int position) {
        String durationString = (String) spinner.getAdapter().getItem(position);
        switch(durationString) {
            case "100ms":
                duration = 100;
            case "900ms":
                duration = 900;
                break;
            case "1500ms":
                duration = 1500;
                break;
            case "3000ms":
                duration = 3000;
                break;
            default:
                duration = 300;
                break;
        }
        // Kick off transition
        int item = binding.interpolatorSpinner.getSelectedItemPosition();
        onItemSelected(binding.interpolatorSpinner, /* position */ item);
    }

    String findFullInterpolatorPath(String className) {
        if (className.equals("FastOutLinearInInterpolator") || className.equals("FastOutSlowInInterpolator") || className.equals("LinearOutSlowInInterpolator"))
            return PACKAGE_V4 + className;
        else if (className.startsWith("-"))
            return null;
        else return PACKAGE + className;
    }

    //@OnItemSelected({R.id.interpolator_spinner})
    void onItemSelected(Spinner spinner, int position) {
        Log.e(MainActivity.class.getSimpleName() + " --- interpolationSpinner --->",  " " + position);
        String interpolatorName = (String) spinner.getAdapter().getItem(position);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        binding.textView.setTranslationY(metrics.heightPixels);

        try {
            String path = findFullInterpolatorPath(interpolatorName);
            if (path == null)
                return;

            interpolator = (Interpolator) Class.forName(path).newInstance();
            binding.textView.animate().setInterpolator(interpolator)
                    .setDuration(duration)
                    .setStartDelay(500)
                    .translationYBy(-metrics.heightPixels)
                    .start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    @OnItemSelected(value=R.id.interpolator_spinner, callback = OnItemSelected.Callback.NOTHING_SELECTED)
//    void onNothingSelected() {
//
//    }

}
