import com.beust.jcommander.JCommander;


public class Program {
    public static void main(String[] args) {
        Arguments arguments = new Arguments();
        JCommander.newBuilder().addObject(arguments).build().parse(args);

        System.out.println(arguments.getPath());
        System.out.println(arguments.getPrefix());
        System.out.println(arguments.getFiles());
        System.out.println(arguments.isAddOption());
        System.out.println(arguments.isFullStatistics());
        System.out.println(arguments.isShortStatistics());

    }
}
