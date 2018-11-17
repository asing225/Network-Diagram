package com.company.Controller;

import javafx.scene.text.Text;

public class HelpText {
    public Text helpText(){
        Text text = new Text();
        text.setText("Activity name - Enter the name of the activity. This field can have value with alphabets and numbers."
                + "\n\nDuration - Enter the time duration of the activity. This field can only have numeric values."
                + "\n\nDependencies - Enter the activities on which this activity depends. This filed can only have values from previously entered activities. Any other activity entered will result in error."
                + "\n\nActivity Table - This table with all the data of activities that the user has entered."
                + "\n\nButtons: \nAdd Activity - to save the activity entered in the input fields and display it in the activity table."
                + "\n\nReset Activity - to reset the input fields and user can start entering new activity values."
                + "\n\nDisplay All Path/s - to analyze the inputs from the user and display all paths in the network diagram."
                + "\n\nDisplay Critical Path/s - to display only the critical path/s in the network diagram."
                + "\n\nNew Network Diagram - to discard all entered data and start a new network diagram."
                + "\n\nCreate Report - to create a text report of the activities and the paths associated with the network diagram."
                + "\n\nReport name - Enter the name of the text file for the report."
                + "\n\nUpdate Network Diagram - to update the changes made by user in table and update the paths."
                + "\n\nAbout - to open the help tab that contains information about the application."
                + "\n\nHelp - to open the help tab that contains information about all the buttons and fields in the application."
                + "\n\nQuit - to close the application."
                + "\n\nFinal Output - to display the current log and the final paths."
                );
        text.setWrappingWidth(500);
        return text;
    }
}
