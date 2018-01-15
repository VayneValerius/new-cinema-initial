package com.qa.cinema.buisness.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.*;

import com.qa.cinema.model.Movie;

@Transactional(Transactional.TxType.SUPPORTS)
public class MovieServiceDBImpl {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	public Movie findMovie(Long id) {
        return manager.find(Movie.class, id);
    }
	
	public List<Movie> findAllMovies() {
        TypedQuery<Movie> query = manager.createQuery("SELECT m FROM Movie m ORDER BY m.title", Movie.class);
        return query.getResultList();
    }

	
	@Transactional(Transactional.TxType.REQUIRED) 
	public String createMovie(Movie movie) {
		manager.persist(movie);
		return "Movie Added";
	}
	
	@Transactional(Transactional.TxType.REQUIRED) 
	public String deleteMovie(Long id) {
		manager.remove(id);
		return "{\"message\": \"Movie sucessfully deleted\"}";
	}
	
	@Transactional(Transactional.TxType.REQUIRED) 
	public String updateMovie(Movie originalMovie, Movie newMovie) {
		newMovie = manager.merge(originalMovie);
		return "{\"message\": \"Movie sucessfully Updated\"}";
	}
	



}
