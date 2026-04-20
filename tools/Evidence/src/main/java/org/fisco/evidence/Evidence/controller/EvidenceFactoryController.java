package org.fisco.evidence.Evidence.controller;

import org.fisco.evidence.Evidence.model.bo.*;
import org.fisco.evidence.Evidence.service.EvidenceFactoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/evidence/factory")
public class EvidenceFactoryController {

    private static final Logger log = LoggerFactory.getLogger(EvidenceFactoryController.class);
    @Autowired
    private EvidenceFactoryService service;

    // 创建新证据
    @PostMapping("/evidence")
    public List<Object> newEvidence(@RequestParam String evidence) throws Exception {
        EvidenceFactoryNewEvidenceInputBO input = new EvidenceFactoryNewEvidenceInputBO(evidence);
        return service.newEvidence(input).getReturnObject();
    }

    // 查询证据信息
    @GetMapping("/{address}")
    public List<Object> getEvidence(@PathVariable("address") String address) throws Exception {
        EvidenceFactoryGetEvidenceInputBO input = new EvidenceFactoryGetEvidenceInputBO(address);
        return service.getEvidence(input).getReturnObject();
    }

    // 添加证据合约签名
    @PostMapping("/{address}/signatures")
    public List<Object> addSignature(@PathVariable("address") String address) throws Exception {
        EvidenceFactoryAddSignaturesInputBO input = new EvidenceFactoryAddSignaturesInputBO(address);
        List<Object> result = service.addSignatures(input).getReturnObject();
        log.info("add signature result: " + result);
        return result;
    }

    // 验证地址是否在白名单中
    @GetMapping("/verify")
    public List<Object> verifySigner(@RequestParam("address") String address) throws Exception {
        EvidenceFactoryVerifyInputBO input = new EvidenceFactoryVerifyInputBO(address);
        return service.verify(input).getReturnObject();
    }

    // 检查地址是否是签名人
    @PostMapping("/is-signer")
    public List<Object> isSigner(@RequestParam("address") String address) throws Exception {
        EvidenceFactoryIsSignerInputBO input = new EvidenceFactoryIsSignerInputBO(address);
        return service.isSigner(input).getReturnObject();
    }

    // 获取所有白名单
    @GetMapping("/signers")
    public List<Object> getSigners() throws Exception {
        return service.getSigners().getReturnObject();
    }

    // 获取所有白名单长度
    @GetMapping("/signers-size")
    public List<Object> getSignersSize() throws Exception {
        return service.getSignersSize().getReturnObject();
    }

    // 查询指定索引的签名人
    @PostMapping("/signers/{index}")
    public List<Object> getSigner(@PathVariable ("index") BigInteger index) throws Exception {
        EvidenceFactoryGetSignerInputBO input = new EvidenceFactoryGetSignerInputBO(index);
        return service.getSigner(input).getReturnObject();
    }
}
