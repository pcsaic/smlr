package ru.ref.smlr.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.ref.smlr.SmlrApplication;
import ru.ref.smlr.services.KeyMapperService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SmlrApplication.class)
@WebAppConfiguration
public class RedirectControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @Mock
    KeyMapperService service;

    @InjectMocks
    RedirectController controller;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();

        Mockito.when(service.getLink(PATH)).thenReturn(new KeyMapperService.Get.Link(HEADER_VALUE));
        Mockito.when(service.getLink(BAD_PATH)).thenReturn(new KeyMapperService.Get.NotFound(BAD_PATH));
    }

    private String PATH = "/aAbBcCdD";
    private int REDIRECT_STATUS = 302;
    private String HEADER_NAME = "Location";
    private String HEADER_VALUE =  "http://www.eveonline.com";
    private String BAD_PATH = "/ololo";
    private int NOT_FOUND = 404;

    @Test
    public void controllerMustRedirectUsWhenRequestIsSuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(PATH))
                .andExpect(MockMvcResultMatchers.status().is(REDIRECT_STATUS))
                .andExpect(MockMvcResultMatchers.header().string(HEADER_NAME, HEADER_VALUE));
    }

    @Test
    public void controllerMustReturn404IfBadKey() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(BAD_PATH))
                .andExpect(MockMvcResultMatchers.status().is(NOT_FOUND));

    }

}
