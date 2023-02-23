/**
 * @author: Jiahui Zhu
 * @email address: jiahuiz2@andrew.cmu.edu
 * @Project Number: Project1 Task3
 */
package ds.project1task3;

public class ClickerModel {
    // Set up parameter to represent each choice result
    static int [] choices = {0, 0, 0, 0};
    static int AChoice = 0;
    static int BChoice = 0;
    static int CChoice = 0;
    static int DChoice = 0;

    /**
     * This method is used to store the number of each choice be selected.
     * @param choice The choice selected by user
     * @return The array of number of times each option is selected.
     */
    public int[] getChoice(String choice) {
        // If user select A, then increase the number of times of A,
        // and put into the first place of the array.
        if (choice.equalsIgnoreCase("A")) {
            AChoice++;
            choices[0] = AChoice;
        }
        // If user select B, then increase the number of times of B,
        // and put into the second place of the array.
        if (choice.equalsIgnoreCase("B")) {
            BChoice++;
            choices[1] = BChoice;
        }
        // If user select C, then increase the number of times of C,
        // and put into the third place of the array.
        if (choice.equalsIgnoreCase("C")) {
            CChoice++;
            choices[2] = CChoice;
        }
        // If user select D, then increase the number of times of D,
        // and put into the last place of the array.
        if (choice.equalsIgnoreCase("D")) {
            DChoice++;
            choices[3] = DChoice;
        }
        // Return the results of four choices.
        return choices;
    }
}
