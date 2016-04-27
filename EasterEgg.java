/*
    This class implements an easter egg which will print out the names of the members of this project team

*/
class EasterEgg {
    private String[] team;
    //Needs to be updated each time a new member adds their name.
    private int numMems = 3;
    
    /* This method prints out a welcome statement.
     * This method doesn't take any arguments, and returns no values.
     */
    private void printStinc () {
        System.out.println("You've found team STINC's Easter Egg!");
    }
    
    /* This method adds the specified string to the team list at an index equal
     * to the order given -1 (1st person in is in index 0, 5th person in is at index 4)
     * @param aName the name of the team member to be added.
     * @param anIndex the order the given member entered their name.
     * @return Void.
     */
    private void assignTeamMember (String aName, int anIndex) {
        team[anIndex-1] = aName;
    }
    
    public static void main(String[] args) {
        team = String[numMems];
        printStinc();
        
        //Team member assignment
        //====================================================================================
        // ADD YOUR NAME UNDER THIS. YOU WILL ALSO NEED TO UPDATE THE numMems VALUE AT THE TOP
        //====================================================================================
        assignTeamMember("Taylor", 1);
        assignTeamMember("Nicholas", 2);
        assignTeamMember("Igor", 3);
        
        
        
        
        
        //Prints the names in team[]
        System.out.print("We are: ");
        
        int i = 0;
        for (i; i < numMems; i++) {
            if (i == numMems-1 && numMems > 1) {
                System.out.println("and " + team[i]);
            } else if(i == numMems-1 && numMems <= 1){
                System.out.println(team[i]);
            } else {
                System.out.print(team[i]+", ");
            }
        }
    }
}
