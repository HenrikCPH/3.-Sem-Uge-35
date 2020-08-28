package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Employee;
import facades.EmployeeFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("employee_resource")
public class EmployeeResource {
    
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    //NOTE: Change Persistence unit name according to your setup
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    EmployeeFacade facade =  EmployeeFacade.getEmployeeFacade(emf);
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }
    
    @GET
    @Path("hp")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getHighestPaid() {
        return Response.ok().entity(gson.toJson((facade.getHighestSalary()))).build();
    }
    
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllEmployees() {
        return Response.ok().entity(gson.toJson((facade.getAllEmployee()))).build();
    }
    
    @Path("/id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmplyeeById(@PathParam("id")int id) {
         return Response.ok().entity(gson.toJson(EmployeeFacade.findEmployee(id))).build();
    }
    
    @Path("/name/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getName(@PathParam("name")String name) {
         return Response.ok().entity(gson.toJson(facade.findEmployeeName(name))).build();
    }
     
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Employee entity) {
        throw new UnsupportedOperationException();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Employee entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}
