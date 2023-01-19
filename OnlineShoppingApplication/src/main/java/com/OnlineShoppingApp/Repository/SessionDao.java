package com.OnlineShoppingApp.Repository;

import com.OnlineShoppingApp.Entity.CurrentSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SessionDao extends JpaRepository<CurrentSession,Integer> {
    public CurrentSession findByUuid(String uuid);


}
