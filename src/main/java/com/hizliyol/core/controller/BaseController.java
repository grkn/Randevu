package com.hizliyol.core.controller;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by bilge_gilleez on 16.01.2018.
 */
public abstract class BaseController {

    public String getMessage(String message){
        String userDefined = ResourceBundle.getBundle("message",new Locale("tr")).getString(message);
        return userDefined;
    }

}
