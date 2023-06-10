import os
import io
import apis_config as config
import function as func
from PIL import Image
from starlette.responses import FileResponse 
from fastapi import FastAPI, UploadFile, File

app = config.app

# Defining path operation for root endpoint
@app.get('/')
def main():
    return {'message': 'Welcome to KANAPA Model test API!'}



 
# Defining path operation for /predict endpoint
@app.post('/predict')
async def predict_image(file: UploadFile = File(...)):
    # Read and preprocess the image
    content = await file.read()
    try:
        image = Image.open(io.BytesIO(content))
        image = func.preprocess_image(image)
    except Exception as e:
        return {"error" : str(e)}

    response = func.predict(image)
    # Return the predicted class
    return response

if __name__ == "__main__":
	port = int(os.environ.get('PORT', 8080))
	run(app, host="0.0.0.0", port=port, timeout_keep_alive=1200)