package com.cadenza.slider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
        LinearLayout sliderDotspanel;
        private int dotscount;
        private ImageView[] dots;
        private Handler sliderhandler = new Handler();


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);




            viewPager = (ViewPager) findViewById(R.id.viewPager);sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

            viewPager.setAdapter(viewPagerAdapter);


            dotscount = viewPagerAdapter.getCount();
            dots = new ImageView[dotscount];

            for(int i = 0; i < dotscount; i++){

                dots[i] = new ImageView(this);
                dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                params.setMargins(8, 0, 8, 0);

                sliderDotspanel.addView(dots[i], params);

            }

            dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    sliderhandler.removeCallbacks(slideRunnable);
                    sliderhandler.postDelayed(slideRunnable,3000); //slide duration 3seconds



                    for(int i = 0; i< dotscount; i++){
                        dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                    }

                    dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }


            });


        }

    private Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    };
    @Override
    protected void onPause() {
        super.onPause();
        sliderhandler.removeCallbacks(slideRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderhandler.postDelayed(slideRunnable,3000);
    }



}