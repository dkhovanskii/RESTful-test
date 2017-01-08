package com.silverrailtech.controller;

import com.silverrailtech.entity.Session;
import com.silverrailtech.entity.request.AddSessionRequest;
import com.silverrailtech.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.logging.Logger;

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

    private Session getFirstSessionByBrowserId(String browserId) {
        List<Session> sessions = sessionRepository.findAllByBrowserId(browserId);
        if (sessions.size() > 0)
            return sessions.get(0);
        else
            return null;
    }

    /*
    * GET /state - returns the current state
    * */
    @RequestMapping(method = RequestMethod.GET, path = "state")
    public String getState(String id) {
        String state = null;


        try {
            state = getFirstSessionByBrowserId(id).getState();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).severe(e.getMessage());
        }
        return state;
    }

    /*
     * GET /sum - sums all numbers in a string, e.g. “5abc141def” returns 146, if there are no numbers
     * return 0
     */
    @RequestMapping(method = RequestMethod.GET, path = "sum")
    public Integer getSum(String id) {
        String str = getFirstSessionByBrowserId(id).getState();
        str = str.replaceAll("[^0-9]+", " ");
        List<String> numbers = Arrays.asList(str.trim().split(" "));
        Integer sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            sum += Integer.decode(numbers.get(i));
        }
        return sum;
    }

    /*
    * GET /chars - shows the current state without numbers, e.g. “5abc141def” returns abcdef
    * */
    @RequestMapping(method = RequestMethod.GET, path = "chars")
    public String getChars(String id) {

        return getFirstSessionByBrowserId(id).getState().replaceAll("[0-9]+", "");
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "chars/{param}")
    public void deleteChars(String id, String param) {
        Session session = getFirstSessionByBrowserId(id);
        String curState = session.getState();
        int i = curState.lastIndexOf(param.charAt(0));
        if (i > -1) {
            curState = curState.substring(0, i - 1) + curState.substring(i, curState.length());
            session.setState(curState);
            sessionRepository.save(session);
        }
    }

    /*
    * POST /chars - adds the character/s to the string state, e.g. with JSON input
    * {“character”:”a”,”amount”:3} adds “aaa” to the state string
    * */
    @RequestMapping(method = RequestMethod.POST, path = "chars")
    public ResponseEntity<String> addChars(String id, Character c, Integer num) {
        Session session = getFirstSessionByBrowserId(id);
        String curState = session.getState();
        if(curState.length()+num>400){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        for (int i = 0; i < num; i++) {
            curState += c;
        }
        sessionRepository.save(session);
        return null;
    }

    /*
    * DELETE /chars/<character> - deletes the last occurrence of the character in the state string
    */
    @RequestMapping(method = RequestMethod.POST)
    public void addSession(@RequestBody AddSessionRequest addSessionRequest) {
        Session session = new Session();
        session.setBrowserId(addSessionRequest.getBroserId());
        session.setState(addSessionRequest.getState());
        sessionRepository.save(session);
    }
}
