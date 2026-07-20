// ================= Show Password =================

const password =
    document.getElementById(
        "password"
    );

const confirmPassword =
    document.getElementById(
        "confirmPassword"
    );

const showPassword =
    document.getElementById(
        "showPassword"
    );

const showConfirmPassword =
    document.getElementById(
        "showConfirmPassword"
    );

showPassword.addEventListener(
    "click",
    function () {

        if (
            password.type ===
            "password"
        ) {

            password.type =
                "text";

            showPassword.innerHTML =
                "Hide";
        }
        else {

            password.type =
                "password";

            showPassword.innerHTML =
                "Show";
        }
    }
);

showConfirmPassword.addEventListener(
    "click",
    function () {

        if (
            confirmPassword.type ===
            "password"
        ) {

            confirmPassword.type =
                "text";

            showConfirmPassword.innerHTML =
                "Hide";
        }
        else {

            confirmPassword.type =
                "password";

            showConfirmPassword.innerHTML =
                "Show";
        }
    }
);

// ================= Register =================

document.getElementById(
    "registerForm"
).addEventListener(
    "submit",
    registerUser
);

async function registerUser(
    event
) {

    event.preventDefault();

    const fullName =
        document.getElementById(
            "fullName"
        ).value.trim();

    const email =
        document.getElementById(
            "email"
        ).value.trim();

    const phoneNumber =
        document.getElementById(
            "phone"
        ).value.trim();


        const address =
    document.getElementById(
        "address"
    ).value.trim();

    const password =
        document.getElementById(
            "password"
        ).value.trim();

    const confirm =
        document.getElementById(
            "confirmPassword"
        ).value.trim();

    const customerType =
        document.getElementById(
            "customerType"
        ).value;

    // ================= Validation =================

    if (
        fullName === ""
    ) {

        alert(
            "Please enter full name."
        );

        return;
    }

    if (
        email === ""
    ) {

        alert(
            "Please enter email."
        );

        return;
    }


    if (
    !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)
) {
    alert(
        "Please enter a valid email."
    );
    return;
}

    if (
        phoneNumber === ""
    ) {

        alert(
            "Please enter phone number."
        );

        return;
    }


    if (
    address === ""
) {

    alert(
        "Please enter address."
    );

    return;
}

    if (
        customerType === ""
    ) {

        alert(
            "Please select user type."
        );

        return;
    }

    if (
        password === ""
    ) {

        alert(
            "Please enter password."
        );

        return;
    }

    if (
        confirm === ""
    ) {

        alert(
            "Please confirm password."
        );

        return;
    }

    if (
        password !== confirm
    ) {

        alert(
            "Passwords do not match."
        );

        return;
    }

    if (
        password.length < 6
    ) {

        alert(
            "Password must contain at least 6 characters."
        );

        return;
    }

    if (
    !/^[0-9]{10}$/.test(phoneNumber)
) {
    alert(
        "Please enter a valid phone number."
    );
    return;
}

    // ================= JSON =================

    const user = {
    fullName: fullName,
    email: email,
    phoneNumber: phoneNumber,
    address: address,
    password: password,
    role: "USER",
    customerType: customerType,
    active: true
};

    try {

        const response =
            await fetch(
                "http://localhost:9090/api/auth/register",
                {
                    method:
                        "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify(
                            user
                        )
                }
            );

        if (
            response.ok
        ) {

            alert(
                "Account Created Successfully"
            );

            window.location.href =
                "index.html";
        }
        else {

            const message =
                await response.text();

            alert(
                message
            );
        }

    }
    catch (error) {

        console.log(error);

        alert(
            "Server Error"
        );
    }
}