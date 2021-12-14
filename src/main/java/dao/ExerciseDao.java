package dao;

import models.Exercise;
import models.Solution;

import java.util.List;

public interface ExerciseDao {
    //Create
    void addExercise(Exercise exercise);

    //Read
    List<Exercise> getAllExercises();
    Exercise getExerciseById(int id);
    List<Solution> getSolutionsByExercise(int exerciseId);

    //Update

    //Delete
    void deleteAllExercises();
    void deleteExerciseById(int id);
}
