package com.cognizant.truyum.controller;

import java.util.List;

import javax.validation.Valid;

import com.cognizant.truyum.exception.MenuItemNotFoundException;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.MenuItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("menuItem")
public class MenuItemController {

    @Autowired
    MenuItemService menuItemService;

    /**
     * 
     * @return list of admin menu item object 
     */
    @GetMapping("/getAdminMenuItem")
    public List<MenuItem> getMenuItemListAdmin() {
        log.info("Inside get admin menu item");
        return menuItemService.getMenuItemListAdmin();
    }

    /**
     * 
     * @return list of customer menu item object 
     */
    @GetMapping("/getCustomerMenuItem")
    public List<MenuItem> getMenuItemListCustomer() {
        log.info("Inside get customer menu item");
        return menuItemService.getMenuItemListCustomer();
    }

    /**
     * 
     * @param menuItem
     * @throws MenuItemNotFoundException
     */
    @PutMapping("/updateMenuItem")
    public ResponseEntity<MenuItem> modifyMenuItem(@Valid @RequestBody MenuItem menuItem) throws MenuItemNotFoundException {
        log.info("Inside modify menu item");
        menuItemService.modifyMenuItem(menuItem);
        return new ResponseEntity<>(menuItem,HttpStatus.OK);
    }

    /**
     * 
     * @param menuItemId
     * @return menu item based on passed id
     * @throws MenuItemNotFoundException
     */
    @GetMapping("/getMenuItem/{menuItemId}")
    public MenuItem getMenuItem(@PathVariable long menuItemId) throws MenuItemNotFoundException {
        log.info("Inside get item by id");
        return menuItemService.getMenuItem(menuItemId);
    }
}
