
package com.test.tyroassignment.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WorkoutPlan {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("reps")
    @Expose
    private String reps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

}
