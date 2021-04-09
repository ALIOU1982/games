package store.games.qins.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="GAMES")
public class TGames implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String cover;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( name="id_User", nullable=true)
	private TUser user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( name="id_Store", nullable=false)
	private TStore store;
	/*
	public TGames (Long id, String title, String cover){
		this.id = id;
		this.title = title;
		this.cover = cover;
	}*/
	
	public TGames (String title, String cover){
		this.title = title;
		this.cover = cover;
	}
	
	public String toString() {
        return  "["+ id + ", " + title + ", " + cover + "]";
    }
	
	public String toStringId() {
        return  "["+ id+ "]";
    }

}
