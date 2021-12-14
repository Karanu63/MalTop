package models;

public class Exercise {
    private int id;
    private String exerciseName;
    private String exerciseDescription;
    private int exercisePoints;
    private String exerciseUrl;

    public Exercise(String exerciseName, String exerciseDescription, int exercisePoints, String exerciseUrl) {
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.exercisePoints = exercisePoints;
        this.exerciseUrl = exerciseUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public int getExercisePoints() {
        return exercisePoints;
    }

    public void setExercisePoints(int exercisePoints) {
        this.exercisePoints = exercisePoints;
    }

    public String getExerciseUrl() {
        return exerciseUrl;
    }

    public void setExerciseUrl(String exerciseUrl) {
        this.exerciseUrl = exerciseUrl;
    }
}
