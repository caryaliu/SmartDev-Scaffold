package org.fisco.evidence.Evidence.model.bo;

import java.lang.Object;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvidenceAddSignaturesInputBO {
  private String signer;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(signer);
    return args;
  }
}
