package com.infoservice.init.infothreads.controller;

import com.infoservice.init.infothreads.models.InfoStreams;
import com.infoservice.init.infothreads.repos.InfoStreamsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//annotation tells Spring that this class will requested by URL and will return data to the requester
@RestController
@RequestMapping("/infos")
public class InfosController {
    /*
     *annotation creates an instance of the InfoStreamsRepository object
    that will allow us to access and modify the pets database
     */
    @Autowired
    private InfoStreamsRepository repository;

    //    @RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping
    @CrossOrigin
    public List<InfoStreams> getAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/hot")
    @CrossOrigin
    public Slice<InfoStreams> getHot(Pageable pageable) {
        return repository.findAllByOrderByInfoTimestampDesc(pageable);
    }
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public InfoEntries getPetById(@PathVariable(“id”) ObjectId id) {
//        return repository.findBy_id(id);
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyInfoById(@PathVariable("id") ObjectId id, @Valid
    @RequestBody InfoStreams info) {
        info.set_id(id);
        repository.save(info);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public InfoStreams createInfo(@RequestBody InfoStreams info) {
        info.set_id(ObjectId.get());
        repository.save(info);
        return info;
    }

}
