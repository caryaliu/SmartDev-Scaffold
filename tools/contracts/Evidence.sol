// SPDX-License-Identifier: MIT
pragma solidity ^0.8.11;

// ABI 接口合约
interface EvidenceSignersDataABI {
    function verify(address addr) external view returns(bool);
    function getSigner(uint index) external view returns(address);
    function getSignersSize() external view returns(uint);
    function getSigners() external view returns(address[] memory);  // 新增
}

contract Evidence{
    string public evidence;
    address[] public signers;
    address public immutable factoryAddr;

    event AddSignaturesEvent(string evi, address indexed signer);
    event NewSignaturesEvent(string evi, address indexed signer);
    event ErrorNewSignaturesEvent(string evi, address signer);
    event SignatureFailed(string evi, address indexed signer, string reason);
    event DuplicateSignature(string evi, address indexed signer);

    modifier onlyFactory() {
        require(msg.sender == factoryAddr, "Only factory can call");
        _;
    }

    // 构造函数
    constructor(string memory evi, address addr, address creator) {
        factoryAddr = addr;
        require(_callVerify(creator), "Creator not in whitelist");
        evidence = evi;
        signers.push(creator);
        emit NewSignaturesEvent(evi, creator);
    }

    // 验证签名权限
    function _callVerify(address addr) private view returns(bool) {
        return EvidenceSignersDataABI(factoryAddr).verify(addr); 
    }

    // 获取证据+白名单+已签名列表
    function getEvidence() external view returns(string memory, address[] memory, address[] memory){
        address[] memory signerList = EvidenceSignersDataABI(factoryAddr).getSigners();
        return (evidence, signerList, signers);
    }

    // 添加签名
    function addSignatures(address signer) external onlyFactory returns(bool) {
        for(uint i=0; i<signers.length; i++) {
            if(signer == signers[i]) {
                emit DuplicateSignature(evidence, signer);
                return false;
            }
        }

        if(_callVerify(signer)) {
            signers.push(signer);
            emit AddSignaturesEvent(evidence, signer);
            return true;
        } else {
            emit SignatureFailed(evidence, signer, "Not in whitelist");
            return false;
        }
    }

    // 获取工厂白名单
    function getFactorySigners() external view returns(address[] memory)
    {
         return EvidenceSignersDataABI(factoryAddr).getSigners();  // 直接返回完整白名单
    }

    // 辅助函数：获取已签名者长度（适配外部调用）
    function getSignersLength() external view returns(uint) {
        return signers.length;
    }
}