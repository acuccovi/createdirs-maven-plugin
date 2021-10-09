package io.github.acuccovi.maven;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 *
 * @author alessio
 */
@Mojo(name = "create-dirs")
public class CreateDirsMojo extends AbstractMojo {

    @Parameter(defaultValue = "false")
    /**
     * Boolean value that indicates whether skip the execution of this
     * plugin. <br />Default false
     */
    boolean skip;

    @Parameter(required = false, defaultValue = "true")
    /**
     * Boolean value that indicates whether create intermediate
     * subdirectory. <br />Default true
     */
    boolean intermediateDir;

    @Parameter(required = true)
    /**
     * A list of directory to be created. Example of usage:<br />&lt;dirs><br />
     * &nbsp;&lt;dir>${project.build.directory}/path&lt;/dir><br />
     * &nbsp;&lt;dir>~/This/is/another/path&lt/dir><br />&lt;dirs>
     */
    List<File> dirs;

    public void execute() throws MojoExecutionException, MojoFailureException {

        if (skip) {
            getLog().info("Skipping the execution of the plugin");
            return;
        }
        for (File dir : dirs) {
            String path = dir.getAbsolutePath();
            if (!dir.exists()) {
                getLog().debug("Creating dir: " + path);
                try {
                    if (intermediateDir) {
                        Files.createDirectories(Paths.get(path));
                    } else {
                        Files.createDirectory(Paths.get(path));
                    }
                } catch (IOException ioe) {
                    getLog().error("Creating dir: " + path);
                    throw new MojoExecutionException("Error creating dir: " + path, ioe);
                }
            } else {
                getLog().warn("The directory already exists: " + path);
            }
        }
    }

}
