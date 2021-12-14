package dao;

import models.Analyst;
import models.Trainer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

public class Sql2oTrainerDaoTest {
    private Sql2oTrainerDao trainerDao;
    private Sql2oAnalystDao analystDao;
    private Connection conn;

    @BeforeEach
    void setUp() {
        String connectionString = "jdbc:postgresql://localhost:5432/maltop_test";
        Sql2o sql2o = new Sql2o(connectionString,"moringa","sparkpass");
        trainerDao = new Sql2oTrainerDao(sql2o);
        analystDao = new Sql2oAnalystDao(sql2o);
        conn = sql2o.open();
    }

    @Test
    public void addingTrainerSetsId() throws Exception {
        Trainer trainer = setUpNewTrainer();
        int originalTrainerId = trainer.getId();
        trainerDao.addTrainer(trainer);
        assertNotEquals(originalTrainerId,trainer.getId());
    }

    @Test
    public void existingTrainerCanBeFoundById() throws Exception {
        Trainer trainer =setUpNewTrainer();
        trainerDao.addTrainer(trainer);
        Trainer foundTrainer = trainerDao.findTrainerById(trainer.getId());
        assertEquals(trainer,foundTrainer);
    }

    @Test
    public void addedTrainersAreReturnedFromGetAllTrainers() throws Exception {
        Trainer trainer = setUpNewTrainer();
        trainerDao.addTrainer(trainer);
        assertEquals(1,trainerDao.getAllTrainers().size());
    }

    @Test
    public void noTrainerReturnsEmptyList() throws Exception {
        assertEquals(0,trainerDao.getAllTrainers().size());
    }

    @Test
    public void deleteTrainerByIdDeletesCorrectTrainer() throws Exception {
        Trainer trainer = setUpNewTrainer();
        trainerDao.addTrainer(trainer);
        trainerDao.deleteTrainerById(trainer.getId());
        assertEquals(0,trainerDao.getAllTrainers().size());
    }

    @Test
    public void deleteAllTrainersDeletesAllTrainers() throws Exception {
        Trainer trainer = setUpNewTrainer();
        Trainer trainer1 = new Trainer("Eston","e@gmail.com","yug67",20);
        trainerDao.addTrainer(trainer);
        trainerDao.addTrainer(trainer1);
        int daoSize = trainerDao.getAllTrainers().size();
        trainerDao.deleteAllTrainers();
        assertTrue(daoSize >0 && daoSize > trainerDao.getAllTrainers().size());
    }

    @Test
    public void getAllAnalystsByTrainerReturnsAnalystsCorrectly() throws Exception{
        Trainer trainer = setUpNewTrainer();
        trainerDao.addTrainer(trainer);
        int trainerId = trainer.getId();
        Analyst analyst1 = new Analyst("Kim","kim@gmail.com","eiur786",trainerId);
        Analyst analyst2 = new Analyst("Quinter","q@gmail.com","eiurds786",trainerId);
        Analyst analyst3 = new Analyst("Archimedes","archimedes@gmail.com","ytcytw",trainerId);
        analystDao.addAnalyst(analyst1);
        analystDao.addAnalyst(analyst2);

        assertEquals(2,trainerDao.getAllAnalystsByTrainer(trainerId).size());
        assertTrue(trainerDao.getAllAnalystsByTrainer(trainerId).contains(analyst1));
        assertFalse(trainerDao.getAllAnalystsByTrainer(trainerId).contains(analyst3));
    }


    //helper method
    public Trainer setUpNewTrainer(){
        return new Trainer("Joanne","jojo@gmail.com","xr%87of",20);
    }

    @AfterEach
    void tearDown() {
        trainerDao.deleteAllTrainers();
        analystDao.deleteAllAnalysts();
        conn.close();
    }
}
