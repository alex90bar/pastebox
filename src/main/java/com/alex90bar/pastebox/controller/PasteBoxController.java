package com.alex90bar.pastebox.controller;

import com.alex90bar.pastebox.api.request.PasteBoxRequest;
import java.util.Collection;
import java.util.Collections;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasteBoxController {

  @GetMapping("/")
  public Collection<String> getPublicPasteList() {
    return Collections.emptyList();
  }

  @GetMapping("/{hash}")
  public String getByHash(@PathVariable String hash) {
    return hash;
  }

  @PostMapping("/")
  public String add(@RequestBody PasteBoxRequest request){
    return request.getData();
  }

}
