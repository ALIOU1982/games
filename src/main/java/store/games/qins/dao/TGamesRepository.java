package store.games.qins.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import store.games.qins.entities.TGames;
import store.games.qins.entities.TUser;

@RepositoryRestResource
public interface TGamesRepository extends JpaRepository<TGames, Long> {
	
	@Query("Select games from TGames games where games.user = ?1" )
	List<TGames> listeGamesOfUser(TUser tUser);

}
