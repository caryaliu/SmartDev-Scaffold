package org.fisco.asset.Asset.controller;

import org.fisco.asset.Asset.model.bo.AssetBalancesInputBO;
import org.fisco.asset.Asset.model.bo.AssetIssueInputBO;
import org.fisco.asset.Asset.model.bo.AssetSendInputBO;
import org.fisco.asset.Asset.service.AssetService;
import org.fisco.bcos.sdk.v3.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.v3.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

    /**
     * 发行积分（仅发行者）
     *
     * @param to     接收地址
     * @param amount 发行数量
     * @return 交易响应结果
     */
    @PostMapping("/issue")
    public String issue(@RequestParam String to, @RequestParam BigInteger amount) throws Exception {
        AssetIssueInputBO input = new AssetIssueInputBO(to, amount);
        return assetService.issue(input).getTransactionReceipt().getTransactionHash();
    }

    /**
     * 转账积分
     *
     * @param to     接收地址
     * @param amount 转账数量
     * @return 交易响应结果
     */
    @PostMapping("/send")
    public String send(@RequestParam String to, @RequestParam BigInteger amount) throws Exception {
        AssetSendInputBO input = new AssetSendInputBO(to, amount);
        return assetService.send(input).getTransactionReceipt().getTransactionHash();
    }

    /**
     * 查询地址余额
     *
     * @param address 查询地址
     * @return 余额查询结果
     */
    @GetMapping("/balances")
    public List<Object> balances(@RequestParam String address) throws Exception {
        AssetBalancesInputBO input = new AssetBalancesInputBO(address);
        return assetService.balances(input).getReturnObject();
    }

    /**
     * 获取发行者地址
     *
     * @return 发行者地址查询结果
     */
    @GetMapping("/issuer")
    public List<Object> issuer() throws Exception {
        return assetService.issuer().getReturnObject();
    }
}
