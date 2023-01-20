package com.OnlineShoppingApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminRegisterDTO {
    private String adminCompanyId;
    private String adminName;
    private String password;
}
