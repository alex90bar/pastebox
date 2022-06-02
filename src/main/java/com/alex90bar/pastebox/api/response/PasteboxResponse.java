package com.alex90bar.pastebox.api.response;

import com.alex90bar.pastebox.api.request.PublicStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PasteboxResponse {

  private final String data;
  private final boolean isPublic;

}
