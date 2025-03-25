# Diet Application

The Diet Application is designed to help users plan cost-effective diets by allowing them to filter and combine meal and product data from multiple sources. This application integrates Java, Spring, Scala, Spark, PostgreSQL, and MongoDB to provide a comprehensive solution.

## Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Modules](#modules)
    - [product-domain](#product-domain)
    - [meal-domain](#meal-domain)
    - [data/integration](#dataintegration)
    - [diet-application](#diet-application)
- [Technology Stack](#technology-stack)
- [Endpoints](#endpoints)

## Overview

This application assists users in following a budget-friendly diet by:
- Managing food products and meals via dedicated modules.
- Ingesting data from two separate PostgreSQL databases (one for products and one for meals).
- Using Apache Spark to join and transform data from both domains.
- Persisting the integrated data into MongoDB for efficient querying.
- Enabling users to filter meals based on meal types, calorie ranges, and maximum price.

## Architecture

The application is divided into the following modules:

1. **product-domain**
    - Manages food product information.
    - Provides endpoints for retrieving and adding products (single and bulk).
    - Uses PostgreSQL as its data source.
    - Utilizes Lombok for reducing boilerplate code and MapStruct for object mapping.

2. **meal-domain**
    - Manages meal data that groups products into meal offerings.
    - Provides endpoints for retrieving and creating meals.
    - Uses PostgreSQL as its data source.
    - Utilizes Lombok and MapStruct similarly to the product-domain.

3. **data/integration**
    - Connects to both PostgreSQL databases.
    - Uses Apache Spark to join product and meal data.
    - Persists the final, integrated data into MongoDB for fast querying.

4. **diet-application**
    - Acts as the final API layer.
    - Provides an endpoint that allows users to filter meals from MongoDB based on specific criteria.

## Technology Stack

- **Languages & Frameworks:** Java, Spring Boot, Scala, Apache Spark
- **Databases:** PostgreSQL (two separate databases for products and meals), MongoDB
- **Libraries:** Lombok, MapStruct
- **Other Tools:** Maven/Gradle (build tool), Docker (optional for containerization)

## Endpoints

### Product Endpoints

- **GET `/api/products`**  
  Retrieves the list of products.

- **POST `/api/products`**  
  Creates a single product.  
  **Request Body Example:**
  ```json
  {
    "id": 0,
    "name": "string",
    "description": "string",
    "currency": "PLN",
    "price": 0,
    "value": 0,
    "unit": "PIECE",
    "kcal": 0,
    "protein": 0,
    "fat": 0,
    "carbohydrate": 0
  }

- **POST `/api/products/bulk`**  
  Bulk uploads products.  
  **Request Body Example:**
```json
 {
  "products": [
    {
      "id": 0,
      "name": "string",
      "description": "string",
      "currency": "PLN",
      "price": 0,
      "value": 0,
      "unit": "PIECE",
      "kcal": 0,
      "protein": 0,
      "fat": 0,
      "carbohydrate": 0
    }
  ]
 }
```

## Meal Endpoints

### GET `/api/meals`
- Retrieves the list of meals.

### POST `/api/meals`
- Creates a new meal.
- **Request Body Example:**
  ```json
  {
    "id": "string",
    "name": "string",
    "mealType": "breakfast",
    "description": "string",
    "products": [
      {
        "id": 0,
        "name": "string",
        "value": "string",
        "unit": "PIECE",
        "description": "string"
      }
    ]
  }

### Diet Application Endpoints

#### POST `/api/diet`

Filters meals from MongoDB based on criteria provided by the user.

**Request Body Example:**

```json
{
  "mealTypeList": [
    "string"
  ],
  "minKcal": 0,
  "maxKcal": 0,
  "maxPrice": 0
}



