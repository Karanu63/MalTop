package models;

public class Solution {
    private int id;
    private String analystEmail;
    private String solution;
    private int exerciseId;
    private String createdAt;

    public Solution(String analystEmail, String solution, int exerciseId, String createdAt) {
        this.analystEmail = analystEmail;
        this.solution = solution;
        this.exerciseId = exerciseId;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnalystEmail() {
        return analystEmail;
    }

    public void setAnalystEmail(String analystEmail) {
        this.analystEmail = analystEmail;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }
}
