package nl.hva.dev.mobile.seriestracker.network.services;

import nl.hva.dev.mobile.seriestracker.data.LoginCredentials;
import nl.hva.dev.mobile.seriestracker.data.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TvdbAuthenticationService {

  String LOGIN_PATH = "login";

  @POST(LOGIN_PATH)
  Call<Token> login(@Body LoginCredentials apiKey);

  @GET("refresh_token")
  Call<Token> refreshToken();
}