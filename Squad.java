package sandbox;

import java.util.ArrayList;

public class Squad {
    
    private int[] initial = new int[3];
    private int[] desired = new int[3];
    private int record = 100;
    StringBuilder outputText = new StringBuilder();
    
    public Squad(int[] currentValues, int[] desiredValues) {
        initial = currentValues.clone();
        desired = desiredValues.clone();
    }
    
    public String solve() {
        ArrayList<String> initPath = new ArrayList<String>();
        matchArrays(initial, desired, initPath);
        if (record == 100) {
            outputText.append("No solution found. Please double check the values you entered\n");
        }
        else {
            outputText.append("Best Solution: " + record + "\n");
        }
        return outputText.toString();
    }
    
    public int matchArrays(int[] compArray, int[] desiredArray, ArrayList<String> path) {
        
        if (compArray[0] == desiredArray[0] && compArray[1] == desiredArray[1] && compArray[2] == desiredArray[2]) {
            if (path.size() <= record) {
                record = path.size();
                outputText.append("Solution [" + path.size() + "]:");
                //System.out.print("Solution [" + path.size() + "]:");
                for (String s : path) {
                    //System.out.print(s + " ");
                    outputText.append(s + "  ");
                }
                outputText.append("\n");
                //System.out.println("");
            }
            if (path.size() > 0)
                path.remove(path.size() - 1);
            return 0;
        }
        if (path.size() > 8) {
            if (path.size() > 0)
                path.remove(path.size() - 1);
            return 0;
        }
        if ((compArray[1] - 20 >= 0 && compArray[2] - 20 >= 0) || (compArray[1] - 40 >= 0 && compArray[2] == 0) || (compArray[2] - 40 >= 0 && compArray[1] == 0)) {
            path.add("P");            
            if ((compArray[1] - 20 >= 0 && compArray[2] - 20 >= 0)) {
                matchArrays(new int[]{compArray[0] + 40, compArray[1] - 20, compArray[2] - 20}, desiredArray, path);
            }
            else if ((compArray[1] - 40 >= 0 && compArray[2] == 0)) {
                matchArrays(new int[]{compArray[0] + 40, compArray[1] - 40, compArray[2]}, desiredArray, path);
            }
            else {
                matchArrays(new int[]{compArray[0] + 40, compArray[1], compArray[2] - 40}, desiredArray, path);
            }
        }
        if (compArray[2] - 40 >= 0) {
            path.add("P+M");            
            matchArrays(new int[]{compArray[0] + 20, compArray[1] + 20, compArray[2] - 40}, desiredArray, path);
        }
        if ((compArray[0] - 20 >= 0 && compArray[2] - 20 >= 0) || (compArray[0] - 40 >= 0 && compArray[2] == 0) || (compArray[2] - 40 >= 0 && compArray[0] == 0)) {
            path.add("M");            
            if ((compArray[0] - 20 >= 0 && compArray[2] - 20 >= 0)) {
                matchArrays(new int[]{compArray[0] - 20, compArray[1] + 40, compArray[2] - 20}, desiredArray, path);
            }
            else if ((compArray[0] - 40 >= 0 && compArray[2] == 0)) {
                matchArrays(new int[]{compArray[0] - 40, compArray[1] + 40, compArray[2]}, desiredArray, path);
            }
            else {
                matchArrays(new int[]{compArray[0], compArray[1] + 40, compArray[2] - 40}, desiredArray, path);
            }
        }
        if (compArray[0] - 40 >= 0) {
            path.add("M+T");            
            matchArrays(new int[]{compArray[0] - 40, compArray[1] + 20, compArray[2] + 20}, desiredArray, path);
        }
        if ((compArray[0] - 20 >= 0 && compArray[1] - 20 >= 0) || (compArray[0] - 40 >= 0 && compArray[1] == 0) || (compArray[1] - 40 >= 0 && compArray[0] == 0)) {
            path.add("T");            
            if ((compArray[0] - 20 >= 0 && compArray[1] - 20 >= 0)) {
                matchArrays(new int[]{compArray[0] - 20, compArray[1] - 20, compArray[2] + 40}, desiredArray, path);
            }
            else if ((compArray[0] - 40 >= 0 && compArray[1] == 0)) {
                matchArrays(new int[]{compArray[0] - 40, compArray[1], compArray[2] + 40}, desiredArray, path);
            }
            else {
                matchArrays(new int[]{compArray[0], compArray[1] - 40, compArray[2] + 40}, desiredArray, path);
            }
        }
        if (compArray[1] - 40 >= 0) {
            path.add("P+T");            
            matchArrays(new int[]{compArray[0] + 20, compArray[1] - 40, compArray[2] + 20}, desiredArray, path);
        }
        if (path.size() > 0)
            path.remove(path.size() - 1);
        
        return 0;
    }
}
