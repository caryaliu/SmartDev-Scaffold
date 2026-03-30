package org.fisco.asset.Asset.constants;

import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.String;

public class ContractConstants {
  public static String AssetAbi;

  public static String AssetBinary;

  public static String AssetGmBinary;

  static {
    try {
      AssetAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/Asset.abi"));
      AssetBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/Asset.bin"));
      AssetGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/Asset.bin"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
