package android.ivo.popularmovies.database;

import android.ivo.popularmovies.models.MovieInfo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AppDao {
    @Query("SELECT * FROM movies")
    LiveData<List<MovieInfo>> getEntities();

    @Query("SELECT * FROM movies WHERE mId=:id")
    MovieInfo getMovieInfo(int id);

    @Insert
    void insertMovieInfo(MovieInfo movieInfo);

    @Update
    void updateMovieInfo(MovieInfo movieInfo);

    @Delete
    void deleteMovieInfo(MovieInfo movieInfo);
}
