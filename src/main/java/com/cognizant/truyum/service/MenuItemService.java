package com.cognizant.truyum.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.cognizant.truyum.dao.MenuItemDaoImpl;
import com.cognizant.truyum.exception.MenuItemNotFoundException;
import com.cognizant.truyum.model.MenuItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MenuItemService {
    @Autowired
    MenuItemDaoImpl menuItemDaoImpl;

    /**
     * 
     * @return admin menu item list
     */
    @Transactional
    public List<MenuItem> getMenuItemListAdmin() {
        return menuItemDaoImpl.getMenuItemListAdmin();
    }

    /**
     * 
     * @return customer menu item list
     */
    @Transactional
    public List<MenuItem> getMenuItemListCustomer() {
        return menuItemDaoImpl.getMenuItemListCustomer();
    }

    /**
     * 
     * @param menuItem
     * @throws MenuItemNotFoundException on menu item not found
     */
    @Transactional
    public void modifyMenuItem(MenuItem menuItem) throws MenuItemNotFoundException {
        long menuItemId = menuItem.getId();
        Optional<MenuItem> opMenuItem = menuItemDaoImpl.getMenuItem(menuItemId);

        if(opMenuItem.isPresent()){
            menuItemDaoImpl.saveMenuItem(menuItem);
        }else{
            throw new MenuItemNotFoundException("Menu Item not found");
        }
        
    }

    /**
     * 
     * @param menuItemId
     * @return menu item based on id
     * @throws MenuItemNotFoundException
     */
    @Transactional
    public MenuItem getMenuItem(long menuItemId) throws MenuItemNotFoundException {
        Optional<MenuItem> opMenuItem = menuItemDaoImpl.getMenuItem(menuItemId);
        if(opMenuItem.isPresent()){
            return opMenuItem.get();
        }
        throw new MenuItemNotFoundException("Menu Item not found");
        
    }
}
