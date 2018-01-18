package com.hizliyol.core.exception;

/**
 * Created by bilge_gilleez on 18.01.2018.
 */
public class ServiceException extends RuntimeException {

    public ServiceException(Throwable e){
        super(e);
    }
}
