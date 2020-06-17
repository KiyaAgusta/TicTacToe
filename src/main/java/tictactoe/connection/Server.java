package tictactoe.connection;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Connection {
    private ServerSocket serverSocket1;
    private ServerSocket serverSocket2;
    private Socket server1;
    private Socket server2;
    
    public void startServer() {
        try {
            server1 = serverSocket1.accept();
            server2 = serverSocket2.accept();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void echoConnection() {
        try {
            serverSocket1 = new ServerSocket(2022);
            serverSocket2 = new ServerSocket(2023);
            startServer();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void closeConnection() {
        try {
            serverSocket1.close();
            server1.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void sendMessage(char[][] output) {
        OutputStream outputStream;
        ObjectOutputStream objectOutputStream;
        try {
            outputStream = server1.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(output);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public char[][] listenMessage() {
        InputStream inputStream;
        ObjectInputStream objectInputStream;
        try {
            inputStream = server1.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            char[][] receivedBoard = (char[][]) objectInputStream.readObject();
            if(receivedBoard != null)
                return receivedBoard;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void sendResetMessage() {
        OutputStream outputStream;
        ObjectOutputStream objectOutputStream;
        try {
            outputStream = server2.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeUTF("RESET");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String listenResetMessage() {
        InputStream inputStream;
        ObjectInputStream objectInputStream;
        try {
            inputStream = server2.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            String receivedString = (String) objectInputStream.readUTF();
            if(receivedString != null)
                return receivedString;
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
