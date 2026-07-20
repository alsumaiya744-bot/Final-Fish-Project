// ================= Show / Hide Password =================

const password =
    document.getElementById(
        "password"
    );

const showPassword =
    document.getElementById(
        "showPassword"
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

// ================= Login =================

document.getElementById(
    "loginForm"
).addEventListener(
    "submit",
    loginUser
);

async function loginUser(
    event
) {

    event.preventDefault();

    const email =
        document.getElementById(
            "email"
        ).value.trim();

    const pass =
        document.getElementById(
            "password"
        ).value.trim();

    const role =
        document.getElementById(
            "role"
        ).value;

    // ================= Validation =================

    if (
        email === ""
    ) {

        alert(
            "Please enter email."
        );

        return;
    }

    if (
        pass === ""
    ) {

        alert(
            "Please enter password."
        );

        return;
    }

    if (
        role === ""
    ) {

        alert(
            "Please select role."
        );

        return;
    }

    const loginData = {

        email:
            email,

        password:
            pass,

        role:
            role
    };

    try {

        const response =
            await fetch(
                "http://localhost:9090/api/auth/login",
                {
                    method:
                        "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify(
                            loginData
                        )
                }
            );

        if (
            !response.ok
        ) {

            const message =
                await response.text();

            alert(
                message
            );

            return;
        }

        const data =
            await response.json();

        // ================= Save Token =================


        localStorage.removeItem("token");
localStorage.removeItem("userId");
localStorage.removeItem("role");
localStorage.removeItem("email");
localStorage.removeItem("fullName");
localStorage.removeItem("phoneNumber");
localStorage.removeItem("address");
        
        localStorage.setItem(
            "token",
            data.token
        );

        localStorage.setItem(
            "role",
            data.role
        );

        localStorage.setItem(
    "userId",
    data.userId
);

localStorage.setItem(
    "fullName",
    data.fullName
);


        localStorage.setItem(
            "email",
            data.email
        );

        localStorage.setItem(
    "phoneNumber",
    data.phoneNumber
);

localStorage.setItem(
    "address",
    data.address
);

        alert(
            "Login Successful"
        );

        // ================= Redirect =================

        if (
            data.role ===
            "ADMIN"
        ) {

           window.location.href =
               "admin-dashboard.html";
        }
        else {

            window.location.href =
               "UserHome.html";
        }

    }
    catch (error) {

        console.log(error);

        alert(
            "Server Error"
        );
    }
}