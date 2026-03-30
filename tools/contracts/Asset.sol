// SPDX-License-Identifier: MIT
pragma solidity ^0.8.11;

/// @title Asset积分合约
/// @notice 内部积分系统，支持发行和转账
contract Asset {
    // 状态变量
    address public issuer;          // 积分发行者地址
    mapping(address => uint256) public balances; // 各地址积分余额

    // 事件
    event Issue(address indexed to, uint256 amount);
    event Send(address indexed from, address indexed to, uint256 amount);

    // 构造函数：初始化发行者为合约部署者
    constructor() {
        issuer = msg.sender;
    }

    /// @dev 仅发行者可以调用的修饰器
    modifier onlyIssuer() {
        require(msg.sender == issuer, "Asset: only issuer can call");
        _;
    }

    /// @notice 发行积分（仅发行者）
    /// @param to 接收积分的地址
    /// @param amount 发行的积分数量
    function issue(address to, uint256 amount) external onlyIssuer {
        require(to != address(0), "Asset: invalid address");
        require(amount > 0, "Asset: amount must be greater than 0");

        balances[to] += amount;
        emit Issue(to, amount);
    }

    /// @notice 转账积分（任何人可调用）
    /// @param to 接收积分的地址
    /// @param amount 转账的积分数量
    function send(address to, uint256 amount) external {
        require(to != address(0), "Asset: invalid address");
        require(amount > 0, "Asset: amount must be greater than 0");
        require(balances[msg.sender] >= amount, "Asset: insufficient balance");

        balances[msg.sender] -= amount;
        balances[to] += amount;
        emit Send(msg.sender, to, amount);
    }
}
