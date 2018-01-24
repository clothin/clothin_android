package si.cit.clothingorigin.Blockchain;

import android.content.Context;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import si.cit.clothingorigin.Objects.Product;
import si.cit.clothingorigin.R;

/**
 * Created by Anže Kožar on 23.1.2018.
 * nzkozar@gmail.com
 */

public final class ClothingTrackingV6 extends Contract {

    private ClothingTrackingV6(Context context, String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(context.getString(R.string.clothingBinary), contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private ClothingTrackingV6(Context context, String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(context.getString(R.string.clothingBinary), contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<BigInteger> countFactories() {
        Function function = new Function("countFactories",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple2<String, BigInteger>> getProductProductionChain(BigInteger _id) {
        final Function function = new Function("getProductFlowInfo",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple2<String, BigInteger>>(
                new Callable<Tuple2<String, BigInteger>>() {
                    @Override
                    public Tuple2<String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, BigInteger>(
                                (String) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<Tuple4<String, String, BigInteger, String>> getFactory(BigInteger _id) {
        final Function function = new Function("getFactory",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple4<String, String, BigInteger, String>>(
                new Callable<Tuple4<String, String, BigInteger, String>>() {
                    @Override
                    public Tuple4<String, String, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, String, BigInteger, String>(
                                (String) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (String) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> removeFactory(BigInteger code) {
        Function function = new Function(
                "removeFactory",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(code)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addFactoryToProduct(BigInteger _productID, BigInteger _factoryID, String _work) {
        Function function = new Function(
                "addFactoryToProduct",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_productID),
                        new org.web3j.abi.datatypes.generated.Uint256(_factoryID),
                        new org.web3j.abi.datatypes.Utf8String(_work)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addProduct(BigInteger _id, String _name, String _size, String _material, String _color, BigInteger _price, String _pic) {
        Function function = new Function(
                "addProduct",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id),
                        new org.web3j.abi.datatypes.Utf8String(_name),
                        new org.web3j.abi.datatypes.Utf8String(_size),
                        new org.web3j.abi.datatypes.Utf8String(_material),
                        new org.web3j.abi.datatypes.Utf8String(_color),
                        new org.web3j.abi.datatypes.generated.Uint256(_price),
                        new org.web3j.abi.datatypes.Utf8String(_pic)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Product> getProductObject(final BigInteger _id) {
        final Function function = new Function("getProductInfo",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id)),
                Arrays.<TypeReference<?>>asList(
                        new TypeReference<Utf8String>() {},
                        new TypeReference<Utf8String>() {},
                        new TypeReference<Utf8String>() {},
                        new TypeReference<Utf8String>() {},
                        new TypeReference<Uint256>() {},
                        new TypeReference<Utf8String>() {}
                        )
        );
        return new RemoteCall<>(
                new Callable<Product>() {
                    @Override
                    public Product call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        Product product = new Product();
                        product.id = Long.valueOf(_id.toString());
                        product.title = (String)results.get(0).getValue();
                        product.size = (String)results.get(1).getValue();
                        product.materials = (String)results.get(2).getValue();
                        product.color = (String)results.get(3).getValue();
                        product.price = String.valueOf(results.get(4).getValue());
                        product.picture_url = (String)results.get(5).getValue();

                        return product;
                    }
                });
    }

    public RemoteCall<TransactionReceipt> addFactory(BigInteger _id, String _country, String _name, String _tip, String _loc, BigInteger _score) {
        Function function = new Function(
                "addFactory",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id),
                        new org.web3j.abi.datatypes.Utf8String(_country),
                        new org.web3j.abi.datatypes.Utf8String(_name),
                        new org.web3j.abi.datatypes.Utf8String(_tip),
                        new org.web3j.abi.datatypes.Utf8String(_loc),
                        new org.web3j.abi.datatypes.generated.Uint256(_score)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<ClothingTrackingV6> deploy(Context context, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ClothingTrackingV6.class, web3j, credentials, gasPrice, gasLimit, context.getString(R.string.clothingBinary), "");
    }

    public static RemoteCall<ClothingTrackingV6> deploy(Context context, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ClothingTrackingV6.class, web3j, transactionManager, gasPrice, gasLimit, context.getString(R.string.clothingBinary), "");
    }

    public static ClothingTrackingV6 load(Context context, String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ClothingTrackingV6(context, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static ClothingTrackingV6 load(Context context, String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ClothingTrackingV6(context, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}