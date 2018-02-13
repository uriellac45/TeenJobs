package com.example.user.teenjobs;

/**
 * Created by USER
 */


public class Job {
    private String jobTitle;
    private String jobID;

    //For Firebase
    public Job() {
    }

    public Job(String jobTitle, String jobID) {
        this.jobTitle = jobTitle;
        this.jobID = jobID;
    }

    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getJobID() {
        return jobID;
    }
    public void setJobID(String jobID) {
        this.jobID = jobID;
    }


    @Override
    public String toString() {
        return jobTitle;
    }
}
