package android.ivo.popularmovies.database;
import android.content.Context;
import android.ivo.popularmovies.network.models.MovieInfo;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(exportSchema = false, entities = MovieInfo.class, version = 1)
public abstract class AppDatabase  extends RoomDatabase {
    private static final String DB_NAME = "movies_db";
    private static AppDatabase mInstance;

    public static synchronized AppDatabase getInstance(Context context)
    {
        if(mInstance ==null) {
            mInstance = Room.databaseBuilder(context,AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    // TODO: remove the line bellow when finished with the
                    //       main thread tests.
                    .allowMainThreadQueries()
                    .build();
        }
        return mInstance;
    }

    public abstract AppDao dao();
}
