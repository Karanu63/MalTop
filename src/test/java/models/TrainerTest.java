package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TrainerTest {

    @Test
    public void newTrainerObjectGetsCorrectlyCreated(){
        Trainer trainer = newTrainer();
        assertNotNull(trainer);
    }

    @Test
    public void trainerInstantiatesWithAllTheProperties(){
        Trainer trainer = newTrainer();
        assertEquals("Joanne,jojo@gmail.com,xr%87of,20",String.format("%s,%s,%s,%d",trainer.getTrainerName(),trainer.getTrainerEmail(),trainer.getTrainerPassword(),trainer.getClassSize()));
    }

    //helper method
    public Trainer newTrainer(){
        return new Trainer("Joanne","jojo@gmail.com","xr%87of",20);
    }
}
