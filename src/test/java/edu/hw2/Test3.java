package edu.hw2;

import edu.hw2.Task3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test3 {
    @Test
    @DisplayName("Sample test (true)")
    void testSuccess() {
        Task3.ConnectionManager manager = new Task3.FaultyConnectionManager();
        Task3.PopularCommandExecutor executor = new Task3.PopularCommandExecutor(manager, 1, 1);
        try {
            executor.updatePackages();
            System.out.println("Executed successful");
        } catch (Task3.ConnectionException e) {
            System.out.println("Connection error");
        }

    }

    @Test
    @DisplayName("Sample test (false)")
    void testFail() {
        Task3.ConnectionManager manager = new Task3.FaultyConnectionManager();
        Task3.PopularCommandExecutor executor = new Task3.PopularCommandExecutor(manager, 1, 0);
        try {
            executor.updatePackages();
            System.out.println("Executed successful");
        } catch (Task3.ConnectionException e) {
            System.out.println("Connection error");
        }

    }

    @Test
    @DisplayName("Sample test (fifty-fifty)")
    void test() {
        Task3.ConnectionManager manager = new Task3.FaultyConnectionManager();
        Task3.PopularCommandExecutor executor = new Task3.PopularCommandExecutor(manager, 1, 0.5);
        try {
            executor.updatePackages();
            System.out.println("Executed successful");
        } catch (Task3.ConnectionException e) {
            System.out.println("Connection error");
        }

    }
}
