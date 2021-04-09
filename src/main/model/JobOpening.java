package model;

import java.util.Objects;

public class JobOpening {
  private String id;
  private String industry;
  private String jobDescription;

  public JobOpening() {}

  public JobOpening(String id, String industry, String jobDescription) {
    this.id = id;
    this.industry = industry;
    this.jobDescription = jobDescription;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

  public String getJobDescription() {
    return jobDescription;
  }

  public void setJobDescription(String jobDescription) {
    this.jobDescription = jobDescription;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    JobOpening that = (JobOpening) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(industry, that.industry) &&
        Objects.equals(jobDescription, that.jobDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, industry, jobDescription);
  }
}
