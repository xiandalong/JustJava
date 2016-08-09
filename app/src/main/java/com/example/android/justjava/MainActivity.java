package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    int quantity = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.nameField);
        String name = nameField.getText().toString();
        int price = calculatePrice();
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedCreamCheckBox);
        boolean addWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox addChocolateCheckBox = (CheckBox) findViewById(R.id.chocolateCheckBox);
        boolean addChocolate = addChocolateCheckBox.isChecked();
        String priceMessage = createOrderSummary(name, addWhippedCream, addChocolate, quantity, price);
        displayMessage(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }



    public void increment(View view) {
        quantity += 1;
        display(quantity);
    }

    public void decrement(View view) {
        quantity -= 1;
        display(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

//    private int calculatePrice(int number) {
//        return number * 5;
//    }
//
//    private int calculatePrice(int number, int unitPrice) {
//        return number * unitPrice;
//    }

    private int calculatePrice() {
        return quantity * 5;
    }

    private String createOrderSummary(String name, boolean addWhippedCream, boolean addChocolate, int quantity, int price) {
        String summary = "";
        summary += "Name: " + name;
        summary += "\nAdd whipped cream? " + addWhippedCream;
        summary += "\nAdd chocolate? " + addChocolate;
        summary += "\nQuantity: " + quantity;
        summary += "\nTotal: $" + price;
        summary += "\nThank you!";
        return summary;
    }
}
