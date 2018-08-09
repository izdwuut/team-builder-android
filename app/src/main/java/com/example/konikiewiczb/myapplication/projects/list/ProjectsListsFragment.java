package com.example.konikiewiczb.myapplication.projects.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.login.LoginActivity;
import com.example.konikiewiczb.myapplication.model.TeamLeaderRepository;
import com.example.konikiewiczb.myapplication.model.TokenRepository;
import com.example.konikiewiczb.myapplication.model.UserProject;

import java.util.List;

public class ProjectsListsFragment extends Fragment implements ProjectsListContract.View{

    RecyclerView memberProjects, leaderProjects;
    ProjectsListContract.Presenter presenter;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_projects_list, container, false);

        progressBar = view.findViewById(R.id.progress_bar);
        showProgressBar();
        leaderProjects = view.findViewById(R.id.leader_projects);
        leaderProjects.setAdapter(new UserProjectsAdapter(getActivity().getApplicationContext(), View.VISIBLE));
        leaderProjects.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        memberProjects = view.findViewById(R.id.member_projects);
        memberProjects.setAdapter(new UserProjectsAdapter(getActivity().getApplicationContext(), View.INVISIBLE));
        memberProjects.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        Context context = getActivity().getApplicationContext();
        TokenRepository token = new TokenRepository(context);
        TeamLeaderRepository teamLeader = new TeamLeaderRepository(context);
        presenter = new ProjectsListPresenter(this, token, teamLeader);
        presenter.getProjectsList();

        return inflater.inflate(R.layout.fragment_projects_list, container, false);
    }


    @Override
    public void displayMessage(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLeaderProjectsList(List<UserProject> projects) {
        setDataset(leaderProjects, projects);
    }

    @Override
    public void showMemberProjectsList(List<UserProject> projects) {
        setDataset(memberProjects, projects);
    }

    void setDataset(RecyclerView view, List<UserProject> dataset) {
        UserProjectsAdapter adapter = (UserProjectsAdapter) view.getAdapter();
        adapter.setDataset(dataset);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

}
