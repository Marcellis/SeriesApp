package nl.hva.dev.mobile.seriestracker.data.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import nl.hva.dev.mobile.seriestracker.data.Series;
import nl.hva.dev.mobile.seriestracker.data.persistence.dao.SeriesDao;

@Database(entities = {Series.class}, version = 1)
public abstract class SeriesDatabase extends RoomDatabase {

  public abstract SeriesDao seriesDao();

}
