package com.company.Controller;

import javafx.scene.text.Text;

public class HelpText {
    public Text helpText(){
        Text text = new Text();
        text.setText("Activity name - Enter the name of the activity. This field can have value with alphabets and numbers."
                + "\n\nDuration - Enter the time duration of the activity. This field can only have numeric values."
                + "\n\nDependencies - Enter the activities on which this activity depends. This filed can only have values from previously entered activities. Any other activity entered will result in error."
                + "\n\nActivity Table - This table contains all the activities that the user has already entered."
                + "\n\nButtons: \nAdd Activity - This button will save the activity entered in the input fields and display it in the activity table."
                + "\n\nReset - This button will reset the input fields and user can start entering new activity values."
                + "\n\nCreate Network Diagram - This button will analyze the inputs from the user and create the network diagram."
                + "\n\nAbout - This button will open the help tab that contains information about the application."
                + "\n\nHelp - This button will open the help tab that contains information about all the buttons and fields in the application."
                + "\n\nQuit - This button will close the application."
                + "\n\nEdit - This button will enable the user to edit already entered activity details. To enable this button, user will have to select one activity from the activity table."
                + "\n\nDelete - This button will delete the activity selected from the activity table."
                + "\n\nFinal Output: \tCreate New Diagram - This button will open the home page for user to enter the new inputs."
                + "\n\nExit Application - This button will close the application.");
        text.setWrappingWidth(400);
        return text;
    }
}