package com.hizliyol.core.exception.handler;

import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.slf4j.LoggerFactory;

import com.hizliyol.core.util.Util;

import ch.qos.logback.classic.Logger;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {
  private ExceptionHandler wrapped;
 
  Logger logger = (Logger) LoggerFactory.getLogger("jsf.exception");
  
  public CustomExceptionHandler(ExceptionHandler wrapped) {
    this.wrapped = wrapped;
  }
 
  @Override
  public ExceptionHandler getWrapped() {
    return wrapped;
  }

  @Override
  public void handle() throws FacesException {
    Iterator iterator = getUnhandledExceptionQueuedEvents().iterator();
    
    while (iterator.hasNext()) {
      ExceptionQueuedEvent event = (ExceptionQueuedEvent) iterator.next();
      ExceptionQueuedEventContext context = (ExceptionQueuedEventContext)event.getSource();
 
      Throwable throwable = context.getException();
      
      FacesContext fc = FacesContext.getCurrentInstance();
      
      try {
          Flash flash = fc.getExternalContext().getFlash();
          
          // Put the exception in the flash scope to be displayed in the error 
          // page if necessary ...
          flash.put("errorDetails", throwable.getMessage());
          logger.error(Util.getStackTrace(throwable.getStackTrace()));


          NavigationHandler navigationHandler = fc.getApplication().getNavigationHandler();
          
          navigationHandler.handleNavigation(fc, null, "error.xhtml?faces-redirect=true");
          
          fc.renderResponse();
      } finally {
          iterator.remove();
      }
    }
    
    // Let the parent handle the rest
    getWrapped().handle();
  }
}