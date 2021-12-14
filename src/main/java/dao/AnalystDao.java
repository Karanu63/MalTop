package dao;

import models.Analyst;

import java.util.List;

public interface AnalystDao {

    //List all analysts
    List<Analyst> getAllAnalysts();

    //create an new analysts
    void addAnalyst(Analyst analyst);

    //get a specific analyst
    Analyst findAnalystById(int id);


    //Update a nanalyst

    //Delete an analyst
    void deleteAnalystById(int id);

    //Delete all analysts
    void deleteAllAnalysts();
}
