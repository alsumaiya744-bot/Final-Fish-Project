


let customers = [];
let selectedCustomerId = null;


async function loadCustomers() {

    try {

        const response =
    await fetch(
        `${BASE_URL}/api/customers`,
        {
            headers: {
                Authorization:
                    "Bearer " + token
            }
        }
    );

if (!response.ok) { 
    throw new Error("Failed : " + response.status);
}

customers = await response.json();

loadCustomerDropdown();
displayCustomers();
} 

catch (error) {

        console.log(error);
    }
}

loadCustomers();



function loadCustomerDropdown() {

    const dropdown =
        document.getElementById(
            "customerId"
        );

    dropdown.innerHTML =
        `
        <option value="">
            Select Customer ID
        </option>
        `;

    customers.forEach(customer => {

        dropdown.innerHTML += `
            <option value="${customer.userId}">
                ${customer.userId}
            </option>
        `;
    });
}



document.getElementById(
    "customerId"
).addEventListener(
    "change",
    function () {

        const id =
            this.value;

        if (id === "") {

            clearForm();
            return;
        }

        const customer =
            customers.find(
                c => c.userId == id
            );

        if (!customer) {
            return;
        }

        selectedCustomerId =
            customer.userId;

        document.getElementById(
            "customerName"
        ).value =
            customer.fullName;

        document.getElementById(
            "phone"
        ).value =
            customer.phoneNumber;

        document.getElementById(
            "email"
        ).value =
            customer.email;

        document.getElementById(
            "address"
        ).value =
            customer.address;

        document.getElementById(
            "customerType"
        ).value =
            customer.customerType;
    }
);



function displayCustomers() {

    const table =
        document.getElementById(
            "customerTable"
        );

    table.innerHTML = "";

    customers.forEach(customer => {

        table.innerHTML += `
            <tr>

                <td>
                    ${customer.userId}
                </td>

                <td>
                    ${customer.fullName}
                </td>

                <td>
                    ${customer.phoneNumber}
                </td>
                <td>
                    ${customer.email}
                </td>
                <td>${customer.address ?? ""}</td>

                

                <td>
                    ${customer.customerType}
                </td>

            </tr>
        `;
    });
}



document.getElementById(
    "updateBtn"
).addEventListener(
    "click",
    updateCustomer
);

async function updateCustomer() {

    if (
        selectedCustomerId == null
    ) {

        alert(
            "Select Customer ID"
        );

        return;
    }

    const customer = {

        fullName:
            document.getElementById(
                "customerName"
            ).value,

        phoneNumber:
            document.getElementById(
                "phone"
            ).value,

        email:
            document.getElementById(
                "email"
            ).value,

        address:
            document.getElementById(
                "address"
            ).value,

        customerType:
            document.getElementById(
                "customerType"
            ).value
    };

    try {

        const response =
            await fetch(
                `${BASE_URL}/api/customers/${selectedCustomerId}`,
                {
                    method: "PUT",
                    headers: {
    "Content-Type":
        "application/json",
    Authorization:
        "Bearer " + token
},
                    body:
                        JSON.stringify(
                            customer
                        )
                }
            );

        if (response.ok) {

            alert(
                "Customer Updated Successfully"
            );

            clearForm();

            await loadCustomers();
        }
        else {

            alert(
                "Update Failed"
            );
        }

    } catch (error) {

        console.log(error);
    }
}



document.getElementById(
    "deleteBtn"
).addEventListener(
    "click",
    deleteCustomer
);

async function deleteCustomer() {

    if (
        selectedCustomerId == null
    ) {

        alert(
            "Select Customer ID"
        );

        return;
    }

    try {

        const response =
            await fetch(
                `${BASE_URL}/api/customers/${selectedCustomerId}`,
                {
                    method: "DELETE",
                    headers: {
                Authorization:
                    "Bearer " + token
            }
                }
            );

        if (response.ok) {

            alert(
                "Customer Deleted Successfully"
            );

            clearForm();

            await loadCustomers();
        }
        else {

            alert(
                "Delete Failed"
            );
        }

    } catch (error) {

        console.log(error);
    }
}



document.getElementById(
    "search"
).addEventListener(
    "keyup",
    function () {

        const value =
            this.value.toLowerCase();

        const rows =
            document.querySelectorAll(
                "#customerTable tr"
            );

        rows.forEach(row => {

            const text =
                row.innerText.toLowerCase();

            row.style.display =
                text.includes(value)
                    ? ""
                    : "none";
        });
    }
);



function clearForm() {

    selectedCustomerId =
        null;

    document.getElementById(
        "customerId"
    ).value = "";

    document.getElementById(
        "customerName"
    ).value = "";

    document.getElementById(
        "phone"
    ).value = "";

    document.getElementById(
        "email"
    ).value = "";

    document.getElementById(
        "address"
    ).value = "";

    document.getElementById(
        "customerType"
    ).value = "";
}



document.getElementById(
    "backBtn"
).addEventListener(
    "click",
    function () {

        window.location.href =
            "admin-dashboard.html";
    }
);