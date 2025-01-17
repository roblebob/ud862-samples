package com.example.android.transitionsquiz;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.android.transitionsquiz.databinding.ActivityMainBinding;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    ActivityMainBinding binding;

    //@Bind(R.id.grid) GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);

        //ButterKnife.bind(this);



        binding.grid.setAdapter(new GridAdapter());
        binding.grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, DetailActivity.class),
                        ActivityOptions.makeSceneTransitionAnimation(MainActivity.this
                                //, view, "hero"
                                                                    ).toBundle());
            }
        });
    }

    private class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.grid_item, parent, false);
            }
            ((TextView) convertView).setText("A");
            return convertView;
        }
    }


}
