import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        n1();
        n2();
        n3();
    }
    private static void n1(){
        final int numberOfRuns = 10;
        final long intervalMillis = 2000;

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            int runs = 0;

            @Override
            public void run() {
                if (runs < numberOfRuns) {
                    try {
                        // Запускаем блокнот
                        ProcessBuilder processBuilder = new ProcessBuilder("notepad.exe");
                        processBuilder.start();
                        //System.out.println("Запущен блокнот.");

                        runs++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    timer.cancel();
                    //System.out.println("Завершено выполнение.");
                }
            }
        }, 0, intervalMillis);
    }
    private static void n2(){
        try {
            // Execute the "tasklist" command to get a list of running processes
            @Deprecated
            Process process = Runtime.getRuntime().exec(System.getenv("SystemRoot") + "\\system32\\tasklist.exe");

            // Read the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            int skip = 0;
            boolean first = true;
            while ((line = reader.readLine()) != null) {
                // Parse each line to extract the PID and process name
                if (!line.trim().isEmpty()) {
                    String[] parts = line.trim().split("\\s+");
                    //System.out.println(line);
                    if (parts.length >= 2 && skip++>2) {
                        String pid = parts[1];
                        String processName = parts[0];
                        if(first){
                            System.out.println("PID: " + pid + " он был первым!!!");
                            first = false;
                            continue;
                        }
                        System.out.println("PID: " + pid);
                    }
                }
            }

            // Close the reader and wait for the process to exit
            reader.close();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
    private static void n3(){
        try {
            // Execute the "tasklist" command to get a list of running processes
            @Deprecated
            Process process = Runtime.getRuntime().exec(System.getenv("SystemRoot") + "\\system32\\tasklist.exe");

            // Read the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            int skip = 0;
            boolean first = true;
            while ((line = reader.readLine()) != null) {
                // Parse each line to extract the PID and process name
                if (!line.trim().isEmpty()) {
                    String[] parts = line.trim().split("\\s+");
                    //System.out.println(line);
                    if (parts.length >= 2 && skip++>2) {
                        String pid = parts[1];
                        String processName = parts[0];
                        if(first){
                            System.out.println("Name of process " + processName + " он был первым!");
                            first = false;
                            continue;
                        }
                        System.out.println("Name of process " + processName);
                    }
                }
            }

            // Close the reader and wait for the process to exit
            reader.close();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
