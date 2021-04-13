package store.games.qins.service.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.*;
import org.springframework.test.context.junit4.SpringRunner;
import store.games.qins.dao.TGamesRepository;
import store.games.qins.dao.TUserRepository;
import store.games.qins.entities.TGames;
import store.games.qins.entities.TStore;
import store.games.qins.entities.TUser;
import store.games.qins.exception.UserException;
import store.games.qins.iMetier.IUserMetier;

//@WebMvcTest(controllers = UsersController.class)
//@DataJpaTest
@RunWith(SpringRunner.class)
public class UserMetierTest {
	
	@Mock
	private IUserMetier iUserMetier;
	
	@Mock
	private TUserRepository tUserRepository;
	
	@Mock
	private TGamesRepository tGamesRepository;
	
	//@Autowired
   // private MockMvc mockMvc;
	
	TUser tuser;
	List<TUser> allUsers;
	
	TGames tGames;
	
	TStore store;
	
	List<TGames> allGames;
	
	/* @Test
	 public void testGetAllUsers() throws Exception {
	        mockMvc.perform(get("/api/users"))
	            .andExpect(status().isOk());
	 }*/
	 
	 	@SuppressWarnings("deprecation")
		@Before
	    public void setup() {
	 		tuser = new TUser(15L, "TestEmail", "TestName");	 		
	 		store = new TStore(1L, "store");	 		
	 		tGames = new TGames(1L, "title", "cover", store);	 		
			
	 		allUsers = Arrays.asList(tuser);
	 		allGames = Arrays.asList(tGames);
	 		
	 		initMocks(this);
	 		
	 		tUserRepository = mock(TUserRepository.class);
	 		tGamesRepository = mock(TGamesRepository.class);
		 	iUserMetier = mock(IUserMetier.class);

	    	when(tUserRepository.findAll()).thenReturn(allUsers);
	    	when(tUserRepository.findById(anyLong()).get()).thenReturn(tuser);
	    	when(tUserRepository.save(any())).thenReturn(tuser);
	    	when(tUserRepository.listeGamesByUser(any())).thenReturn(allGames);
	    	when(tUserRepository.save(any())).thenReturn(tuser);
	    	when(tGamesRepository.save(any())).thenReturn(tGames);	
	    	when(tUserRepository.listeFriendsOfUser(anyLong())).thenReturn(allUsers);
	    }
	 
		@Test
	    public void testFindAllUsers() { 
			Collection<TUser> users = iUserMetier.allUsers();
	    	assertNotNull(users);		    
	    	assertEquals(users.size(), allUsers.size());
	    	verify(tUserRepository.findAll());
	    }
		
		@Test
		public void testFindById(){
			TUser user = iUserMetier.findById(anyLong());
			assertNotNull(user);	
			assertEquals(user, tuser);
			verify(tUserRepository.findById(anyLong()).get());
		}
		
		@Test
		public void testSaveUsers(){			
			iUserMetier.saveUsers(tuser);
			assertNotNull(tuser.getId());
			verify(tUserRepository.save(any()));			
		}
		
		@Test
		public void testUserbyGames(){
			List<TGames> listGames = iUserMetier.userbyGames(15L);
			assertNotNull(listGames);
			assertEquals(listGames.size(), allGames.size());
			verify(tUserRepository.listeGamesByUser(any()));
		}
		
		@Test
		public void testSaveGamesOfUser(){
			try {
				iUserMetier.saveGamesOfUser(anyLong(), tGames);
				assertNotNull(tGames.getUser());
				verify(tUserRepository.save(any()));
				verify(tGamesRepository.save(any()));
			} catch (UserException e) {
				e.printStackTrace();
				e.getCause();
				e.getMessage();
			}			
		}
		
		@Test
		public void testSaveFriendsOfUser(){
			try {
				iUserMetier.saveFriendsOfUser(anyLong(), anyLong());
				verify(tUserRepository.save(any()));
			} catch (UserException e) {
				e.printStackTrace();
				e.getCause();
				e.getMessage();
			}
		}
		
		@Test
		public void testDeleteUser(){
			iUserMetier.deleteUser(anyLong());	
		}
		
		@Test
		public void testDeleteGamesByUser(){
			try {
				iUserMetier.deleteGamesByUser(anyLong(), anyLong());
				verify(tUserRepository.save(any()));
			} catch (UserException e) {
				e.getCause();
				e.getMessage();
				e.printStackTrace();
			}			
		}
		
		@Test
		public void testAllFriendsByUser(){
			List<TUser> allFriend = iUserMetier.allFriendsByUser(anyLong());
			assertNotNull(allFriend);
			assertEquals(allFriend.size(), allUsers.size());
			verify(tUserRepository.listeFriendsOfUser(anyLong()));
		}
		
		@Test
		public void testDeleteFiendsOfUser(){
			try {
				iUserMetier.deleteFiendsOfUser(anyLong(), anyLong());
				verify(tUserRepository.listeFriendsOfUser(anyLong()));
				verify(tUserRepository.findById(anyLong()).get());
				verify(tUserRepository.save(any()));
			} catch (UserException e) {
				e.printStackTrace();
				e.getCause();
				e.getMessage();
			}
		}
					 
}
