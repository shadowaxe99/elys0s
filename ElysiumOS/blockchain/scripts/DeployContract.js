const { ethers } = require("ethers");
const fs = require("fs");

const provider = new ethers.providers.JsonRpcProvider("https://arbitrum-rinkeby.infura.io/v3/YOUR_INFURA_PROJECT_ID");
const wallet = new ethers.Wallet("YOUR_PRIVATE_KEY", provider);

const contractFilePath = "ElysiumOS/blockchain/contracts/ElysiumToken.sol";
const contractName = "ElysiumToken";

async function main() {
    const contractSource = fs.readFileSync(contractFilePath, "utf8");
    const compiledContract = compileContract(contractSource, contractName);
    const deployedContract = await deployContract(compiledContract);
    console.log(`Contract deployed at address: ${deployedContract.address}`);
}

function compileContract(source, contractName) {
    const solc = require("solc");
    const input = {
        language: "Solidity",
        sources: {
            [contractName]: {
                content: source,
            },
        },
        settings: {
            outputSelection: {
                "*": {
                    "*": ["*"],
                },
            },
        },
    };

    const output = JSON.parse(solc.compile(JSON.stringify(input)));
    const contract = output.contracts[contractName][contractName];
    return {
        abi: contract.abi,
        bytecode: contract.evm.bytecode.object,
    };
}

async function deployContract(compiledContract) {
    const ContractFactory = new ethers.ContractFactory(compiledContract.abi, compiledContract.bytecode, wallet);
    const contract = await ContractFactory.deploy();
    await contract.deployed();
    return contract;
}

main().catch((error) => {
    console.error(error);
    process.exitCode = 1;
});