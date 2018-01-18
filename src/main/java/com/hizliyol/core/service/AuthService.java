package com.hizliyol.core.service;

import com.hizliyol.core.data.AuthorizationDataDao;
import com.hizliyol.core.entity.Auhorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bilge_gilleez on 18.01.2018.
 */
@Service
public class AuthService {

    @Autowired
    private AuthorizationDataDao authorizationDataDao;

    public void insert(Auhorization auhorization){
        authorizationDataDao.save(auhorization);
    }

    public List<Auhorization> getByName(String name){
        return authorizationDataDao.findByAuthName(name);
    }

    public void delete(Auhorization auth){
        authorizationDataDao.delete(auth);
    }

    public List<Auhorization> findAll(){
        List<Auhorization> authList = new ArrayList<>();
        authorizationDataDao.findAll().forEach(auhorization -> authList.add(auhorization));
        return authList;
    }
}
