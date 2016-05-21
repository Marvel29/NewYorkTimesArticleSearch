package com.gmail.marvelfds.nytimessearch.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.GridView;

import com.gmail.marvelfds.nytimessearch.R;
import com.gmail.marvelfds.nytimessearch.adapters.ArticleArrayAdapter;
import com.gmail.marvelfds.nytimessearch.fragment.SettingDialogFragment;
import com.gmail.marvelfds.nytimessearch.models.Article;
import com.gmail.marvelfds.nytimessearch.network.ArticleSearchClient;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity  implements  DatePickerDialog.OnDateSetListener{

    // define GV for get a query result
    GridView gvResult;
   // define ArticleArticleAdapter
    ArticleArrayAdapter aArticles ;
    //define a ArrayList articles
    ArrayList<Article> articles ;
    SettingDialogFragment settingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupViews();


    }

    private void  setupViews(){
        gvResult =(GridView)findViewById(R.id.gvResults);
        articles = new ArrayList<Article>();
        aArticles = new ArticleArrayAdapter(this,articles);
        gvResult.setAdapter(aArticles);
        settingDialog = SettingDialogFragment.newInstance("Advance search");
         //hook up listener for gvResult
        gvResult.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //create intent to display article
                Intent i = new Intent(getApplicationContext(),ArticleActivity.class);
                // get the article to display
                Article article = aArticles.getItem(position);

                // pass in that article into the intent
                i.putExtra("article",article);
                //lauch the article activity
                startActivity(i);
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        //define
        MenuItem searchItem = menu.findItem(R.id.action_search);
        // define the SearchView
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                // Toast.makeText(this,"Search for"+query, Toast.LENGTH_LONG).show();

                ArticleSearchClient.beginDate = settingDialog.getBeginDate();
                ArticleSearchClient.newsDeskValue = settingDialog.getAvanceSearch();
                ArticleSearchClient.sortOrder =settingDialog.getSortOrder();

                ArticleSearchClient.searchClient(getApplicationContext(),query,aArticles);

               /* AsyncHttpClient client = new AsyncHttpClient();
                String url = "https://api.nytimes.com/svc/search/v2/articlesearch.json";
                RequestParams params = new RequestParams();
                params.put("api-key","a61facb60c0a43bf9f04e3dd4ddd9e92");
                params.put("page","0");
                params.put("q",query);

                //get a http response

                client.get(url,params , new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Log.i("DEBUG",response.toString());
                        try {
                            JSONArray articleResultsJSON = response.getJSONObject("response").getJSONArray("docs");
                            aArticles.addAll(Article.fromJSONArray(articleResultsJSON));
                            aArticles.notifyDataSetChanged();
                        }
                        catch (JSONException ex){
                            ex.printStackTrace();
                        }
                    }
                });*/
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void onClickSettingItem(MenuItem item) {
        showSetupDialog();
    }

    private void showSetupDialog() {

        FragmentManager fm = getSupportFragmentManager();
        settingDialog.show(fm, "advance_search");

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // get the datepicker date in the EditText
       this.settingDialog.etBeginDate.setText(""+year+""+dayOfMonth+""+dayOfMonth);
        //

    }
}
