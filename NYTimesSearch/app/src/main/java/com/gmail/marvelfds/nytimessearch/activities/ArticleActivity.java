package com.gmail.marvelfds.nytimessearch.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gmail.marvelfds.nytimessearch.R;
import com.gmail.marvelfds.nytimessearch.models.Article;

public class ArticleActivity extends AppCompatActivity {
   // define a current article
    Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        WebView wvArticle = (WebView) findViewById(R.id.wvArticle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // define a setup views
        setupViews();



    }

    private void setupViews() {
        WebView wvArticle = (WebView) findViewById(R.id.wvArticle);
        article =(Article)getIntent().getSerializableExtra("article");
        wvArticle.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
     wvArticle.loadUrl(article.getWebUrl());
    }
  @Override

    public boolean onOptionsItemSelected(MenuItem item) {

              NavUtils.navigateUpFromSameTask(this);
              // overridePendingTransition(R.animator.anim_left, R.animator.anim_right);


      return false;
  }
}
