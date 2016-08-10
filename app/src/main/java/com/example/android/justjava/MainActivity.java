package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    int quantity = 99;
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

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedCreamCheckBox);
        boolean addWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox addChocolateCheckBox = (CheckBox) findViewById(R.id.chocolateCheckBox);
        boolean addChocolate = addChocolateCheckBox.isChecked();

        int price = calculatePrice(addWhippedCream, addChocolate);

        String priceMessage = createOrderSummary(name, addWhippedCream, addChocolate, quantity, price);
//        displayMessage(priceMessage);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subj, name));
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

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
        if (quantity == 100) {
            Toast toast = Toast.makeText(this, getString(R.string.toast_too_large), Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantity += 1;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast toast = Toast.makeText(this, getString(R.string.toast_too_small), Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantity -= 1;
        display(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }

//    private int calculatePrice(int number) {
//        return number * 5;
//    }
//
//    private int calculatePrice(int number, int unitPrice) {
//        return number * unitPrice;
//    }
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int unitPrice = 5;
        if (addWhippedCream) unitPrice += 1;
        if (addChocolate) unitPrice += 2;
        return quantity * unitPrice;
    }

    private String createOrderSummary(String name, boolean addWhippedCream, boolean addChocolate, int quantity, int price) {
        String summary = "";
        summary += getString(R.string.order_summary_name, name);
        summary += "\n" + getString(R.string.order_summary_addWhippedCream, addWhippedCream);
        summary += "\n" + getString(R.string.order_summary_addChocolate, addChocolate);
        summary += "\n" + getString(R.string.order_summary_quantity, quantity);
        summary += "\n" + getString(R.string.order_summary_total, NumberFormat.getCurrencyInstance().format(price));
        summary += "\n" + getString(R.string.order_summary_thankyou);
        return summary;
    }
}
