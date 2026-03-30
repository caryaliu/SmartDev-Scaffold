package org.fisco.asset.Asset.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.codec.datatypes.Address;
import org.fisco.bcos.sdk.v3.codec.datatypes.Event;
import org.fisco.bcos.sdk.v3.codec.datatypes.Function;
import org.fisco.bcos.sdk.v3.codec.datatypes.Type;
import org.fisco.bcos.sdk.v3.codec.datatypes.TypeReference;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.v3.contract.Contract;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.CryptoType;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.model.callback.CallCallback;
import org.fisco.bcos.sdk.v3.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class Asset extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50600080546001600160a01b0319163317905561049e806100326000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c80631d1438481461005157806327e235e314610081578063867904b4146100af578063d0679d34146100c4575b600080fd5b600054610064906001600160a01b031681565b6040516001600160a01b0390911681526020015b60405180910390f35b6100a161008f366004610393565b60016020526000908152604090205481565b604051908152602001610078565b6100c26100bd3660046103b5565b6100d7565b005b6100c26100d23660046103b5565b610214565b6000546001600160a01b031633146101365760405162461bcd60e51b815260206004820152601b60248201527f41737365743a206f6e6c79206973737565722063616e2063616c6c000000000060448201526064015b60405180910390fd5b6001600160a01b0382166101855760405162461bcd60e51b815260206004820152601660248201527541737365743a20696e76616c6964206164647265737360501b604482015260640161012d565b600081116101a55760405162461bcd60e51b815260040161012d906103df565b6001600160a01b038216600090815260016020526040812080548392906101cd908490610439565b90915550506040518181526001600160a01b038316907fc65a3f767206d2fdcede0b094a4840e01c0dd0be1888b5ba800346eaa0123c169060200160405180910390a25050565b6001600160a01b0382166102635760405162461bcd60e51b815260206004820152601660248201527541737365743a20696e76616c6964206164647265737360501b604482015260640161012d565b600081116102835760405162461bcd60e51b815260040161012d906103df565b336000908152600160205260409020548111156102e25760405162461bcd60e51b815260206004820152601b60248201527f41737365743a20696e73756666696369656e742062616c616e63650000000000604482015260640161012d565b3360009081526001602052604081208054839290610301908490610451565b90915550506001600160a01b0382166000908152600160205260408120805483929061032e908490610439565b90915550506040518181526001600160a01b0383169033907f93eb3c629eb575edaf0252e4f9fc0c5ccada50496f8c1d32f0f93a65a8257eb59060200160405180910390a35050565b80356001600160a01b038116811461038e57600080fd5b919050565b6000602082840312156103a557600080fd5b6103ae82610377565b9392505050565b600080604083850312156103c857600080fd5b6103d183610377565b946020939093013593505050565b60208082526024908201527f41737365743a20616d6f756e74206d75737420626520677265617465722074686040820152630616e20360e41b606082015260800190565b634e487b7160e01b600052601160045260246000fd5b6000821982111561044c5761044c610423565b500190565b60008282101561046357610463610423565b50039056fea264697066735822122090f154729be2d573169b528782c7fa67eba793a8dd0cad6b8af202b31cdf6a7764736f6c634300080b0033"};

    public static final String BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b50600080546001600160a01b031916331790556104a4806100326000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c806396cdbf1e14610051578063cf1b810414610066578063e3f36d6314610096578063e6505ace146100c4575b600080fd5b61006461005f366004610399565b6100d7565b005b600054610079906001600160a01b031681565b6040516001600160a01b0390911681526020015b60405180910390f35b6100b66100a43660046103c3565b60016020526000908152604090205481565b60405190815260200161008d565b6100646100d2366004610399565b610242565b6001600160a01b03821661012c57604051636381e58960e11b815260206004820152601660248201527541737365743a20696e76616c6964206164647265737360501b60448201526064015b60405180910390fd5b6000811161014d57604051636381e58960e11b8152600401610123906103e5565b336000908152600160205260409020548111156101ad57604051636381e58960e11b815260206004820152601b60248201527f41737365743a20696e73756666696369656e742062616c616e636500000000006044820152606401610123565b33600090815260016020526040812080548392906101cc90849061043f565b90915550506001600160a01b038216600090815260016020526040812080548392906101f9908490610456565b90915550506040518181526001600160a01b0383169033907f492dcc1a5ec44b44775530ad72067581d508542c8fd91e6ff77453ed212cdad49060200160405180910390a35050565b6000546001600160a01b0316331461029d57604051636381e58960e11b815260206004820152601b60248201527f41737365743a206f6e6c79206973737565722063616e2063616c6c00000000006044820152606401610123565b6001600160a01b0382166102ed57604051636381e58960e11b815260206004820152601660248201527541737365743a20696e76616c6964206164647265737360501b6044820152606401610123565b6000811161030e57604051636381e58960e11b8152600401610123906103e5565b6001600160a01b03821660009081526001602052604081208054839290610336908490610456565b90915550506040518181526001600160a01b038316907fa7a829948d4758f057969ddde8a57b1408d8e8c593629889793c026230c55d879060200160405180910390a25050565b80356001600160a01b038116811461039457600080fd5b919050565b600080604083850312156103ac57600080fd5b6103b58361037d565b946020939093013593505050565b6000602082840312156103d557600080fd5b6103de8261037d565b9392505050565b60208082526024908201527f41737365743a20616d6f756e74206d75737420626520677265617465722074686040820152630616e20360e41b606082015260800190565b63b95aa35560e01b600052601160045260246000fd5b60008282101561045157610451610429565b500390565b6000821982111561046957610469610429565b50019056fea2646970667358221220bf6dd53107355826c95c0b06825dba0870ec6b30030e83ac5af185ece9aafdbf64736f6c634300080b0033"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"inputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"Issue\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"from\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"Send\",\"type\":\"event\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"name\":\"balances\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"issue\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"issuer\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"send\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_BALANCES = "balances";

    public static final String FUNC_ISSUE = "issue";

    public static final String FUNC_ISSUER = "issuer";

    public static final String FUNC_SEND = "send";

    public static final Event ISSUE_EVENT = new Event("Issue", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event SEND_EVENT = new Event("Send", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    protected Asset(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static String getABI() {
        return ABI;
    }

    public List<IssueEventResponse> getIssueEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ISSUE_EVENT, transactionReceipt);
        ArrayList<IssueEventResponse> responses = new ArrayList<IssueEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            IssueEventResponse typedResponse = new IssueEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.to = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<SendEventResponse> getSendEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SEND_EVENT, transactionReceipt);
        ArrayList<SendEventResponse> responses = new ArrayList<SendEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SendEventResponse typedResponse = new SendEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public BigInteger balances(String param0) throws ContractException {
        final Function function = new Function(FUNC_BALANCES, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public void balances(String param0, CallCallback callback) throws ContractException {
        final Function function = new Function(FUNC_BALANCES, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        asyncExecuteCall(function, callback);
    }

    public TransactionReceipt issue(String to, BigInteger amount) {
        final Function function = new Function(
                FUNC_ISSUE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(to), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForIssue(String to, BigInteger amount) {
        final Function function = new Function(
                FUNC_ISSUE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(to), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String issue(String to, BigInteger amount, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ISSUE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(to), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple2<String, BigInteger> getIssueInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ISSUE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public String issuer() throws ContractException {
        final Function function = new Function(FUNC_ISSUER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public void issuer(CallCallback callback) throws ContractException {
        final Function function = new Function(FUNC_ISSUER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        asyncExecuteCall(function, callback);
    }

    public TransactionReceipt send(String to, BigInteger amount) {
        final Function function = new Function(
                FUNC_SEND, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(to), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForSend(String to, BigInteger amount) {
        final Function function = new Function(
                FUNC_SEND, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(to), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String send(String to, BigInteger amount, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SEND, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(to), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple2<String, BigInteger> getSendInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SEND, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public static Asset load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new Asset(contractAddress, client, credential);
    }

    public static Asset deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(Asset.class, client, credential, getBinary(client.getCryptoSuite()), getABI(), null, null);
    }

    public static class IssueEventResponse {
        public TransactionReceipt.Logs log;

        public String to;

        public BigInteger amount;
    }

    public static class SendEventResponse {
        public TransactionReceipt.Logs log;

        public String from;

        public String to;

        public BigInteger amount;
    }
}
