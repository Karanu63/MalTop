package dao;

import models.Analyst;
import models.Trainer;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oTrainerDao implements TrainerDao{
    private final Sql2o sql2o;

    public Sql2oTrainerDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    public void getDrivers(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Trainer> getAllTrainers() {
        getDrivers();
        String sql = "SELECT * FROM trainers";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .executeAndFetch(Trainer.class);
        }
    }

    @Override
    public void addTrainer(Trainer trainer) {
        getDrivers();

        String sql = "INSERT INTO trainers(trainerName,trainerEmail,trainerPassword,classSize) VALUES (:trainerName, :trainerEmail, :trainerPassword, :classSize)";
        try(Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql,true)
                    .bind(trainer)
                    .executeUpdate()
                    .getKey();
            trainer.setId(id);
        }catch(Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public Trainer findTrainerById(int id) {
        getDrivers();
        String sql = "SELECT * FROM trainers WHERE  id = :id";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Trainer.class);
        }
    }

    public Trainer findTrainerByMailAndPassword(String email, String password) {
        getDrivers();
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM trainers WHERE email = :email AND password = :password")
                    .addParameter("email", email)
                    .addParameter("password",password)
                    .executeAndFetchFirst(Trainer.class);
        }
    }

    @Override
    public List<Analyst> getAllAnalystsByTrainer(int trainerId) {
        getDrivers();
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM analysts WHERE trainerId = :trainerId")
                    .addParameter("trainerId",trainerId)
                    .executeAndFetch(Analyst.class);
        }
    }

    @Override
    public void deleteTrainerById(int id) {
        getDrivers();
        String sql = "DELETE FROM trainers WHERE id = :id";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }catch(Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public void deleteAllTrainers() {
        getDrivers();
        String sql = "DELETE FROM trainers";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .executeUpdate();
        }catch(Sql2oException e){
            System.out.println(e);
        }
    }
}
