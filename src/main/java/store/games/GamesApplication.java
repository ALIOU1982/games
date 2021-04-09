package store.games;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import store.games.qins.dao.StoreRepository;
import store.games.qins.dao.TGamesRepository;
import store.games.qins.dao.TUserRepository;
import store.games.qins.entities.TGames;
import store.games.qins.entities.TStore;
import store.games.qins.entities.TUser;
import store.games.qins.iMetier.IGamesMetier;
import store.games.qins.iMetier.IUserMetier;

@SpringBootApplication
public class GamesApplication implements CommandLineRunner{

	@Autowired
	StoreRepository storeRepository;
	
	@Autowired
	TGamesRepository tGamesRepository;
	
	@Autowired
	TUserRepository tUserRepository;
	
	@Autowired
	IUserMetier iUserMetier;
	
	@Autowired
	IGamesMetier iGamesMetier;
	
	public static void main(String[] args) {
		SpringApplication.run(GamesApplication.class, args);
	}
	
	public void run(String... args) throws Exception{
		
		TUser tUser1 = new TUser(null, "userName1", "email1");
		TUser tUser2 = new TUser(null, "userName2", "email2");
		TUser tUser3 = new TUser(null, "userName3", "email3");
		
		tUserRepository.save(tUser1);
		tUserRepository.save(tUser2);
		tUserRepository.save(tUser3);
		//=======================================================================
		TStore tStore = new TStore(null, "Store1", null);
		storeRepository.save(tStore);
		//=======================================================================
		TGames tGames1 = new TGames(null, "title1", "cover1", null, tStore);
		TGames tGames2 = new TGames(null, "title2", "cover2", null, tStore);
		TGames tGames3 = new TGames(null, "title3", "cover3", null, tStore);
		TGames tGames4 = new TGames(null, "title4", "cover4", null, tStore);
		TGames tGames5 = new TGames(null, "title5", "cover5", null, tStore);
		TGames tGames6 = new TGames(null, "title6", "cover6", null, tStore);
		tGamesRepository.save(tGames1);
		tGamesRepository.save(tGames2);
		tGamesRepository.save(tGames3);
		//tGamesRepository.save(tGames4);
		tGamesRepository.save(tGames5);
		tGamesRepository.save(tGames6);	
		
		iUserMetier.saveGamesOfUser(tUser1.getId(), tGames4);
		/*iUserMetier.saveGamesOfUser(tUser1.getId(), tGames2);
		iUserMetier.saveGamesOfUser(tUser1.getId(), tGames3);
		iUserMetier.saveGamesOfUser(tUser3.getId(), tGames6);*/
		//System.out.println("Les Jeux de l'utilisateur "+tUser1.getId()+" ---->"+iUserMetier.userbyGames(3L));
		//iUserMetier.deleteGamesByUser(tUser1.getId(), tGames3.getId());
		iUserMetier.saveFriendsOfUser(1L, 2L);
		//iUserMetier.deleteUser(2L);
		System.out.println("L'utilisateur numero 1 est: "+tGamesRepository.listeGamesOfUser(tUser1));
	}
}
