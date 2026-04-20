// SPDX-License-Identifier: MIT
pragma solidity ^0.8.11;

import "./Evidence.sol";

contract EvidenceFactory{
    address[] public signers;
    mapping(address => bool) public isSigner;
    event EvidenceCreated(address indexed evidenceAddr);

    constructor(address[] memory evidenceSigners){
        require(evidenceSigners.length > 0, "Empty signers list");
        // 0.8+ 支持数组直接赋值
        signers = evidenceSigners;
        for (uint i = 0; i < evidenceSigners.length; ++i) {
            require(evidenceSigners[i] != address(0), "Signer address cannot be zero");
            isSigner[evidenceSigners[i]] = true;
        }
    }

    // 创建新证据合约
    function newEvidence(string calldata evi) external returns(address) {
        require(bytes(evi).length > 0, "Empty evidence");
        Evidence evidence = new Evidence(evi, address(this), msg.sender);
        emit EvidenceCreated(address(evidence));
        return address(evidence);
    }

    // 查询证据信息
    function getEvidence(address addr) external view returns(string memory,address[] memory,address[] memory){
        return Evidence(addr).getEvidence();
    }

    // 调用证据合约添加签名
    function addSignatures(address addr) external returns(bool) {
        return Evidence(addr).addSignatures(msg.sender);
    }

    // 验证地址是否在白名单
    function verify(address addr) external view returns(bool) {
        return isSigner[addr];
    }

    // 获取指定索引的白名单地址
    function getSigner(uint index) external view returns(address){
        require(index < signers.length, "Index out of bounds");
        return signers[index];
    }

    // 获取白名单长度
    function getSignersSize() external view returns(uint){
        return signers.length;
    }

    // 获取完整白名单
    function getSigners() external view returns(address[] memory){
        return signers;
    }
}