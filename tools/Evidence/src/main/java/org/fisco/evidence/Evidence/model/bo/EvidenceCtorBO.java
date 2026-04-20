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
public class EvidenceCtorBO {
  private String evi;

  private String addr;

  private String creator;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(evi);
    args.add(addr);
    args.add(creator);
    return args;
  }
}
