package com.example.konikiewiczb.myapplication.projects.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.framework.view.GenericFragment;
import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.repositories.Repository;
import com.example.konikiewiczb.myapplication.model.repositories.TeamLeaderRepository;
import com.example.konikiewiczb.myapplication.model.UserProject;
import com.example.konikiewiczb.myapplication.model.repositories.UserRepository;
import com.example.konikiewiczb.myapplication.projects.list.adapter.UserProjectsAdapter;
import com.example.konikiewiczb.myapplication.projects.project.ProjectFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectsListsFragment extends GenericFragment implements ProjectsListContract.View{

    @BindView(R.id.member_projects) RecyclerView memberProjects;
    @BindView(R.id.leader_projects) RecyclerView leaderProjects;
    ProjectsListContract.Presenter presenter;
    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getString(R.string.app_user_projects));
        View view = inflate(R.layout.fragment_projects_list, inflater, container);
        ButterKnife.bind(this, view);

        progressBar.show();
        FragmentManager fm = getFragmentManager();
        leaderProjects.setAdapter(new UserProjectsAdapter(View.VISIBLE, fm));
        leaderProjects.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        memberProjects.setAdapter(new UserProjectsAdapter(View.INVISIBLE, fm));
        memberProjects.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        Context context = getActivity().getApplicationContext();
        Repository<User> user = new UserRepository(context);
        TeamLeaderRepository teamLeader = new TeamLeaderRepository(context);
        presenter = new ProjectsListPresenter(this, user, teamLeader);
        presenter.getProjectsList();

        return view;
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
        fragmentManager = getFragmentManager();
        UserProjectsAdapter adapter = (UserProjectsAdapter) view.getAdapter();
        adapter.setDataset(dataset);
        adapter.notifyDataSetChanged();
    }
}
