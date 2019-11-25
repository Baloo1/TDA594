import com.sun.tools.javac.comp.Todo;
import org.sat4j.pb.SolverFactory;
import org.sat4j.reader.DimacsReader;
import org.sat4j.reader.ParseFormatException;
import org.sat4j.reader.Reader;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        ISolver solver = SolverFactory.newDefault();
        Reader reader = new DimacsReader(solver);
        PrintWriter out = new PrintWriter(System.out,true);
        // CNF filename is given on the command line

        try {
            boolean unsat = true;
            IProblem problem = reader.parseInstance(args[0]);
            while (problem.isSatisfiable()) {
                unsat = false;
                int [] model = problem.model();
                // TODO each model needs to be used for something
                System.out.println("isSatisfiable");
            }
            if (unsat) {
                System.out.println("isUnSatisfiable");
                // TODO handle unsatisfiable
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (ParseFormatException e) {
            System.out.println("Format not correct");
        } catch (IOException e) {
            System.out.println("IO exception");
        } catch (ContradictionException e) {
            System.out.println("Unsatisfiable (trivial)!");
        } catch (TimeoutException e) {
            System.out.println("Timeout, sorry!");
        }
    }
}