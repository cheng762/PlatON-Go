package network.platon.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.PlatonFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.GasProvider;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 0.7.5.0.
 */
public class Guessing extends Contract {
    private static final String BINARY = "60806040526000600260006101000a81548160ff021916908315150217905550674563918244f400006003556000600a556000600d5534801561004157600080fd5b506040516111bb3803806111bb8339818101604052602081101561006457600080fd5b810190808051906020019092919050505033600c60006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600081905550506110ee806100cd6000396000f3fe6080604052600436106101355760003560e01c80638941f2f0116100ab578063b69ef8a81161006f578063b69ef8a8146105e2578063c2930f911461060d578063c29bde8c14610638578063c7f40b6414610663578063ecbde5e6146106ba578063ff37be16146106e557610135565b80638941f2f01461042857806394696a9214610477578063a46c3637146104e3578063ae4479411461050e578063b03e00771461057357610135565b806313eaca43116100fd57806313eaca43146102b85780631ef9c56f146102e7578063220bc55e14610353578063629374ab1461035d5780636e5ab6711461039857806382d333a0146103fd57610135565b8063045f9c9714610141578063062d6a98146101bc578063083c6323146101e7578063094cc1ab146102125780630b8b85021461023d575b61013f3433610710565b005b34801561014d57600080fd5b5061017a6004803603602081101561016457600080fd5b81019080803590602001909291905050506108d3565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101c857600080fd5b506101d161090f565b6040518082815260200191505060405180910390f35b3480156101f357600080fd5b506101fc610915565b6040518082815260200191505060405180910390f35b34801561021e57600080fd5b5061022761091b565b6040518082815260200191505060405180910390f35b34801561024957600080fd5b506102766004803603602081101561026057600080fd5b8101908080359060200190929190505050610921565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156102c457600080fd5b506102cd610954565b604051808215151515815260200191505060405180910390f35b3480156102f357600080fd5b506102fc610967565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b8381101561033f578082015181840152602081019050610324565b505050509050019250505060405180910390f35b61035b6109f5565b005b34801561036957600080fd5b506103966004803603602081101561038057600080fd5b8101908080359060200190929190505050610a01565b005b3480156103a457600080fd5b506103e7600480360360208110156103bb57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610d52565b6040518082815260200191505060405180910390f35b34801561040957600080fd5b50610412610d6a565b6040518082815260200191505060405180910390f35b34801561043457600080fd5b506104616004803603602081101561044b57600080fd5b8101908080359060200190929190505050610d70565b6040518082815260200191505060405180910390f35b34801561048357600080fd5b5061048c610d99565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b838110156104cf5780820151818401526020810190506104b4565b505050509050019250505060405180910390f35b3480156104ef57600080fd5b506104f8610e2e565b6040518082815260200191505060405180910390f35b34801561051a57600080fd5b5061055d6004803603602081101561053157600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610e3b565b6040518082815260200191505060405180910390f35b34801561057f57600080fd5b506105cc6004803603604081101561059657600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610e53565b6040518082815260200191505060405180910390f35b3480156105ee57600080fd5b506105f7610e81565b6040518082815260200191505060405180910390f35b34801561061957600080fd5b50610622610e87565b6040518082815260200191505060405180910390f35b34801561064457600080fd5b5061064d610e8d565b6040518082815260200191505060405180910390f35b34801561066f57600080fd5b50610678610e93565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156106c657600080fd5b506106cf610eb9565b6040518082815260200191505060405180910390f35b3480156106f157600080fd5b506106fa610ed8565b6040518082815260200191505060405180910390f35b43600054106108cf5760035482101561072857600080fd5b600860008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600a5490806001815401808255809150509060018203906000526020600020016000909192909190915055508060076000600a54815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600a6000815480929190600101919050555081600660008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282540192505081905550816004600082825401925050819055507fe842aea7a5f1b01049d752008c53c52890b1a6daf660cf39e8eec506112bbdf681836001604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183815260200182151515158152602001935050505060405180910390a15b5050565b600b81815481106108e057fe5b906000526020600020016000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600a5481565b60005481565b600d5481565b60076020528060005260406000206000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600260009054906101000a900460ff1681565b6060600b8054806020026020016040519081016040528092919081815260200182805480156109eb57602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190600101908083116109a1575b5050505050905090565b6109ff3433610710565b565b600054431115610d4f57600260009054906101000a900460ff16158015610a7557503373ffffffffffffffffffffffffffffffffffffffff16600c60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b8015610a8357506000600a54115b15610d4e57600081604051602001808281526020019150506040516020818303038152906040528051906020012060001c90506000600a548281610ac357fe5b0690506064600a541015610ae157610adc81600a610ee2565b610b0b565b612710600a541015610afd57610af8816064610ee2565b610b0a565b610b09816103e8610ee2565b5b5b600b8054905060045481610b1b57fe5b04600581905550600080600090505b600b80549050811015610d2757600160096000600b8481548110610b4a57fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020540160096000600b8481548110610bc257fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550600073ffffffffffffffffffffffffffffffffffffffff16600b8281548110610c5057fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614610d1a57600b8181548110610ca357fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1691508173ffffffffffffffffffffffffffffffffffffffff166108fc6005549081150290604051600060405180830381858888f19350505050158015610d18573d6000803e3d6000fd5b505b8080600101915050610b2a565b506001600260006101000a81548160ff021916908315150217905550836001819055505050505b5b50565b60096020528060005260406000206000915090505481565b60055481565b6000610101430382118015610d8757506001430382105b610d9057600080fd5b81409050919050565b6060600860003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020805480602002602001604051908101604052809291908181526020018280548015610e2457602002820191906000526020600020905b815481526020019060010190808311610e10575b5050505050905090565b6000600b80549050905090565b60066020528060005260406000206000915090505481565b60086020528160005260406000208181548110610e6c57fe5b90600052602060002001600091509150505481565b60045481565b60035481565b60015481565b600c60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60003073ffffffffffffffffffffffffffffffffffffffff1631905090565b6000600154905090565b808281610eeb57fe5b06600d819055506000600d541415610fd05760008090505b600a54811015610fca57600082600d54830381610f1c57fe5b061415610fbd57600b6007600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505b8080600101915050610f03565b506110b5565b60008090505b600a548110156110b3576000828281610feb57fe5b06141580156110075750600082600d5483038161100457fe5b06145b156110a657600b6007600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505b8080600101915050610fd6565b505b505056fea265627a7a723158208f1178a17dddf9accda690f0f9da8e5f2cef18a51436468bcb328c3521843bce64736f6c634300050d0032";

