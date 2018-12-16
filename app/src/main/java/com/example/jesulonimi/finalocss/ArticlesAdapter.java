package com.example.jesulonimi.finalocss;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder> {
private String _link;
private String _title;
    public interface URLLoader{
        void load(String title,String link);
    }
    private  URLLoader _urlLoader;
    private Document _document=null;

    public ArticlesAdapter(URLLoader _urlLoader) {
        this._urlLoader = _urlLoader;
    }

    List<model> modelList=new ArrayList<>();

    public void addAarticles(List<model> modelList1){
        modelList.addAll(modelList1);
        Collections.sort(modelList);
        notifyDataSetChanged();


    }

    Context c;

    public ArticlesAdapter(Context c) {
        this.c = c;
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_items,parent,false);
    return new ArticlesViewHolder(v);
    }

    public void clear(){
        modelList.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder holder, int position) {
                holder.bind(modelList.get(position));

                final model m=modelList.get(position);
                _link=m.link;
                _title=m.title;
   }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ArticlesViewHolder extends RecyclerView.ViewHolder{
TextView title;
CardView cardView;
        public ArticlesViewHolder(final View itemView) {
            super(itemView);
            cardView=(CardView) itemView.findViewById(R.id.cardView);
            title=(TextView) itemView.findViewById(R.id.title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v.findViewById(R.id.articleF)!=null){
                        ArticleFragment fragment=ArticleFragment.create(_link);
                        MainActivity m=new MainActivity();

                m.fm.beginTransaction().replace(R.id.articleF,fragment)
                                .addToBackStack(null).commit();
                    }else{
                        Intent i=new Intent(c,ArticleActivity.class);
                        i.putExtra("link",_link);
                        i.putExtra("title",_title);
                        c.startActivity(i);
                    }
                }
            });
        }
        public void bind(model m){
            title.setText(m.title);
        }
    }
}
