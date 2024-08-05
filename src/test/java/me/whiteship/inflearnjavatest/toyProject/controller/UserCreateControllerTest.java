package me.whiteship.inflearnjavatest.toyProject.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UserCreateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 유저_크리에이트_응답이_200으로_내려온다() throws Exception{
        //given
        //when
        //then
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk());
    }

}