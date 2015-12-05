package barqsoft.footballscores;

import android.app.Application;
import android.content.Context;

/**
 * Created by Olga on 12/4/2015.
 * Creating a class to be able to use local resources in any class
 * Found this snippet on stackoverflow
 */
public class FootballScoresApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate(){
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
