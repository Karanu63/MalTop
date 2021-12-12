import static spark.Spark.*;
public class App {
    public static void main(String[] args) {
        get("/",(request, response) -> "<h1>Site under construction</h1>");
    }
}
