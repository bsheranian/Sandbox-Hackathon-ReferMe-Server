package model;

import java.util.Objects;

public abstract class User {
  private String email;
  private String password;
  private String name;
  private byte[] imageBytes;
  private String imageUrl;

  public User() {}

  public User(String email, String password, String imageUrl, String name) {
    this.email = email;
    this.password = password;
    this.imageUrl = imageUrl;
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(email, user.email) &&
        Objects.equals(password, user.password) &&
        Objects.equals(imageUrl, user.imageUrl) &&
        Objects.equals(name, user.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password, imageUrl, name);
  }
}
