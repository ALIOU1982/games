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
	TGamesRepository gameRepository;
	
	@Override
	public TGames findById(Long id) {
		return gameRepository.getOne(id);
	}

	@Override
	public List<TGames> allGames() {
		return gameRepository.findAll();
	}

	@Override
	public void saveGame(TGames games) {
		gameRepository.save(games);
	}

	@Override
	public void deleteGame(Long idGames) {
		gameRepository.deleteById(idGames);
	}

}
