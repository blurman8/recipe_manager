    package com.byborn.recipe.service;

import com.byborn.recipe.model.PredictcostEntity;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
public class PrediectcostControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private PredictcostService predictcostService;

    @Autowired
    WebApplicationContext wac; // cached
    
    @BeforeEach
    void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        
    }
    
    private Matcher<String> doesNotContainString(String s) {
       return CoreMatchers.not(containsString(s));
    }
 
    @Test
    public void PredictcostAdd() throws Exception {

        mockMvc.perform(get("/predictcost")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("sandwich")));
        mockMvc.perform(get("/predictcost/edit")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("value=\"\"")));

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/predictcost/createPredictcost")
            .param("id", "")
            .param("rid", "2")
            .param("total", "45");
        mockMvc.perform(builder)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(redirectedUrl("/predictcost"));

        List<PredictcostEntity> predictcost_all = predictcostService.getAllPredictcosts();
        predictcostService.deletePredictcostById(predictcost_all.get(predictcost_all.size() -1).getId() );

    }
    @Test
    public void PredictcostEdit() throws Exception {

        mockMvc.perform(get("/predictcost/edit/1")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("30.0")));
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/predictcost/createPredictcost")
            .param("id", "1")
            .param("rid", "1")
            .param("Total", "35.0");
 
        mockMvc.perform(builder)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(redirectedUrl("/predictcost"));

        mockMvc.perform(get("/predictcost/edit/1")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("35.0")));
    }
    @Test
    public void PredictcostIngredientCost() throws Exception {
        mockMvc.perform(get("/predictcost/edit/cost/1")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("200.0")));
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/predictcost/createIngredientcost")
            .param("inid","1")
            .param("nameingredient","Sea Salt")
            .param("nameunit","grams")
            .param("total","90.0")
            .param("total2","200.0")
            .param("uid","1")
            .param("cost","55.0");
 
        mockMvc.perform(builder)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(redirectedUrl("/predictcost/"));

        mockMvc.perform(get("/predictcost/")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("55.0")));
     
 
        mockMvc.perform(get("/predictcost/start")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("0.0"))).andExpect(content().string(doesNotContainString("60.0")));
        builder = MockMvcRequestBuilders.post("/predictcost/createIngredientcost")
            .param("inid","3")
            .param("nameingredient","Bread")
            .param("nameunit","grams")
            .param("total","60  .0")
            .param("total2","220.0")
            .param("uid","1")
            .param("cost","21.0");
         mockMvc.perform(builder)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(redirectedUrl("/predictcost/"));
         mockMvc.perform(get("/predictcost/")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("21.0")));
         mockMvc.perform(get("/predictcost/cal/1")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("63.0")));
         mockMvc.perform(get("/predictcost/cal/7")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("420.0")));
         mockMvc.perform(get("/predictcost/cal/30")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("1722.0")));
        
    }
}
