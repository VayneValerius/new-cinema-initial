package com.qa.cinema.intergration;

import java.util.List;

import javax.inject.Inject;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import com.qa.cinema.buisness.repository.MovieServiceDBImpl;
import com.qa.cinema.model.Movie;
import com.qa.cinema.util.JSONUtil;

@Path("/cinema")
public class MovieEndPoint {
	
	@Inject 
	MovieServiceDBImpl service;
	
	@Inject
	JSONUtil jsonUtil;
	
	@Path("/json")
	@GET
//	@Produces({"json"})
	public String getAllMovies(){
		List<Movie> movies = service.findAllMovies();
		return jsonUtil.getJSONForObject(movies);
	}

	@Path("/json/{id}")
	@GET
//	@Produces({"json"})
	public String getMovie(@PathParam("id") Long id){
		return jsonUtil.getJSONForObject(service.findMovie(id));
	}
	
	@Path("/json")
	@POST
//	@Produces({"json"})
	public String createMovie(String movie){
		Movie newMovie = jsonUtil.getObjectForJSON(movie, Movie.class);
		return service.createMovie(newMovie);
	
	}
	
	@Path("/json/{id}")
	@DELETE
//	@Produces({"json"})
	public String deleteMovie(@PathParam("id") Long id){
		return service.deleteMovie(id);
	}
	
	@Path("/json")
	@PUT
//	@Produces({"json"})
	public String updateMovie(Movie original, Movie newMovie){
		return service.updateMovie(original, newMovie);
	}
	
	
	

}