    public static final String FUNC_AVERAGEAMOUNT = "averageAmount";

    public static final String FUNC_BALANCE = "balance";

    public static final String FUNC_BASEUNIT = "baseUnit";

    public static final String FUNC_BLOCK_HASH = "block_hash";

    public static final String FUNC_CREATEADDRESS = "createAddress";

    public static final String FUNC_DRAW = "draw";

    public static final String FUNC_ENDBLOCK = "endBlock";

    public static final String FUNC_GENERATEBLOCKHASH = "generateBlockHash";

    public static final String FUNC_GETBALANCEOF = "getBalanceOf";

    public static final String FUNC_GETENDBLOCKHASH = "getEndBlockHash";

    public static final String FUNC_GETMYGUESSCODES = "getMyGuessCodes";

    public static final String FUNC_GETWINNERADDRESSES = "getWinnerAddresses";

    public static final String FUNC_GETWINNERCOUNT = "getWinnerCount";

    public static final String FUNC_GUESSINGCLOSED = "guessingClosed";

    public static final String FUNC_GUESSINGWITHLAT = "guessingWithLat";

    public static final String FUNC_GUSSINGERCODES = "gussingerCodes";

    public static final String FUNC_GUSSINGERLAT = "gussingerLat";

    public static final String FUNC_INDEXKEY = "indexKey";

    public static final String FUNC_INDEXOFGUSSINGER = "indexOfgussinger";

    public static final String FUNC_POSTFIX = "postfix";

    public static final String FUNC_WINNERADDRESSES = "winnerAddresses";

    public static final String FUNC_WINNERMAP = "winnerMap";

