package store.games.qins.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@SuppressWarnings("serial")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Table(name="STORE")
public class TStore implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nomStore;
	
	@OneToMany(targetEntity=TGames.class, mappedBy="store",  cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	Set<TGames> games = new HashSet<TGames>();
	
	public TStore(Long id, String nomStore){
		this.id = id;
		this.nomStore = nomStore;
	}

}
