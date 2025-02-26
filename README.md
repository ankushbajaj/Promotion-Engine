# Promotion Engine

A Spring Boot application to calculate the total order value after applying dynamic promotions for a checkout process. This project is built using:
- **Spring Boot** – For building the REST API.
- **Lombok** – To reduce boilerplate code.
- **Gson** – For JSON configuration loading.
- **JUnit & Mockito** – For unit and integration testing.

---

## Table of Contents
- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Configuration](#configuration)
- [Endpoints](#endpoints)
- [Test Scenarios](#test-scenarios)
- [Run Tests](#run-tests)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)

---

## Features
- Dynamic promotion rules loaded from `promotionConfig.json`.
- Supports multiple promotion types:
  - **Fixed Price Promotion** (e.g., 3 A's for 130)
  - **Combo Promotion** (e.g., C & D for 30)
- Easily extendable for new promotion types.
- RESTful API for:
  - Adding items to the cart.
  - Calculating the total order value.

---

## Requirements
- Java 17 or higher
- Maven 3.x
- IDE (IntelliJ, Eclipse) with Lombok plugin installed
- Postman or cURL for testing the API

---

## Installation
1. **Clone the Repository:**
```sh
git clone <repository-url>
cd PromotionEngine
```

2. **Build the Project:**
```sh
mvn clean install
```

3. **Run the Application:**
```sh
mvn spring-boot:run
```

4. **Access the API:**
```
http://localhost:8080
```

---

## Configuration
### `promotionConfig.json`
Located in `src/main/resources`, this file defines the SKUs and active promotions.

```json
{
  "skus": {
    "A": 50,
    "B": 30,
    "C": 20,
    "D": 15
  },
  "promotions": [
    {
      "type": "FixedPricePromotion",
      "skuId": "A",
      "quantityRequired": 3,
      "fixedPrice": 130
    },
    {
      "type": "FixedPricePromotion",
      "skuId": "B",
      "quantityRequired": 2,
      "fixedPrice": 45
    },
    {
      "type": "ComboPromotion",
      "skuId1": "C",
      "skuId2": "D",
      "comboPrice": 30
    }
  ]
}
```

---

## Endpoints

### 1. Add Items to Cart
- **Endpoint:** `POST /api/v1/cart`
- **Request Body:**
```json
{
    "items": {
        "A": 3,
        "B": 2,
        "C": 1
    }
}
```
- **Example cURL Request:**
```sh
curl -X POST http://localhost:8080/api/v1/cart -H "Content-Type: application/json" -d '{
    "items": {
        "A": 3,
        "B": 2,
        "C": 1
    }
}'
```

---

### 2. Calculate Total Order Value
- **Endpoint:** `GET /api/v1/checkout`
- **Example cURL Request:**
```sh
curl -X GET http://localhost:8080/api/v1/checkout
```

---

## Test Scenarios
### Scenario A (No Promotions Applied)
```json
{
    "items": {
        "A": 1,
        "B": 1,
        "C": 1
    }
}
```
- Expected Total: `100`

### Scenario B (Promotions Applied)
```json
{
    "items": {
        "A": 5,
        "B": 5,
        "C": 1
    }
}
```
- Expected Total: `370`

### Scenario C (Combo Promotions Applied)
```json
{
    "items": {
        "A": 3,
        "B": 5,
        "C": 1,
        "D": 1
    }
}
```
- Expected Total: `280`

---

## Run Tests
```sh
mvn test
```

---

## Technologies Used
- **Spring Boot** – For building RESTful APIs.
- **Lombok** – To reduce boilerplate code.
- **Gson** – For JSON configuration loading.
- **JUnit 5 and Mockito** – For unit and integration testing.
- **Maven** – For dependency management and build automation.

---

## Project Structure
```
com.promotion.engine
│
├── config
│   └── PromotionConfigLoader.java
├── controller
│   └── PromotionController.java
├── engine
│   └── PromotionEngine.java
├── model
│   ├── SKU.java
│   └── Cart.java
└── promotion
    ├── Promotion.java
    ├── FixedPricePromotion.java
    └── ComboPromotion.java
```

---

## License
This project is licensed under the MIT License.

---

## Contact
For any issues or feature requests, please contact the developer.
