package controllers;


import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.Agent;
import models.Arbitre;
import models.Billet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Path("billet")
public class BilletController {


    //check/idAgent?idAgent=${e['idAgent']}&qrcode=${e['qrcode']}
    @GET
    @Path("check")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response check(@QueryParam("idAgent") Long idAgent, @QueryParam("qrcode") String qrcode) {
        //HashMap params = new HashMap();
        //params.put("telephone",telephone);
        //params.put("mdp",mdp);
        //
        Billet billet = (Billet) Billet.find("qrCode",qrcode).firstResult();
        if(billet != null){
            //
            System.out.println("Billet: "+billet.qrCode);
            //
            billet.checker = billet.checker == null ? false : true;
            if(billet.checker){
                return Response.status(200).entity("Ce billet a déjà été scanné !\n"+billet.typePlace).build();
            }else{
                billet.checker = true;
                billet.idAgent = idAgent;
                return Response.status(200).entity("Bienvenu et bon match à vous! \n"+billet.typePlace).build();
            }
        }else{
            return Response.status(404).entity("Ce billet n'est enregistré dans la base de donné.").build();
        }
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response allBillet() {
        //HashMap params = new HashMap();
        //params.put("telephone",telephone);
        //params.put("mdp",mdp);
        //
        List<Billet> billets = Billet.listAll();
          return Response.ok(billets).build();
    }

    @POST
    @Path("all")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveAllBillet() {
        //HashMap params = new HashMap();
        List<Billet> billets = new LinkedList<>();
        for(int i = 0; i < 10; i++){
            Billet billet = new Billet();
            billet.qrCode =getCode();
            billet.typePlace = "Pourtour";
            billet.idMatch = 0L;
            billet.persist();
            billets.add(billet);
        }
        //params.put("telephone",telephone);
        //params.put("mdp",mdp);
        //

        return Response.ok(billets).build();
    }

    private String getCode(){
        String qrcode = "";
        Random random = new Random();
        int v1 = random.ints(0, 9)
                .findFirst()
                .getAsInt();
        int v2 = random.ints(0, 9)
                .findFirst()
                .getAsInt();
        int v3 = random.ints(0, 9)
                .findFirst()
                .getAsInt();
        int v4 = random.ints(0, 9)
                .findFirst()
                .getAsInt();
        int v5 = random.ints(0, 9)
                .findFirst()
                .getAsInt();
        int v6 = random.ints(0, 9)
                .findFirst()
                .getAsInt();
        int v7 = random.ints(0, 9)
                .findFirst()
                .getAsInt();
        int v8 = random.ints(0, 9)
                .findFirst()
                .getAsInt();
        int v9 = random.ints(0, 9)
                .findFirst()
                .getAsInt();
        int v10 = random.ints(0, 9)
                .findFirst()
                .getAsInt();
        int v11 = random.ints(0, 9)
                .findFirst()
                .getAsInt();

        return qrcode+v1+v2+v3+v4+v5+v6+v7+v8+v9+v10+v11;
    }

}
