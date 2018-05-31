package nl.hva.dev.mobile.seriestracker.data;

public class LoginCredentials {

  public String apikey;
  public String username;
  public String userpass;

  public LoginCredentials(String apikey) {
    this.apikey = apikey;
  }

  public LoginCredentials user(String username, String userpass) {
    this.username = username;
    this.userpass = userpass;
    return this;
  }

}