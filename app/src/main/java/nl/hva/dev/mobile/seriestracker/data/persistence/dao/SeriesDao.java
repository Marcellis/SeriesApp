package nl.hva.dev.mobile.seriestracker.data.persistence.dao;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import nl.hva.dev.mobile.seriestracker.data.Series;

@Dao
public interface SeriesDao {

  @Insert(onConflict = REPLACE)
  void save(Series series);

  @Query("SELECT * FROM series WHERE id = :seriesId")
  LiveData<Series> load(String seriesId);

}