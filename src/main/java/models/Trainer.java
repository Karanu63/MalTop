package models;

import java.util.Objects;

public class Trainer {
    private int id;
    private String trainerName;
    private String trainerEmail;
    private String trainerPassword;
    private int classSize;

    public Trainer(String trainerName, String trainerEmail, String trainerPassword, int classSize) {
        this.trainerName = trainerName;
        this.trainerEmail = trainerEmail;
        this.trainerPassword = trainerPassword;
        this.classSize = classSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return id == trainer.id && classSize == trainer.classSize && trainerName.equals(trainer.trainerName) && trainerEmail.equals(trainer.trainerEmail) && trainerPassword.equals(trainer.trainerPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trainerName, trainerEmail, trainerPassword, classSize);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getTrainerEmail() {
        return trainerEmail;
    }

    public void setTrainerEmail(String trainerEmail) {
        this.trainerEmail = trainerEmail;
    }

    public String getTrainerPassword() {
        return trainerPassword;
    }

    public void setTrainerPassword(String trainerPassword) {
        this.trainerPassword = trainerPassword;
    }

    public int getClassSize() {
        return classSize;
    }

    public void setClassSize(int classSize) {
        this.classSize = classSize;
    }
}
