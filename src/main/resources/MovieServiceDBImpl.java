//import javax.persistence.EntityManager;
import javax.persistence.EntityManager;
import javax.transaction.*;

@Transactional(SUPPORTS)
public class MovieServiceDBImpl {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	public Movie findMovie(Long id) {
        return manager.find(Movie.class, id);
    }
	
	public List<Movie> findAllMovies() {
        TypedQuery<Movie> query = em.createQuery("SELECT * FROM Movie", Movie.class);
        return query.getResultList();
    }

	
	@Transactional(REQUIRED)
	public Movie createMovie(Movie movie) {
		manager.persist(movie);
		return movie
	}
	
	@Transactional(REQUIRED)
	public Movie deleteMovie(Long id) {
		manager.remove(id));
	}
	
	@Transactional(REQUIRED)
	public Movie updateMovie(Movie originalMovie, Movie newMovie) {
		newMovie = manager.merge(originalMovie);
	}
	



}
