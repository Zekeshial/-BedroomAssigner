/**
 * Created with the use of AI tools
 */



package com.example.lab4project;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;
import java.util.function.UnaryOperator;

public class NumericTextField extends TextField {

    public NumericTextField() {
        super();

        // Define a filter using a regular expression to allow only digits (and an optional negative sign at the start)
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            // This regex allows optional negative sign followed by digits
            if (newText.matches("([0-9]*)?")) {
                return change;
            } else {
                // Reject the change if it doesn't match the pattern
                return null;
            }
        };

        // Create the TextFormatter with the converter and filter
        TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), null, integerFilter);

        // Apply the text formatter to the TextField
        this.setTextFormatter(textFormatter);
    }

    /**
     * Gets the current numeric value of the text field.
     * The TextFormatter's value property holds the converted number.
     * @return The current integer value, or null if the field is empty or invalid.
     */
    public Integer getIntValue() {
        return ((TextFormatter<Integer>) this.getTextFormatter()).getValue();
    }
}
