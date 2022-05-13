package com.oho.oho.views.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oho.oho.R;
import com.oho.oho.databinding.FragmentHomeBinding;
import com.oho.oho.databinding.FragmentSettingsBinding;
import com.oho.oho.views.FAQActivity;
import com.oho.oho.views.TermsOfUseActivity;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    FragmentSettingsBinding binding;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSettingsBinding.inflate(inflater,container,false);

        binding.buttonFaqSettings.setOnClickListener(this);
        binding.buttonTermsSettings.setOnClickListener(this);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_account_settings:
                break;
            case R.id.button_faq_settings:
                startActivity(new Intent(requireActivity(), FAQActivity.class));
                break;
            case R.id.button_terms_settings:
                startActivity(new Intent(requireActivity(), TermsOfUseActivity.class));
                break;
        }
    }
}