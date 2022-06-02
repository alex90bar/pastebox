package com.alex90bar.pastebox.controller;

import com.alex90bar.pastebox.api.request.PasteboxRequest;
import com.alex90bar.pastebox.api.response.PasteboxResponse;
import com.alex90bar.pastebox.api.response.PasteboxUrlResponse;
import com.alex90bar.pastebox.service.PasteboxService;
import java.util.Collection;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PasteboxController {

  private final PasteboxService pasteboxService;

  @GetMapping("/")
  public Collection<PasteboxResponse> getPublicPasteList() {
    return pasteboxService.getFirstPublicPasteboxes();
  }

  @GetMapping("/{hash}")
  public PasteboxResponse getByHash(@PathVariable String hash) {
    return pasteboxService.getByHash(hash);
  }

  @PostMapping("/")
  public PasteboxUrlResponse add(@RequestBody PasteboxRequest request){
    return pasteboxService.create(request);
  }

}
