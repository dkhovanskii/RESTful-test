package com.silverrailtech.controller;

import com.silverrailtech.RestTestApplication;
import com.silverrailtech.entity.Session;
import com.silverrailtech.repository.SessionRepository;
import org.hamcrest.text.IsEmptyString;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by d on 8/01/17.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestTestApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SessionControllerTest {
    @Autowired
    SessionRepository sessionRepository;

    Session session = new Session();

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        session.setState("abc123def5c");
        session.setId("1");
        sessionRepository.save(session);
    }


    @Test
    public void getState() throws Exception {
//        mockMvc.perform(
//                post("/chars?id=1")
//                        .content("{\"character\":\"a\",\"amount\":\"2\"}")
//        );
//
//        mockMvc
//                .perform(
//                        get("/state?id=1"))
//                .andExpect(content().string("aa"))
//                .andExpect(status().isOk());
    }

    @Test
    public void getSum() throws Exception {

    }

    @Test
    public void getChars() throws Exception {

    }

    @Test
    public void addChars() throws Exception {
        mockMvc.perform(
                post("/chars?id=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"character\":\"a\",\"amount\":\"2\"}")

        )
                .andExpect(status().isOk());

    }

    @Test
    public void deleteChars() throws Exception {

    }
}