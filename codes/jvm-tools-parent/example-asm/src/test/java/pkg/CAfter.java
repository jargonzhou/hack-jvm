package pkg;

public class CAfter {
    public static long timer;

    public void m() throws Exception {
        timer -= System.currentTimeMillis();
        Thread.sleep(100);
        timer += System.currentTimeMillis();
        System.out.println("Last " + timer + " ms");
    }
}
