package edu.jsu.mcis.cs408.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.beans.PropertyChangeEvent;
import edu.jsu.mcis.cs408.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements AbstractView {

    public static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private CalculatorController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        controller = new CalculatorController();
        CalculatorModel model = new CalculatorModel();

        controller.addView(this);
        controller.addModel(model);

        model.init();

        CalculatorClickHandler click = new CalculatorClickHandler();
        ConstraintLayout layout = binding.constraintLayout;

        for (int i = 0; i < layout.getChildCount(); ++i) {
            View child = layout.getChildAt(i);
            if(child instanceof Button) {
                child.setOnClickListener(click);
            }
        }
    }

    class CalculatorClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String tag = ((Button) v).getTag().toString();
            controller.processInput(tag);
        }
    }

    public void modelPropertyChange(final PropertyChangeEvent evt) {

        String propertyName = evt.getPropertyName();
        String propertyValue = evt.getNewValue().toString();

        Log.i(TAG, "New " + propertyName + " Value from Model: " + propertyValue);

        if ( propertyName.equals(CalculatorController.OUTPUT_PROPERTY) ) {

            String oldPropertyValue = binding.outputScreen.getText().toString();

            if ( !oldPropertyValue.equals(propertyValue) ) {
                binding.outputScreen.setText(propertyValue);
            }
        }
    }
}