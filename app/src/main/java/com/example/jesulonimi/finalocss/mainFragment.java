package com.example.jesulonimi.finalocss;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class mainFragment extends Fragment {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ArticlesAdapter adapter;
    DownloadTask task;

    List<String> url= Arrays.asList(
            "https://www.lemonde.fr/attentat-de-manchester/rss_full.xml",
            "https://www.lemonde.fr/orientation-scolaire/rss_full.xml",
            "http://www.sciencemag.org/rss/news_current.xml"
    );
    List<DownloadTask> tasks=new ArrayList<>();


    public mainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=(RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new ArticlesAdapter(getContext());
        recyclerView.setAdapter(adapter);
        progressBar=(ProgressBar)view. findViewById(R.id.progress);

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if(adapter.getItemCount()>0){
                    progressBar.setVisibility(View.INVISIBLE);
                }else{
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

        startDownoadTask();

    }


    public void startDownoadTask(){
        for(DownloadTask t:tasks)
            t.cancel(true);

        for(String urls:url){
            task=new DownloadTask(adapter);
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,urls);}
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main,menu);

    }
        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.refresh){
            adapter.clear();
            startDownoadTask();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for(DownloadTask cancel:tasks){
            cancel.cancel(true);
        }
    }
}
