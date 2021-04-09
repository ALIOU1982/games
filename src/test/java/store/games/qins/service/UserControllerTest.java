package store.games.qins.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import store.games.qins.Controller.UsersController;
import store.games.qins.dao.TUserRepository;
import store.games.qins.entities.TUser;
import store.games.qins.iMetier.IUserMetier;

//@WebMvcTest(controllers = UsersController.class)
public class UserControllerTest {
	
	
	@Autowired
	private IUserMetier iUserMetier;
	
	@Autowired
	private TUserRepository tUserRepository;
	
	@Autowired
    private MockMvc mockMvc;
	
	/* @Test
	 public void testGetAllUsers() throws Exception {
	        mockMvc.perform(get("/api/users"))
	            .andExpect(status().isOk());
	 }*/
	 
	 	@Before
	    public void setup() {
		 	//tUserRepository = Mockito.mock(TUserRepository.class);
		 	//iUserMetier = Mockito.mock(IUserMetier.class);
	    }
	 
		@Test
	    public void testFindAllUsers() throws Exception {
	    	TUser tuser = new TUser(15L, "TestEmail", "TestName");
	    	List<TUser> allUsers = Arrays.asList(tuser);         
	    	//Mockito.when(tUserRepository.findAll()).thenReturn(allUsers);
	    	/*Collection<TUser> users = iUserMetier.allUsers();
	    	assertNotNull(users);
	    
	    	assertEquals(users, allUsers);
	    	assertEquals(users.size(), allUsers.size());*/
	    	//verify(tUserRepository.findAll());
	    }
	 
}
