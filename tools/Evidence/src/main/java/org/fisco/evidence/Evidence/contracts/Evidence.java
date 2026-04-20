package org.fisco.evidence.Evidence.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.codec.abi.FunctionEncoder;
import org.fisco.bcos.sdk.v3.codec.datatypes.Address;
import org.fisco.bcos.sdk.v3.codec.datatypes.Bool;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicArray;
import org.fisco.bcos.sdk.v3.codec.datatypes.Event;
import org.fisco.bcos.sdk.v3.codec.datatypes.Function;
import org.fisco.bcos.sdk.v3.codec.datatypes.Type;
import org.fisco.bcos.sdk.v3.codec.datatypes.TypeReference;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.v3.contract.Contract;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.CryptoType;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.model.callback.CallCallback;
import org.fisco.bcos.sdk.v3.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class Evidence extends Contract {
    public static final String[] BINARY_ARRAY = {"60a06040523480156200001157600080fd5b5060405162000f2738038062000f278339810160408190526200003491620002c7565b6001600160a01b0382166080526200004c8162000143565b6200009d5760405162461bcd60e51b815260206004820152601860248201527f43726561746f72206e6f7420696e2077686974656c6973740000000000000000604482015260640160405180910390fd5b8251620000b2906000906020860190620001bb565b506001805480820182556000919091527fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf60180546001600160a01b0319166001600160a01b0383169081179091556040517f33e1a6123ff7a4853d4abf096f7e69068036296e150c895e88a15a808c7d38969062000132908690620003a6565b60405180910390a250505062000443565b6080516040516363a9c3d760e01b81526001600160a01b03838116600483015260009216906363a9c3d790602401602060405180830381865afa1580156200018f573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190620001b59190620003db565b92915050565b828054620001c99062000406565b90600052602060002090601f016020900481019282620001ed576000855562000238565b82601f106200020857805160ff191683800117855562000238565b8280016001018555821562000238579182015b82811115620002385782518255916020019190600101906200021b565b50620002469291506200024a565b5090565b5b808211156200024657600081556001016200024b565b634e487b7160e01b600052604160045260246000fd5b60005b83811015620002945781810151838201526020016200027a565b83811115620002a4576000848401525b50505050565b80516001600160a01b0381168114620002c257600080fd5b919050565b600080600060608486031215620002dd57600080fd5b83516001600160401b0380821115620002f557600080fd5b818601915086601f8301126200030a57600080fd5b8151818111156200031f576200031f62000261565b604051601f8201601f19908116603f011681019083821181831017156200034a576200034a62000261565b816040528281528960208487010111156200036457600080fd5b6200037783602083016020880162000277565b80975050505050506200038d60208501620002aa565b91506200039d60408501620002aa565b90509250925092565b6020815260008251806020840152620003c781604085016020870162000277565b601f01601f19169190910160400192915050565b600060208284031215620003ee57600080fd5b81518015158114620003ff57600080fd5b9392505050565b600181811c908216806200041b57607f821691505b602082108114156200043d57634e487b7160e01b600052602260045260246000fd5b50919050565b608051610aac6200047b6000396000818161010a01528181610156015281816101ec0152818161040b015261063a0152610aac6000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c8063596f21f81161005b578063596f21f8146100ee578063963671be14610105578063a77e09871461012c578063f44c2ab21461014157600080fd5b806307a83cd714610082578063203e4922146100a05780632079fb9a146100c3575b600080fd5b61008a610152565b60405161009791906106f1565b60405180910390f35b6100b36100ae366004610723565b6101df565b6040519015158152602001610097565b6100d66100d1366004610740565b6103d8565b6040516001600160a01b039091168152602001610097565b6100f6610402565b604051610097939291906107a6565b6100d67f000000000000000000000000000000000000000000000000000000000000000081565b61013461058a565b60405161009791906107e9565b600154604051908152602001610097565b60607f00000000000000000000000000000000000000000000000000000000000000006001600160a01b03166394cf795e6040518163ffffffff1660e01b8152600401600060405180830381865afa1580156101b2573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526101da919081019061081d565b905090565b6000336001600160a01b037f000000000000000000000000000000000000000000000000000000000000000016146102555760405162461bcd60e51b815260206004820152601560248201527413db9b1e48199858dd1bdc9e4818d85b8818d85b1b605a1b604482015260640160405180910390fd5b60005b6001548110156102f15760018181548110610275576102756108e2565b6000918252602090912001546001600160a01b03848116911614156102df57826001600160a01b03167f50d8d9b7fef3029ea03aa940fab670b2a19cb74043892523b4605a1221ac340960006040516102ce91906109d3565b60405180910390a250600092915050565b806102e9816109e6565b915050610258565b506102fb82610618565b1561038d5760018054808201825560009182527fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf60180546001600160a01b0319166001600160a01b03851690811790915560405190917fb2d607696b46e986a742a086a30c8c0c6e3b40b5f47d2230cb94efc2f0569a269161037d91906109d3565b60405180910390a2506001919050565b816001600160a01b03167f50278d0d6850de9ca5edac480216ba1cccb316149048c5b5c5b4e801acdb42b460006040516103c79190610a0f565b60405180910390a25060005b919050565b600181815481106103e857600080fd5b6000918252602090912001546001600160a01b0316905081565b606080606060007f00000000000000000000000000000000000000000000000000000000000000006001600160a01b03166394cf795e6040518163ffffffff1660e01b8152600401600060405180830381865afa158015610467573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405261048f919081019061081d565b905060008160018280546104a2906108f8565b80601f01602080910402602001604051908101604052809291908181526020018280546104ce906108f8565b801561051b5780601f106104f05761010080835404028352916020019161051b565b820191906000526020600020905b8154815290600101906020018083116104fe57829003601f168201915b505050505092508080548060200260200160405190810160405280929190818152602001828054801561057757602002820191906000526020600020905b81546001600160a01b03168152600190910190602001808311610559575b5050505050905093509350935050909192565b60008054610597906108f8565b80601f01602080910402602001604051908101604052809291908181526020018280546105c3906108f8565b80156106105780601f106105e557610100808354040283529160200191610610565b820191906000526020600020905b8154815290600101906020018083116105f357829003601f168201915b505050505081565b6040516363a9c3d760e01b81526001600160a01b0382811660048301526000917f0000000000000000000000000000000000000000000000000000000000000000909116906363a9c3d790602401602060405180830381865afa158015610683573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906106a79190610a54565b92915050565b600081518084526020808501945080840160005b838110156106e65781516001600160a01b0316875295820195908201906001016106c1565b509495945050505050565b60208152600061070460208301846106ad565b9392505050565b6001600160a01b038116811461072057600080fd5b50565b60006020828403121561073557600080fd5b81356107048161070b565b60006020828403121561075257600080fd5b5035919050565b6000815180845260005b8181101561077f57602081850181015186830182015201610763565b81811115610791576000602083870101525b50601f01601f19169290920160200192915050565b6060815260006107b96060830186610759565b82810360208401526107cb81866106ad565b905082810360408401526107df81856106ad565b9695505050505050565b6020815260006107046020830184610759565b634e487b7160e01b600052604160045260246000fd5b80516103d38161070b565b6000602080838503121561083057600080fd5b825167ffffffffffffffff8082111561084857600080fd5b818501915085601f83011261085c57600080fd5b81518181111561086e5761086e6107fc565b8060051b604051601f19603f83011681018181108582111715610893576108936107fc565b6040529182528482019250838101850191888311156108b157600080fd5b938501935b828510156108d6576108c785610812565b845293850193928501926108b6565b98975050505050505050565b634e487b7160e01b600052603260045260246000fd5b600181811c9082168061090c57607f821691505b6020821081141561092d57634e487b7160e01b600052602260045260246000fd5b50919050565b8054600090600181811c908083168061094d57607f831692505b602080841082141561096f57634e487b7160e01b600052602260045260246000fd5b8388526020880182801561098a576001811461099b576109c6565b60ff198716825282820197506109c6565b60008981526020902060005b878110156109c0578154848201529086019084016109a7565b83019850505b5050505050505092915050565b6020815260006107046020830184610933565b6000600019821415610a0857634e487b7160e01b600052601160045260246000fd5b5060010190565b604081526000610a226040830184610933565b8281036020840152601081526f139bdd081a5b881dda1a5d195b1a5cdd60821b60208201526040810191505092915050565b600060208284031215610a6657600080fd5b8151801515811461070457600080fdfea26469706673582212202adc2217ba46dd0825b691b6471407296860ac6ba4d5db73172d2cc595460f4b64736f6c634300080b0033"};

    public static final String BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"60a06040523480156200001157600080fd5b5060405162000f2438038062000f248339810160408190526200003491620002c8565b6001600160a01b0382166080526200004c8162000144565b6200009e57604051636381e58960e11b815260206004820152601860248201527f43726561746f72206e6f7420696e2077686974656c6973740000000000000000604482015260640160405180910390fd5b8251620000b3906000906020860190620001bc565b506001805480820182556000919091527f801412028db4471413ca17a15806a4f6857ca4e7c2783885b524c92c7efcdc980180546001600160a01b0319166001600160a01b0383169081179091556040517f95d857aa7e2fe7d3b3d3ca5189140a02ed727551b88f172876ffda2674ba354a9062000133908690620003a7565b60405180910390a250505062000444565b608051604051632372162f60e21b81526001600160a01b0383811660048301526000921690638dc858bc90602401602060405180830381865afa15801562000190573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190620001b69190620003dc565b92915050565b828054620001ca9062000407565b90600052602060002090601f016020900481019282620001ee576000855562000239565b82601f106200020957805160ff191683800117855562000239565b8280016001018555821562000239579182015b82811115620002395782518255916020019190600101906200021c565b50620002479291506200024b565b5090565b5b808211156200024757600081556001016200024c565b63b95aa35560e01b600052604160045260246000fd5b60005b83811015620002955781810151838201526020016200027b565b83811115620002a5576000848401525b50505050565b80516001600160a01b0381168114620002c357600080fd5b919050565b600080600060608486031215620002de57600080fd5b83516001600160401b0380821115620002f657600080fd5b818601915086601f8301126200030b57600080fd5b81518181111562000320576200032062000262565b604051601f8201601f19908116603f011681019083821181831017156200034b576200034b62000262565b816040528281528960208487010111156200036557600080fd5b6200037883602083016020880162000278565b80975050505050506200038e60208501620002ab565b91506200039e60408501620002ab565b90509250925092565b6020815260008251806020840152620003c881604085016020870162000278565b601f01601f19169190910160400192915050565b600060208284031215620003ef57600080fd5b815180151581146200040057600080fd5b9392505050565b600181811c908216806200041c57607f821691505b602082108114156200043e5763b95aa35560e01b600052602260045260246000fd5b50919050565b608051610aa96200047b6000396000818160870152818161020f015281816103920152818161042801526106370152610aa96000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c806344c219de1161005b57806344c219de146100ee5780634ae70cef146100ff578063bb850a5e14610116578063c698f7ef1461012b57600080fd5b8063164089ab146100825780631912cfa3146100c65780633c8ea23a146100d9575b600080fd5b6100a97f000000000000000000000000000000000000000000000000000000000000000081565b6040516001600160a01b0390911681526020015b60405180910390f35b6100a96100d43660046106aa565b61014e565b6100e1610178565b6040516100bd9190610710565b6001546040519081526020016100bd565b610107610206565b6040516100bd9392919061076e565b61011e61038e565b6040516100bd91906107b1565b61013e6101393660046107dc565b61041b565b60405190151581526020016100bd565b6001818154811061015e57600080fd5b6000918252602090912001546001600160a01b0316905081565b60008054610185906107f9565b80601f01602080910402602001604051908101604052809291908181526020018280546101b1906107f9565b80156101fe5780601f106101d3576101008083540402835291602001916101fe565b820191906000526020600020905b8154815290600101906020018083116101e157829003601f168201915b505050505081565b606080606060007f00000000000000000000000000000000000000000000000000000000000000006001600160a01b03166319cd57146040518163ffffffff1660e01b8152600401600060405180830381865afa15801561026b573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526102939190810190610855565b905060008160018280546102a6906107f9565b80601f01602080910402602001604051908101604052809291908181526020018280546102d2906107f9565b801561031f5780601f106102f45761010080835404028352916020019161031f565b820191906000526020600020905b81548152906001019060200180831161030257829003601f168201915b505050505092508080548060200260200160405190810160405280929190818152602001828054801561037b57602002820191906000526020600020905b81546001600160a01b0316815260019091019060200180831161035d575b5050505050905093509350935050909192565b60607f00000000000000000000000000000000000000000000000000000000000000006001600160a01b03166319cd57146040518163ffffffff1660e01b8152600401600060405180830381865afa1580156103ee573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526104169190810190610855565b905090565b6000336001600160a01b037f0000000000000000000000000000000000000000000000000000000000000000161461049257604051636381e58960e11b815260206004820152601560248201527413db9b1e48199858dd1bdc9e4818d85b8818d85b1b605a1b604482015260640160405180910390fd5b60005b60015481101561052e57600181815481106104b2576104b261091a565b6000918252602090912001546001600160a01b038481169116141561051c57826001600160a01b03167f203e4d63d889b167c2175d2e85dd4aee67a613817fa28698028d97a7d27178ba600060405161050b91906109d0565b60405180910390a250600092915050565b80610526816109e3565b915050610495565b5061053882610615565b156105ca5760018054808201825560009182527f801412028db4471413ca17a15806a4f6857ca4e7c2783885b524c92c7efcdc980180546001600160a01b0319166001600160a01b03851690811790915560405190917fa266095c895649d5b50e487baeae2cb9704aedcb7ff553bf6ff85894682d5003916105ba91906109d0565b60405180910390a2506001919050565b816001600160a01b03167fe29d311ed4c1008ad36d83da728f90af3c18dd7747ba510f85a3ae84eccd0ed460006040516106049190610a0c565b60405180910390a25060005b919050565b604051632372162f60e21b81526001600160a01b0382811660048301526000917f000000000000000000000000000000000000000000000000000000000000000090911690638dc858bc90602401602060405180830381865afa158015610680573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906106a49190610a51565b92915050565b6000602082840312156106bc57600080fd5b5035919050565b6000815180845260005b818110156106e9576020818501810151868301820152016106cd565b818111156106fb576000602083870101525b50601f01601f19169290920160200192915050565b60208152600061072360208301846106c3565b9392505050565b600081518084526020808501945080840160005b838110156107635781516001600160a01b03168752958201959082019060010161073e565b509495945050505050565b60608152600061078160608301866106c3565b8281036020840152610793818661072a565b905082810360408401526107a7818561072a565b9695505050505050565b602081526000610723602083018461072a565b6001600160a01b03811681146107d957600080fd5b50565b6000602082840312156107ee57600080fd5b8135610723816107c4565b600181811c9082168061080d57607f821691505b6020821081141561082e5763b95aa35560e01b600052602260045260246000fd5b50919050565b63b95aa35560e01b600052604160045260246000fd5b8051610610816107c4565b6000602080838503121561086857600080fd5b825167ffffffffffffffff8082111561088057600080fd5b818501915085601f83011261089457600080fd5b8151818111156108a6576108a6610834565b8060051b604051601f19603f830116810181811085821117156108cb576108cb610834565b6040529182528482019250838101850191888311156108e957600080fd5b938501935b8285101561090e576108ff8561084a565b845293850193928501926108ee565b98975050505050505050565b63b95aa35560e01b600052603260045260246000fd5b8054600090600181811c908083168061094a57607f831692505b602080841082141561096c5763b95aa35560e01b600052602260045260246000fd5b838852602088018280156109875760018114610998576109c3565b60ff198716825282820197506109c3565b60008981526020902060005b878110156109bd578154848201529086019084016109a4565b83019850505b5050505050505092915050565b6020815260006107236020830184610930565b6000600019821415610a055763b95aa35560e01b600052601160045260246000fd5b5060010190565b604081526000610a1f6040830184610930565b8281036020840152601081526f139bdd081a5b881dda1a5d195b1a5cdd60821b60208201526040810191505092915050565b600060208284031215610a6357600080fd5b8151801515811461072357600080fdfea264697066735822122014766d8cca65f11ce9c974ad4d7bd27a4b4155e9ff4cb32236d47cb4fb71811364736f6c634300080b0033"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"inputs\":[{\"internalType\":\"string\",\"name\":\"evi\",\"type\":\"string\"},{\"internalType\":\"address\",\"name\":\"addr\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"creator\",\"type\":\"address\"}],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"string\",\"name\":\"evi\",\"type\":\"string\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"signer\",\"type\":\"address\"}],\"name\":\"AddSignaturesEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"string\",\"name\":\"evi\",\"type\":\"string\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"signer\",\"type\":\"address\"}],\"name\":\"DuplicateSignature\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"string\",\"name\":\"evi\",\"type\":\"string\"},{\"indexed\":false,\"internalType\":\"address\",\"name\":\"signer\",\"type\":\"address\"}],\"name\":\"ErrorNewSignaturesEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"string\",\"name\":\"evi\",\"type\":\"string\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"signer\",\"type\":\"address\"}],\"name\":\"NewSignaturesEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"string\",\"name\":\"evi\",\"type\":\"string\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"signer\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"string\",\"name\":\"reason\",\"type\":\"string\"}],\"name\":\"SignatureFailed\",\"type\":\"event\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"signer\",\"type\":\"address\"}],\"name\":\"addSignatures\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"evidence\",\"outputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"factoryAddr\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"getEvidence\",\"outputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"},{\"internalType\":\"address[]\",\"name\":\"\",\"type\":\"address[]\"},{\"internalType\":\"address[]\",\"name\":\"\",\"type\":\"address[]\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"getFactorySigners\",\"outputs\":[{\"internalType\":\"address[]\",\"name\":\"\",\"type\":\"address[]\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"getSignersLength\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"signers\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_ADDSIGNATURES = "addSignatures";

    public static final String FUNC_EVIDENCE = "evidence";

    public static final String FUNC_FACTORYADDR = "factoryAddr";

    public static final String FUNC_GETEVIDENCE = "getEvidence";

    public static final String FUNC_GETFACTORYSIGNERS = "getFactorySigners";

    public static final String FUNC_GETSIGNERSLENGTH = "getSignersLength";

    public static final String FUNC_SIGNERS = "signers";

    public static final Event ADDSIGNATURESEVENT_EVENT = new Event("AddSignaturesEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event DUPLICATESIGNATURE_EVENT = new Event("DuplicateSignature", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event ERRORNEWSIGNATURESEVENT_EVENT = new Event("ErrorNewSignaturesEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event NEWSIGNATURESEVENT_EVENT = new Event("NewSignaturesEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event SIGNATUREFAILED_EVENT = new Event("SignatureFailed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    protected Evidence(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static String getABI() {
        return ABI;
    }

    public List<AddSignaturesEventEventResponse> getAddSignaturesEventEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADDSIGNATURESEVENT_EVENT, transactionReceipt);
        ArrayList<AddSignaturesEventEventResponse> responses = new ArrayList<AddSignaturesEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddSignaturesEventEventResponse typedResponse = new AddSignaturesEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.signer = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<DuplicateSignatureEventResponse> getDuplicateSignatureEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DUPLICATESIGNATURE_EVENT, transactionReceipt);
        ArrayList<DuplicateSignatureEventResponse> responses = new ArrayList<DuplicateSignatureEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DuplicateSignatureEventResponse typedResponse = new DuplicateSignatureEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.signer = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<ErrorNewSignaturesEventEventResponse> getErrorNewSignaturesEventEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ERRORNEWSIGNATURESEVENT_EVENT, transactionReceipt);
        ArrayList<ErrorNewSignaturesEventEventResponse> responses = new ArrayList<ErrorNewSignaturesEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ErrorNewSignaturesEventEventResponse typedResponse = new ErrorNewSignaturesEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.signer = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<NewSignaturesEventEventResponse> getNewSignaturesEventEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWSIGNATURESEVENT_EVENT, transactionReceipt);
        ArrayList<NewSignaturesEventEventResponse> responses = new ArrayList<NewSignaturesEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewSignaturesEventEventResponse typedResponse = new NewSignaturesEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.signer = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<SignatureFailedEventResponse> getSignatureFailedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SIGNATUREFAILED_EVENT, transactionReceipt);
        ArrayList<SignatureFailedEventResponse> responses = new ArrayList<SignatureFailedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SignatureFailedEventResponse typedResponse = new SignatureFailedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.signer = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.reason = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public TransactionReceipt addSignatures(String signer) {
        final Function function = new Function(
                FUNC_ADDSIGNATURES, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(signer)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForAddSignatures(String signer) {
        final Function function = new Function(
                FUNC_ADDSIGNATURES, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(signer)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String addSignatures(String signer, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ADDSIGNATURES, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(signer)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple1<String> getAddSignaturesInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ADDSIGNATURES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public Tuple1<Boolean> getAddSignaturesOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_ADDSIGNATURES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public String evidence() throws ContractException {
        final Function function = new Function(FUNC_EVIDENCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public void evidence(CallCallback callback) throws ContractException {
        final Function function = new Function(FUNC_EVIDENCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        asyncExecuteCall(function, callback);
    }

    public String factoryAddr() throws ContractException {
        final Function function = new Function(FUNC_FACTORYADDR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public void factoryAddr(CallCallback callback) throws ContractException {
        final Function function = new Function(FUNC_FACTORYADDR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        asyncExecuteCall(function, callback);
    }

    public Tuple3<String, List<String>, List<String>> getEvidence() throws ContractException {
        final Function function = new Function(FUNC_GETEVIDENCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Address>>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple3<String, List<String>, List<String>>(
                (String) results.get(0).getValue(), 
                convertToNative((List<Address>) results.get(1).getValue()), 
                convertToNative((List<Address>) results.get(2).getValue()));
    }

    public void getEvidence(CallCallback callback) throws ContractException {
        final Function function = new Function(FUNC_GETEVIDENCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Address>>() {}));
        asyncExecuteCall(function, callback);
    }

    public List getFactorySigners() throws ContractException {
        final Function function = new Function(FUNC_GETFACTORYSIGNERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        List<Type> result = (List<Type>) executeCallWithSingleValueReturn(function, List.class);
        return convertToNative(result);
    }

    public void getFactorySigners(CallCallback callback) throws ContractException {
        final Function function = new Function(FUNC_GETFACTORYSIGNERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        asyncExecuteCall(function, callback);
    }

    public BigInteger getSignersLength() throws ContractException {
        final Function function = new Function(FUNC_GETSIGNERSLENGTH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public void getSignersLength(CallCallback callback) throws ContractException {
        final Function function = new Function(FUNC_GETSIGNERSLENGTH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        asyncExecuteCall(function, callback);
    }

    public String signers(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_SIGNERS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public void signers(BigInteger param0, CallCallback callback) throws ContractException {
        final Function function = new Function(FUNC_SIGNERS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        asyncExecuteCall(function, callback);
    }

    public static Evidence load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new Evidence(contractAddress, client, credential);
    }

    public static Evidence deploy(Client client, CryptoKeyPair credential, String evi, String addr,
            String creator) throws ContractException {
        byte[] encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(evi), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(addr), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(creator)));
        return deploy(Evidence.class, client, credential, getBinary(client.getCryptoSuite()), getABI(), encodedConstructor, null);
    }

    public static class AddSignaturesEventEventResponse {
        public TransactionReceipt.Logs log;

        public String signer;

        public String evi;
    }

    public static class DuplicateSignatureEventResponse {
        public TransactionReceipt.Logs log;

        public String signer;

        public String evi;
    }

    public static class ErrorNewSignaturesEventEventResponse {
        public TransactionReceipt.Logs log;

        public String evi;

        public String signer;
    }

    public static class NewSignaturesEventEventResponse {
        public TransactionReceipt.Logs log;

        public String signer;

        public String evi;
    }

    public static class SignatureFailedEventResponse {
        public TransactionReceipt.Logs log;

        public String signer;

        public String evi;

        public String reason;
    }
}
