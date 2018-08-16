package com.example.konikiewiczb.myapplication.projects.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.ProfileFragment;
import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.coworkers.employee.SingleEmployeeFragment;
import com.example.konikiewiczb.myapplication.model.User;
import com.example.konikiewiczb.myapplication.model.repositories.Repository;
import com.example.konikiewiczb.myapplication.model.repositories.TeamLeaderRepository;
import com.example.konikiewiczb.myapplication.model.repositories.TokenRepository;
import com.example.konikiewiczb.myapplication.model.UserProject;
import com.example.konikiewiczb.myapplication.model.repositories.UserRepository;

import java.util.List;

public class ProjectsListsFragment extends Fragment implements ProjectsListContract.View{

    RecyclerView memberProjects, leaderProjects;
    ProjectsListContract.Presenter presenter;
    ProgressBar progressBar;
    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Twoje projekty");
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
        Log.d("PROJECTS", projects.toString());
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
        adapter.setOnProjectClickListener(new UserProjectsAdapter.OnProjectClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), dataset.get(position).getName(),Toast.LENGTH_SHORT).show();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new ProfileFragment());
                fragmentTransaction.commit();
            }
        });
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
