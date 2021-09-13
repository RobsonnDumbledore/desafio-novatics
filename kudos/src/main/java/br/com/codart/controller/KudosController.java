package br.com.codart.controller;

import br.com.codart.service.KudosService;
import br.com.codart.response.KudosResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/kudos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class KudosController {

  @Autowired
  private KudosService service;

  @GetMapping
  public KudosResponse getReward(@RequestParam(required = true) Integer points) {
    KudosResponse response = new KudosResponse();
    response.setMessage(service.showMessage(points));
    return response;
  }
}
