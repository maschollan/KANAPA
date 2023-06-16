/* eslint-disable quotes */
/* eslint-disable no-unused-vars */
/* eslint-disable new-cap */
/* eslint-disable max-len */
const functions = require("firebase-functions");
const admin = require("firebase-admin");
const express = require("express");
const cors = require("cors");
const axios = require("axios");
const multer = require("multer");
const {v4: uuidv4} = require('uuid');
const fs = require("fs");
const path = require("path");
const bodyParser = require('body-parser');


const app = express();
app.use(cors({origin: true}));
const authMiddleware = require("./authMiddleware");


// const serviceAccount = require("./serviceAccountKey.json");
// const storageBucket = 'capstone-kanapa.appspot.com';

// admin.initializeApp({
//   credential: admin.credential.cert(serviceAccount),
//   storageBucket: storageBucket,
// });
// const bucket = admin.storage().bucket();
// const db = admin.firestore();

// Register endpoint
app.post("/register", async (req, res) => {
  try {
    const {name, email, password} = req.body;

    // Create user with email and password
    // eslint-disable-next-line no-unused-vars
    const userRecord = await admin.auth().createUser({
      displayName: name,
      email: email,
      password: password,
    });

    res.json({
      error: false,
      message: "User Created",
    });
  } catch (error) {
    console.error("Error creating user:", error);
    res.status(500).json({
      error: true,
      message: "Failed to create user",
    });
  }
});

// Login Endpoint

app.post("/login", async (req, res) => {
  try {
    const {email, password} = req.body;

    const apiKey = "AIzaSyCD32BhfedAIaIEA_yXX3Krw2SklUTaKF8"; // Ganti dengan API key Firebase Anda

    const endpoint = `https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=${apiKey}`;

    const response = await axios.post(endpoint, {
      email,
      password,
      returnSecureToken: true,
    });

    // Mengambil data nama dari response
    const {displayName} = response.data;

    // Mengirim response sukses dengan data nama
    res.json({
      error: false,
      message: "success",
      loginResult: {
        userId: response.data.localId,
        name: displayName,
        token: response.data.idToken,
      },
    });
  } catch (error) {
    // Mengirim response gagal dengan pesan error
    res.status(500).json({
      error: true,
      message: "Failed to login",
    });
  }
});

// Add New Image With Authentication
const {Storage} = require('@google-cloud/storage');
const service = require("./serviceAccountKey.json"); // path to service.json file obtained from firebase server for firebase admin access
const {
  fileParser,
} = require('express-multipart-file-parser'); //
app.use(fileParser({
  rawBodyOptions: {
    limit: '15mb', // file size limit
  },
  busboyOptions: {
    limits: {
      fields: 20, // Number text fields allowed
    },
  },
}));

admin.initializeApp({
  credential: admin.credential.cert(service), // service.json
  databaseURL: "https://console.firebase.google.com/project/capstone-kanapa/firestore/data/~2F", // DATABASE_URL
});
const storage = new Storage();
const bucket = storage.bucket("gs://capstone-kanapa.appspot.com"); // BUCKET_URL from firebase console
app.post('/images', authMiddleware, (req, res) => {
  const {
    fieldname,
    originalname,
    encoding,
    mimetype,
    buffer,
  } = req.files[0];
  if (req.files[0]) {
    uploadImageToStorage(req.files[0]).then((url) => {
      const downloadUrl = getDownloadUrl(url);
      res.send({status: true, data: "Image uploaded successfully", downloadUrl});
    })
        .catch((err)=>{
          res.send({status: false,
            data: "Post failed to add"});
        });
  } else {
    res.send('image not provided');
  }
});

// /**
// * Upload the image file to Google Storage
// * @param {File} file object that will be uploaded to Google Storage
// */
const uploadImageToStorage = (file) => {
  const prom = new Promise((resolve, reject) => {
    if (!file) {
      // eslint-disable-next-line prefer-promise-reject-errors
      reject('No image file');
    }
    const newFileName = uuidv4() + file.originalname; // uqiue name

    const fileUpload = bucket.file(newFileName);
    const blobStream = fileUpload.createWriteStream({
      metadata: {
        contentType: file.mimetype,
      },
    });

    blobStream.on('error', (error) => {
      // eslint-disable-next-line prefer-promise-reject-errors
      reject('Something is wrong! Unable to upload at the moment.');
    });

    blobStream.on('finish', () => {
      const url = `https://storage.googleapis.com/${bucket.name}/${fileUpload.name}`; // image url from firebase server
      resolve(url);
    });

    blobStream.end(file.buffer);
  });
  return prom;
};

const getDownloadUrl = (imageUrl) => {
  // Lakukan proses atau manipulasi URL gambar sesuai kebutuhan
  const fileName = imageUrl.split('/').pop(); // Mengambil nama file dari URL
  const downloadUrl = `https://storage.cloud.google.com/${bucket.name}/${fileName}`; // Contoh: Mengganti domain dan menambahkan direktori unduhan
  return downloadUrl;
};

// exports the api to firebase cloud functions
exports.app = functions.https.onRequest(app);


