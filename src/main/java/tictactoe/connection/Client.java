package tictactoe.connection;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Connection {
    private Socket clientSocket1;
    private Socket clientSocket2;
        
    @Override
    public void echoConnection() {
        try {
            clientSocket1 = new Socket("localhost", 2022);
            clientSocket2 = new Socket("localhost", 2023);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void closeConnection() {
        try {
            clientSocket1.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void sendMessage(char[][] output) {
        OutputStream outputStream;
        ObjectOutputStream objectOutputStream;
        try {
            outputStream = clientSocket1.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(output);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public char[][] listenMessage() {
        InputStream inputStream;
        ObjectInputStream objectInputStream;
        try {
            inputStream = clientSocket1.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            char[][] receivedBoard = (char[][]) objectInputStream.readObject();
            if (receivedBoard != null)
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
            outputStream = clientSocket2.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeUTF("RESET");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String listenResetMessage() {
        InputStream inputStream;
        ObjectInputStream objectInputStream;
        try {
            inputStream = clientSocket2.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            String receivedString = (String) objectInputStream.readUTF();
            if(receivedString != null)
                return receivedString;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
