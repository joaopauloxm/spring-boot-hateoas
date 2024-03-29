package br.senaigo.mobile.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.hateoas.ResourceSupport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "people")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class People extends ResourceSupport {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long idPeople;

	@Column
	private String name;

	@Column
	private String surName;

	@Column
	private String email;
	
	@OneToMany(fetch=FetchType.LAZY)
	private List<Order> orders = new ArrayList<Order>();
}
