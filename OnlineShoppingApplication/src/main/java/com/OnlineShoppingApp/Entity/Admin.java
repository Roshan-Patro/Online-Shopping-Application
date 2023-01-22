package com.OnlineShoppingApp.Entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer adminLoginId;
    
    @Size(min = 4, message = "admin company id must be of atleast 4 character length.")
    private String adminCompanyId;
    
    @Size(min = 5, message = "admin name must be of atleast 5 character length. Please, write your full name.")
    private String adminName;
}
