package com.cognizant.truyum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Menu item not found")
public class MenuItemNotFoundException extends Exception {
    /**
     * Exception if menu item not found in the collection
     * @param message
     */
    public MenuItemNotFoundException(String message){
        super(message);
    }
}
