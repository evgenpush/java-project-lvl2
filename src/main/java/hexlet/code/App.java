package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@Command(name = "gendiff ",  version = "App 1.0", mixinStandardHelpOptions = true,
         description = "Compares two configuration files and shows a difference.")

class App implements Callable<Integer> {
    private static final int SUCCESS_CODE = 0;
    private static final int ERROR_CODE = 1;


    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String file1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String file2;

    @Override
    public Integer call() {
        try {
            String diff = Differ.generate(file1, file2, format);
            System.out.println(diff);
            return SUCCESS_CODE;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR_CODE;
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

    }
}
