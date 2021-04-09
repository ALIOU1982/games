package store.games.qins.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import store.games.qins.entities.TGames;
import store.games.qins.entities.TUser;
import store.games.qins.exception.UserException;
import store.games.qins.iMetier.IUserMetier;

@RestController
@RequestMapping("api/*")
public class UsersController {
	
	@Autowired
	IUserMetier iUserMetier;
	
	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TUser> allUsers(){
		return iUserMetier.allUsers();
	}
	
	@PostMapping(value = "/users", produces = MediaType.ALL_VALUE)
	@ResponseBody
	public void saveUsers(@RequestBody TUser tUser){
		iUserMetier.saveUsers(tUser);
	}
	
	@GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TUser findByIdUser(@PathVariable Long id){
		return iUserMetier.findById(id);
	}
	
	@GetMapping(value = "/users/{idUser}/games", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TGames> userbyGames(@PathVariable Long idUser){
		return iUserMetier.userbyGames(idUser);
	}
	
	@PostMapping(value = "/users/{idUser}/games", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody // ** A revoir
	public void saveGamesOfUser(@PathVariable Long idUser, @RequestBody TGames tGames){
		try {
			iUserMetier.saveGamesOfUser(idUser, tGames);
		} catch (UserException e) {
			e.getMessage();
			e.getCause();
			e.printStackTrace();
		}
	}
	
	@DeleteMapping(value="/users/{idUser}/{idGames}")
	public void deleteGamesByUser(@PathVariable Long idUser, Long idGames){
		try {
			iUserMetier.deleteGamesByUser(idUser, idGames);
		} catch (UserException e) {
			e.getMessage();
			e.getCause();
			e.printStackTrace();
		}
	}
	
	@DeleteMapping(value="/users/{idUser}")
	public void deleteUser(@PathVariable Long idUser){
		iUserMetier.deleteUser(idUser);
	}
	
	@GetMapping(value = "/users/{idUser}/friends", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TUser> allFriendsByUser(@PathVariable Long idUser){
		return iUserMetier.allFriendsByUser(idUser);
	}
	
	@PostMapping(value = "/users/{idUser}/{idFriends}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void saveFriendsOfUser(@PathVariable Long idUser, @PathVariable Long idFriends){
			try {
				iUserMetier.saveFriendsOfUser(idUser, idFriends);
			} catch (UserException e) {
				e.getMessage();
				e.getCause();
				e.printStackTrace();
			}
	}
	
	@DeleteMapping(value="/users/{idUser}/friends/{idFriends}")
	public void deleteFiendsOfUser(@PathVariable Long idUser,  @PathVariable Long idFriends){
		try {
			iUserMetier.deleteFiendsOfUser(idUser, idFriends);
		} catch (UserException e) {
			e.getMessage();
			e.getCause();
			e.printStackTrace();
		}
	}	
}
