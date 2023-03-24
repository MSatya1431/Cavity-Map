import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'cavityMap' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING_ARRAY grid as parameter.
     */

    public static List<String> cavityMap(List<String> grid) {
    List<String> result = new ArrayList<>();

    // iterate over the rows of the grid
    for(int i=0;i<grid.size();i++)
    {
        String row=grid.get(i);
        StringBuilder modifiedRow=new StringBuilder();

        // iterate over the columns of the row
        for(int j=0;j<row.length();j++)
        {
            char c=row.charAt(j);

            // skip border cells
            if(i==0||i==grid.size()-1||j==0||j==row.length()-1)
            {
                modifiedRow.append(c);
                continue;
            }

            // check if the cell is a cavity
            boolean isCavity=true;
            char[] neighbors={
                grid.get(i-1).charAt(j), // north
                grid.get(i+1).charAt(j), // south
                row.charAt(j-1), // west
                row.charAt(j+1) // east
            };
            for(char neighbor:neighbors)
            {
                if (c<=neighbor) 
                {
                    isCavity=false;
                    break;
                }
            }

            // add the modified cell to the row
            if(isCavity)
            {
                modifiedRow.append('X');
            } 
            else 
            {
                modifiedRow.append(c);
            }
        }
        result.add(modifiedRow.toString());
    }
    return result;
}


}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> grid = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<String> result = Result.cavityMap(grid);

        bufferedWriter.write(
            result.stream()
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
