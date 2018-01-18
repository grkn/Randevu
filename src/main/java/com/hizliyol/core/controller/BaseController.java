package com.hizliyol.core.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    public void showInfoMessage(String message){
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"",getMessage(message)));
    }

}
