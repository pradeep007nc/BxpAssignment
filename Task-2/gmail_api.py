from googleapiclient.discovery import build
from google_auth_oauthlib.flow import InstalledAppFlow
from google.auth.transport.requests import Request
import os
import pickle

# Define the scopes
SCOPES = ['https://www.googleapis.com/auth/gmail.readonly']

def authenticate_gmail():
    """Authenticate the user and return a Gmail API service instance."""
    creds = None
    # Check if token.pickle exists (stored credentials)
    if os.path.exists('token.pickle'):
        with open('token.pickle', 'rb') as token:
            creds = pickle.load(token)
    # If no valid credentials, request the user to log in
    if not creds or not creds.valid:
        if creds and creds.expired and creds.refresh_token:
            creds.refresh(Request())
        else:
            flow = InstalledAppFlow.from_client_secrets_file(os.path.join(os.getcwd(), 'Task-2/credentials.json'), SCOPES)
            creds = flow.run_local_server(port=0)
        # Save credentials for future use
        with open('token.pickle', 'wb') as token:
            pickle.dump(creds, token)
    return build('gmail', 'v1', credentials=creds)

def fetch_emails(service, max_results=50):
    """Fetch the sender and subject of the last 'max_results' emails."""
    results = service.users().messages().list(userId='me', maxResults=max_results).execute()
    messages = results.get('messages', [])

    email_list = []
    for message in messages:
        msg = service.users().messages().get(userId='me', id=message['id']).execute()
        payload = msg['payload']
        headers = payload.get('headers', [])

        sender = subject = None
        for header in headers:
            if header['name'] == 'From':
                sender = header['value']
            if header['name'] == 'Subject':
                subject = header['value']

        if sender and subject:
            email_list.append((sender, subject))

    return email_list

def main():
    service = authenticate_gmail()
    emails = fetch_emails(service)

    # Display the sender and subject of each email
    print(f"{'Sender':<50} | {'Subject'}")
    print('-' * 100)
    for sender, subject in emails:
        print(f"{sender[:50]:<50} | {subject[:50]}")

if __name__ == '__main__':
    main()
