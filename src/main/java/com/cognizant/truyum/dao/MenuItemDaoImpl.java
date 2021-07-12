package com.cognizant.truyum.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.cognizant.truyum.model.MenuItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuItemDaoImpl implements MenuItemDao {


    @Autowired
    MenuItemRepository menuItemRepository;

    /**
    * @return All the items fetched by the menu item repository
    */
    @Override
    public List<MenuItem> getMenuItemListAdmin() {
        return menuItemRepository.findAll();
    }

    /**
     * @return customer list from the menu item repository
    */
    @Override
    public List<MenuItem> getMenuItemListCustomer() {
        return menuItemRepository.findByActiveAndDateOfLaunchBefore(true, new Date(Calendar.getInstance().getTime().getTime()));
    }

    /**
     * @param menuItem
     * @return void
     */
    @Override
    public void saveMenuItem(MenuItem menuItem) {
        menuItemRepository.delete(menuItem);
        menuItemRepository.save(menuItem);
    }

    /**
     * @param menuItemId
     * @return Menu item from the repository
     */
    @Override
    public Optional<MenuItem> getMenuItem(long menuItemId) {
        return menuItemRepository.findById(menuItemId);
    } 
}
