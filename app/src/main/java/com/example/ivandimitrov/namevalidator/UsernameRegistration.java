package com.example.ivandimitrov.namevalidator;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Ivan Dimitrov on 12/27/2016.
 */

public class UsernameRegistration {
    private boolean isUsernameValid        = false;
    private boolean isFirstNameValid       = false;
    private boolean isLastNameValid        = false;
    private boolean isEmailValid           = false;
    private boolean isPasswordValid        = false;
    private boolean isPasswordConfirmValid = false;
    private boolean isRegistrationValid    = false;

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
    Activity activity;

    UsernameRegistration(Activity activity) {
        this.activity = activity;
        usernameWrapper = (TextInputLayout) activity.findViewById(R.id.usernameWrapper);
        firstNameWrapper = (TextInputLayout) activity.findViewById(R.id.firstNameWrapper);
        lastNameWrapper = (TextInputLayout) activity.findViewById(R.id.lastNameWrapper);
        emailWrapper = (TextInputLayout) activity.findViewById(R.id.emailWrapper);
        passwordWrapper = (TextInputLayout) activity.findViewById(R.id.passwordWrapper);
        passwordConfirmWrapper = (TextInputLayout) activity.findViewById(R.id.passwordConfirmWrapper);

        final EditText usernameEdit = (EditText) activity.findViewById(R.id.username);
        final EditText firstNameEdit = (EditText) activity.findViewById(R.id.firstName);
        final EditText lastNameEdit = (EditText) activity.findViewById(R.id.lastName);
        final EditText emailEdit = (EditText) activity.findViewById(R.id.email);
        final EditText passwordEdit = (EditText) activity.findViewById(R.id.password);
        final EditText passwordConfirmEdit = (EditText) activity.findViewById(R.id.passwordConfirm);

        usernameWrapper.setHint(activity.getString(R.string.username));
        firstNameWrapper.setHint(activity.getString(R.string.firstName));
        lastNameWrapper.setHint(activity.getString(R.string.lastName));
        emailWrapper.setHint(activity.getString(R.string.email));
        passwordWrapper.setHint(activity.getString(R.string.password));
        passwordConfirmWrapper.setHint(activity.getString(R.string.passwordConfirm));

        usernameEdit.addTextChangedListener(mUsernameWatcher);
        firstNameEdit.addTextChangedListener(mFirstNameWatcher);
        lastNameEdit.addTextChangedListener(mLastNameWatcher);
        emailEdit.addTextChangedListener(mEmailWatcher);
        passwordEdit.addTextChangedListener(mPasswordWatcher);
        passwordConfirmEdit.addTextChangedListener(mPasswordConfirmWatcher);
    }

    public void clearResources() {
        activity = null;
    }

