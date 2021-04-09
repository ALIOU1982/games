package store.games.qins.iMetier;

import java.util.List;

import store.games.qins.entities.TGames;
import store.games.qins.entities.TUser;
import store.games.qins.exception.UserException;

public interface IUserMetier {
	
	public List<TUser> allUsers();
	
	public void saveUsers(TUser tUser);
	
	public  TUser findById(Long id);
	
	public List<TGames> userbyGames(Long idUser);
	
	public void deleteGamesByUser(Long idUser, Long idGames) throws UserException;
	
	public void deleteUser(Long idUser);
	
	public List<TUser> allFriendsByUser(Long idUser);
	
	public void saveFriendsOfUser(Long idUser, Long idFriends) throws UserException;
	
	public void deleteFiendsOfUser(Long idUser, Long idFriends) throws UserException;
	
	public void saveGamesOfUser(Long idUser, TGames tGames) throws UserException;
}
