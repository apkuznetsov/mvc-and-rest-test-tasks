package demo.solver;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class SubstringSearch {

    public static String[] getSubstringsThatInStrings(String[] substrings, String[] strings) {

        Set<String> resultSet = searchSubstringsInStrings(substrings, strings);
        String[] resultArr = new String[resultSet.size()];
        
        return resultSet.toArray(resultArr);
    }

    private static Set<String> searchSubstringsInStrings(String[] substrings, String[] strings) {

        Arrays.sort(substrings);

        final Set<String> resultSet = new TreeSet<>();
        for (String substring : substrings) {
            for (String string : strings) {
                if (string.contains(substring)) {
                    resultSet.add(substring);
                    break;
                }
            }
        }

        return resultSet;
    }
}
