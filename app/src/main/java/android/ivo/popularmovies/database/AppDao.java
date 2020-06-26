package android.ivo.popularmovies.database;

import android.ivo.popularmovies.models.MovieInfo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AppDao {
    @Query("SELECT * FROM movies")
    public List<MovieInfo> getMovieInfoList();

    @Query("SELECT * FROM movies WHERE mId=:id")
    public MovieInfo getMovieInfo(int id);

    @Insert
    public void insertMovieInfo(MovieInfo movieInfo);

    @Update
    public void updateMovieInfo(MovieInfo movieInfo);

    @Delete
    public void deleteMovieInfo(MovieInfo movieInfo);
}
