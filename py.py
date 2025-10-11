import socket

HOST = '0.0.0.0'  # Listen on all interfaces
PORT = 5000

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind((HOST, PORT))
    s.listen()
    print(f"Listening on port {PORT}...")
    conn, addr = s.accept()
    with conn:
        print('Connected by', addr)
        while True:
            data = input("Enter command: ")
            if data.lower() == 'exit':
                break
            conn.sendall(data.encode())
            response = conn.recv(4096).decode()
            print(response)