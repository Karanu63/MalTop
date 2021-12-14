import dao.Sql2oAnalystDao;
import dao.Sql2oExerciseDao;
import dao.Sql2oSolutionDao;
import dao.Sql2oTrainerDao;
import models.Analyst;
import models.Exercise;
import models.Solution;
import models.Trainer;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static spark.Spark.*;
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        String connectionString = "jdbc:postgresql://localhost:5432/maltop?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        Sql2o sql2o = new Sql2o(connectionString,"moringa","sparkpass");
        Sql2oAnalystDao analystDao = new Sql2oAnalystDao(sql2o);
        Sql2oTrainerDao trainerDao = new Sql2oTrainerDao(sql2o);
        Sql2oExerciseDao exerciseDao = new Sql2oExerciseDao(sql2o);
        Sql2oSolutionDao solutionDao = new Sql2oSolutionDao(sql2o);

        //show homepage
        get("/",(request,response)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        //show new analyst form
        get("/analyst/register",(request,response)->{
            Map<String, Object>model = new HashMap<>();
            List<Trainer> allTrainers = trainerDao.getAllTrainers();
            model.put("trainers",allTrainers);
            return new ModelAndView(model,"analyst-register.hbs");
        }, new HandlebarsTemplateEngine());

        //process new analyst
        post("/analyst/register",(request,response)->{
            Map<String,Object>model = new HashMap<>();
            String analystName = request.queryParams("analystName");
            String analystEmail = request.queryParams("analystEmail");
            String analystPassword = request.queryParams("analystPassword");
            String analystPasswordConf = request.queryParams("password_confirmation");
            int trainerId = Integer.parseInt(request.queryParams("trainerId"));
            if(!analystPassword.equals(analystPasswordConf)){
                response.redirect("/analyst/register");
            }else{
                Analyst newAnalyst = new Analyst(analystName,analystEmail,analystPassword,trainerId);
                analystDao.addAnalyst(newAnalyst);
                response.redirect("/analyst/login");
            }
            return null;
        }, new HandlebarsTemplateEngine());

        //show analyst login form
        get("/analyst/login",(request,response)->{
            Map<String, Object>model = new HashMap<>();
            return new ModelAndView(model,"analyst-login.hbs");
        }, new HandlebarsTemplateEngine());


        //process analyst login form
        post("/analyst/login", (request,response)->{
            Map<String,Object>model = new HashMap<>();
            String analystEmail = request.queryParams("analystEmail");
            String analystPassword = request.queryParams("analystPassword");
            Analyst exist = analystDao.findAnalystByMailAndPassword(analystEmail,analystPassword);
            System.out.println(exist.getAnalystEmail());

            if (exist.getAnalystEmail().equals(analystEmail) && exist.getAnalystPassword().equals(analystPassword)){
                response.redirect("/analyst/exercise");
            }else{
                response.redirect("/analyst/login");
            }
        return null;
    }, new HandlebarsTemplateEngine());

        //new trainer form
        get("/trainer/register",(request,response)->{
            Map<String,Object>model = new HashMap<>();
            return new ModelAndView(model,"trainer-register.hbs");
        }, new HandlebarsTemplateEngine());

        //process new trainer
        post("/trainer/register",(request,response)->{
            Map<String,Object>model = new HashMap<>();
            String trainerName = request.queryParams("trainerName");
            String trainerEmail = request.queryParams("trainerEmail");
            String trainerPassword = request.queryParams("trainerPassword");
            String trainerPasswordConf = request.queryParams("password_confirmation");
            int classSize = Integer.parseInt(request.queryParams("classSize"));
            if(!trainerPassword.equals(trainerPasswordConf)){
                response.redirect("/trainer/register");
            }else{
                Trainer trainer = new Trainer(trainerName,trainerEmail,trainerPassword,classSize);
                trainerDao.addTrainer(trainer);
                response.redirect("/trainer/login");
            }
            return null;
        }, new HandlebarsTemplateEngine());

        //trainer login form
        get("/trainer/login",(request,response)->{
            Map<String,Object>model = new HashMap<>();
            return new ModelAndView(model,"trainer-login.hbs");
        }, new HandlebarsTemplateEngine());

        //process trainer login
        post("/trainer/login", (request,response)->{
            Map<String,Object>model = new HashMap<>();
            String trainerEmail = request.queryParams("trainerEmail");
            String trainerPassword = request.queryParams("trainerPassword");
            Trainer exist = trainerDao.findTrainerByMailAndPassword(trainerEmail,trainerPassword);
            System.out.println(trainerEmail+trainerPassword);
            System.out.println("ssss"+exist.getTrainerPassword());
            System.out.println("ssss"+exist.getTrainerPassword());
            if (exist.getTrainerEmail().equals(trainerEmail) && exist.getTrainerPassword().equals(trainerPassword)){
                response.redirect("/trainer/dashboard");
            }else{
                response.redirect("/trainer/login");
            }
            return null;
        }, new HandlebarsTemplateEngine());


        //trainer dashboard
        get("/trainer/dashboard",(request,response)->{
            Map<String,Object>model = new HashMap<>();
            List<Exercise> allExercises = exerciseDao.getAllExercises();
            model.put("exercises",allExercises);
            return new ModelAndView(model,"trainer-dashboard.hbs");
        }, new HandlebarsTemplateEngine());

        //new exercise form
        get("/trainer/exercise/new",(request,response)->{
            Map<String,Object>model = new HashMap<>();
            return new ModelAndView(model,"new-exercise.hbs");
        }, new HandlebarsTemplateEngine());

        //process new challenge
        post("/trainer/exercise/new",(request,response)->{
            Map<String,Object>model = new HashMap<>();
            String exerciseName = request.queryParams("exerciseName");
            String exerciseUrl = request.queryParams("exerciseUrl");
            String exerciseDescription = request.queryParams("exerciseDescription");
            int exercisePoints = Integer.parseInt(request.queryParams("exercisePoints"));
            Exercise exercise = new Exercise(exerciseName,exerciseDescription,exercisePoints,exerciseUrl);
            exerciseDao.addExercise(exercise);
            response.redirect("/trainer/dashboard");
            return null;
        }, new HandlebarsTemplateEngine());

        //show exercises
        get("/analyst/exercise",(request,response)->{
            Map<String, Object> model = new HashMap<>();
            List<Exercise> allExercises = exerciseDao.getAllExercises();
            model.put("exercises",allExercises);
            return new ModelAndView(model,"exercise.hbs");
        }, new HandlebarsTemplateEngine());

        //show solution form
        get("/analyst/solution/:id/new",(request,response)->{
            Map<String, Object> model = new HashMap<>();
            int exerciseId = Integer.parseInt(request.params("id"));
            Exercise foundExercise = exerciseDao.getExerciseById(exerciseId);
            model.put("selectedExercise",foundExercise);
            return new ModelAndView(model,"analyst-solution.hbs");
        }, new HandlebarsTemplateEngine());


        //process soln form
        post("/analyst/solution/:id/new",(request,response)->{
            Map<String,Object>model = new HashMap<>();
            String analystEmail = request.queryParams("analystEmail");
            String solution = request.queryParams("solution");
            int exerciseId = Integer.parseInt(request.params("id"));
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String  createdAt = formatter.format(new Timestamp(new Date().getTime()));
            Solution solve = new Solution(analystEmail,solution,exerciseId,createdAt);
            solutionDao.addSolution(solve);
            response.redirect("/analyst/exercise");
            return null;
        }, new HandlebarsTemplateEngine());

        //show exercises
        get("/trainer/solutions/:id/show",(request,response)->{
            Map<String, Object> model = new HashMap<>();
            int exerciseId = Integer.parseInt(request.params("id"));
            List<Solution> solves = solutionDao.getSolutionsByExerciseId(exerciseId);
            Exercise current = exerciseDao.getExerciseById(exerciseId);
            model.put("solutions",solves);
            model.put("exercise",current);
            return new ModelAndView(model,"trainer-solutions.hbs");
        }, new HandlebarsTemplateEngine());


    }
}
