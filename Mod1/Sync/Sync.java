/**
 * Created by vsevolodmolchanov on 09.04.17.
 */

import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sync {
    public static void main(String[] args) throws IOException {
        String S = args[0] + '/';
        String D = args[1] + '/';

        Set<String> delete = new TreeSet<>();
        Set<String> copy = new TreeSet<>();

        Set<String> filesS = new HashSet<>();
        Set<String> filesD = new HashSet<>();
        Files.walk(Paths.get(S)).forEach(filePath -> {
            if(Files.isRegularFile(filePath)) {
                filesS.add(filePath.toString().replaceFirst(S, ""));
            }
        });
        Files.walk(Paths.get(D)).forEach(filePath -> {
            if(Files.isRegularFile(filePath)) {
                filesD.add(filePath.toString().replaceFirst(D, ""));
            }
        });

        Set<String> toDelete = new HashSet<>(filesD);
        Set<String> toCopy = new HashSet<>(filesS);
        toDelete.removeAll(filesS);
        toCopy.removeAll(filesD);
        delete.addAll(toDelete);
        copy.addAll(toCopy);

        Set<String> intersection = new HashSet<>(filesS);
        intersection.retainAll(filesD);
        for(String file: intersection) {
            byte[] f1 = Files.readAllBytes(Paths.get(S + file));
            byte[] f2 = Files.readAllBytes(Paths.get(D + file));
            boolean isEqual = Arrays.equals(f1, f2);;
            if(!isEqual) {
                delete.add(file);
                copy.add(file);
            }
        }

        if(delete.size() == 0 && copy.size() == 0) {
            System.out.println("IDENTICAL");
        } else {
            String result = "";
            for(String elem: delete) {
                result += "DELETE " + elem + '\n';
            }
            for(String elem: copy) {
                result += "COPY " + elem + '\n';
            }
            System.out.println(result);
        }
    }
}
