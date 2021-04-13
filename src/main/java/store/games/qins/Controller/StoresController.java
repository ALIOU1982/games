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
import store.games.qins.iMetier.IGamesMetier;

@RestController
@RequestMapping("api/stores/*")
public class StoresController {
	
	@Autowired
	IGamesMetier iGamesMetier;
	
	@GetMapping(value = "/games", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TGames> allGames(){
		return iGamesMetier.allGames();		
	}
	
	@GetMapping(value = "/games/{id}", produces = MediaType.APPLICATION_JSON_VALUE)	
	public TGames findById(@PathVariable Long id){
		return iGamesMetier.findById(id);
	}
	
	@DeleteMapping(value = "/games/{idGames}", produces = MediaType.APPLICATION_JSON_VALUE)	
	public void deleteGame(@PathVariable Long idGames){
		iGamesMetier.deleteGame(idGames);
	}
	
	@PostMapping(value="/games", produces = MediaType.ALL_VALUE)
	@ResponseBody
	public void saveGame(@RequestBody TGames game){
		iGamesMetier.saveGame(game); 
	}
	

}
