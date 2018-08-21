package com.example.konikiewiczb.myapplication.profile;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.repositories.Repository;
import com.example.konikiewiczb.myapplication.model.repositories.UserRepository;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment implements ProfileContract.ProfileView{

    private Repository<User> userRepository;
    private ProfileContract.ProfilePresenter profilePresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        getActivity().setTitle(getString(R.string.profil_tittle));

        userRepository = new UserRepository(getActivity().getApplicationContext());
        fetchUserData(view, userRepository.get());

        profilePresenter = new ProfilePresenterImpl(this);
        profilePresenter.fetchUserTechnologies(userRepository.get().getEmailAddress());


        return view;
    }

    void fetchUserData(View view, User user){
        TextView tvFirstLastName = view.findViewById(R.id.tvName);
        TextView tvEmail = view.findViewById(R.id.tvEmail);
        TextView tvRole = view.findViewById(R.id.tvRole);
        tvFirstLastName.setText(user.toString());
        tvEmail.setText(user.getEmailAddress());
        tvRole.setText(user.getSystemRole());
    }
}
