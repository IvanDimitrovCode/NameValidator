package com.example.ivandimitrov.namevalidator;

import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout usernameWrapper;
    private TextInputLayout firstNameWrapper;
    private TextInputLayout lastNameWrapper;
    private TextInputLayout emailWrapper;
    private TextInputLayout passwordWrapper;
    private TextInputLayout passwordConfirmWrapper;

    public static final int MAX_USERNAME_LENGTH = 30;
    public static final int MIN_USERNAME_LENGTH = 3;
    public static final int MAX_PASSWORD_LENGTH = 30;
    public static final int MIN_PASSWORD_LENGTH = 6;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        firstNameWrapper = (TextInputLayout) findViewById(R.id.firstNameWrapper);
        lastNameWrapper = (TextInputLayout) findViewById(R.id.lastNameWrapper);
        emailWrapper = (TextInputLayout) findViewById(R.id.emailWrapper);
        passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        passwordConfirmWrapper = (TextInputLayout) findViewById(R.id.passwordConfirmWrapper);

        final EditText usernameEdit = (EditText) findViewById(R.id.username);
        final EditText firstNameEdit = (EditText) findViewById(R.id.firstName);
        final EditText lastNameEdit = (EditText) findViewById(R.id.lastName);
        final EditText emailEdit = (EditText) findViewById(R.id.email);
        final EditText passwordEdit = (EditText) findViewById(R.id.password);
        final EditText passwordConfirmEdit = (EditText) findViewById(R.id.passwordConfirm);

        usernameWrapper.setHint(getString(R.string.username));
        firstNameWrapper.setHint(getString(R.string.firstName));
        lastNameWrapper.setHint(getString(R.string.lastName));
        emailWrapper.setHint(getString(R.string.email));
        passwordWrapper.setHint(getString(R.string.password));
        passwordConfirmWrapper.setHint(getString(R.string.passwordConfirm));

        usernameEdit.addTextChangedListener(mUsernameWatcher);
        firstNameEdit.addTextChangedListener(mFirstNameWatcher);
        lastNameEdit.addTextChangedListener(mLastNameWatcher);
        emailEdit.addTextChangedListener(mEmailWatcher);
        passwordEdit.addTextChangedListener(mPasswordWatcher);
        passwordConfirmEdit.addTextChangedListener(mPasswordConfirmWatcher);
    }

    TextWatcher mUsernameWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable arg0) {
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            usernameWrapper.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            StringBuffer message = new StringBuffer();
            if (s.length() == 0) {
                message.append(getString(R.string.emptyField) + "\n");

            } else if (start < MIN_USERNAME_LENGTH - 1) {
                message.append(String.format(getString(R.string.minFieldLength),
                        getString(R.string.username), MIN_USERNAME_LENGTH) + "\n");

            } else if (start > MAX_USERNAME_LENGTH) {
                message.append(String.format(getString(R.string.maxFieldLength),
                        getString(R.string.username), MAX_USERNAME_LENGTH) + "\n");
            }

            boolean isAlphanumerical = !s.toString().matches("^.*[^a-zA-Z0-9 ].*$");
            if (!isAlphanumerical) {
                message.append(getString(R.string.containsAlphanumerical) + "\n");
            }
            usernameWrapper.setError(message);
        }
    };

    TextWatcher mFirstNameWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable arg0) {
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            firstNameWrapper.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            StringBuffer message = new StringBuffer();

            if (s.length() == 0) {
                message.append(getString(R.string.emptyField) + "\n");

            } else if (start <= MIN_USERNAME_LENGTH - 1) {
                message.append(String.format(getString(R.string.minFieldLength),
                        getString(R.string.firstName), MIN_USERNAME_LENGTH) + "\n");

            } else if (start > MAX_USERNAME_LENGTH) {
                message.append(String.format(getString(R.string.maxFieldLength),
                        getString(R.string.firstName), MAX_USERNAME_LENGTH) + "\n");
            }

            boolean isAlphanumerical = !s.toString().matches("^.*[^a-zA-Z0-9 ].*$");
            if (!isAlphanumerical) {
                message.append(getString(R.string.containsAlphanumerical) + "\n");
            }

            firstNameWrapper.setError(message);
        }
    };

    TextWatcher mLastNameWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable arg0) {
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            lastNameWrapper.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            StringBuffer message = new StringBuffer();

            if (s.length() == 0) {
                message.append(getString(R.string.emptyField) + "\n");

            } else if (start <= MIN_USERNAME_LENGTH - 1) {
                message.append(String.format(getString(R.string.minFieldLength),
                        getString(R.string.lastName), MIN_USERNAME_LENGTH) + "\n");

            } else if (start > MAX_USERNAME_LENGTH) {
                message.append(String.format(getString(R.string.maxFieldLength),
                        getString(R.string.lastName), MAX_USERNAME_LENGTH) + "\n");
            }

            boolean isAlphanumerical = !s.toString().matches("^.*[^a-zA-Z0-9 ].*$");
            if (!isAlphanumerical) {
                message.append(getString(R.string.containsAlphanumerical) + "\n");
            }
            lastNameWrapper.setError(message);
        }
    };

    TextWatcher mEmailWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable arg0) {
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            emailWrapper.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            StringBuffer message = new StringBuffer();
            String email = s.toString();
            if (!email.contains("@") || email.indexOf("@") <= 1 || email.indexOf("@") == email.length() - 1
                    || !email.contains(".") || email.lastIndexOf(".") < email.indexOf("@")) {
                message.append(getString(R.string.invalidEmail) + "\n");
            }
            emailWrapper.setError(message);
        }
    };

    TextWatcher mPasswordWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable arg0) {
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            passwordWrapper.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            StringBuffer message = new StringBuffer();
            String password = s.toString();
            mPassword = password;

            if (password.length() > MAX_PASSWORD_LENGTH) {
                message.append(String.format(getString(R.string.maxFieldLength),
                        getString(R.string.password), MAX_PASSWORD_LENGTH) + "\n");

            } else if (password.length() < MIN_PASSWORD_LENGTH) {
                message.append(String.format(getString(R.string.minFieldLength),
                        getString(R.string.password), MAX_PASSWORD_LENGTH) + "\n");
            }

            if (password.equals(password.toLowerCase())) {
                message.append(getString(R.string.missingCapital) + "\n");
            }

            boolean containsNumerical = password.matches(".*\\d.*");
            if (!containsNumerical) {
                message.append(getString(R.string.missingNumeric) + "\n");
            }

            boolean containsAlphanumerical = password.matches("^.*[^a-zA-Z0-9 ].*$");
            if (!containsAlphanumerical) {
                message.append(getString(R.string.missingAlphanumerical) + "\n");
            }
            passwordWrapper.setError(message);
        }
    };

    TextWatcher mPasswordConfirmWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable arg0) {
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            passwordConfirmWrapper.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            StringBuffer message = new StringBuffer();
            String passwordConfirm = s.toString();
            if (!passwordConfirm.equals(mPassword)) {
                message.append(getString(R.string.mismatchPassword) + "\n");
            }
            passwordConfirmWrapper.setError(message);
        }
    };
}
