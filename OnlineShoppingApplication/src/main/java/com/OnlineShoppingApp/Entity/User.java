package com.OnlineShoppingApp.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;

import com.OnlineShoppingApp.Enum.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
	@Id
	private Integer userId;
	
	private String compIdEmail;
	
//	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{6,15}$",message = "password length must contain atleast 6 characters of atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
}
