const assert = require('assert');
const ethers = require('ethers');
const { ElysiumToken } = require('../contracts/ElysiumToken.sol');

describe('ElysiumToken Smart Contract Tests', function () {
  let provider;
  let owner;
  let addr1;
  let addr2;
  let contract;

  before(async () => {
    provider = new ethers.providers.JsonRpcProvider();
    const wallet = ethers.Wallet.createRandom();
    owner = wallet.connect(provider);
    const wallet1 = ethers.Wallet.createRandom();
    addr1 = wallet1.connect(provider);
    const wallet2 = ethers.Wallet.createRandom();
    addr2 = wallet2.connect(provider);

    const ElysiumTokenFactory = new ethers.ContractFactory(
      ElysiumToken.abi,
      ElysiumToken.bytecode,
      owner
    );

    contract = await ElysiumTokenFactory.deploy();
  });

  describe('Deployment', () => {
    it('Should set the right owner', async () => {
      assert.equal(await contract.owner(), owner.address);
    });

    it('Should assign the total supply of tokens to the owner', async () => {
      const ownerBalance = await contract.balanceOf(owner.address);
      assert.equal(await contract.totalSupply(), ownerBalance);
    });
  });

  describe('Transactions', () => {
    it('Should transfer tokens between accounts', async () => {
      // Transfer 50 tokens from owner to addr1
      await contract.transfer(addr1.address, 50);
      const addr1Balance = await contract.balanceOf(addr1.address);
      assert.equal(addr1Balance, 50);

      // Transfer 50 tokens from addr1 to addr2
      await contract.connect(addr1).transfer(addr2.address, 50);
      const addr2Balance = await contract.balanceOf(addr2.address);
      assert.equal(addr2Balance, 50);
    });

    it('Should fail if sender doesn’t have enough tokens', async () => {
      const initialOwnerBalance = await contract.balanceOf(owner.address);

      // Try to send 1 token from addr1 (0 tokens) to owner (1000000 tokens).
      // `require` will evaluate false and revert the transaction.
      await assert.rejects(
        contract.connect(addr1).transfer(owner.address, 1),
        {
          name: 'Error',
          message: 'VM Exception while processing transaction: revert',
        }
      );

      // Owner balance shouldn't have changed.
      assert.equal(await contract.balanceOf(owner.address), initialOwnerBalance);
    });
  });
});