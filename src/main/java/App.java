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

        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
         port(port);
//displaying the homepage
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
           /* *model.put("stylists", Stylist.all());*/
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    //adding a new client to a stylist

        get("stylists/:id/clients/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("template", "templates/stylist-clients-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    //linking the client ID to the stylist ID

        get("/stylists/:stylist_id/clients/:id ", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist= Stylist.find(Integer.parseInt(request.params(":stylistId")));
            Client client = Client.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("template", "templates/clients.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    //getting the client ID

        get("/clients/:id", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            Client client = Client.find(Integer.parseInt(request.params(":id")));
            model.put("client", client);
            model.put("template", "templates/client.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    //adding a new stylist

        get("/stylists/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/stylist-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    //getting the stylists

        get("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylist.all());
            model.put("template", "templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    //retrieving the stylist Id

        get("/stylists/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    //updating the client details

        post("/clients", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Client> clients = request.session().attribute("clients");
            Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));

            String description = request.queryParams("description");
            Client newClient = new Client(description, stylist.getId());
            clients.add(newClient);

            model.put("template", "templates/success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    //updating the stylist details

        post("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            Stylist newStylist = new Stylist(name);
            newStylist.save();
            model.put("template", "templates/stylist-success.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
       post("/stylists/:id/clients", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            /* Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));*/

            String description = request.queryParams("description");
            int stylistId = Integer.parseInt(request.queryParams(":id"));
            Client newClient = new Client(description, stylistId);

            newClient.save();
          /*  response.redirect("/stylists/" + stylistId);
            model.put("stylist", stylist);
            model.put("template", "templates/stylist-client-success.vtl");*/
           response.redirect("/stylists/" + stylistId);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

       //adding a client to a specific stylist
        post("/stylists/:stylist_id/clients/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Client client = Client.find(Integer.parseInt(request.params("id")));
            String description = request.queryParams("description");
            Stylist stylist = Stylist.find(client.getStylistId());
            client.update(description);
            String url = String.format("/stylists/%d/clients/%d", stylist.getId(), client.getId());
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //deleting the client or stylist
        post("/stylists/:stylist_id/clients/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Client client = Client.find(Integer.parseInt(request.params("id")));
            String description = request.queryParams("description");
            Stylist stylist = Stylist.find(client.getStylistId());
            client.delete();
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        post("/stylists/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params("id")));
            stylist.delete();
            model.put("stylist", Stylist.all());
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    }
}
