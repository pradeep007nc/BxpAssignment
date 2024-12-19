# BxpAssignment
BxpAssignment

# Gmail API Setup Guide

This guide will help you set up the Gmail API to access your personal Gmail account and fetch your emails. Follow the steps below to enable the API, obtain the necessary credentials, and authenticate your account.

---

## Step 1: Enable Gmail API for Your Personal Account

1. **Go to Google Cloud Console**  
   - Visit the [Google Cloud Console](https://console.cloud.google.com/).

2. **Log in with Your Personal Google Account**  
   - Use the Gmail account for which you want to fetch emails.

3. **Create a New Project**  
   - In the top menu, click the project dropdown.
   - Select **New Project**.
   - Enter a name for your project, such as `MyGmailAPIProject`.
   - Click **Create**.

---

## Step 2: Enable Gmail API

1. Navigate to **API & Services > Library** in the Google Cloud Console.  
2. Search for "Gmail API" in the search bar.  
3. Select the **Gmail API** and click **Enable**.

---

## Step 3: Configure OAuth Consent Screen

1. Go to **API & Services > OAuth consent screen**.  
2. Select **External** (for personal use).  
3. Fill in the required details:
   - **App name**: Enter something like `My Gmail API`.
   - **User support email**: Use your personal Gmail address.
   - **App domain**: Leave this blank (optional for personal use).
   - **Developer contact email**: Use your personal Gmail address.
4. Click **Save and Continue**.

---

## Step 4: Create OAuth 2.0 Credentials

1. Go to **API & Services > Credentials**.  
2. Click **+ CREATE CREDENTIALS** and select **OAuth Client ID**.  
3. Choose **Desktop app** as the application type.  
4. Click **Create**.  
5. A dialog will appear with your client ID and client secret:
   - Click **Download JSON** to download the `credentials.json` file.
   - Save the file in the same directory as your Python script.

---

## Step 5: Authenticate Your Personal Gmail

1. Run your Python script (e.g., a script to fetch your Gmail emails).  
2. The first time you run the script, it will:
   - Open a browser and prompt you to log in with your Google account.
   - Ask for permission to access Gmail.
3. Grant permission using your personal Gmail account.  
4. A `token.pickle` file will be generated to store your authentication for subsequent runs.  

---

## Notes

- **Keep `credentials.json` Secure**:  
  Do not share this file. If using version control, add it to your `.gitignore` file.

- **Revoking Access**:  
  If you no longer want the application to access your Gmail, go to [Google Account Permissions](https://myaccount.google.com/permissions) and revoke the app's access.

---

Once these steps are completed, you will be able to fetch emails from your personal Gmail account securely.
