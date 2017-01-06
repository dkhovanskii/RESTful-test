package com.silverrailtech.controller;

import com.silverrailtech.entity.Session;
import com.silverrailtech.entity.request.AddSessionRequest;
import com.silverrailtech.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by d on 6/01/17.
 */
@RestController(value = "sessions")
public class SessionController {

    private SessionRepository sessionRepository;

    @Autowired
    public SessionController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    /*
    * GET /state - returns the current state
GET /sum - sums all numbers in a string, e.g. “5abc141def” returns 146, if there are no numbers
return 0
GET /chars - shows the current state without numbers, e.g. “5abc141def” returns abcdef
POST /chars - adds the character/s to the string state, e.g. with JSON input
{“character”:”a”,”amount”:3} adds “aaa” to the state string
DELETE /chars/<character> - deletes the last occurrence of the character in the state string
    *
    * */

    @RequestMapping(method = RequestMethod.GET, path = "state")
    public String getState(long id) {
        return sessionRepository.findOne(id).getState();
    }

    @RequestMapping(method = RequestMethod.GET, path = "sum")
    public String getSum(long id) {
        return sessionRepository.findOne(id).getState();
    }

    @RequestMapping(method = RequestMethod.GET, path = "chars")
    public String getChars(long id) {
        return sessionRepository.findOne(id).getState();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "chars/{param}")
    public String deleteChars(long id) {
        return sessionRepository.findOne(id).getState();
    }


    @RequestMapping(method = RequestMethod.POST, path = "chars")
    public String addChars(long id) {
        return sessionRepository.findOne(id).getState();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addSession(@RequestBody AddSessionRequest addSessionRequest) {
        Session session = new Session();
        session.setBroserId(addSessionRequest.getBroserId());
        session.setState(addSessionRequest.getState());
        sessionRepository.save(session);

    }
}
