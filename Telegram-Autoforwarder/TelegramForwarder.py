import asyncio
from telethon import events
from telethon.sync import TelegramClient

class TelegramForwarder:
    def __init__(self, api_id, api_hash, phone_number):
        self.api_id = api_id
        self.api_hash = api_hash
        self.phone_number = phone_number
        self.client = TelegramClient('session_' + phone_number, api_id, api_hash)

    async def connect_and_authorize(self):
        await self.client.connect()

        # Ensure you're authorized
        if not await self.client.is_user_authorized():
            await self.client.send_code_request(self.phone_number)
            await self.client.sign_in(self.phone_number, input('Enter the code: '))

    async def list_chats(self):
        await self.connect_and_authorize()

        # Get a list of all the dialogs (chats)
        dialogs = await self.client.get_dialogs()
        chats_file = open(f"chats_of_{self.phone_number}.txt", "w")
        # Print information about each chat
        for dialog in dialogs:
            print(f"Chat ID: {dialog.id}, Title: {dialog.title}")
            chats_file.write(f"Chat ID: {dialog.id}, Title: {dialog.title} \n")

        chats_file.close()
        print("List of groups printed successfully!")

    async def forward_message(self, source_chat_id, destination_chat_id, keywords):
        @self.client.on(events.NewMessage(chats=source_chat_id))
        async def handler(event):
            message = event.message

            # Check if the message has a text or media
            if message.text:
                # Check if the message text includes any of the keywords
                if keywords and any(keyword in message.text.lower() for keyword in keywords):
                    print(f"Message contains a keyword: {message.text}")
                    await self.client.send_message(destination_chat_id, message.text)
                    print("Text message forwarded to destination")
            elif message.media:  # Check if the message has media (files/images)
                file_type = message.media.document.mime_type if message.media.document else None
                allowed_types = [
                    'application/pdf',         # PDF
                    'application/vnd.openxmlformats-officedocument.wordprocessingml.document',  # DOCX
                    'application/vnd.ms-word',  # DOC
                    'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',  # XLSX
                    'application/vnd.ms-excel',  # XLS
                    'image/jpeg',                # JPEG
                    'image/png',                 # PNG
                ]
                
                if file_type in allowed_types:
                    print(f"Forwarding media: {file_type}")
                    await self.client.send_file(destination_chat_id, message.media)
                    print("Media forwarded to destination")

    async def forward_messages_bi_directional(self, chat1_id, chat2_id, keywords):
        await self.forward_message(chat1_id, chat2_id, keywords)  # Forward from chat1 to chat2
        await self.forward_message(chat2_id, chat1_id, keywords)  # Forward from chat2 to chat1

# Function to read credentials from file
def read_credentials():
    try:
        with open("credentials.txt", "r") as file:
            lines = file.readlines()
            api_id = lines[0].strip()
            api_hash = lines[1].strip()
            phone_number = lines[2].strip()
            return api_id, api_hash, phone_number
    except FileNotFoundError:
        print("Credentials file not found.")
        return None, None, None

# Function to write credentials to file
def write_credentials(api_id, api_hash, phone_number):
    with open("credentials.txt", "w") as file:
        file.write(api_id + "\n")
        file.write(api_hash + "\n")
        file.write(phone_number + "\n")

async def main():
    api_id, api_hash, phone_number = read_credentials()

    if api_id is None or api_hash is None or phone_number is None:
        api_id = input("Enter your API ID: ")
        api_hash = input("Enter your API Hash: ")
        phone_number = input("Enter your phone number: ")
        write_credentials(api_id, api_hash, phone_number)

    forwarder = TelegramForwarder(api_id, api_hash, phone_number)
    
    await forwarder.connect_and_authorize()  # Connect and authorize the client before proceeding

    print("Choose an option:")
    print("1. List Chats")
    print("2. Forward Messages (Bidirectional)")
    
    choice = input("Enter your choice: ")
    
    if choice == "1":
        await forwarder.list_chats()
    elif choice == "2":
        chat1_id = int(input("Enter the first chat ID: "))
        chat2_id = int(input("Enter the second chat ID: "))
        print("Enter keywords if you want to forward messages with specific keywords, or leave blank to forward every message!")
        keywords = input("Put keywords (comma separated if multiple, or leave blank): ").split(",")
        
        await forwarder.forward_messages_bi_directional(chat1_id, chat2_id, keywords)
        
        # Run the client until you stop it
        await forwarder.client.run_until_disconnected()
    else:
        print("Invalid choice")

# Start the event loop and run the main function
if __name__ == "__main__":
    asyncio.run(main())
