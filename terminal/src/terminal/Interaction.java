package terminal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Interaction {

	public static void main(String[] args) throws IOException {
		String line;
		Scanner scan = new Scanner(System.in);

		ProcessBuilder builder = new ProcessBuilder("/bin/bash");
		builder.redirectErrorStream(true);
		Process process = builder.start();
		OutputStream stdin = process.getOutputStream ();
		InputStream stderr = process.getErrorStream ();
		InputStream stdout = process.getInputStream ();

		BufferedReader reader = new BufferedReader (new InputStreamReader(stdout));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));

		while (scan.hasNext()) {
		    String input = scan.nextLine();
		    if (input.trim().equals("exit")) {
		        // Putting 'exit' amongst the echo --EOF--s below doesn't work.
		        writer.write("exit\n");
		    } else {
		        writer.write("((" + input + ") && echo --EOF--) || echo --EOF--\n");
		    }
		    writer.flush();

		    line = reader.readLine();
		    while (line != null && ! line.trim().equals("--EOF--")) {
		        System.out.println ("Stdout: " + line);
		        line = reader.readLine();
		    }
		    if (line == null) {
		        break;
		    }
		}
	}
}
