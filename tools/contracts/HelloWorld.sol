// SPDX-License-Identifier: MIT
pragma solidity ^0.8.11;

contract HelloWorld {
    // 状态变量：存储数据，private 确保内部可见
    uint256 private data;
    // 合约部署者地址，用于访问控制
    address private immutable owner;

    // 事件：数据更新时触发，参数索引便于前端检索
    event SetData(uint256 indexed newData, address indexed caller);

    // 构造函数：部署时设置 owner
    constructor() {
        owner = msg.sender;
    }

    // 修改器：仅允许 owner 调用
    modifier onlyOwner() {
        require(msg.sender == owner, "Caller is not the owner");
        _;
    }

    // 更新数据：仅 owner 可调用，更新后触发事件
    function setData(uint256 n) external onlyOwner {
        data = n;
        emit SetData(n, msg.sender);
    }

    // 读取数据：任何人都可以查看
    function getData() external view returns (uint256) {
        return data;
    }

    // 可选：获取 owner 地址（便于前端确认）
    function getOwner() external view returns (address) {
        return owner;
    }
}
