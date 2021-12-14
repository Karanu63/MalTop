package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AnalystTest {
    @Test
    public void analystObjectInstantiatesCorrectly(){
        Analyst analyst = newAnalyst();
        assertNotNull(analyst);
    }

    @Test
    public void analystObjectInstantiatesWithAllProperties(){
        Analyst analyst = newAnalyst();
        assertEquals("Kim,kim@gmail.com,eiur786,1",String.format("%s,%s,%s,%d",analyst.getAnalystName(),analyst.getAnalystEmail(),analyst.getAnalystPassword(),analyst.getTrainerId()));
    }

    //helper method
    public Analyst newAnalyst(){
        return new Analyst("Kim","kim@gmail.com","eiur786",1);
    }
}
