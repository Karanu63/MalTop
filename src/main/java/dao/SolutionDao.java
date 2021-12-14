package dao;

import models.Solution;

import java.util.List;

public interface SolutionDao {
    //Create
    void addSolution(Solution solution);

    //Read
    List<Solution> getAllSolutions();
    Solution getSolutionById(int id);

    //Update

    //Delete
    void deleteAllSolutions();
    void deleteSolutionById(int id);
}
