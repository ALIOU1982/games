package store.games.qins.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import store.games.qins.dao.TGamesRepository;
import store.games.qins.dao.TUserRepository;
import store.games.qins.entities.TGames;
import store.games.qins.iMetier.IGamesMetier;

public class GamesMetierTest {
	@Mock
	private IGamesMetier iGamesMetier;
	
	@Mock
	private TUserRepository tUserRepository;
	
	@Mock
	private TGamesRepository tGamesRepository;
	
	private TGames tGames;
	
	private List<TGames> allGames;
	
	
	@SuppressWarnings("deprecation")
	@Before
    public void setup() {
		
		tGames = new TGames("title", "cover");
		allGames = Arrays.asList(tGames);
		
 		
 		initMocks(this);
 		
 		tUserRepository = mock(TUserRepository.class);
 		tGamesRepository = mock(TGamesRepository.class);
 		
 		when(tGamesRepository.findById(anyLong()).get()).thenReturn(tGames);
 		when(tGamesRepository.findAll()).thenReturn(allGames);
 		when(tGamesRepository.save(any())).thenReturn(tGames);
    }
 
	@Test
	public void testFindById(){
		TGames game = tGamesRepository.findById(anyLong()).get();
		assertNotNull(game);	
		assertEquals(game, tGames);
		verify(tGamesRepository.findById(anyLong()).get());		
	}
	
	@Test
	public void testAllGames(){
		Collection<TGames> games = iGamesMetier.allGames();
    	assertNotNull(games);		    
    	assertEquals(games.size(), allGames.size());
    	verify(tGamesRepository.findAll());
	}
	
	@Test
	public void testSaveGame(){
		iGamesMetier.saveGame(tGames);
		assertNotNull(tGames.getId());
		verify(tGamesRepository.save(any()));		
	}
	
	@Test
	public void testDeleteGame(){
		iGamesMetier.deleteGame(anyLong());
	}
}
