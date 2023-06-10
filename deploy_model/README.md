# KANAPA API Documentation
Welcome to the Nutriary API documentation. This API allows you to classify Ornamental Fish images using TensorFlow, providing information about the predicted fish class and its corresponding probability.

## How to run

1. Install the necessary libraries located at [requirements.txt](requirements.txt) by using `pip install -r requirements.txt`
2. Run the file with `uvicorn [filename]:app --reload`
3. Experiment with it or open the docs

Examples:


### 1. To run the [main.py](main.py) Ornamental Fish classification model

#### Note that you need to have model.h5 to run this api

You can get it through this [link](https://drive.google.com/file/d/1XiQVH7yVTdA2vn5XzkJbuuY9UBgRiq3d/view?usp=sharing)


```bash
uvicorn main:app --reload
```

You will obtain an output similar to this in the console:

```text
INFO:     Will watch for changes in these directories: ['E:\\Projects\\deploy_model']
INFO:     Uvicorn running on http://127.0.0.1:8000 (Press CTRL+C to quit)
INFO:     Started reloader process [10712] using StatReload
2023-04-19 03:25:13.691430: W tensorflow/tsl/framework/cpu_allocator_impl.cc:83] Allocation of 160563200 exceeds 10% of free system memory.
2023-04-19 03:25:14.177428: W tensorflow/tsl/framework/cpu_allocator_impl.cc:83] Allocation of 160563200 exceeds 10% of free system memory.
2023-04-19 03:25:14.230110: W tensorflow/tsl/framework/cpu_allocator_impl.cc:83] Allocation of 160563200 exceeds 10% of free system memory.
2023-04-19 03:25:15.825289: W tensorflow/tsl/framework/cpu_allocator_impl.cc:83] Allocation of 160563200 exceeds 10% of free system memory.
2023-04-19 03:25:15.865302: W tensorflow/tsl/framework/cpu_allocator_impl.cc:83] Allocation of 160563200 exceeds 10% of free system memory.
INFO:     Started server process [20704]
INFO:     Waiting for application startup.
INFO:     Application startup complete.
```

or you can run the code by running `py main.py` in the terminal and you will receive an output similar to this:

```text
2023-04-19 02:58:57.877659: W tensorflow/tsl/framework/cpu_allocator_impl.cc:83] Allocation of 160563200 exceeds 10% of free system memory.
2023-04-19 02:58:58.345451: W tensorflow/tsl/framework/cpu_allocator_impl.cc:83] Allocation of 160563200 exceeds 10% of free system memory.
2023-04-19 02:58:58.397330: W tensorflow/tsl/framework/cpu_allocator_impl.cc:83] Allocation of 160563200 exceeds 10% of free system memory.
2023-04-19 02:58:59.431456: W tensorflow/tsl/framework/cpu_allocator_impl.cc:83] Allocation of 160563200 exceeds 10% of free system memory.
2023-04-19 02:58:59.467924: W tensorflow/tsl/framework/cpu_allocator_impl.cc:83] Allocation of 160563200 exceeds 10% of free system memory.
INFO:     Started server process [16784]
INFO:     Waiting for application startup.
INFO:     Application startup complete.
INFO:     Uvicorn running on http://127.0.0.1:8000 (Press CTRL+C to quit)
```

Then you can open the url in the browser and receive an output:

```json
{"message":"Welcome to KANAPA Model test API!"}
```

To try the API, simply follow these steps:

1. `http://127.0.0.1:8000/docs`, you will get to the documentation page
2. Find the `POST` method and click `Try it out`
3. Input the file which is an image and execute it
4. (Optional) you can use the example input in the [test](data_test) directory


# FastAPI Documentations

FastAPI is a modern web framework for building RESTful APIs in Python. It was first released in 2018 and has quickly gained popularity among developers due to its ease of use, speed and robustness. FastAPI is based on Pydantic and type hints to validate, serialize, and deserialize data.

Documentation: https://fastapi.tiangolo.com

Source Code: https://github.com/tiangolo/fastapi

To run this file, do
```bash
uvicorn main:app
```
or
```bash
uvicorn main:app --reload
```
to automatically restart the kernel everytime there's a change saved inside `main.py`

# Basic REST API using FastAPI

### 1. Index
Endpoint: GET `/` <br>
Returns all items data as a dictionary. <br>

### 2. Query item by id
Endpoint: GET `/items/{item_id}` <br>
Parameters: <ul>
<li>item_id: integer representing the id of the item to be queried. </ul>
Returns the item corresponding to the provided item_id parameter. <br>
If no item corresponds to the provided item_id, raises a 404 Not Found error. <br>

### 3. Query item by parameters <br>
Endpoint: GET `/items/` <br>
Query parameters: <ul>
<li>name: (optional) string representing the name of the item to be queried.
<li>price: (optional) float representing the price of the item to be queried.
<li>count: (optional) integer representing the count of the item to be queried.
<li>category: (optional) string representing the category of the item to be queried. </ul>
Returns the items that match the provided query parameters. <br>
If no item matches the query parameters, returns an empty selection. <br>
If no query parameters are provided, returns all items. <br>

### 4. Add new item <br>
Endpoint: POST `/` <br>
Parameters: <ul>
<li>item: JSON data representing an item to be added. </ul>
Adds the provided item to the items data. <br>
If the item ID already exists in the data, raises a 400 Bad Request error. <br>

### 5. Update item <br>
Endpoint: PUT `/update/{item_id}` <br>
Path parameter: <ul>
<li>item_id: integer representing the id of the item to be updated. </ul>
Query parameters (all optional): <ul><br>
<li>name: string representing the new name of the item.
<li>price: float representing the new price of the item.
<li>count: integer representing the new count of the item. </ul>
Updates the attributes of the item with the provided item_id. <br>
If the item with the provided item_id does not exist in the data, raises a 404 Not Found error. <br>
If no update parameters are provided, raises a 400 Bad Request error. <br>

### 6. Delete item <br>
Endpoint: DELETE `/delete/{item_id}`
Parameters: <ul>
<li>item_id: integer representing the id of the item to be deleted. </ul>
Deletes the item with the provided item_id from the data. <br>
If the item with the provided item_id does not exist in the data, raises a 404 Not Found error.
