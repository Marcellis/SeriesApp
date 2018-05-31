package nl.hva.dev.mobile.seriestracker.network.services;

import nl.hva.dev.mobile.seriestracker.data.SeriesResponse;
import nl.hva.dev.mobile.seriestracker.network.TvdbApi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface TvdbSeriesService {

  @GET("series/{id}")
  Call<SeriesResponse> series(
      @Path("id") int id,
      @Header(TvdbApi.HEADER_ACCEPT_LANGUAGE) String language
  );
}
