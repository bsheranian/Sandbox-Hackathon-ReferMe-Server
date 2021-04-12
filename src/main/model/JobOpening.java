package model;

import java.util.Objects;

public class JobOpening {
  private String id;
  private String industry;
  private String companyId;
  private String jobDescription;

  public JobOpening() {}

  public JobOpening(String id, String industry, String jobDescription, String companyId) {
    this.id = id;
    this.industry = industry;
    this.jobDescription = jobDescription;
    this.companyId = companyId;
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

  public String getCompanyId() {
    return companyId;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }
}
