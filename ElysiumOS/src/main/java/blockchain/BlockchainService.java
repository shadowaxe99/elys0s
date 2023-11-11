package blockchain;

import org.web3j.abi.datatypes.Address;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class BlockchainService {

    private final Web3j web3j;
    private final ContractGasProvider gasProvider;
    private final String privateKey;
    private final String contractAddress;

    public BlockchainService(String rpcUrl, String privateKey, String contractAddress) {
        this.web3j = Web3j.build(new HttpService(rpcUrl));
        this.gasProvider = new DefaultGasProvider();
        this.privateKey = privateKey;
        this.contractAddress = contractAddress;
    }

    public String deployElysiumTokenContract() throws Exception {
        ElysiumToken token = ElysiumToken.deploy(
                web3j,
                new org.web3j.crypto.Credentials(privateKey),
                gasProvider
        ).send();
        return token.getContractAddress();
    }

    public ElysiumToken loadElysiumTokenContract() {
        return ElysiumToken.load(
                contractAddress,
                web3j,
                new org.web3j.crypto.Credentials(privateKey),
                gasProvider
        );
    }

    public BigInteger getBalance(String address) throws ExecutionException, InterruptedException {
        ElysiumToken token = loadElysiumTokenContract();
        return token.balanceOf(new Address(address)).sendAsync().get().getValue();
    }

    public String transferElysiumToken(String toAddress, BigInteger amount) throws Exception {
        ElysiumToken token = loadElysiumTokenContract();
        return token.transfer(new Address(toAddress), amount).send().getTransactionHash();
    }

    public boolean isContractDeployed() {
        return contractAddress != null && !contractAddress.isEmpty();
    }

    // Additional blockchain-related methods can be added here to interact with the smart contracts and the blockchain network

    // Close the connection to the Ethereum node
    public void shutdown() {
        web3j.shutdown();
    }
}