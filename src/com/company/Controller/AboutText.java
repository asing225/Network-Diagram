package com.company.Controller;

import javafx.scene.text.*;

public class AboutText {
    public Text[] aboutText(){

        Text text1 = new Text();
        text1.setText("\t\tNetwork Diagram Analyzer\n\n");
        text1.setTextAlignment(TextAlignment.CENTER);
        text1.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 16));

        Text text2 = new Text();
        text2.setText("\t\t\t\t\tversion v1\n\n" + "\t\t\t\tNot for commercial use\n\n\n\n\n\n\n");
        text2.setTextAlignment(TextAlignment.CENTER);
        text2.setFont(Font.font(null, FontWeight.BLACK, FontPosture.REGULAR, 16));

        Text text3 = new Text();
        text3.setText("\n\n\n\n\n\t\t\tDevelopers: Amanjot Singh\n\n\t\t\t\t\t     Bhawana Prasad\n\n\t\t\t\t\t     Andrew Tran\n\n\t\t\t\t\t     Venkata Maganti\n");
        text3.setTextAlignment(TextAlignment.CENTER);
        text3.setFont(Font.font(null, FontWeight.BLACK, FontPosture.REGULAR, 16));

        Text text4 = new Text();
        text4.setText("\n\n\n\n\n\n\n\n\n\n\n\n\n\tProudly made at Software Engineering Department,\n\n\t\t\t\tArizona State University");
        text4.setTextAlignment(TextAlignment.CENTER);
        text4.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 16));

        Text[] texts = new Text[]{text1,text2,text3,text4};
        return texts;
    }
}