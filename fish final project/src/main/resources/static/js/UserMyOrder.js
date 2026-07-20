let orders = [];
let currentIndex = -1;

const userId =
    localStorage.getItem(
        "userId"
    );

loadOrders();

// ================= Load Orders =================

async function loadOrders() {

    

    const response =
        await fetch(
            `http://localhost:9090/api/orders/user/${userId}`,
            {
                headers: {
                    Authorization:
                        `Bearer ${token}`
                }
            }
        );

    if (response.ok) {

        orders =
            await response.json();

        displayOrders();
        updateSummary();
    }
}

// ================= Display Orders =================

function displayOrders() {

    let container =
        document.getElementById(
            "ordersContainer"
        );

    container.innerHTML = "";

    orders.forEach(
        (order, index) => {

            let statusClass =
                order.orderStatus
                    .toLowerCase()
                    .replaceAll(
                        "_",
                        ""
                    );

            let fishNames = "";

            order.orderItems.forEach(
                item => {

                    fishNames +=
                        item.fish.fishName +
                        " (" +
                        item.quantity +
                        " Kg), ";
                }
            );

            fishNames =
                fishNames.slice(
                    0,
                    -2
                );

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

            container.innerHTML += `

            <div class="order-card">

                <div class="order-top">

                    <div>

                        <div class="order-id">
                            ORD-${order.orderId}
                        </div>

                        <div class="order-date">
                            ${date}
                        </div>

                    </div>

                    <div class="${statusClass}">
                        ${order.orderStatus}
                    </div>

                </div>

                <div class="order-body">

                    <p>
                        <strong>
                        Fish :
                        </strong>
                        ${fishNames}
                    </p>

                    <p>
                        <strong>
                        Quantity :
                        </strong>
                        ${order.totalQuantity} Kg
                    </p>

                    <p>
                        <strong>
                        Amount :
                        </strong>
                        ₹${order.totalAmount}
                    </p>

                    <p>
                        <strong>
                        Payment :
                        </strong>
                        ${order.paymentStatus}
                    </p>

                </div>

                <div
                class="order-buttons">

                    <button
                    class="view-btn"
                    onclick=
                    "viewOrder(${index})">

                        View Details

                    </button>

                    ${
                        order.orderStatus
                        === "PROCESSING"

                        ?

                        `<button
                        class="cancel-btn"
                        onclick=
                        "cancelOrder(${order.orderId})">

                            Cancel Order

                        </button>`

                        : ""
                    }

                </div>

            </div>

            `;
        });
}

// ================= View Order =================

function viewOrder(index) {

    currentIndex = index;

    const order =
        orders[index];

    let fishNames = "";

    order.orderItems.forEach(
        item => {

            fishNames +=
                item.fish.fishName +
                " (" +
                item.quantity +
                " Kg), ";
        }
    );

    fishNames =
        fishNames.slice(
            0,
            -2
        );

    document.getElementById(
        "orderDetails"
    ).innerHTML = `

        <p>
        <strong>
        Order ID :
        </strong>
        ORD-${order.orderId}
        </p>

        <p>
        <strong>
        Fish :
        </strong>
        ${fishNames}
        </p>

        <p>
        <strong>
        Quantity :
        </strong>
        ${order.totalQuantity} Kg
        </p>

        <p>
        <strong>
        Amount :
        </strong>
        ₹${order.totalAmount}
        </p>

        <p>
        <strong>
        Payment Method :
        </strong>
        ${order.paymentMethod}
        </p>

        <p>
        <strong>
        Payment Status :
        </strong>
        ${order.paymentStatus}
        </p>

        <p>
        <strong>
        Address :
        </strong>
        ${order.deliveryAddress}
        </p>

        <p>
        <strong>
        Status :
        </strong>
        ${order.orderStatus}
        </p>

    `;

    document.getElementById(
        "cancelBtn"
    ).style.display =
        order.orderStatus
            === "PROCESSING"
            ? "block"
            : "none";

    document.getElementById(
        "orderModal"
    ).style.display =
        "block";
}

// ================= Close Modal =================

document.getElementById(
    "closeModal"
).addEventListener(
    "click",
    function () {

        document.getElementById(
            "orderModal"
        ).style.display =
            "none";
    }
);

// ================= Cancel Order =================

async function cancelOrder(
    orderId
) {

    const confirmCancel =
        confirm(
            "Cancel this order?"
        );

    if (!confirmCancel) {
        return;
    }

    

    const response =
        await fetch(
            `http://localhost:9090/api/orders/cancel/${orderId}`,
            {
                method: "PUT",

                headers: {
                    Authorization:
                        `Bearer ${token}`
                }
            }
        );

    if (response.ok) {

        alert(
            "Order Cancelled"
        );

        document.getElementById(
            "orderModal"
        ).style.display =
            "none";

        loadOrders();
    }
    else {

        alert(
            "Cannot Cancel Order"
        );
    }
}

// ================= Search =================

document.getElementById(
    "search"
).addEventListener(
    "keyup",
    function () {

        let value =
            this.value
                .toLowerCase();

        let cards =
            document.querySelectorAll(
                ".order-card"
            );

        cards.forEach(
            card => {

                let text =
                    card.innerText
                        .toLowerCase();

                card.style.display =
                    text.includes(
                        value
                    )
                        ? ""
                        : "none";
            }
        );
    }
);

// ================= Summary =================

function updateSummary() {

    document.getElementById(
        "totalOrders"
    ).innerHTML =
        orders.length;

    document.getElementById(
        "processingOrders"
    ).innerHTML =
        orders.filter(
            o =>
                o.orderStatus
                === "PROCESSING"
        ).length;

    document.getElementById(
        "deliveredOrders"
    ).innerHTML =
        orders.filter(
            o =>
                o.orderStatus
                === "DELIVERED"
        ).length;

    document.getElementById(
        "cancelledOrders"
    ).innerHTML =
        orders.filter(
            o =>
                o.orderStatus
                === "CANCELLED"
        ).length;
}

// ================= Back Button =================

document.getElementById(
    "backBtn"
).addEventListener(
    "click",
    function () {

        window.location.href =
            "UserHome.html";
    }
);