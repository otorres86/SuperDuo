package barqsoft.footballscores;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by yehya khaled on 3/3/2015.
 * Updated by Olga Torres
 */
public class Utilities
{
    //@olga - champions league is now at 405
    public static final int CHAMPIONS_LEAGUE = 405;

    //@olga Get the context - app in this case
    private static Context mContext = FootballScoresApp.getContext();

    /*@olga - get the name of the league from the leagues dictionary
     if the league code exists in the dictionary, otherwise return "unknown league"
     */
    public static String getLeague(int league_num, HashMap leagues)
    {
        if(leagues.containsKey(league_num)) return (String)leagues.get(league_num);
        else return mContext.getString(R.string.unknown_league_name);
    }

    /*
    * @olga - made all strings to be pulled from strings.xml
    */
    public static String getMatchDay(int match_day,int league_num)
    {
        if(league_num == CHAMPIONS_LEAGUE)
        {
            if (match_day <= 6)
            {
                return mContext.getString(R.string.group_stages_text);
            }
            else if(match_day == 7 || match_day == 8)
            {
                return mContext.getString(R.string.first_knockout_round);
            }
            else if(match_day == 9 || match_day == 10)
            {
                return mContext.getString(R.string.quarter_final);
            }
            else if(match_day == 11 || match_day == 12)
            {
                return mContext.getString(R.string.semi_final);
            }
            else
            {
                return mContext.getString(R.string.final_text);
            }
        }
        else
        {
            return mContext.getString(R.string.matchday_text) + " " + String.valueOf(match_day);
        }
    }

    public static String getScores(int score1,int score2)
    {
        if(score1 < 0 || score2 < 0)
        {
            return " - ";
        }
        else
        {
            return String.valueOf(score1) + " - " + String.valueOf(score2);
        }
    }

    public static int getTeamCrestByTeamName (String teamname)
    {
        if (teamname==null){return R.drawable.no_icon;}
        switch (teamname)
        { //This is the set of icons that are currently in the app. Feel free to find and add more
            //as you go.
            case "Arsenal London FC" : return R.drawable.arsenal;
            case "Manchester United FC" : return R.drawable.manchester_united;
            case "Swansea City" : return R.drawable.swansea_city_afc;
            case "Leicester City" : return R.drawable.leicester_city_fc_hd_logo;
            case "Everton FC" : return R.drawable.everton_fc_logo1;
            case "West Ham United FC" : return R.drawable.west_ham;
            case "Tottenham Hotspur FC" : return R.drawable.tottenham_hotspur;
            case "West Bromwich Albion" : return R.drawable.west_bromwich_albion_hd_logo;
            case "Sunderland AFC" : return R.drawable.sunderland;
            case "Stoke City FC" : return R.drawable.stoke_city;
            default: return R.drawable.no_icon;
        }
    }

    /*
    * Gets and returns current date in YYYY-MM-DD format
     */
    public static String getCurrentDate(){
        return (new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }
}
