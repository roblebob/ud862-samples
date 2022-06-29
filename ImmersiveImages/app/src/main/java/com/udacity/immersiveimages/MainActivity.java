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
package com.udacity.immersiveimages;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;

import android.graphics.Matrix;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.widget.ImageView.*;

import androidx.appcompat.app.AppCompatActivity;

import com.udacity.immersiveimages.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    
    
//    @InjectView(R.id.imageView) ImageView imageView;
//
//    // Radio Buttons
//    @InjectView(R.id.noneBtn) RadioButton noneBtn;
//    @InjectView(R.id.centerBtn) RadioButton centerBtn;
//    @InjectView(R.id.centerCropBtn) RadioButton centerCropBtn;
//    @InjectView(R.id.centerInsideBtn) RadioButton centerInsideBtn;
//    @InjectView(R.id.fitCenterBtn) RadioButton fitCenterBtn;
//    @InjectView(R.id.fitEndBtn) RadioButton fitEndBtn;
//    @InjectView(R.id.fitStartBtn) RadioButton fitStartBtn;
//    @InjectView(R.id.fitXYBtn) RadioButton fitXYBtn;
//    @InjectView(R.id.matrixBtn) RadioButton matrixBtn;

    Matrix matrix;

    ActivityMainBinding binding;


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);


        //ButterKnife.inject(this);

        RadioButton [] btns = {binding.centerBtn, binding.centerCropBtn, binding.centerInsideBtn,
                binding.fitCenterBtn, binding.fitEndBtn, binding.fitStartBtn, binding.fitXYBtn,
                binding.matrixBtn, binding.noneBtn};

        for (RadioButton radioButton : btns) {
            radioButton.setOnClickListener( (View view) -> {
                 clickRadioButton((RadioButton) view);
            });
        }


        binding.fitCenterBtn.setChecked(true);
        matrix = new Matrix();
        matrix.postScale(0.5f, 0.5f);
        matrix.postTranslate(100,100);
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

    public RadioButton getSelectedRadio(View view) {
        RadioButton [] btns = {binding.centerBtn, binding.centerCropBtn, binding.centerInsideBtn,
                binding.fitCenterBtn, binding.fitEndBtn, binding.fitStartBtn, binding.fitXYBtn,
                binding.matrixBtn, binding.noneBtn};
        for (RadioButton radioButton : btns) {
            if (radioButton.isChecked() && radioButton != view) {
                return radioButton;
            }
        }
        return null;
    }

    //@OnClick({R.id.centerBtn, R.id.centerCropBtn, R.id.centerInsideBtn, R.id.fitCenterBtn, R.id.fitEndBtn, R.id.fitStartBtn, R.id.fitXYBtn, R.id.matrixBtn, R.id.noneBtn})
    public void clickRadioButton(RadioButton view) {
        // Check to see what is clicked
        RadioButton checkedRadio = getSelectedRadio(view);
        if (checkedRadio != null && checkedRadio != view) {
            checkedRadio.setChecked(false);
        }
        // If currently checked, do nothing.
        binding.imageView.setPadding(0,0,0,0);
        switch((String)view.getText()) {

            case "NO!!!!":
                int px = dpToPx(32);
                binding.imageView.setPadding(px,px,px,px);
                binding.imageView.setScaleType(ScaleType.FIT_CENTER);
                break;
            case "CENTER":
                binding.imageView.setScaleType(ScaleType.CENTER);
                break;
            case "CENTER_CROP":
                binding.imageView.setScaleType(ScaleType.CENTER_CROP);
                break;
            case "CENTER_INSIDE":
                binding.imageView.setScaleType(ScaleType.CENTER_INSIDE);
                break;
            case "FIT_CENTER":
                binding.imageView.setScaleType(ScaleType.FIT_CENTER);
                break;
            case "FIT_END":
                binding.imageView.setScaleType(ScaleType.FIT_END);
                break;
            case "FIT_START":
                binding.imageView.setScaleType(ScaleType.FIT_START);
                break;
            case "FIT_XY":
                binding.imageView.setScaleType(ScaleType.FIT_XY);
                break;
            case "MATRIX":
                binding.imageView.setScaleType(ScaleType.MATRIX);
                binding.imageView.setImageMatrix(matrix);
                break;

        }
        // If different, alter view

    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

}
