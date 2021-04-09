 package store.games.qins.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@NoArgsConstructor
@Data
@Table(name="USERS")
public class TUser implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String userName;
	
	private String email;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	  name = "FRIEND_USER", 
	  joinColumns = @JoinColumn(name = "user_id"), 
	  inverseJoinColumns = @JoinColumn(name = "user_friend_id"))
	List<TUser> friend = new ArrayList<TUser>();
	
	@OneToMany(mappedBy="user", targetEntity=TGames.class,  fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
	List<TGames> games = new ArrayList<>();
	
	public TUser(Long id, String userName, String email){
		this.id = id;
		this.userName = userName;
		this.email = email;
	}
	
	public TUser(String userName, String email){
		this.userName = userName;
		this.email = email;
	}
	
	public String toString() {
        return  "["+ id + ", " + userName + ", " + email +", "+((TUser) friend).toStringId()+ ", "+((TGames) games).toStringId()+ "]";
    }
	
	public String toStringId(){
		return "["+id+"]";
	}

	public List<TUser> getFriend(){
		return this.friend;
	}
}
