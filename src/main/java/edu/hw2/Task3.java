package edu.hw2;

import java.util.Random;

public class Task3 {
    private Task3() {

    }

    private static final int CONSTANT_FOR_PROBABILITY = 99;

    public interface Connection extends AutoCloseable {
        void execute(String command, double probability);
    }

    public interface ConnectionManager {
        Connection getConnection();
    }

    public static class StableConnection implements Connection {
        public void execute(String command, double probability) throws ConnectionException {

        }

        @Override
        public void close() throws ConnectionException {

        }
    }

    public static class FaultyConnection implements Connection {
        public void execute(String message, double probability) throws ConnectionException {
            Random random = new Random();
            int randInt = random.nextInt(CONSTANT_FOR_PROBABILITY);
            if (randInt < probability * (CONSTANT_FOR_PROBABILITY + 1)) {
                return;
            } else {
                throw new ConnectionException("Failed to execute", new Exception("random error"));
            }
        }

        public void close() throws ConnectionException {
        }
    }

    public static class FaultyConnectionManager implements ConnectionManager {
        @Override
        public Connection getConnection() {
            return new FaultyConnection();
        }
    }

    public static class DefaultConnectionManager implements ConnectionManager {

        @Override
        public Connection getConnection() {
            Random random = new Random();
            int randInt = random.nextInt(2);
            if (randInt == 0) {
                return new StableConnection();
            } else {
                return new FaultyConnection();
            }
        }
    }

    public static class ConnectionException extends RuntimeException {
        public ConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static final class PopularCommandExecutor {
        private final ConnectionManager manager;
        private final int maxAttempts;
        private final double probability;

        public PopularCommandExecutor(ConnectionManager manager, int maxAttempts, double probability) {
            this.manager = manager;
            this.maxAttempts = maxAttempts;
            this.probability = probability;
        }

        public void updatePackages() {
            tryExecute("apt update && apt upgrade -y");
        }

        public void tryExecute(String command) {

            for (int i = 0; i < maxAttempts; ++i) {
                try {
                    manager.getConnection().execute(command, this.probability);
                    return;
                } catch (ConnectionException e) {
                    continue;
                }
            }
            throw new ConnectionException("Failed to execute command" + command, new Exception("Error"));
        }
    }
}
