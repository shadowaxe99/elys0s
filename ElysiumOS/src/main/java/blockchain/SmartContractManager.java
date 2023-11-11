```java
package blockchain;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import contracts.ElysiumToken;
import java.math.BigInteger;

public class SmartContractManager {

    private final Web3j web3j;
    private final Credentials credentials;
    private final ContractGasProvider gasProvider;
    private ElysiumToken elysiumTokenContract;

    public SmartContractManager(String rpcUrl, String privateKey) {
        this.web3j = Web3j.build(new HttpService(rpcUrl));
        this.credentials = Credentials.create(privateKey);
        this.gasProvider = new DefaultGasProvider();
    }

    public void deployElysiumTokenContract() throws Exception {
        elysiumTokenContract = ElysiumToken.deploy(
                web3j, credentials, gasProvider,
                BigInteger.ZERO, // Initial supply
                "Elysium Token", // Token name
                "ELYS" // Token symbol
        ).send();
    }

    public String getElysiumTokenContractAddress() {
        return elysiumTokenContract.getContractAddress();
    }

    public void transferElysiumTokens(Address to, Uint256 value) throws Exception {
        elysiumTokenContract.transfer(to, value).send();
    }

    public BigInteger getElysiumTokenBalance(Address owner) throws Exception {
        return elysiumTokenContract.balanceOf(owner).send();
    }

    // Additional smart contract management functions can be added here

}
```