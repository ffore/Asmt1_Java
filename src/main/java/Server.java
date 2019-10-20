import com.mysql.cj.xdevapi.JsonArray;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import main.java.SqlDatabase;

public class Server {

    private ServerSocket server;
    private SqlDatabase database;

    public Server(SqlDatabase database) {
        this.database = database;
        try{
            this.server = createServerSocket();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    public Server() {
        try{
            this.server = createServerSocket();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

//    public static void main(String args[]) throws IOException {
//        Server x = new Server();
//        ServerSocket server = x.getServer();
//        acceptRequests(server);
//
//    }

    public void startServer() throws IOException{
        ServerSocket server = this.getServer();
        this.acceptRequests(server);
    }

    public void getDistanceTable() {
        System.out.println("server about to print distance table");
        try {
            this.database.printDistanceTable();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void acceptRequests(ServerSocket server) throws IOException {
        while (true){
            try (Socket socket = server.accept()) {
                System.out.println(socket.getInetAddress().getHostName());
                sendMessage(socket);
            }
        }
    }

    public static void sendMessage(Socket socket) throws IOException {
        String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + "hello how r u";
        socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
    }

    public ServerSocket createServerSocket() throws IOException {
        System.out.println("Listening for connection on port 5000 ....");
        return new ServerSocket(5000);
    }

    public ServerSocket getServer() {
        return this.server;
    }
}
