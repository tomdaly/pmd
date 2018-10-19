/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.cpd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import net.sourceforge.pmd.cpd.renderer.CPDRenderer;

/**
 * @author Philippe T'Seyen
 * @deprecated {@link CPDRenderer} directly renders to a Writer
 */
@Deprecated // to be removed with 7.0.0
public class FileReporter {
    private File reportFile;
    private String encoding;

    public FileReporter(String encoding) {
        this(null, encoding);
    }

    public FileReporter(File reportFile) {
        this(reportFile, System.getProperty("file.encoding"));
    }

    public FileReporter(File reportFile, String encoding) {
        this.reportFile = reportFile;
        this.encoding = encoding;
    }

    public void report(String content) throws ReportException {
        try {
            try (OutputStream outputStream = reportFile == null ? System.out : new FileOutputStream(reportFile);
                 Writer writer = new BufferedWriter(new OutputStreamWriter(outputStream, encoding))) {
                writer.write(content);
            }
        } catch (IOException ioe) {
            throw new ReportException(ioe);
        }
    }
}
