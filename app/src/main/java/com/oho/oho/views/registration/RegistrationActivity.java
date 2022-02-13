package com.oho.oho.views.registration;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.oho.oho.R;
import com.oho.oho.adapters.RegistrationAdapter;
import com.oho.oho.models.Profile;
import com.oho.oho.viewmodels.LoginViewModel;
import com.oho.oho.viewmodels.RegistrationViewModel;
import com.oho.oho.views.animations.ZoomOutPageTransformer;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class RegistrationActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private RegistrationAdapter adapter;
    private CardView buttonNext, buttonPrevious, buttonStart, buttonComplete, buttonCompleteProfile;
    private DotsIndicator dotsIndicator;

    private String onBoardingUserName;
    private RegistrationViewModel registrationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initAuthViewModel();

        onBoardingUserName = getIntent().getStringExtra("name");

        viewPager2 = findViewById(R.id.viewpager_registration);

        buttonNext = findViewById(R.id.button_next_registration);
        buttonPrevious = findViewById(R.id.button_prev_registration);
        buttonStart = findViewById(R.id.button_start_registration);
        buttonComplete = findViewById(R.id.button_complete_registration);
        buttonCompleteProfile = findViewById(R.id.button_complete_profile_registration);
        dotsIndicator = findViewById(R.id.dots_indicator);
        //Setting up Animations
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        //Set button alpha to zero
        buttonStart.setAlpha(0f);
        buttonComplete.setAlpha(0f);
        buttonNext.setAlpha(0f);
        buttonPrevious.setAlpha(0f);
        //Animate the alpha value to 1f and set duration as 1.5 secs.
        buttonStart.animate().alpha(1f).setDuration(1000);
        buttonComplete.animate().alpha(1f).setDuration(1000);
        buttonNext.animate().alpha(1f).setDuration(1000);
        buttonPrevious.animate().alpha(1f).setDuration(1000);

        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new RegistrationAdapter(fragmentManager,getLifecycle(),onBoardingUserName);
        viewPager2.setAdapter(adapter);

        dotsIndicator.setViewPager2(viewPager2);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(1);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
            }
        });

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem()-1);
            }
        });

        buttonComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
            }
        });

        buttonCompleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegistration();
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    dotsIndicator.setVisibility(View.GONE);
                    buttonStart.setVisibility(View.VISIBLE);
                    buttonComplete.setVisibility(View.GONE);
                    buttonCompleteProfile.setVisibility(View.GONE);
                    buttonNext.setVisibility(View.GONE);
                    buttonPrevious.setVisibility(View.GONE);
                } else if (position == 11){
                    dotsIndicator.setVisibility(View.GONE);
                    buttonStart.setVisibility(View.GONE);
                    buttonComplete.setVisibility(View.VISIBLE);
                    buttonCompleteProfile.setVisibility(View.GONE);
                    buttonNext.setVisibility(View.GONE);
                    buttonPrevious.setVisibility(View.GONE);
                } else if (position == 14){
                    dotsIndicator.setVisibility(View.GONE);
                    buttonStart.setVisibility(View.GONE);
                    buttonComplete.setVisibility(View.GONE);
                    buttonCompleteProfile.setVisibility(View.VISIBLE);
                    buttonNext.setVisibility(View.GONE);
                    buttonPrevious.setVisibility(View.GONE);
                } else {
                    dotsIndicator.setVisibility(View.VISIBLE);
                    buttonStart.setVisibility(View.GONE);
                    buttonComplete.setVisibility(View.GONE);
                    buttonCompleteProfile.setVisibility(View.GONE);
                    buttonNext.setVisibility(View.VISIBLE);
                    buttonPrevious.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    private void initAuthViewModel() {
        registrationViewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
    }
    private void startRegistration(){
        Profile profile = new Profile();
        profile.setName("John Doe");
        profile.setGender("M");
        profile.setEmail("oh@gmail.com");
        profile.setPhone(123567);
        registrationViewModel.registerUser(profile);
        registrationViewModel.registeredUserProfileData.observe(this,userProfile -> {
            Toast.makeText(RegistrationActivity.this, "registration successfull with profile = "+userProfile, Toast.LENGTH_SHORT).show();
        });
    }
}