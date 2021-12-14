package dao;

import models.Exercise;
import models.Solution;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oExerciseDao implements ExerciseDao {
    private final Sql2o sql2o;

    public Sql2oExerciseDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public static void getDrivers(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addExercise(Exercise exercise) {
        getDrivers();
        try(Connection conn = sql2o.open()){
            String sql = "INSERT INTO exercises (exerciseName, exerciseDescription, exercisePoints, exerciseUrl) VALUES (:exerciseName, :exerciseDescription, :exercisePoints, :exerciseUrl)";
            int id = (int) conn.createQuery(sql, true)
                    .bind(exercise)
                    .executeUpdate()
                    .getKey();
            exercise.setId(id);
        }catch(Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public List<Exercise> getAllExercises() {
        getDrivers();
        try(Connection conn = sql2o.open()){
            String sql = "SELECT * FROM exercises";
            return conn.createQuery(sql)
                    .executeAndFetch(Exercise.class);
        }
    }

    @Override
    public Exercise getExerciseById(int id) {
        getDrivers();
        try(Connection conn = sql2o.open()){
            String sql = "SELECT * FROM exercises WHERE id = :id";
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Exercise.class);
        }
    }

    @Override
    public List<Solution> getSolutionsByExercise(int exerciseId) {
        getDrivers();
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM solutions WHERE exerciseId = :exerciseId")
                    .addParameter("exerciseId",exerciseId)
                    .executeAndFetch(Solution.class);
        }
    }

    @Override
    public void deleteAllExercises() {
        getDrivers();
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM exercises *")
                    .executeUpdate();
        }catch(Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public void deleteExerciseById(int id) {
        getDrivers();
        try(Connection conn = sql2o.open()){
            String sql = "DELETE FROM exercises WHERE id = :id";
            conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch(Sql2oException e){
            System.out.println(e);
        }
    }
}
