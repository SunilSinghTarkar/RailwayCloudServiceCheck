package com.masai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractUser {

	private String name;

	@Column(unique = true)
	private String userName;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	private String address;
	
	@Column(unique = true)
	private String mobileNumber;

	private String role;

	private Boolean isDeleted = false;
}
