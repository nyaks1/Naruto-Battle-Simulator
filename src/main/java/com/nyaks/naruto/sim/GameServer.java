package main.java.com.nyaks.naruto.sim;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import main.java.com.nyaks.naruto.sim.Shinobi.Shinobi;

public class GameServer {
    private static final int PORT = 9000;
    private static final Queue<ClientHandler> matchmakingQueue = new ConcurrentLinkedQueue<>();
    private static final Set<ClientHandler> activeClients = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public static void main(String[] args) {
        System.out.println("⚡=========================================================⚡");
        System.out.println("              🔥 NARUTO MULTIPLAYER SERVER 🔥               ");
        System.out.println("                  Listening on Port " + PORT + "                  ");
        System.out.println("⚡=========================================================⚡");

        // Matchmaking thread
        Thread matchmaker = new Thread(() -> {
            while (true) {
                try {
                    if (matchmakingQueue.size() >= 2) {
                        ClientHandler p1 = matchmakingQueue.poll();
                        ClientHandler p2 = matchmakingQueue.poll();
                        
                        if (p1 != null && p2 != null && p1.isAlive() && p2.isAlive()) {
                            System.out.println("⚔️ MATCH FOUND: " + p1.getShinobi().getName() + " vs " + p2.getShinobi().getName());
                            MultiplayerBattle battle = new MultiplayerBattle(p1, p2);
                            new Thread(battle).start();
                        } else {
                            // Re-queue active ones if one died
                            if (p1 != null && p1.isAlive()) matchmakingQueue.add(p1);
                            if (p2 != null && p2.isAlive()) matchmakingQueue.add(p2);
                        }
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Matchmaker interrupted.");
                    break;
                }
            }
        });
        matchmaker.setDaemon(true);
        matchmaker.start();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(clientSocket);
                activeClients.add(handler);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    public static class ClientHandler implements Runnable {
        private final Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private Shinobi shinobi;
        private boolean inQueue = false;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                System.err.println("Failed to establish I/O streams: " + e.getMessage());
            }
        }

        public Socket getSocket() {
            return socket;
        }

        public Shinobi getShinobi() {
            return shinobi;
        }

        public BufferedReader getIn() {
            return in;
        }

        public PrintWriter getOut() {
            return out;
        }

        public boolean isAlive() {
            return socket != null && !socket.isClosed();
        }

        @Override
        public void run() {
            try {
                // First read character details
                String characterData = in.readLine();
                if (characterData == null) {
                    closeConnection();
                    return;
                }
                
                this.shinobi = Shinobi.deserialize(characterData);
                System.out.println("👥 Connected: " + shinobi.getName() + " (Lvl " + shinobi.getLevel() + ") from " + shinobi.getVillage());
                
                out.println("✅ Welcome to the Shinobi Multiplayer Server, " + shinobi.getName() + "!");
                out.println("🔎 Entering the matchmaking queue... Please wait for an opponent.");
                
                this.inQueue = true;
                matchmakingQueue.add(this);
                
                // Monitor connection
                while (isAlive()) {
                    // Check if socket is open
                    int readValue = in.read();
                    if (readValue == -1) {
                        break;
                    }
                }
            } catch (IOException e) {
                // Connection closed
            } finally {
                closeConnection();
            }
        }

        public void closeConnection() {
            try {
                if (inQueue) {
                    matchmakingQueue.remove(this);
                }
                activeClients.remove(this);
                if (socket != null && !socket.isClosed()) {
                    System.out.println("👥 Disconnected: " + (shinobi != null ? shinobi.getName() : "Unknown Client"));
                    socket.close();
                }
            } catch (IOException e) {
                // ignore
            }
        }
    }
}
