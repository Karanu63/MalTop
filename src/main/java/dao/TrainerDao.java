package dao;

import models.Analyst;
import models.Trainer;

import java.util.List;

public interface TrainerDao {
    //list
    List<Trainer> getAllTrainers();

    //create
    void addTrainer(Trainer squad);

    //find by id/read
    Trainer findTrainerById(int id);
    List<Analyst> getAllAnalystsByTrainer(int TrainerId);

    //Squad update
    //void squadUpdate(int id, String squadName, String squadCause, int maxSize);

    //delete squad
    void deleteTrainerById(int id);
    void deleteAllTrainers();

}
