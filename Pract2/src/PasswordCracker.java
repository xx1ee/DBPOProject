import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PasswordCracker {
    private static final List<String> targetHashes = Arrays.asList(
            "1115dd800feaacefdf481f1f9070374a2a81e27880f187396db67958b207cbad",
            "3a7bd3e2360a3d29eea436fcfb7e44c735d117c42d1c1835420b6b9942dd4f1b",
            "74e1bb62f8dabb8125a58852b63bdf6eaef667cb56ac7f7cdba6d7305c50a22f",
            "7a68f09bd992671bb3b19a5e70b7827e"
    );

    public static String hashMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md.digest(password.getBytes());
        return bytesToHex(hashBytes);
    }

    public static String hashSHA256(String password) throws NoSuchAlgorithmException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = sha256.digest(password.getBytes());
        return bytesToHex(hashBytes);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    public static void bruteForce(int numThreads) throws InterruptedException {
        long startTime = System.nanoTime();

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        for (char c1 = 'a'; c1 <= 'z'; c1++) {
            for (char c2 = 'a'; c2 <= 'z'; c2++) {
                for (char c3 = 'a'; c3 <= 'z'; c3++) {
                    for (char c4 = 'a'; c4 <= 'z'; c4++) {
                        for (char c5 = 'a'; c5 <= 'z'; c5++) {
                            final String password = "" + c1 + c2 + c3 + c4 + c5;
                            executor.submit(() -> {
                                try {
                                    checkPassword(password);
                                } catch (NoSuchAlgorithmException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    }
                }
            }
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        long endTime = System.nanoTime();
        System.out.println("Время выполнения: " + (endTime - startTime) / 1_000_000 + " ms");
    }

    public static void checkPassword(String password) throws NoSuchAlgorithmException {
        String md5Hash = hashMD5(password);
        String sha256Hash = hashSHA256(password);

        if (targetHashes.contains(md5Hash) || targetHashes.contains(sha256Hash)) {
            System.out.println("Пароль найден: " + password);
            System.out.println("MD5: " + md5Hash);
            System.out.println("SHA-256: " + sha256Hash);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int numThreads = 4;
        System.out.println("Однопоточный режим:");
        bruteForce(1);

        System.out.println("\nМногопоточный режим:");
        bruteForce(numThreads);
    }
}