    public boolean isRegistrationValid() {
        isRegistrationValid = true;
        if (!isUsernameValid) {
            isRegistrationValid = false;
        } else if (!isFirstNameValid) {
            isRegistrationValid = false;
        } else if (!isLastNameValid) {
            isRegistrationValid = false;
        } else if (!isEmailValid) {
            isRegistrationValid = false;
        } else if (!isPasswordValid) {
            isRegistrationValid = false;
        } else if (!isPasswordConfirmValid) {
            isPasswordConfirmValid = false;
        }

        return isRegistrationValid;
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
            isUsernameValid = true;
            StringBuffer message = new StringBuffer();
            if (s.length() == 0) {
                message.append(activity.getString(R.string.emptyField) + "\n");
                isUsernameValid = false;
            } else if (start < MIN_USERNAME_LENGTH - 1) {
                message.append(String.format(activity.getString(R.string.minFieldLength),
                        activity.getString(R.string.username), MIN_USERNAME_LENGTH) + "\n");
                isUsernameValid = false;

            } else if (start > MAX_USERNAME_LENGTH) {
                message.append(String.format(activity.getString(R.string.maxFieldLength),
                        activity.getString(R.string.username), MAX_USERNAME_LENGTH) + "\n");
                isUsernameValid = false;
            }

            boolean isAlphanumerical = !s.toString().matches("^.*[^a-zA-Z0-9 ].*$");
            if (!isAlphanumerical) {
                message.append(activity.getString(R.string.containsAlphanumerical) + "\n");
                isUsernameValid = false;
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
            isFirstNameValid = true;

            if (s.length() == 0) {
                message.append(activity.getString(R.string.emptyField) + "\n");
                isFirstNameValid = false;

            } else if (start <= MIN_USERNAME_LENGTH - 1) {
                message.append(String.format(activity.getString(R.string.minFieldLength),
                        activity.getString(R.string.firstName), MIN_USERNAME_LENGTH) + "\n");
                isFirstNameValid = false;

            } else if (start > MAX_USERNAME_LENGTH) {
                message.append(String.format(activity.getString(R.string.maxFieldLength),
                        activity.getString(R.string.firstName), MAX_USERNAME_LENGTH) + "\n");
                isFirstNameValid = false;
            }

            boolean isAlphanumerical = !s.toString().matches("^.*[^a-zA-Z0-9 ].*$");
            if (!isAlphanumerical) {
                message.append(activity.getString(R.string.containsAlphanumerical) + "\n");
                isFirstNameValid = false;
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
            isLastNameValid = true;

            if (s.length() == 0) {
                message.append(activity.getString(R.string.emptyField) + "\n");
                isLastNameValid = false;

            } else if (start <= MIN_USERNAME_LENGTH - 1) {
                message.append(String.format(activity.getString(R.string.minFieldLength),
                        activity.getString(R.string.lastName), MIN_USERNAME_LENGTH) + "\n");
                isLastNameValid = false;

            } else if (start > MAX_USERNAME_LENGTH) {
                message.append(String.format(activity.getString(R.string.maxFieldLength),
                        activity.getString(R.string.lastName), MAX_USERNAME_LENGTH) + "\n");
                isLastNameValid = false;
            }

            boolean isAlphanumerical = !s.toString().matches("^.*[^a-zA-Z0-9 ].*$");
            if (!isAlphanumerical) {
                message.append(activity.getString(R.string.containsAlphanumerical) + "\n");
                isLastNameValid = false;
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
            isEmailValid = true;
            String email = s.toString();
            if (!email.contains("@") || email.indexOf("@") <= 1 || email.indexOf("@") == email.length() - 1
                    || !email.contains(".") || email.lastIndexOf(".") < email.indexOf("@")) {
                message.append(activity.getString(R.string.invalidEmail) + "\n");
                isEmailValid = false;
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
            isPasswordValid = true;
            mPassword = password;

            if (password.length() > MAX_PASSWORD_LENGTH) {
                message.append(String.format(activity.getString(R.string.maxFieldLength),
                        activity.getString(R.string.password), MAX_PASSWORD_LENGTH) + "\n");
                isPasswordValid = false;

            } else if (password.length() < MIN_PASSWORD_LENGTH) {
                message.append(String.format(activity.getString(R.string.minFieldLength),
                        activity.getString(R.string.password), MAX_PASSWORD_LENGTH) + "\n");
                isPasswordValid = false;
            }

            if (password.equals(password.toLowerCase())) {
                message.append(activity.getString(R.string.missingCapital) + "\n");
                isPasswordValid = false;
            }

            boolean containsNumerical = password.matches(".*\\d.*");
            if (!containsNumerical) {
                message.append(activity.getString(R.string.missingNumeric) + "\n");
                isPasswordValid = false;
            }

            boolean containsAlphanumerical = password.matches("^.*[^a-zA-Z0-9 ].*$");
            if (!containsAlphanumerical) {
                message.append(activity.getString(R.string.missingAlphanumerical) + "\n");
                isPasswordValid = false;
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
                message.append(activity.getString(R.string.mismatchPassword) + "\n");
            }
            passwordConfirmWrapper.setError(message);
        }
    };
}
