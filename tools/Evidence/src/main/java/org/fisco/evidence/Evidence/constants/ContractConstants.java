package org.fisco.evidence.Evidence.constants;

import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.String;

public class ContractConstants {
  public static String EvidenceAbi;

  public static String EvidenceBinary;

  public static String EvidenceGmBinary;

  public static String EvidenceFactoryAbi;

  public static String EvidenceFactoryBinary;

  public static String EvidenceFactoryGmBinary;

  static {
    try {
      EvidenceAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/Evidence.abi"));
      EvidenceBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/Evidence.bin"));
      EvidenceGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/Evidence.bin"));
      EvidenceFactoryAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/EvidenceFactory.abi"));
      EvidenceFactoryBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/EvidenceFactory.bin"));
      EvidenceFactoryGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/EvidenceFactory.bin"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
