package com.example.savingdatafirebase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class HeroList extends ArrayAdapter<Artist> {
    private Activity context;
    private List<Artist> heroList;
    public HeroList(Activity context, List<Artist> heroList){
        super(context, R.layout.list_layout,heroList);
        this.context = context;
        this.heroList = heroList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater in = context.getLayoutInflater();
        View listViewItem = in.inflate(R.layout.list_layout, null, true);

        TextView name1 = (TextView)listViewItem.findViewById(R.id.superhero);
        TextView comic1 = (TextView)listViewItem.findViewById(R.id.text1);

        Artist hero = heroList.get(position);
        name1.setText(hero.getheroName());
        comic1.setText(hero.getheroComic());
        return listViewItem;
    }
}
