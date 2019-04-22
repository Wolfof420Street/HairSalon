import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("clients", request.session().attribute("clients"));
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        post("/clients", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Client> clients = request.session().attribute("clients");
            if (clients == null){
                clients = new ArrayList<Client>();
                request.session().attribute("clients", clients);
            }

            String description = request.queryParams("description");
            Client newClient = new Client(description);
            clients.add(newClient);

            model.put("template", "templates/success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("client", request.session().attribute("client"));
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}