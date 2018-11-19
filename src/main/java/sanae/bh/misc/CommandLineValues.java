package sanae.bh.misc;

import java.io.File;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

//
public class CommandLineValues {
    @Option(name = "-i", aliases = { "--input" },
            usage = "input file with two matrices")
    private File source;
    @Option(name="-cl", aliases = {"--location"},
            usage = "custom specified class location if class not marked in manifest")
    private String classLocation;
    private boolean errorFree = false;

    public CommandLineValues(String... args) {
        CmdLineParser parser = new CmdLineParser(this);
        parser.setUsageWidth(80);
        try {
            parser.parseArgument(args);

            if (getSource()!= null&& !getSource().isFile()) {
                throw new CmdLineException(parser,
                        "--input is no valid input file.",null);
            }

            errorFree = true;
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }
    }

    public boolean isErrorFree() {
        return errorFree;
    }

    public File getSource() {
        return source;
    }
}