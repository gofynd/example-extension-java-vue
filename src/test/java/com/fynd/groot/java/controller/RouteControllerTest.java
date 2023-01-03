package com.fynd.groot.java.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class DefaultControllerTest {
//
//    @LocalServerPort
//    private int port;
//    @Mock
//    private RestTemplate restTemplate;
//
//    @Test
//    void redirect() {
//        ResultMatcher res = restTemplate.getForObject("http://localhost:"+ port + "/company/1", ResultMatcher.class);
//        System.out.println(res);
//        assertThat(res).isEqualTo(forwardedUrl("/"));
//    }
//}

@RunWith(SpringRunner.class)
@WebMvcTest(RouteController.class)
class RouteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    void redirect() throws Exception {
        this.mockMvc.perform(get("/company/1"))
                .andExpect(forwardedUrl("/"));
    }
}