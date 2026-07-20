// ================= Sample Delivery Data =================
 
let deliveries = [];

let currentOrderId;

loadDeliveries();





async function loadDeliveries(){

    

    const response =
        await fetch(
            "${BASE_URL}/api/orders",
            {
                headers:{
                    Authorization:
                        `Bearer ${token}`
                }
            }
        );

    deliveries =
        await response.json();

    deliveries =
    deliveries.filter(
        d =>
            d.orderStatus !==
            "PROCESSING"
            &&
            d.orderStatus !==
            "CANCELLED"
    );

    displayDeliveries();

    updateSummary();
}

let currentIndex = -1;

// ================= Display Table =================

function displayDeliveries(){

    let table =
        document.getElementById(
            "deliveryTable"
        );

    table.innerHTML = "";

    deliveries.forEach(
        (delivery,index)=>{

            let statusClass =
                delivery.orderStatus
                        .toLowerCase()
                        .replaceAll(
                            "_",
                            ""
                        );

            table.innerHTML += `

            <tr>

                <td>
                    DEL-${delivery.orderId}
                </td>

                <td>
                    ORD-${delivery.orderId}
                </td>

                <td>
                    ${delivery.user.fullName}
                </td>

                <td>
                    ${delivery.user.phoneNumber}
                </td>

                <td>
                    ${delivery.paymentStatus}
                </td>

                <td class="${statusClass}">
                    ${delivery.orderStatus}
                </td>

                <td>

                    <button
                    onclick=
                    "viewDelivery(${index})">

                        View

                    </button>

                </td>

            </tr>

            `;
        });
}

// ================= View Details =================

function viewDelivery(index){

    const delivery =
        deliveries[index];

    currentOrderId =
        delivery.orderId;

    let fishNames = "";

    delivery.orderItems.forEach(item => {

        fishNames +=
            item.fish.fishName +
            " (" +
            item.quantity +
            " Kg), ";
    });

    fishNames =
        fishNames.slice(0, -2);

    document.getElementById(
        "deliveryDetails"
    ).innerHTML = `

        <p>
            <strong>Order ID :</strong>
            ORD-${delivery.orderId}
        </p>

        <p>
            <strong>Customer :</strong>
            ${delivery.user.fullName}
        </p>

        <p>
            <strong>Phone :</strong>
            ${delivery.user.phoneNumber}
        </p>

        <p>
            <strong>Address :</strong>
            ${delivery.deliveryAddress}
        </p>

        <p>
            <strong>Fish :</strong>
            ${fishNames}
        </p>

        <p>
            <strong>Quantity :</strong>
            ${delivery.totalQuantity} Kg
        </p>

        <p>
            <strong>Amount :</strong>
            ₹${delivery.totalAmount}
        </p>

        <p>
            <strong>Payment :</strong>
            ${delivery.paymentMethod}
        </p>

        <p>
            <strong>Payment Status :</strong>
            ${delivery.paymentStatus}
        </p>
    `;

    document.getElementById(
        "status"
    ).value =
        delivery.orderStatus;



        const statusDropdown =
    document.getElementById(
        "status"
    );

const updateBtn =
    document.getElementById(
        "updateBtn"
    );

if (
    delivery.orderStatus ===
    "DELIVERED"
) {
    statusDropdown.disabled =
        true;

    updateBtn.disabled =
        true;
}
else {
    statusDropdown.disabled =
        false;

    updateBtn.disabled =
        false;
}

    document.getElementById(
        "deliveryModal"
    ).style.display =
        "block";
}
// ================= Close Modal =================

document.getElementById(
    "closeModal"
).addEventListener(
    "click",
    function(){

        document.getElementById(
            "deliveryModal"
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



async function updateStatus(){

   

    const status =
        document.getElementById(
            "status"
        ).value;

    const response =
        await fetch(
            `${BASE_URL}/api/orders/${currentOrderId}/status?status=${status}`,
            {
                method:"PUT",

                headers:{
                    Authorization:
                        `Bearer ${token}`
                }
            }
        );

    if(response.ok){

        alert(
            "Status Updated"
        );

        document.getElementById(
            "deliveryModal"
        ).style.display =
            "none";

        loadDeliveries();
    }
}

// ================= Search =================

document.getElementById(
    "search"
).addEventListener(
    "keyup",
    function(){

        let value =
        this.value
        .toLowerCase();

        let rows =
        document.querySelectorAll(
            "#deliveryTable tr"
        );

        rows.forEach(row=>{

            let text =
            row.innerText
            .toLowerCase();

            row.style.display =
            text.includes(value)
            ? ""
            : "none";

        });

    }
);

// ================= Summary =================

function updateSummary(){

    document.getElementById(
        "totalDeliveries"
    ).innerHTML =
        deliveries.length;

    document.getElementById(
        "pendingDeliveries"
    ).innerHTML =
        deliveries.filter(
            d =>
                d.orderStatus !==
                "DELIVERED"
                &&
                d.orderStatus !==
                "CANCELLED"
        ).length;

    document.getElementById(
        "deliveredOrders"
    ).innerHTML =
        deliveries.filter(
            d =>
                d.orderStatus ===
                "DELIVERED"
        ).length;

    document.getElementById(
        "cancelledOrders"
    ).innerHTML =
        deliveries.filter(
            d =>
                d.orderStatus ===
                "CANCELLED"
        ).length;
}

// ================= Back Button =================

document.getElementById(
    "backBtn"
).addEventListener(
    "click",
    function(){

        window.location.href =
        "admin-dashboard.html";

    }
);