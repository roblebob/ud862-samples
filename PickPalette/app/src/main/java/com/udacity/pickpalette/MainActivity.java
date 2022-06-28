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
package com.udacity.pickpalette;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.palette.graphics.Palette;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;


import com.udacity.pickpalette.databinding.ActivityMainBinding;


public class MainActivity extends Activity {

    //@InjectView(R.id.fab)
    //FloatingActionButton fab;

    SwatchAdapter swatchAdapter;

    //@InjectView(R.id.grid_view)
    //GridView gridView;

    //@InjectView(R.id.tool_bar)
    //Toolbar toolbar;

    //@InjectView(R.id.imageView)
    //ImageView imageView;

    int numPixels;


    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);

        //ButterKnife.inject(this);

        binding.toolBar.setTitle(getString(R.string.app_name));

        binding.fab.setOnClickListener((View view) -> {
            Snackbar.make(findViewById(R.id.fragment), "Clicked FAB.", Snackbar.LENGTH_LONG)
                    //.setAction("Action", this)
                    .show();

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            PickerFragment pickerFragment = new PickerFragment();
            pickerFragment.show(getFragmentManager(), "dialog");
            ft.commit();
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



    public void createPalette(Object object){
        Bitmap bitmap;
        try {
            if (object instanceof Uri) {
                Uri imageUri = (Uri) object;
                Picasso.get().load(imageUri).into(binding.imageView);
                InputStream imageStream = getContentResolver().openInputStream(imageUri);
                bitmap = BitmapFactory.decodeStream(imageStream);
            } else {
                bitmap = (Bitmap) object;
                binding.imageView.setImageBitmap(bitmap);
            }

            // Do this async on activity

            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    HashMap map = processPalette(palette);
                    swatchAdapter = new SwatchAdapter(getApplicationContext(), map.entrySet().toArray());
                    binding.gridView.setAdapter(swatchAdapter);

                    binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            Palette.Swatch swatch = ((Map.Entry<String, Palette.Swatch>) binding.gridView.getItemAtPosition(position)).getValue();

                            StringBuilder b = new StringBuilder();
                            b.append("Title Text Color: ").append("#" + Integer.toHexString(swatch.getBodyTextColor()).toUpperCase()).append("\n");
                            b.append("Population: ").append(swatch.getPopulation());

                            Snackbar.make(binding.gridView, b.toString(), Snackbar.LENGTH_LONG).show();
                        }
                    });
                }
            });


        } catch (Exception ex) {
            Log.e("MainActivity", "error in creating palette");
        }
    }

    HashMap<String, Palette.Swatch> processPalette(Palette p) {
        HashMap<String, Palette.Swatch> map = new HashMap<>();

        if (p.getVibrantSwatch() != null)
            map.put("Vibrant", p.getVibrantSwatch());
        if (p.getDarkVibrantSwatch() != null)
            map.put("DarkVibrant", p.getDarkVibrantSwatch());
        if (p.getLightVibrantSwatch() != null)
            map.put("LightVibrant", p.getLightVibrantSwatch());

        if (p.getMutedSwatch() != null)
            map.put("Muted", p.getMutedSwatch());
        if (p.getDarkMutedSwatch() != null)
            map.put("DarkMuted", p.getDarkMutedSwatch());
        if (p.getLightMutedSwatch() != null)
            map.put("LightMuted", p.getLightMutedSwatch());

        return map;
    }

    //@OnItemClick(R.id.grid_view)
//    void onItemClick(int position) {
//        Palette.Swatch swatch = ((Map.Entry<String, Palette.Swatch>) binding.gridView.getItemAtPosition(position)).getValue();
//
//        StringBuilder b = new StringBuilder();
//        b.append("Title Text Color: ").append("#" + Integer.toHexString(swatch.getBodyTextColor()).toUpperCase()).append("\n");
//        b.append("Population: ").append(swatch.getPopulation());
//
//        Snackbar.make(binding.gridView, b.toString(), Snackbar.LENGTH_LONG).show();
//    }

}
