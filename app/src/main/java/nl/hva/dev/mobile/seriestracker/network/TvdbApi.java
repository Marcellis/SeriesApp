package nl.hva.dev.mobile.seriestracker.network;

import android.support.annotation.Nullable;
import nl.hva.dev.mobile.seriestracker.network.services.TvdbAuthenticationService;
import nl.hva.dev.mobile.seriestracker.network.services.TvdbSeriesService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvdbApi {

  public static final String HEADER_AUTHORIZATION = "Authorization";
  public static final String HEADER_ACCEPT_LANGUAGE = "Accept-Language";

  private static final String BASE_URL = "https://api.thetvdb.com/";

  private OkHttpClient okHttpClient;
  private Retrofit retrofit;

  private String apiKey;
  private String currentJsonWebToken;

  public TvdbApi(String apiKey) {
    this.apiKey = apiKey;
  }

  @Nullable
  public String apiKey() {
    return apiKey;
  }

  @Nullable
  public String jsonWebToken() {
    return currentJsonWebToken;
  }

  public void jsonWebToken(@Nullable String value) {
    this.currentJsonWebToken = value;
  }

  private Retrofit.Builder retrofitBuilder() {
    return new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClientSetup());
  }

  private synchronized OkHttpClient okHttpClientSetup() {
    if (okHttpClient == null) {
      OkHttpClient.Builder builder = new OkHttpClient.Builder();

      builder.addNetworkInterceptor(new TvdbInterceptor(this))
          .authenticator(new TvdbAuthenticator(this));

      okHttpClient = builder.build();
    }
    return okHttpClient;
  }

  private Retrofit getRetrofit() {
    if (retrofit == null) {
      retrofit = retrofitBuilder().build();
    }
    return retrofit;
  }

  public TvdbAuthenticationService authenticate() {
    return getRetrofit().create(TvdbAuthenticationService.class);
  }

  public TvdbSeriesService series() {
    return getRetrofit().create(TvdbSeriesService.class);
  }

  // add more services...
}
