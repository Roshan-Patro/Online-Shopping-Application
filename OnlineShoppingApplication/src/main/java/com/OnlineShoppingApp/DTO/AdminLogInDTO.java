package com.OnlineShoppingApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminLogInDTO {
	private String adminCompanyId;
	private String password;
}