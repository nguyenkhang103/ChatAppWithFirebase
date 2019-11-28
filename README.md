APP CHAT EASY
- Change and add your google-services.json
- Change Authorization:key with your key from firebase project
- Create Firebase Project (https://console.firebase.google.com/);
- Import the file google-service.json into your project
- Connect to firebase console authentication and database from your IDE 
- in firebase Storage Rules, change value of "allow read, write:" from "if request.auth != null" to "if true;" 
- For sending notification, paste your Firebase project key into your project APIService.java 
- When you change database settings, you likely will need to uninstall and reinstall apps to avoid app crashes due to app caches.
