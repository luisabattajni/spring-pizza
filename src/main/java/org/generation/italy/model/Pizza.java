package org.generation.italy.model;

import java.util.ArrayList;
import java.util.List;

//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pizze")

public class Pizza {

	@Id
	//@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	@NotNull (message="il campo nome non può essere vuoto")
	private String name;
	@Lob
	private String synopsis;
	@NotNull (message="il campo prezzo non può essere vuoto")
	private String price; //integer? double? string per $ etc?

	@ManyToMany
	private List<Ingrediente> ingredienti;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	public String ingredientiToString() {
		List<String> ingredientiString = new ArrayList<>();
		for(Ingrediente i : ingredienti) {
			ingredientiString.add(i.getName());
		}
		return String.join(", ", ingredientiString);
	}
	
	
	

}
