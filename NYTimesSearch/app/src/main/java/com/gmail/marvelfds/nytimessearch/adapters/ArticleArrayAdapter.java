package com.gmail.marvelfds.nytimessearch.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.marvelfds.nytimessearch.R;
import com.gmail.marvelfds.nytimessearch.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Core i7 on 5/17/2016.
 */
public class ArticleArrayAdapter extends ArrayAdapter<Article> {

    public ArticleArrayAdapter (Context context , List<Article> articles){
        super(context,android.R.layout.simple_list_item_1,articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            //let's define all the steps
            /* 1 Get the data item for this position
               2 Check if an existing view is being reused, otherwise inflate the view
               3 Lookup view for data population
               4 Populate the data into the template view using the data object
                4.1 use Picosso to populate data into image view
               5 Return the completed view to render on screen
             */


          //1 Get the data item for this position
            Article article = getItem(position);

        //2 Check if an existing view is being reused, otherwise inflate the view

            if (convertView == null) {

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_article_result, parent, false);

            }

            //3 Lookup view for data population

            ImageView ivThumbNail = (ImageView) convertView.findViewById(R.id.ivThumbNail);

            TextView tvHeadLine = (TextView) convertView.findViewById(R.id.tvHeadLine);

            //4 Populate the data into the template view using the data object

            tvHeadLine.setText(article.getHeadline());
             ivThumbNail.setImageResource(0);
             //4.1 use picosso to populate data into image view
        if (!TextUtils.isEmpty(article.getThumbNail())){

            Picasso.with(getContext()).load(article.getThumbNail()).into(ivThumbNail);

        }


            // Return the completed view to render on screen

            return convertView;
    }
}
