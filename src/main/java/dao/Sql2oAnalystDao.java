package dao;

import models.Analyst;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oAnalystDao implements AnalystDao {
    private final Sql2o sql2o;

    public Sql2oAnalystDao(Sql2o sql2o) {
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
    public List<Analyst> getAllAnalysts() {
        getDrivers();
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM analysts")
                    .executeAndFetch(Analyst.class);
        }
    }

    @Override
    public void addAnalyst(Analyst analyst) {
        getDrivers();
        String sql = "INSERT INTO analysts (analystName,analystEmail,trainerId,analystPassword) VALUES (:analystName,:analystEmail,:trainerId,:analystPassword)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(analyst)
                    .executeUpdate()
                    .getKey();
            analyst.setId(id);
        }catch(Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public Analyst findAnalystById(int id) {
        getDrivers();
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM analysts WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Analyst.class);
        }
    }

    public Analyst findAnalystByMailAndPassword(String analystEmail, String analystPassword) {
        getDrivers();
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM analysts WHERE analystEmail = :analystEmail AND analystPassword = :analystPassword")
                    .addParameter("analystEmail", analystEmail)
                    .addParameter("analystPassword",analystPassword)
                    .executeAndFetchFirst(Analyst.class);
        }
    }


    @Override
    public void deleteAnalystById(int id) {
        getDrivers();
        String sql = "DELETE FROM analysts WHERE id =:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public void deleteAllAnalysts() {
        getDrivers();
        String sql = "DELETE from analysts";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .executeUpdate();
        }catch(Sql2oException e){
            System.out.println(e);
        }
    }

}
