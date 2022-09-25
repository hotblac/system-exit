public class ExitWithCode {

    public static void main(String[] args) {
        if (args.length < 1) return;

        int statusCode = Integer.parseInt(args[0]);
        System.exit(statusCode);
    }
}
