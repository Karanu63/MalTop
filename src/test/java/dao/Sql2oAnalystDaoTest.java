package dao;

import models.Analyst;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

public class Sql2oAnalystDaoTest {
    private Sql2oAnalystDao analystDao;
    private Connection conn;

    @BeforeEach
    void setUp() {
        String connectionString = "jdbc:postgresql://localhost:5432/maltop_test";
        Sql2o sql2o = new Sql2o(connectionString,"moringa","sparkpass");
        analystDao = new Sql2oAnalystDao(sql2o);
        conn = sql2o.open();
    }

    @Test
    public void addingAnalystSetsId() throws Exception {
        Analyst analyst = setUpNewAnalyst();
        int originalAnalystId = analyst.getId();
        analystDao.addAnalyst(analyst);
        assertNotEquals(originalAnalystId, analyst.getId());
    }

    @Test
    public void existingAnalystCanBeFoundById() throws Exception {
        Analyst analyst = setUpNewAnalyst();
        analystDao.addAnalyst(analyst);
        Analyst foundAnalyst = analystDao.findAnalystById(analyst.getId());
        assertEquals(foundAnalyst, analyst);
    }

    @Test
    public void deleteByIdDeletesCorrectAnalyst() throws Exception {
        Analyst analyst = setUpNewAnalyst();
        analystDao.addAnalyst(analyst);
        analystDao.deleteAnalystById(analyst.getId());
        assertEquals(0,analystDao.getAllAnalysts().size());
    }

    @Test
    public void clearAllAnalystsClearsAll() throws Exception {
        Analyst analyst = setUpNewAnalyst();
        Analyst analyst1 = new Analyst("Allun","allun@gmail.com","gfduete6",1);
        analystDao.addAnalyst(analyst);
        analystDao.addAnalyst(analyst1);
        int daoSize = analystDao.getAllAnalysts().size();
        analystDao.deleteAllAnalysts();
        assertTrue(daoSize > 0 && daoSize > analystDao.getAllAnalysts().size());
    }

    @Test
    public void trainerIdIsReturnedCorrectly() throws Exception{
        Analyst analyst = setUpNewAnalyst();
        int originalTrainerId = analyst.getTrainerId();
        analystDao.addAnalyst(analyst);
        assertEquals(originalTrainerId,analystDao.findAnalystById(analyst.getId()).getTrainerId());
    }



    //helper method
    public Analyst setUpNewAnalyst(){
        return new Analyst("Imelda","imelda@gmail.com","iude6",2);
    }

    @AfterEach
    void tearDown() {
        analystDao.deleteAllAnalysts();
        conn.close();
    }
}
