/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;


import android.content.Context;
import android.os.Bundle;
/*import android.support.v7.app.AppCompatActivity;*/
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int toppings=0;/* variable declared in global scope*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox WhippedCreamCheckBox = (CheckBox) findViewById(R.id.Whipped_cream_checkbox);
        boolean hasWhippedCream = WhippedCreamCheckBox.isChecked();
        //Log.i("Main Avtivity", "has Whipped cream " + hasWhippedCream);

        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = ChocolateCheckBox.isChecked();

        if (WhippedCreamCheckBox.isChecked() && !ChocolateCheckBox.isChecked()) {
            toppings = 1;

        }
        else if (ChocolateCheckBox.isChecked() && !WhippedCreamCheckBox.isChecked()) {
            toppings = 2;
        }
        else if (WhippedCreamCheckBox.isChecked() && ChocolateCheckBox.isChecked()) {
            toppings = 3;
        }
        else toppings = 0;

        EditText EnterName = (EditText) findViewById(R.id.enter_name_input);
        String name = EnterName.getText().toString();

        int price = calculatePrice();
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        // priceMessage = priceMessage + " \nThank you!";
        displayMessage(priceMessage);
        //displayPrice(quantity);
        /**
         * TO display Toast, which is a small disappearing message ( or feedback) which pops up after an event
         */
        Context context = getApplicationContext();
        CharSequence text = "Thanks for the order!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        // A shorter version would be
        // Toast.makeText(context, text, duration).show()
    }

    /**
     * Create summary of the order.
     *
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate is whether or not the user wants chocolate topping
     * @param price of the order
     * @return text summary
     */

    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }



    /**
     * This method displays the given increment on the screen.
     */
    public void increment(View view) {

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given decrement on the screen.
     */

    public void decrement(View view) {

        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method returns total price back to submitOrder method.
     *
     * @return
     */
    private int calculatePrice() {

        int totalPrice = quantity * (5+toppings);
        return totalPrice;

    }


}