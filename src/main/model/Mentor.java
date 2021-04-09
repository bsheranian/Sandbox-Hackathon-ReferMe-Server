package model;

import java.util.Objects;

public class Mentor extends User {

  private String school;
  private String industry;
  private float rating;   //out of 5
  private int yearsExperience;

  public Mentor() {}

  public Mentor(String email, String password, String imageUrl, String name, String school, String industry, float rating, int yearsExperience) {
    super(email, password, imageUrl, name);
    this.school = school;
    this.industry = industry;
    this.rating = rating;
    this.yearsExperience = yearsExperience;
  }

  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

  public float getRating() {
    return rating;
  }

  public void setRating(float rating) {
    this.rating = rating;
  }

  public int getYearsExperience() {
    return yearsExperience;
  }

  public void setYearsExperience(int yearsExperience) {
    this.yearsExperience = yearsExperience;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Mentor mentor = (Mentor) o;
    return Float.compare(mentor.rating, rating) == 0 &&
        yearsExperience == mentor.yearsExperience &&
        Objects.equals(school, mentor.school) &&
        Objects.equals(industry, mentor.industry);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), school, industry, rating, yearsExperience);
  }
}
