package org.fisco.evidence.Evidence.service;

import java.lang.Exception;
import java.lang.String;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.v3.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.v3.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.v3.transaction.model.dto.TransactionResponse;
import org.fisco.evidence.Evidence.constants.ContractConstants;
import org.fisco.evidence.Evidence.model.bo.EvidenceAddSignaturesInputBO;
import org.fisco.evidence.Evidence.model.bo.EvidenceSignersInputBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Data
public class EvidenceService {
  @Value("${contract.evidenceAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public CallResponse signers(EvidenceSignersInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceAbi, "signers", input.toArgs());
  }

  public CallResponse factoryAddr() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceAbi, "factoryAddr", Arrays.asList());
  }

  public CallResponse getFactorySigners() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceAbi, "getFactorySigners", Arrays.asList());
  }

  public CallResponse evidence() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceAbi, "evidence", Arrays.asList());
  }

  public TransactionResponse addSignatures(EvidenceAddSignaturesInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.EvidenceAbi, "addSignatures", input.toArgs());
  }

  public CallResponse getSignersLength() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceAbi, "getSignersLength", Arrays.asList());
  }

  public CallResponse getEvidence() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceAbi, "getEvidence", Arrays.asList());
  }
}
