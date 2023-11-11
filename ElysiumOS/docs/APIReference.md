# Elysium OS API Reference

Welcome to the Elysium OS API Reference. This document provides detailed information about the RESTful APIs available in Elysium OS, which allow developers to interact with the core functionalities of the Automation Station, The Arena, and the integrated blockchain services.

## Base URL

All API endpoints are relative to the base URL of the Elysium OS instance. Replace `baseurl` with the actual server address.

```
https://baseurl/api
```

## Authentication

All API requests require the use of a generated API token. This token must be included in the header of each request.

Header:
```
Authorization: Bearer YOUR_API_TOKEN
```

## Endpoints

### Automation Station

#### Create Automation Task

- **POST** `/automation/create`
- **Description**: Create a new automation task.
- **Body**:
  ```json
  {
    "name": "string",
    "description": "string",
    "trigger": "string",
    "action": "string"
  }
  ```
- **Response**: `201 Created`

#### List Automation Tasks

- **GET** `/automation/list`
- **Description**: Retrieve a list of all automation tasks.
- **Response**: `200 OK`

### The Arena

#### Start Game Session

- **POST** `/arena/start`
- **Description**: Start a new game session in The Arena.
- **Body**:
  ```json
  {
    "worldId": "string",
    "scenario": "string"
  }
  ```
- **Response**: `201 Created`

#### Get Game Status

- **GET** `/arena/status/{sessionId}`
- **Description**: Get the current status of a game session.
- **Response**: `200 OK`

### Blockchain Services

#### Deploy Smart Contract

- **POST** `/blockchain/deploy`
- **Description**: Deploy a new smart contract to the blockchain.
- **Body**:
  ```json
  {
    "contractName": "string",
    "sourceCode": "string"
  }
  ```
- **Response**: `201 Created`

#### Execute Smart Contract Function

- **POST** `/blockchain/execute`
- **Description**: Execute a function of a deployed smart contract.
- **Body**:
  ```json
  {
    "contractAddress": "string",
    "functionName": "string",
    "parameters": {}
  }
  ```
- **Response**: `200 OK`

### User Services

#### Register User

- **POST** `/user/register`
- **Description**: Register a new user in the system.
- **Body**:
  ```json
  {
    "username": "string",
    "password": "string",
    "email": "string"
  }
  ```
- **Response**: `201 Created`

#### Authenticate User

- **POST** `/user/authenticate`
- **Description**: Authenticate a user and retrieve an API token.
- **Body**:
  ```json
  {
    "username": "string",
    "password": "string"
  }
  ```
- **Response**: `200 OK`

## Status Codes

Elysium OS returns the following status codes in its API:

- `200 OK`: The request was successful.
- `201 Created`: The request was successful and a resource was created.
- `400 Bad Request`: The request could not be understood or was missing required parameters.
- `401 Unauthorized`: Authentication failed or user does not have permissions for the requested operation.
- `404 Not Found`: The requested resource could not be found.
- `500 Internal Server Error`: A generic error occurred on the server.

For more detailed information on each endpoint, including parameters and response schemas, please refer to the individual endpoint documentation.