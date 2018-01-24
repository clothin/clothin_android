package si.cit.clothingorigin.Blockchain;

import android.content.Context;
import android.content.res.AssetManager;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import si.cit.clothingorigin.Interfaces.ContractResultListener;
import timber.log.Timber;

/**
 * Created by Anže Kožar on 23.1.2018.
 * nzkozar@gmail.com
 */

public class BlockchainConnector {

    private static final String contractAddress = "0x5dd26a62db3b73fd984b6d14ad8511ed3c29adf7";

    private Context mContext;
    private Web3j web3j;
    private ClothingTrackingV6 contract;

    public BlockchainConnector(Context context){
        mContext = context;
        try {
            web3j = Web3jFactory.build(new HttpService("http://178.62.45.240:8545"));
            final File walletFile = getWalletFile();
            //Credentials credentials = WalletUtils.loadCredentials("test", walletFile);
            Credentials credentials1 = Credentials.create("35422515660277182308253797447660852175380398836676478606475581938674196088398");
            Timber.i("Credentials loaded!");

            contract =  ClothingTrackingV6.load(mContext,contractAddress, web3j, credentials1, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
            String contractAddress = contract.getContractAddress();
            Timber.i("Contract deployed to address "+contractAddress);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private File getWalletFile(){
        try {
            AssetManager assetManager = mContext.getAssets();
            InputStream inputStream = assetManager.open("WalletFile.json");

            return createFileFromInputStream(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private File createFileFromInputStream(InputStream inputStream) {
        Timber.i(mContext.getFilesDir().getPath());
        try{
            File f = new File(mContext.getFilesDir().getPath()+"/tmp.json");
            OutputStream outputStream = new FileOutputStream(f);
            byte buffer[] = new byte[1024];
            int length = 0;

            while((length=inputStream.read(buffer)) > 0) {
                outputStream.write(buffer,0,length);
            }

            outputStream.close();
            inputStream.close();

            return f;
        }catch (IOException e) {
            //Logging exception
        }
        return null;
    }

    public String getRemoteClientVersion(){
        Web3ClientVersion web3ClientVersion = null;
        try {
            web3ClientVersion = web3j.web3ClientVersion().sendAsync().get();
            return web3ClientVersion.getWeb3ClientVersion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "null";
    }

    public void getProduct(long productId,final ContractResultListener listener){
        final Future future = contract.getProductObject(BigInteger.valueOf(productId)).sendAsync();
        startFutureListener(future,listener);
    }

    public void getProductProductionChainInfo(long productId,final ContractResultListener listener){
        final Future future = contract.getProductProductionChain(BigInteger.valueOf(productId)).sendAsync();
        startFutureListener(future,listener);
    }

    private void startFutureListener(final Future future,final ContractResultListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!future.isDone() && !future.isCancelled()){

                }
                try {
                    listener.onContractResult(future.get(),!future.isCancelled());
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.onContractResult(null,false);
                }
            }
        }).start();
    }
}
