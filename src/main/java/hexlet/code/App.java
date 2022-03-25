package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;
import java.util.*;

@Command(name = "gendiff ",  version = "App 1.0", mixinStandardHelpOptions = true,
         description = "Compares two configuration files and shows a difference.")

class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "my format";

    @Parameters(paramLabel = "filepatch1", description = "patch to first file")
    private String file1;

    @Parameters(paramLabel = "filepatch2", description = "patch to second file")
    private String file2;

    @Override
    public Integer call() throws Exception {

        String test = Differ.generate(file1, file2);

        System.out.println(test);
        return 0;
    }



	public static  void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

	}
}
