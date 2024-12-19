import argparse
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
    # Get the directory where the current Python script is located
    current_dir = os.path.dirname(os.path.abspath(__file__))
    token_path = os.path.join(current_dir, 'token.pickle')

    # Check if token.pickle exists (stored credentials)
    if os.path.exists(token_path):
        with open(token_path, 'rb') as token:
            creds = pickle.load(token)
    
    # If no valid credentials, request the user to log in
    if not creds or not creds.valid:
        if creds and creds.expired and creds.refresh_token:
            creds.refresh(Request())
        else:
            flow = InstalledAppFlow.from_client_secrets_file(os.path.join(current_dir, 'Task-2/credentials.json'), SCOPES)
            creds = flow.run_local_server(port=0)
        
        # Save credentials for future use
        with open(token_path, 'wb') as token:
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
    # Set up argument parser
    parser = argparse.ArgumentParser(description="Fetch emails from Gmail API")
    parser.add_argument('--max_results', type=int, default=50, help="The number of emails to fetch (default: 50)")

    # Parse the arguments
    args = parser.parse_args()

    # Authenticate and fetch emails with the provided max_results
    service = authenticate_gmail()
    emails = fetch_emails(service, max_results=args.max_results)

    # Display the sender and subject of each email
    print(f"{'Sender':<50} | {'Subject'}")
    print('-' * 100)
    for sender, subject in emails:
        print(f"{sender[:50]:<50} | {subject[:50]}")

if __name__ == '__main__':
    main()
