package com.only4play.prome.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertRuleController {

  @GetMapping(value = "readYaml",produces = "text/vnd.yaml")
  public String readYaml(){
    try {
      byte[] bytes = Files.readAllBytes(Paths.get("/Users/benmu/metrics/prometheus-2.14.0-rc.0.darwin-amd64/alerts/example.yml"));
      return new String(bytes,"utf-8");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }
}
