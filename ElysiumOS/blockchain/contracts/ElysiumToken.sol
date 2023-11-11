// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

/**
 * @title ElysiumToken
 * @dev Implementation of the Elysium Token for Elysium OS.
 * This ERC20 token is used within the Elysium OS ecosystem to facilitate
 * transactions, reward users, and operate within the Automation Station and The Arena.
 */
contract ElysiumToken is ERC20, Ownable {
    /**
     * @dev Sets the values for {name} and {symbol}.
     * All two of these values are immutable: they can only be set once during
     * construction.
     */
    constructor() ERC20("Elysium Token", "ELY") {
        // Initial supply of 1 million tokens to the owner of the contract
        _mint(msg.sender, 1000000 * (10 ** uint256(decimals())));
    }

    /**
     * @dev Function to mint tokens
     * @param to The address that will receive the minted tokens.
     * @param amount The amount of tokens to mint.
     * Can only be called by the owner of the contract.
     */
    function mint(address to, uint256 amount) public onlyOwner {
        _mint(to, amount);
    }

    /**
     * @dev Function to burn tokens
     * @param from The address from which tokens will be burned.
     * @param amount The amount of tokens to burn.
     * Can only be called by the owner of the contract.
     */
    function burn(address from, uint256 amount) public onlyOwner {
        _burn(from, amount);
    }
}