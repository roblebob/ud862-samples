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

package com.example.android.papertransformations;

import android.animation.Animator;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.scrolleventsdemo.R;

public class MainActivity extends Activity {
    static String baconTitle = "Bacon";
    static String baconText = "Bacon ipsum dolor amet pork belly meatball kevin spare ribs. Frankfurter swine corned beef meatloaf, strip steak.";
    static String veggieTitle = "Veggie";
    static String veggieText = "Veggies es bonus vobis, proinde vos postulo essum magis kohlrabi welsh onion daikon amaranth tatsoi tomatillo melon azuki bean garlic.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout)).setTitle(getString(R.string.app_name));
        RecyclerView rv = findViewById(R.id.recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new RecyclerView.Adapter<ViewHolder>() {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
                return new ViewHolder(getLayoutInflater().inflate(R.layout.list_item, parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
                viewHolder.text1.setText(baconTitle);
                viewHolder.text2.setText(baconText);
            }

            @Override
            public int getItemCount() {
                return 10;
            }
        });

    }

    private static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text1;
        TextView text2;
        static int green;
        static int white;

        public ViewHolder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
            text2 = itemView.findViewById(android.R.id.text2);
            itemView.setOnClickListener(this);

            if (green == 0)
                green = itemView.getContext().getResources().getColor(R.color.green, null);
            if (white == 0)
                white = itemView.getContext().getResources().getColor(R.color.background_material_light, null);
        }

        @Override
        public void onClick(View view) {
            boolean isVeggie = view.getBackground() != null && ((ColorDrawable)view.getBackground()).getColor() == green;

            int finalRadius = (int)Math.hypot(view.getWidth()/2., view.getHeight()/2.);

            if (isVeggie) {
                text1.setText(baconTitle);
                text2.setText(baconText);
                view.setBackgroundColor(white);
            } else {
                Animator anim = ViewAnimationUtils.createCircularReveal(view, view.getWidth() /2, view.getHeight() /2, 0, finalRadius);
                text1.setText(veggieTitle);
                text2.setText(veggieText);
                view.setBackgroundColor(green);
                anim.start();
            }
        }
    }
}
