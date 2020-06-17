package tictactoe.connection;

public interface Connection {
    public void echoConnection();
    public void closeConnection();
    public void sendMessage(char[][] output);
    public char[][] listenMessage();
    public void sendResetMessage();
    public String listenResetMessage();
}
