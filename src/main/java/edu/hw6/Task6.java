package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;

@SuppressWarnings("RegexpSinglelineJava")
public class Task6 {
    private Task6() {

    }

    static Map<String, String> services = Map.ofEntries(
        Map.entry("53", "DNS"),
        Map.entry("21", "FTP"),
        Map.entry("22", "SSH"),
        Map.entry("135", "EPMAP"),
        Map.entry("443", "HTTPS"),
        Map.entry("25", "SMPT"),
        Map.entry("445", "MICROSOFT-DS"),
        Map.entry("138", "NETBIOS-DGM")
    );

    private static String getPortOccupancy(int port) {
        return services.getOrDefault(String.valueOf(port), "");
    }

    public static void scanTCPPorts(int start, int end) {
        for (int port = start; port <= end; port++) {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                continue;
            } catch (IOException e) {
                System.out.println("TCP | " + port + "| " + getPortOccupancy(port));
            }
        }
    }

    public static void scanUDPPorts(int start, int end) {
        for (int port = start; port <= end; port++) {
            try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
                continue;
            } catch (IOException e) {
                System.out.println("UDP | " + port + " | " + getPortOccupancy(port));
            }
        }
    }

    public static void scanPorts(int start, int end) {
        System.out.println("Протокол | Порт | Сервис");
        scanTCPPorts(start, end);
        scanUDPPorts(start, end);
    }
}
