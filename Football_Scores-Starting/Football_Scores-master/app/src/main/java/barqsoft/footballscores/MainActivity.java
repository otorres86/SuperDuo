package barqsoft.footballscores;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;
/*
@olga created a dictionary (key: code, value: name) of available leagues for 2015/2016 season
 */
public class MainActivity extends ActionBarActivity
{
    public static int selected_match_id;
    public static int current_fragment = 2;

    public static HashMap leagues;

    public static String LOG_TAG = "MainActivity";
    private final String save_tag = "Save Test";
    private PagerFragment my_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "Reached MainActivity onCreate");
        if (savedInstanceState == null) {

            //@olga Create a dictionary of available league codes/names for 2015/2016 season
            leagues  = new HashMap();
            leagues.put(Integer.parseInt(getString(R.string.serie_a_code)), getString(R.string.serie_a_name));
            leagues.put(Integer.parseInt(getString(R.string.primeira_liga_code)), getString(R.string.primeira_liga_name));
            leagues.put(Integer.parseInt(getString(R.string.eredivisie_code)), getString(R.string.eredivisie_name));
            leagues.put(Integer.parseInt(getString(R.string.bundesliga1_code)), getString(R.string.bundesliga1_name));
            leagues.put(Integer.parseInt(getString(R.string.bundesliga2_code)), getString(R.string.bundesliga2_name));
            leagues.put(Integer.parseInt(getString(R.string.bundesliga3_code)), getString(R.string.bundesliga3_name));
            leagues.put(Integer.parseInt(getString(R.string.ligue1_code)), getString(R.string.ligue1_name));
            leagues.put(Integer.parseInt(getString(R.string.ligue2_code)), getString(R.string.ligue2_name));
            leagues.put(Integer.parseInt(getString(R.string.primera_division_code)), getString(R.string.primera_division_name));
            leagues.put(Integer.parseInt(getString(R.string.segunda_division_code)), getString(R.string.segunda_division_name));
            leagues.put(Integer.parseInt(getString(R.string.premier_league_code)), getString(R.string.premier_league_name));
            leagues.put(Integer.parseInt(getString(R.string.champions_league_code)), getString(R.string.champions_league_name));

            my_main = new PagerFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, my_main)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about)
        {
            Intent start_about = new Intent(this,AboutActivity.class);
            startActivity(start_about);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        Log.v(save_tag, "will save");
        Log.v(save_tag, "fragment: " + String.valueOf(my_main.mPagerHandler.getCurrentItem()));
        Log.v(save_tag, "selected id: " + selected_match_id);
        outState.putInt("Pager_Current", my_main.mPagerHandler.getCurrentItem());
        outState.putInt("Selected_match", selected_match_id);
        outState.putSerializable("LeagueDictionary", leagues);
        getSupportFragmentManager().putFragment(outState,"my_main",my_main);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        Log.v(save_tag,"will retrive");
        Log.v(save_tag,"fragment: "+String.valueOf(savedInstanceState.getInt("Pager_Current")));
        Log.v(save_tag,"selected id: "+savedInstanceState.getInt("Selected_match"));
        current_fragment = savedInstanceState.getInt("Pager_Current");
        selected_match_id = savedInstanceState.getInt("Selected_match");
        leagues = (HashMap)savedInstanceState.getSerializable("LeaguesDictionary");
        my_main = (PagerFragment) getSupportFragmentManager().getFragment(savedInstanceState,"my_main");
        super.onRestoreInstanceState(savedInstanceState);
    }
}
