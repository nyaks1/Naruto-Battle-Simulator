package main.java.com.nyaks.naruto.sim;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import main.java.com.nyaks.naruto.sim.Shinobi.Shinobi;

public class GameClient {
    private final String host;
    private final int port;
    private final Shinobi playerShinobi;

    public GameClient(String host, int port, Shinobi playerShinobi) {
        this.host = host;
        this.port = port;
        this.playerShinobi = playerShinobi;
    }

    public void start(Scanner scanner) {
        System.out.println("⚡ Connecting to multiplayer server at " + host + ":" + port + "...");
        
        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
             
            System.out.println("✅ Connected! Initializing network handshake...");
            
            // Send serialized character
            out.println(playerShinobi.serialize());
            
            // Network loop
            String serverMsg;
            while ((serverMsg = in.readLine()) != null) {
                if (serverMsg.startsWith("[INPUT]")) {
                    // Extract prompt if any and wait for user input
                    String prompt = serverMsg.substring(7);
                    System.out.print(prompt + " ");
                    String userInput = scanner.nextLine();
                    out.println(userInput);
                } else if (serverMsg.equals("[DISCONNECT]")) {
                    System.out.println("\n🍃 Connection terminated by the game server.");
                    break;
                } else {
                    System.out.println(serverMsg);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("❌ Error: Unknown host " + host);
        } catch (IOException e) {
            System.err.println("\n❌ Network connection lost: " + e.getMessage());
        }
        System.out.println("Returning to main menu. Press Enter to continue...");
        scanner.nextLine();
    }
}
