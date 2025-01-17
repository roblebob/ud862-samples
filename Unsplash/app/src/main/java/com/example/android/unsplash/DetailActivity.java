package com.example.android.unsplash;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.android.unsplash.databinding.ActivityDetailBinding;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.BindInt;
import butterknife.ButterKnife;

public class DetailActivity extends Activity {

    public static final String EXTRA_AUTHOR = "EXTRA_AUTHOR";

    ActivityDetailBinding binding;

//    @Bind(R.id.toolbar)Toolbar toolbar;
//    @Bind(R.id.photo) ImageView imageView;
//    @Bind(R.id.author) TextView author;
//    @BindInt(R.integer.detail_desc_slide_duration) int slideDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);


//        ButterKnife.bind(this);

        Picasso.get()
                .load(getIntent().getData())
                .placeholder(R.color.placeholder)
                .into(binding.photo);
        binding.author.setText("—" + getIntent().getStringExtra(EXTRA_AUTHOR));
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide(Gravity.BOTTOM);
            slide.addTarget(R.id.description);
            slide.setInterpolator(AnimationUtils.loadInterpolator(this, android.R.interpolator
                    .linear_out_slow_in));
            slide.setDuration(getResources().getInteger(R.integer.detail_desc_slide_duration));
            getWindow().setEnterTransition(slide);
        }
    }
}
