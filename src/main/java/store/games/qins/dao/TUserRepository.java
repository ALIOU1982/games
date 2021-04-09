package store.games.qins.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import store.games.qins.entities.TGames;
import store.games.qins.entities.TUser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@RepositoryRestResource
public interface TUserRepository extends JpaRepository<TUser, Long> {
	
	@Query("Select user.friend from TUser user where user.id = ?1" )
	List<TUser> listeFriendsOfUser(Long idUser);
	
	@Query("Select games from TGames games where games.user = ?1" )
	List<TGames> listeGamesByUser(TUser tUser);
}
