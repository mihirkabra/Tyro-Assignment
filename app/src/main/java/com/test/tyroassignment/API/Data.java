
package com.test.tyroassignment.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("trainer_id")
    @Expose
    private Integer trainerId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("min_calories")
    @Expose
    private Integer minCalories;
    @SerializedName("max_calories")
    @Expose
    private Integer maxCalories;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("difficulty_level")
    @Expose
    private Integer difficultyLevel;
    @SerializedName("createdAt")
    @Expose
    private Integer createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("video_uploadedAt")
    @Expose
    private String videoUploadedAt;
    @SerializedName("equipments")
    @Expose
    private List<String> equipments = null;
    @SerializedName("workout_plans")
    @Expose
    private List<WorkoutPlan> workoutPlans = null;
    @SerializedName("trainer_name")
    @Expose
    private String trainerName;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("difficulty_level_name")
    @Expose
    private String difficultyLevelName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getMinCalories() {
        return minCalories;
    }

    public void setMinCalories(Integer minCalories) {
        this.minCalories = minCalories;
    }

    public Integer getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(Integer maxCalories) {
        this.maxCalories = maxCalories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideoUploadedAt() {
        return videoUploadedAt;
    }

    public void setVideoUploadedAt(String videoUploadedAt) {
        this.videoUploadedAt = videoUploadedAt;
    }

    public List<String> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<String> equipments) {
        this.equipments = equipments;
    }

    public List<WorkoutPlan> getWorkoutPlans() {
        return workoutPlans;
    }

    public void setWorkoutPlans(List<WorkoutPlan> workoutPlans) {
        this.workoutPlans = workoutPlans;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDifficultyLevelName() {
        return difficultyLevelName;
    }

    public void setDifficultyLevelName(String difficultyLevelName) {
        this.difficultyLevelName = difficultyLevelName;
    }

}
