package com.shahian.mscustomer.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shahian.mscustomer.model.User;
import com.shahian.mscustomer.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
        Long userId = 1L;
        Long user1Id = 2L;
        User user = new User();
        user.setId(userId);
        user.setUserName("Shahian");
        user.setEmail("Shahian@example.com");
        User user1 = new User();
        user1.setId(user1Id);
        user1.setUserName("hamidReza");
        user1.setEmail("hamidReza@example.com");
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        when(userService.findAll()).thenReturn(users);

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].userName", is("Shahian")))
                .andExpect(jsonPath("$[0].email", is("Shahian@example.com")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].userName", is("hamidReza")))
                .andExpect(jsonPath("$[1].email", is("hamidReza@example.com")));

        verify(userService, times(1)).findAll();
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testGetUser() throws Exception {
        Long userId = 1L;

        User user = new User();
        user.setId(userId);
        user.setUserName("Shahian");
        user.setEmail("Shahian@example.com");
        when(userService.findById(1L)).thenReturn(user);

        mockMvc.perform(get("/api/v1/user").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userName", is("Shahian")));
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUserName("Shahian");
        user.setEmail("Shahian@example.com");

        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);
        MvcResult result = mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(201, response.getStatus());
        String jsonResponse = response.getContentAsString();
//        mockMvc.perform(post("/api/v1/user")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(userJson))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.userName", is("John Doe")))
//                .andExpect(jsonPath("$.email", is("johndoe@example.com")));

        verify(userService, times(1)).save(refEq(user));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUserName("Shahian");
        user.setEmail("Shahian@example.com");

        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);
        MvcResult result = mockMvc.perform(put("/api/v1/user")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                //.andExpect(content().json(userJson))
                .andReturn();
        //verify(userService, times(1)).update(1L, user);
    }
}