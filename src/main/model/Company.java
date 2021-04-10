package model;

import java.util.Objects;

public class Company extends User {

  private String websiteUrl;
  private String description;

  public Company() {}

  public Company(String email, String password, String imageUrl, String name, String websiteUrl, String description) {
    super(email, password, imageUrl, name);
    this.websiteUrl = websiteUrl;
    this.description = description;
  }

  public String getWebsiteUrl() {
    return websiteUrl;
  }

  public void setWebsiteUrl(String websiteUrl) {
    this.websiteUrl = websiteUrl;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Company company = (Company) o;
    return Objects.equals(websiteUrl, company.websiteUrl) &&
        Objects.equals(description, company.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), websiteUrl, description);
  }
}
