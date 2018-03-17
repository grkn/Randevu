package com.hizliyol.core.rest;

import com.hizliyol.core.data.UserDataDao;
import com.hizliyol.core.entity.RandevuUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestService {

    @Autowired
    private UserDataDao userDataDao;

    @RequestMapping(value = "/users/get/allUsers",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<RandevuUser>> getUsers(){
        List<RandevuUser> dto = new ArrayList<>();
        userDataDao.findAll().forEach(randevuUser -> dto.add(randevuUser));
        return new ResponseEntity<List<RandevuUser>>(dto, HttpStatus.OK);
    }

}
