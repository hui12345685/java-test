import test.OuterClass;
import test.PrintOldAndEventNumbers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void test1(){
        OuterClass out = new OuterClass();
        OuterClass.InnerClass inner = out.createInnerClassInstance();
        System.out.println("used in other method: " + inner.getInnerField());
    }

    public static void printOldAndEventNumbersTest() {
        try {
            PrintOldAndEventNumbers printer = new PrintOldAndEventNumbers();
            Thread oddThread = new Thread(printer.new OddPrinter(), "OddThread");
            Thread evenThread = new Thread(printer.new EvenPrinter(), "EvenThread");

            oddThread.start();
            evenThread.start();

            // 假设我们只打印前20个数字（10个奇数和10个偶数）
            // 由于线程是交替打印的，所以不需要精确控制打印数量
            Thread.sleep(2000); // 让线程运行一段时间

            // 可选：等待线程真正结束（在实际应用中可能不需要）
            oddThread.join();
            evenThread.join();
        } catch (Exception e) {
            System.out.println("PrintOldAndEventNumbersTest main thread Exception: " + e.getMessage());
        }
    }

    public static void simpleTest() {
        List<?> ls = new ArrayList<>();
        HashMap<String,String> hmp = new HashMap<>();
        hmp.put("a", "1");
    }

    private static final int BUFFER_SIZE = 1024 * 1024; // 1MB的缓冲区大小，可以根据需要调整
    public void ReadFileTest() {
        String filePath = "path/to/your/large/file.dat"; // 替换为你的大文件路径

        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                // 处理读取到的数据块
                // 注意：buffer中的有效数据长度为bytesRead，可能小于BUFFER_SIZE
                //processDataBlock(buffer, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // 处理IO异常，例如文件未找到、读取错误等
        }
    }

    public static void main(String[] args) {
        Main.test1();
        printOldAndEventNumbersTest();
        simpleTest();
    }
}