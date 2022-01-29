public class MainClient {
    public static void main(String arg[])
    {
        Thread client = new Thread(new Client(8187,"0.0.0.0",arg[0]));

        client.start();
    }
}
