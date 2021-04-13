package store.games.qins.MetierImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.games.qins.dao.TGamesRepository;
import store.games.qins.entities.TGames;
import store.games.qins.iMetier.IGamesMetier;

@Service
public class GamesMetier implements IGamesMetier{

	@Autowired
	TGamesRepository gamesRepository;
	
	@Override
	public TGames findById(Long id) {
		return gamesRepository.findById(id).get();
	}

	@Override
	public List<TGames> allGames() {
		return gamesRepository.findAll();
	}

	@Override
	public void saveGame(TGames games) {
		gamesRepository.save(games);
	}

	@Override
	public void deleteGame(Long idGames) {
		gamesRepository.deleteById(idGames);
	}

}
