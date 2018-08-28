package com.example.konikiewiczb.myapplication.projects.project;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.coworkers.adapter.WorkersAdapter;
import com.example.konikiewiczb.myapplication.framework.Dataset;
import com.example.konikiewiczb.myapplication.framework.adapter.string_list.StringListAdapter;
import com.example.konikiewiczb.myapplication.framework.view.GenericFragment;
import com.example.konikiewiczb.myapplication.model.ProjectEntry;
import com.example.konikiewiczb.myapplication.model.Technology;
import com.example.konikiewiczb.myapplication.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectFragment extends GenericFragment implements ProjectContract.View {
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.start_date)
    TextView startDate;
    @BindView(R.id.end_date)
    TextView endDate;

    @BindView(R.id.project_entries)
    RecyclerView entries;
    @BindView(R.id.project_technologies)
    RecyclerView technologies;
    RecyclerView candidates;
    RecyclerView participants;

    Integer id;
    ProjectContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getString(R.string.app_single_project));
        View view = inflate(R.layout.fragment_single_project, inflater, container);
        participants = view.findViewById(R.id.participants).findViewById(R.id.rvList);
        candidates = view.findViewById(R.id.candidates).findViewById(R.id.rvList);

        Context context = getActivity().getApplicationContext();
        ButterKnife.bind(this, view);

        candidates.setAdapter(new WorkersAdapter(context));
        candidates.setLayoutManager(new LinearLayoutManager(context));

        participants.setAdapter(new WorkersAdapter(context));
        participants.setLayoutManager(new LinearLayoutManager(context));

        entries.setAdapter(new StringListAdapter<ProjectEntry>());
        entries.setLayoutManager(new LinearLayoutManager(context));

        technologies.setAdapter(new StringListAdapter<Technology>());
        technologies.setLayoutManager(new LinearLayoutManager(context));

        this.id = getArguments().getInt("id");
        this.presenter = new ProjectPresenter(this);
        presenter.getProject(id);

        return view;
    }

    private void displayMessage(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showParticipants(List<User> participants) {
        setDataset(this.participants, participants);
    }

    @Override
    public void showCandidates(List<User> cands) {
        setDataset(this.candidates, cands);
    }

    @Override
    public void showEntries(List<ProjectEntry> entries) {
        setDataset(this.entries, entries);
    }

    @Override
    public void showTechnologies(List<Technology> techs) {
        setDataset(this.technologies, techs);
    }

    @Override
    public void showName(String name) {
        setText(this.name, name);
    }

    @Override
    public void showDescription(String description) {
        setText(this.description, description);
    }

    @Override
    public void showStatus(String status) {
        setText(this.status, status);
    }

    @Override
    public void showStartDate(String startDate) {
        setText(this.startDate, startDate);
    }

    @Override
    public void showEndDate(String endDate) {
        setText(this.endDate, endDate);
    }

    void setText(TextView v, String text) {
        v.setText(text);
    }

    void setDataset(RecyclerView view, List dataset) {
        Dataset adapter = (Dataset) view.getAdapter();
        adapter.setDataset(dataset);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        this.displayMessage(getString(R.string.generic_error));
    }
}
