package com.OnlineShoppingApp.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.OnlineShoppingApp.Enum.Role;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CurrentSession {

    @Id
    private Integer id;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    private String uuid;
    private LocalDateTime localDateTime;

}
