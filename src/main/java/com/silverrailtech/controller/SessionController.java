package com.silverrailtech.controller;

import com.silverrailtech.entity.Session;
import com.silverrailtech.repository.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
public class SessionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionController.class);
    private SessionRepository sessionRepository;

    @Autowired
    public SessionController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    /*
     * GET /state - returns the current state
     */
    @RequestMapping(method = RequestMethod.GET, path = "state")
    public String getState(Session session) {
        LOGGER.info("Get state for browser '" + session.getId() + "'");
        String state = null;

        try {
            state = session.getState();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return state;
    }

    /*
     * GET /sum - sums all numbers in a string, e.g. “5abc141def” returns 146, if there are no numbers
     * return 0
     */
    @RequestMapping(method = RequestMethod.GET, path = "sum")
    public Integer getSum(Session session) {
        LOGGER.info("Get state for state '" + session.getId() + "'");
        String str = session.getState();
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
     */
    @RequestMapping(method = RequestMethod.GET, path = "chars")
    public String getChars(Session session) {
        return session.getState().replaceAll("[0-9]+", "");
    }

    /*
     * POST /chars - adds the character/s to the string state, e.g. with JSON input
     * {“character”:”a”,”amount”:3} adds “aaa” to the state string
     */
    @Transactional
    @RequestMapping(method = RequestMethod.POST, path = "chars")
    public ResponseEntity<String> addChars(Session session, @Valid @RequestBody CharsParameters charsParameters) {
        LOGGER.info("Add char(s) for browser '" + session.getId() +
                "' char='" + charsParameters.getCharacter() +
                "' amount='" + charsParameters.getAmount() + "'");

        String curState = session.getState();
        Integer num = charsParameters.getAmount();
        Character c = charsParameters.getCharacter().charAt(0);
        if ((curState.length() + num > 200) || (num > 9) || (num < -1)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        for (int i = 0; i < num; i++) {
            curState += c;
        }
        session.setState(curState);
        sessionRepository.save(session);
        return new ResponseEntity<String>(session.getId(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "chars/{param}")
    public void deleteChars(Session session, @PathVariable Character param) {
        LOGGER.info("Delete last symbol '" + param + "' for browser '" + session.getId() + "'");

        String curState = session.getState();
        int i = curState.lastIndexOf(param);
        if (i > -1) {
            curState = curState.substring(0, i - 1) + curState.substring(i, curState.length());
            session.setState(curState);
            sessionRepository.save(session);
        }
    }
}
