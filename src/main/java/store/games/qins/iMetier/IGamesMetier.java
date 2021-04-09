package store.games.qins.iMetier;

import java.util.List;
import store.games.qins.entities.TGames;

public interface IGamesMetier {
	
	public TGames findById(Long id);

	public List<TGames> allGames();
	
	public void saveGame(TGames games);
	
	public void deleteGame(Long idGames);
	
}
