package com.byborn.recipe.controller;

import com.byborn.recipe.controller.UnitController;
import com.byborn.recipe.model.UnitEntity;
import com.byborn.recipe.service.UnitService;
import java.util.List;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
 
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class UnitControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private UnitService unitService;

    @Autowired
    WebApplicationContext wac; // cached
    
    @BeforeEach
    void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        
    }

    @Test
    public void UnitAdd() throws Exception {

        mockMvc.perform(get("/unit")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("liter")));
        mockMvc.perform(get("/unit/edit")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("liter")));

        System.out.println("org result : " +unitService.getAllUnits().size());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/unit/createUnit")
            //.contentType("application/x-www-form-urlencoded")
            .param("id", "")
            .param("Nameunit", "NewUnit")
            .param("Total1", "1.0")
            .param("UID2", "1")
            .param("Total2", "1.0") ;
        mockMvc.perform(builder)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(redirectedUrl("/unit"));

        List<UnitEntity> unit_all = unitService.getAllUnits();
        unitService.deleteUnitById(unit_all.get(unit_all.size() -1).getId() );

    }
    @Test
    public void UnitEdit() throws Exception {


        mockMvc.perform(get("/unit/")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("ounce(oz)")));
        mockMvc.perform(get("/unit/edit/2")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("28.35")));

        System.out.println("org result : " +unitService.getAllUnits().size());

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/unit/createUnit")
            //.contentType("application/x-www-form-urlencoded")
            .param("id", "2")
            .param("Nameunit", "NewUnit")
            .param("Total1", "1.0")
            .param("UID2", "1")
            .param("Total2", "1.0") ;
        mockMvc.perform(builder)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(redirectedUrl("/unit"));

        mockMvc.perform(get("/unit/")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("NewUnit")));

    }
}
