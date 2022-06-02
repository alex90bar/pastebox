package com.alex90bar.pastebox.service;

import com.alex90bar.pastebox.api.request.PasteboxRequest;
import com.alex90bar.pastebox.api.response.PasteboxResponse;
import com.alex90bar.pastebox.api.response.PasteboxUrlResponse;
import java.util.List;

public interface PasteboxService {

  PasteboxResponse getByHash(String hash);

  List<PasteboxResponse> getFirstPublicPasteboxes();

  PasteboxUrlResponse create(PasteboxRequest request);

}
