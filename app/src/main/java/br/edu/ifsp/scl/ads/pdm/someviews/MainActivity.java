package br.edu.ifsp.scl.ads.pdm.someviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    // Objetos de binding com as Views
    private EditText nomeEt;
    private EditText sobrenomeEt;
    private EditText emailEt;
    private Spinner estadoCivilSp;
    private LinearLayout conjugeLl;
    private EditText nomeConjugeEt;
    private EditText sobrenomeConjugeEt;
    private RadioGroup sexoRg;
    private RadioButton masculinoRb;

    private String nomeCompleto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ligando (binding) objetos com as Views
        bindViews();

        // Listener de item selecionado
        estadoCivilSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (((TextView) view).getText().equals("Casado")){
                    conjugeLl.setVisibility(View.VISIBLE);
                }
                else {
                    conjugeLl.setVisibility(View.GONE);
                    nomeConjugeEt.setText("");
                    sobrenomeConjugeEt.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Watcher para nome completo (só para teste mesmo, funcionalidade zero)
        sobrenomeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nomeCompleto = nomeEt.getText().toString() + " " + s;
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void onClickButton(View view){
        switch(view.getId()) {
            case R.id.salvarBt:
                saveForm();
                break;
            case R.id.limparBt:
                cleanForm();
                break;
            default:
                break;
        }
    }

    private void cleanForm() {
        nomeEt.setText("");
        sobrenomeEt.setText("");
        emailEt.setText("");
        estadoCivilSp.setSelection(0);
        masculinoRb.setChecked(true);
    }

    private void saveForm() {
        StringBuffer sumarioSb = new StringBuffer();
        sumarioSb.append("Nome completo: ").append(nomeCompleto).append("\n");
        sumarioSb.append("E-mail: ").append(emailEt.getText().toString()).append("\n");

        sumarioSb.append("Estado civil: ").append(((TextView) estadoCivilSp.getSelectedView()).getText()).append("\n");
        // Forma alternativa de lidar com o Spinner
        int casadoIndex = Arrays.asList(getResources().getStringArray(R.array.estado_civil)).indexOf("Casado");
        if (casadoIndex == estadoCivilSp.getSelectedItemPosition()) {
            sumarioSb.append("Cônjuge: ")
                    .append(nomeConjugeEt.getText().toString()).append(" ")
                    .append(sobrenomeConjugeEt.getText().toString()).append("\n");
        }

        // Uma forma de lidar com RadioGroup
        sumarioSb.append("Sexo: ");
        switch (sexoRg.getCheckedRadioButtonId()) {
            case R.id.masculinoRb:
                sumarioSb.append("masculino");
                break;
            case R.id.femininoRb:
                sumarioSb.append("feminino");
                break;
        }
        sumarioSb.append("\n");

        Toast.makeText(this, sumarioSb.toString(), Toast.LENGTH_SHORT).show();
    }

    private void bindViews() {
        nomeEt = findViewById(R.id.nomeEt);
        sobrenomeEt = findViewById(R.id.sobrenomeEt);
        emailEt = findViewById(R.id.emailEt);
        estadoCivilSp = findViewById(R.id.estadoCivilSp);
        conjugeLl = findViewById(R.id.conjugeLl);
        nomeConjugeEt = findViewById(R.id.nomeConjugeEt);
        sobrenomeConjugeEt = findViewById(R.id.sobrenomeConjugeTv);
        sexoRg = findViewById(R.id.sexoRg);
        masculinoRb = findViewById(R.id.masculinoRb);
    }

}