-- Elysium OS Database Update Migration (002_updates.sql)
-- Apply necessary schema changes for Elysium OS updates

BEGIN TRANSACTION;

-- Add new columns to existing tables for enhanced functionality
ALTER TABLE users ADD COLUMN last_login TIMESTAMP;
ALTER TABLE tasks ADD COLUMN priority INTEGER;
ALTER TABLE ai_agents ADD COLUMN status VARCHAR(255);

-- Create new tables for the marketplace and pet customization features
CREATE TABLE marketplace_listings (
    listing_id SERIAL PRIMARY KEY,
    seller_id INTEGER NOT NULL,
    item_name VARCHAR(255) NOT NULL,
    item_description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    date_listed TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (seller_id) REFERENCES users(user_id)
);

CREATE TABLE pet_customizations (
    customization_id SERIAL PRIMARY KEY,
    pet_id INTEGER NOT NULL,
    customization_type VARCHAR(255) NOT NULL,
    customization_value TEXT NOT NULL,
    date_customized TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (pet_id) REFERENCES pets(pet_id)
);

-- Update the AI agents table to include a reference to their human overseers
ALTER TABLE ai_agents ADD COLUMN overseer_id INTEGER;
ALTER TABLE ai_agents ADD FOREIGN KEY (overseer_id) REFERENCES users(user_id);

-- Introduce a new table for AI agent tasks that have real-world impact
CREATE TABLE real_world_tasks (
    task_id SERIAL PRIMARY KEY,
    ai_agent_id INTEGER NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(50) NOT NULL,
    impact_level INTEGER NOT NULL,
    completion_time TIMESTAMP,
    FOREIGN KEY (ai_agent_id) REFERENCES ai_agents(agent_id)
);

-- Implement changes for the Arbitrum blockchain integration
CREATE TABLE blockchain_transactions (
    transaction_id SERIAL PRIMARY KEY,
    from_address VARCHAR(255) NOT NULL,
    to_address VARCHAR(255) NOT NULL,
    amount DECIMAL(18, 8) NOT NULL,
    transaction_hash VARCHAR(255) NOT NULL,
    block_number INTEGER NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Add a table for storing smart contract interactions
CREATE TABLE smart_contract_interactions (
    interaction_id SERIAL PRIMARY KEY,
    contract_address VARCHAR(255) NOT NULL,
    function_signature VARCHAR(255) NOT NULL,
    parameters TEXT,
    from_address VARCHAR(255),
    transaction_hash VARCHAR(255),
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

COMMIT;