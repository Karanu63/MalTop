package dao;

import models.Solution;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oSolutionDao implements SolutionDao {
    private final Sql2o sql2o;

    public Sql2oSolutionDao(Sql2o sql2o) {
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
    public void addSolution(Solution solution) {
        getDrivers();
        try(Connection conn = sql2o.open()){
            String sql = "INSERT INTO solutions (analystEmail, solution, exerciseId, createdAt) VALUES  (:analystEmail, :solution, :exerciseId, :createdAt)";
            int id = (int) conn.createQuery(sql, true)
                    .bind(solution)
                    .executeUpdate()
                    .getKey();
            solution.setId(id);
        }catch(Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public List<Solution> getAllSolutions() {
        getDrivers();
        try(Connection conn = sql2o.open()){
            String sql = "SELECT * FROM solutions";
            return conn.createQuery(sql)
                    .executeAndFetch(Solution.class);
        }
    }

    @Override
    public Solution getSolutionById(int id) {
        getDrivers();
        try(Connection conn = sql2o.open()){
            String sql = "SELECT * FROM solutions WHERE id = :id";
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Solution.class);
        }
    }



    public List<Solution> getSolutionsByExerciseId(int exerciseId) {
        getDrivers();
        try(Connection conn = sql2o.open()){
            String sql = "SELECT * FROM solutions WHERE exerciseId = :exerciseId";
            return conn.createQuery(sql)
                    .addParameter("exerciseId", exerciseId)
                    .executeAndFetch(Solution.class);
        }
    }


    @Override
    public void deleteAllSolutions() {
        getDrivers();
        try(Connection conn = sql2o.open()){
            String sql = "DELETE FROM solutions";
            conn.createQuery(sql)
                    .executeUpdate();
        }catch(Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public void deleteSolutionById(int id) {
        getDrivers();
        try(Connection conn = sql2o.open()){
            String sql = "DELETE FROM solutions WHERE id = :id";
            conn.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }
    }
}
