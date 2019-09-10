package ru.yandex.homework.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.homework.requests.WorkerRequest;
import ru.yandex.homework.responses.WorkerResponse;
import ru.yandex.homework.services.WorkerService;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = WorkerController.class)
@WithMockUser(roles = "USER", username = "USER", password = "USER2")
//@AutoConfigureMockMvc
public class WorkerControllerTest {
    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    @Qualifier("BaseWorkerService")
    WorkerService baseWorkerService;

    @Test
    public void createAction() throws Exception {
        when(baseWorkerService.createWorker(Mockito.any(WorkerRequest.class)))
                .thenReturn(createWorkerResponse(1l, "Pavel", 15000l));

        String json = createRequest("Pavel", new BigDecimal(15000));
        mockMvc.perform(
                post("/api/v1/workers")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(json)
        ).andDo(print()).
                andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.name", is("Pavel")))
                .andExpect(jsonPath("$.salaryValue", is(15000)));
    }

    private String createRequest(String name, BigDecimal value) throws JsonProcessingException {
        WorkerRequest request = new WorkerRequest();
        request.setName(name);
        request.setSalaryValue(value);
        return objectMapper.writeValueAsString(request);
    }

    private WorkerResponse createWorkerResponse(Long id, String name, Long value) {
        WorkerResponse response = new WorkerResponse();
        response.setName(name);
        response.setId(id);
        response.setSalaryValue(new BigDecimal(value));
        return response;
    }
}