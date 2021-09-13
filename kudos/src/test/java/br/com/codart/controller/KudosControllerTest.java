package br.com.codart.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import java.nio.charset.StandardCharsets;
import org.springframework.http.MediaType;
import br.com.codart.response.KudosResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KudosControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void getRewardTest() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    KudosResponse response = new KudosResponse("Você recebeu vinte e cinco reais em retorno aos kudos SUPER!");
    MvcResult mvcResult = mvc.perform(
                    get("/api/kudos")
                            .param("points", "100")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(response)))

            .andExpect(
                    status().isOk())
            .andReturn();

    String actualResponseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
    String expectedResponseBody = objectMapper.writeValueAsString(response);
    assertThat(actualResponseBody).isEqualTo(expectedResponseBody);

  }

}
