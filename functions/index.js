/* eslint-disable quotes */
/* eslint-disable no-unused-vars */
/* eslint-disable new-cap */
/* eslint-disable max-len */
const functions = require("firebase-functions");
const admin = require("firebase-admin");
const express = require("express");
const cors = require("cors");
const axios = require("axios");
const Multer = require("multer");
const {v4: uuidv4} = require("uuid");
const fs = require("fs");
const path = require("path");
const {Storage} = require("@google-cloud/storage");
const bodyParser = require('body-parser');


const app = express();
app.use(cors({origin: true}));


const serviceAccount = require("./serviceAccountKey.json");
const storageBucket = 'gs://capstone-kanapa.appspot.com/';

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  storageBucket: storageBucket,
});
const bucket = admin.storage().bucket();
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
const multer = Multer({
  storage: Multer.diskStorage({
    destination: function(req, file, cb) {
      cb(null, path.join(__dirname, 'public/uploads'));
    },
    filename: function(req, file, cb) {
      cb(
          null,
          file.fieldname + '-' + Date.now() + path.extname(file.originalname),
      );
    },
  }),
  limits: {
    fileSize: 5 * 1024 * 1024, // Batasan ukuran file (5 MB)
  },
});

// Middleware untuk memeriksa token Firebase
const verifyFirebaseToken = (req, res, next) => {
  const authorizationHeader = req.headers.authorization;

  if (!authorizationHeader || !authorizationHeader.startsWith('Bearer ')) {
    return res.status(401).json({error: true, message: 'Unauthorized'});
  }

  const token = authorizationHeader.split('Bearer ')[1];

  admin
      .auth()
      .verifyIdToken(token)
      .then((decodedToken) => {
        req.userId = decodedToken.uid;
        next();
      })
      .catch((error) => {
        console.error('Error verifying Firebase token:', error);
        return res.status(401).json({error: true, message: 'Unauthorized'});
      });
};

// Endpoint untuk menambahkan gambar baru (memerlukan autentikasi Firebase)
app.post('/images', verifyFirebaseToken, multer.single('photo'), (req, res) => {
  const {description, lat, lon} = req.body;
  const file = req.file;

  // Validasi input
  if (!description || !file) {
    return res.status(400).json({error: true, message: 'Description and photo are required'});
  }

  // Generate nama file unik
  const fileName = `${Date.now()}_${file.originalname}`;

  // Upload file ke Firebase Storage
  const uploadFile = bucket.file(fileName);
  const blobStream = uploadFile.createWriteStream({
    metadata: {
      contentType: file.mimetype,
    },
  });

  blobStream.on('error', (error) => {
    console.error(error);
    return res.status(500).json({error: true, message: 'An error occurred while uploading the file'});
  });

  blobStream.on('finish', () => {
    // Dapatkan URL publik untuk file yang diunggah
    const publicUrl = `https://storage.googleapis.com/${storageBucket}/${uploadFile.name}`;

    // Lakukan operasi lain yang diperlukan, misalnya menyimpan data ke database

    // Kirim respons berhasil
    return res.json({error: false, message: 'success'});
  });

  blobStream.end(file.buffer);
});

// exports the api to firebase cloud functions
exports.app = functions.https.onRequest(app);


