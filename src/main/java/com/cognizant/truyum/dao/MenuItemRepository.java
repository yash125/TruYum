package com.cognizant.truyum.dao;

import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {

    /**
     * 
     * @param active
     * @param dateOfLaunch
     * @return customer list from the menuitem collection
     * 
     */

    List<MenuItem> findByActiveAndDateOfLaunchBefore(Boolean active, Date dateOfLaunch);
}
