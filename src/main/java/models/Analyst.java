package models;

import java.util.Objects;

public class Analyst {
    private String analystName;
    private String analystEmail;
    private String analystPassword;
    private int id;
    private int trainerId;

    public Analyst(String analystName, String analystEmail, String analystPassword, int trainerId) {
        this.analystName = analystName;
        this.analystEmail = analystEmail;
        this.analystPassword = analystPassword;
        this.trainerId = trainerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Analyst analyst = (Analyst) o;
        return Objects.equals(analystName, analyst.analystName) && Objects.equals(analystEmail, analyst.analystEmail) && Objects.equals(analystPassword, analyst.analystPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(analystName, analystEmail, analystPassword);
    }

    public String getAnalystName() {
        return analystName;
    }

    public void setAnalystName(String analystName) {
        this.analystName = analystName;
    }

    public String getAnalystEmail() {
        return analystEmail;
    }

    public void setAnalystEmail(String analystEmail) {
        this.analystEmail = analystEmail;
    }

    public String getAnalystPassword() {
        return analystPassword;
    }

    public void setAnalystPassword(String analystPassword) {
        this.analystPassword = analystPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }
}
