/* eslint-disable linebreak-style */
const admin = require("firebase-admin");

const authenticateToken = async (req, res, next) => {
  try {
    const authToken = req.headers.authorization;
    if (!authToken || !authToken.startsWith("Bearer ")) {
      return res.status(401).json({error: true, message: "Unauthorized"});
    }
    const idToken = authToken.split(" ")[1];
    const decodedToken = await admin.auth().verifyIdToken(idToken);
    req.user = decodedToken;
    next();
  } catch (error) {
    console.error("Error verifying ID token:", error);
    return res.status(401).json({error: true, message: "Unauthorized"});
  }
};
module.exports = authenticateToken;
