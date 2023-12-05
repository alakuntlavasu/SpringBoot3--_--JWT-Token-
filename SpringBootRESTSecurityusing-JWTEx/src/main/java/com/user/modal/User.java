package com.user.modal;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="usrtb")
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String username;
	private String password;
	
	@ElementCollection
	@CollectionTable(name="rolestab",joinColumns = @JoinColumn(name="id"))
	@Column(name="role")
	private Set<String> roles;

}