    public static final Event CURRENTBALANCE_EVENT = new Event("CurrentBalance", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event FUNDTRANSFER_EVENT = new Event("FundTransfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
    ;

    @Deprecated
    protected Guessing(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Guessing(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Guessing(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Guessing(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Guessing> deploy(Web3j web3j, Credentials credentials, GasProvider contractGasProvider, BigInteger _endBlock) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_endBlock)));
        return deployRemoteCall(Guessing.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Guessing> deploy(Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, BigInteger _endBlock) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_endBlock)));
        return deployRemoteCall(Guessing.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Guessing> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _endBlock) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_endBlock)));
        return deployRemoteCall(Guessing.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Guessing> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _endBlock) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_endBlock)));
        return deployRemoteCall(Guessing.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<CurrentBalanceEventResponse> getCurrentBalanceEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CURRENTBALANCE_EVENT, transactionReceipt);
        ArrayList<CurrentBalanceEventResponse> responses = new ArrayList<CurrentBalanceEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CurrentBalanceEventResponse typedResponse = new CurrentBalanceEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._msgSenderAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._balance = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CurrentBalanceEventResponse> currentBalanceEventObservable(PlatonFilter filter) {
        return web3j.platonLogObservable(filter).map(new Func1<Log, CurrentBalanceEventResponse>() {
            @Override
            public CurrentBalanceEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CURRENTBALANCE_EVENT, log);
                CurrentBalanceEventResponse typedResponse = new CurrentBalanceEventResponse();
                typedResponse.log = log;
                typedResponse._msgSenderAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._balance = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CurrentBalanceEventResponse> currentBalanceEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        PlatonFilter filter = new PlatonFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CURRENTBALANCE_EVENT));
        return currentBalanceEventObservable(filter);
    }

    public List<FundTransferEventResponse> getFundTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(FUNDTRANSFER_EVENT, transactionReceipt);
        ArrayList<FundTransferEventResponse> responses = new ArrayList<FundTransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            FundTransferEventResponse typedResponse = new FundTransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._backer = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._isSuccess = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<FundTransferEventResponse> fundTransferEventObservable(PlatonFilter filter) {
        return web3j.platonLogObservable(filter).map(new Func1<Log, FundTransferEventResponse>() {
            @Override
            public FundTransferEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(FUNDTRANSFER_EVENT, log);
                FundTransferEventResponse typedResponse = new FundTransferEventResponse();
                typedResponse.log = log;
                typedResponse._backer = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._isSuccess = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<FundTransferEventResponse> fundTransferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        PlatonFilter filter = new PlatonFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FUNDTRANSFER_EVENT));
        return fundTransferEventObservable(filter);
    }

    public RemoteCall<BigInteger> averageAmount() {
        final Function function = new Function(FUNC_AVERAGEAMOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> balance() {
        final Function function = new Function(FUNC_BALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> baseUnit() {
        final Function function = new Function(FUNC_BASEUNIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<byte[]> block_hash() {
        final Function function = new Function(FUNC_BLOCK_HASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<String> createAddress() {
        final Function function = new Function(FUNC_CREATEADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> draw(byte[] _block_hash) {
        final Function function = new Function(
                FUNC_DRAW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_block_hash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> endBlock() {
        final Function function = new Function(FUNC_ENDBLOCK, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<byte[]> generateBlockHash(BigInteger _blocknumber) {
        final Function function = new Function(FUNC_GENERATEBLOCKHASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_blocknumber)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<BigInteger> getBalanceOf() {
        final Function function = new Function(FUNC_GETBALANCEOF, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<byte[]> getEndBlockHash() {
        final Function function = new Function(FUNC_GETENDBLOCKHASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<List> getMyGuessCodes() {
        final Function function = new Function(FUNC_GETMYGUESSCODES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<List> getWinnerAddresses() {
        final Function function = new Function(FUNC_GETWINNERADDRESSES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<BigInteger> getWinnerCount() {
        final Function function = new Function(FUNC_GETWINNERCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Boolean> guessingClosed() {
        final Function function = new Function(FUNC_GUESSINGCLOSED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> guessingWithLat(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_GUESSINGWITHLAT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<BigInteger> gussingerCodes(String param0, BigInteger param1) {
        final Function function = new Function(FUNC_GUSSINGERCODES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> gussingerLat(String param0) {
        final Function function = new Function(FUNC_GUSSINGERLAT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> indexKey() {
        final Function function = new Function(FUNC_INDEXKEY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> indexOfgussinger(BigInteger param0) {
        final Function function = new Function(FUNC_INDEXOFGUSSINGER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> postfix() {
        final Function function = new Function(FUNC_POSTFIX, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> winnerAddresses(BigInteger param0) {
        final Function function = new Function(FUNC_WINNERADDRESSES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> winnerMap(String param0) {
        final Function function = new Function(FUNC_WINNERMAP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static Guessing load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Guessing(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Guessing load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Guessing(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Guessing load(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider) {
        return new Guessing(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Guessing load(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider) {
        return new Guessing(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class CurrentBalanceEventResponse {
        public Log log;

        public String _msgSenderAddress;

        public BigInteger _balance;
    }

    public static class FundTransferEventResponse {
        public Log log;

        public String _backer;

        public BigInteger _amount;

        public Boolean _isSuccess;
    }
}