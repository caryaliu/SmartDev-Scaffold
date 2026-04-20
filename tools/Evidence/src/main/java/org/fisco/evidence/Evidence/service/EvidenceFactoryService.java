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
import org.fisco.evidence.Evidence.model.bo.EvidenceFactoryAddSignaturesInputBO;
import org.fisco.evidence.Evidence.model.bo.EvidenceFactoryGetEvidenceInputBO;
import org.fisco.evidence.Evidence.model.bo.EvidenceFactoryGetSignerInputBO;
import org.fisco.evidence.Evidence.model.bo.EvidenceFactoryIsSignerInputBO;
import org.fisco.evidence.Evidence.model.bo.EvidenceFactoryNewEvidenceInputBO;
import org.fisco.evidence.Evidence.model.bo.EvidenceFactorySignersInputBO;
import org.fisco.evidence.Evidence.model.bo.EvidenceFactoryVerifyInputBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Data
public class EvidenceFactoryService {
  @Value("${contract.evidenceFactoryAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public CallResponse signers(EvidenceFactorySignersInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceFactoryAbi, "signers", input.toArgs());
  }

  public TransactionResponse newEvidence(EvidenceFactoryNewEvidenceInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.EvidenceFactoryAbi, "newEvidence", input.toArgs());
  }

  public CallResponse getEvidence(EvidenceFactoryGetEvidenceInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceFactoryAbi, "getEvidence", input.toArgs());
  }

  public CallResponse verify(EvidenceFactoryVerifyInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceFactoryAbi, "verify", input.toArgs());
  }

  public CallResponse isSigner(EvidenceFactoryIsSignerInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceFactoryAbi, "isSigner", input.toArgs());
  }

  public CallResponse getSigners() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceFactoryAbi, "getSigners", Arrays.asList());
  }

  public CallResponse getSignersSize() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceFactoryAbi, "getSignersSize", Arrays.asList());
  }

  public TransactionResponse addSignatures(EvidenceFactoryAddSignaturesInputBO input) throws
      Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.EvidenceFactoryAbi, "addSignatures", input.toArgs());
  }

  public CallResponse getSigner(EvidenceFactoryGetSignerInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceFactoryAbi, "getSigner", input.toArgs());
  }
}
