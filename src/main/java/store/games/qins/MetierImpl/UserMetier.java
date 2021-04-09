package store.games.qins.MetierImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.games.qins.dao.TGamesRepository;
import store.games.qins.dao.TUserRepository;
import store.games.qins.entities.TGames;
import store.games.qins.entities.TUser;
import store.games.qins.exception.UserException;
import store.games.qins.iMetier.IUserMetier;

@Service
public class UserMetier implements IUserMetier {
	
	@Autowired
	TUserRepository tUserRepository;
	
	@Autowired
	TGamesRepository tGamesRepository;
	

	@Override
	//Tout marche
	public List<TUser> allUsers() {
		return tUserRepository.findAll();
	}

	@Override
	//Tout marche
	public void saveUsers(TUser tUser) {
		tUserRepository.save(tUser);
	}

	@Override
	//
	public TUser findById(Long id) {
		return tUserRepository.findById(id).get();
	}

	@Override
	//Tout marche
	public List<TGames> userbyGames(Long idUser) {
		//tUserRepository.listeGamesByUser(tUser);
		return tUserRepository.listeGamesByUser(tUserRepository.findById(idUser).get());
	}

	@Override
	public void deleteGamesByUser(Long idUser, Long idGames) throws UserException {
		TUser tUser = tUserRepository.findById(idUser).get();
		if(!tUserRepository.listeGamesByUser(tUser).contains(tGamesRepository.findById(idGames)))
			throw new UserException("Suppression impossible, l'utilisateur n'a pas le jeu");
		List<TGames> lGames = new ArrayList<TGames>();
		tUserRepository.listeGamesByUser(tUser).forEach(item->{
			if(!item.getId().equals(idGames)){
				lGames.add(item);
			}
		});
		tUser.setGames(lGames);
		tUserRepository.save(tUser);
	}

	@Override
	//Tout marche
	public void deleteUser(Long idUser) {
		List<TUser> friend = tUserRepository.listeFriendsOfUser(idUser);
		System.out.println("ICI 1");
		friend.forEach(item->{
			try {
				System.out.println("ICI 2");
				deleteFiendsOfUser(idUser, item.getId());
				System.out.println("ICI 3");
			} catch (UserException e) {
				e.getCause();
				e.getMessage();
				e.printStackTrace();
			}
		});
		tUserRepository.deleteById(idUser);
	}

	@Override
	//Tout marche
	public List<TUser> allFriendsByUser(Long idUser) {
		return tUserRepository.listeFriendsOfUser(idUser);
	}

	@Override
	// Tout marche
	public void saveFriendsOfUser(Long idUser, Long idFriends) throws UserException {
		if(idUser.equals(idFriends))
			throw new UserException("Un user ne peut pas être son propre ami");
		TUser tUser = tUserRepository.findById(idUser).get();
		TUser tFriends = tUserRepository.findById(idFriends).get();
		List<TUser> lUser = new ArrayList<TUser>();
		lUser.add(tFriends);
		tUser.setFriend(lUser);
		tUserRepository.save(tUser);
		lUser = new ArrayList<TUser>();
		lUser.add(tUser);
		tFriends.setFriend(lUser);
		tUserRepository.save(tFriends);
	}

	@Override
	//Tout marche
	public void deleteFiendsOfUser(Long idUser, Long idFriends) throws UserException {
		/*if(idUser.equals(idFriends)){
			throw new UserException("Un user ne peut pas être son propre ami");
		}*/
		//System.out.println("ICI 21");
		TUser tUser = tUserRepository.findById(idUser).get();
		TUser tFriends = tUserRepository.findById(idFriends).get();
		List<TUser> lFriends = tUserRepository.listeFriendsOfUser(idUser);
		List<TUser> lUser = new ArrayList<TUser>();
		//lFriends.remove(tUserRepository.findById(idFriends).get());
		lFriends.forEach(item->{
			if(!item.getId().equals(idFriends))
				lUser.add(item);
		});
		tUser.setFriend(lUser);
		tUserRepository.save(tUser);
		lFriends = tUserRepository.listeFriendsOfUser(idFriends);
		lUser.remove(lUser);
		lFriends.forEach(item->{
			if(!item.getId().equals(idUser))
				lUser.add(item);
		});
		lFriends.remove(tUserRepository.findById(idUser).get());
		tFriends.setFriend(lUser);
		tUserRepository.save(tFriends);
	}

	@Override
	//Tout marche
	public void saveGamesOfUser(Long idUser, TGames tGames) throws UserException {
		TUser tUser = tUserRepository.findById(idUser).get();
		//if(tUser.getGames().contains(tGames))
			//throw new UserException("L'utilisateur a déjà ce jeu");
		tGamesRepository.save(tGames);
		List<TGames> lGames = new ArrayList<TGames>();
		lGames.add(tGames);
		tUser.setGames(lGames);
		tUserRepository.save(tUser);
		tGames.setUser(tUser);
		tGamesRepository.save(tGames);
		
	}
}
