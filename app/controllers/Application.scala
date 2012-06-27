package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._
import java.sql.Date
import com.codahale.jerkson.Json

object Application extends Controller with Secured {

  def index = Action { implicit request =>
     Redirect(routes.Tours.tours)
   }

  def listLocationsByTown_Id(town_id:String) = Action {
    val locations = Location.findByTown_id(town_id.toLong);

    val json = Json.generate(locations)

    Ok(json).as("application/json")
  }

  def listToursByTown_Id(town_id:String) = Action {
    val tours = Tour.findByTown_id(town_id.toLong)
    val json ="{\"aaData\": "+ Json.generate(tours) + "}"
    Ok(json).as("application/json")
  }
  
    def listToursByDepLocation_Id(location_id:String) = Action {
    val tours = Tour.findByDepLocation_id(location_id.toLong)
    val json ="{\"aaData\": "+ Json.generate(tours) + "}"
    Ok(json).as("application/json")
  }
 
        def listToursByArrLocation_Id(locationDep_id:String,locationArr_id:String) = Action {
    val tours = Tour.findByArrLocation_id(locationDep_id.toLong,locationArr_id.toLong)
    
    val json ="{\"aaData\": "+ Json.generate(tours) + "}"
    Ok(json).as("application/json")
  }

  def listAllTours() = Action {
    val tours = Tour.findAll();
    val json ="{\"aaData\": "+ Json.generate(tours) + "}"
    Ok(json).as("application/json")
  }

  def authentificateByApp(email:String, hashedPassword:String) = Action {
    val user = User.authenticateWithHashedPassword(email, hashedPassword)
    val json ="{\"aaData\": "+ Json.generate(user) + "}"
    Ok(json).as("application/json")
  }


}


