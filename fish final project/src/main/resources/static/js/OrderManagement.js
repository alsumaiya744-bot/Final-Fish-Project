
let orders = [];

let currentOrderId = null;

loadOrders();

// ================= Load Orders =================

async function loadOrders() {

    try {

        

        const response =
            await fetch(
                `${BASE_URL}/api/orders`,
                {
                    headers: {
                        Authorization:
                            `Bearer ${token}`
                    }
                }
            );

        if (!response.ok) {

            throw new Error(
                "Failed to load orders"
            );
        }

        orders =
            await response.json();

        displayOrders();

    }
    catch (error) {

        console.log(error);

        alert(
            "Failed to load orders"
        );
    }
}

// ================= Display Orders =================

function displayOrders() {

    const table =
        document.getElementById(
            "orderTable"
        );

    table.innerHTML = "";

    orders.forEach(
        (order, index) => {

            const date =
                new Date(
                    order.orderDate
                )
                .toLocaleDateString(
                    "en-GB"
                )
                .replaceAll(
                    "/",
                    "-"
                );

            table.innerHTML += `

            <tr>

                <td>
                    ${order.orderId}
                </td>

                <td>
                    ${date}
                </td>

                <td>
                    ${order.user.fullName}
                </td>

                <td>
                    ₹${order.totalAmount}
                </td>

                <td>
                    ${order.paymentMethod}
                </td>

                <td>
                    ${order.orderStatus}
                </td>

                <td>

                    <button
                        onclick=
                        "viewOrder(${index})">

                        View

                    </button>

                </td>

            </tr>

            `;
        });
}

// ================= View Order =================

function viewOrder(index) {

    const order =
        orders[index];

    currentOrderId =
        order.orderId;

    let fishItems = "";

    order.orderItems.forEach(
        item => {

            fishItems += `

                <p>

                    ${item.fish.fishName}
                    (${item.quantity} Kg)

                </p>

            `;
        }
    );

    document.getElementById(
        "orderDetails"
    ).innerHTML = `

        <p>

            <strong>
                Order ID :
            </strong>

            ${order.orderId}

        </p>

        <p>

            <strong>
                Customer :
            </strong>

            ${order.user.fullName}

        </p>

        <p>

            <strong>
                Phone :
            </strong>

            ${order.user.phoneNumber}

        </p>

        <p>

            <strong>
                Address :
            </strong>

            ${order.deliveryAddress}

        </p>

        <p>

            <strong>
                Fish :
            </strong>

            ${fishItems}

        </p>

        <p>

            <strong>
                Total Quantity :
            </strong>

            ${order.totalQuantity} Kg

        </p>

        <p>

            <strong>
                Total Amount :
            </strong>

            ₹${order.totalAmount}

        </p>

        <p>

            <strong>
                Payment :
            </strong>

            ${order.paymentMethod}

        </p>

        <p>

            <strong>
                Payment Status :
            </strong>

            ${order.paymentStatus}

        </p>

    `;

    document.getElementById(
        "status"
    ).value =
        order.orderStatus;

    document.getElementById(
        "modal"
    ).style.display =
        "block";
}

// ================= Close Modal =================

document.getElementById(
    "closeBtn"
).addEventListener(
    "click",
    function () {

        document.getElementById(
            "modal"
        ).style.display =
            "none";
    }
);

// ================= Update Status =================

document.getElementById(
    "updateBtn"
).addEventListener(
    "click",
    updateStatus
);

async function updateStatus() {

    try {

        

        const status =
            document.getElementById(
                "status"
            ).value;

        const response =
            await fetch(
                `${BASE_URL}/api/orders/${currentOrderId}/status?status=${status}`,
                {
                    method:
                        "PUT",

                    headers: {
                        Authorization:
                            `Bearer ${token}`
                    }
                }
            );

        if (response.ok) {

            alert(
                "Status Updated Successfully"
            );

            document.getElementById(
                "modal"
            ).style.display =
                "none";

            loadOrders();
        }
        else {

            alert(
                "Failed To Update Status"
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

// ================= Back Button =================

document.getElementById(
    "backBtn"
).addEventListener(
    "click",
    function () {

        window.location.href =
            "admin-dashboard.html";
    }
);