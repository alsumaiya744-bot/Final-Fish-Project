// ================= Sample Sales Data =================


let sales = [];

loadSales();





async function loadSales(){

    

    const response =
        await fetch(
            `${BASE_URL}/api/orders`,
            {
                headers:{
                    Authorization:
                        `Bearer ${token}`
                }
            }
        );

    sales =
        await response.json();

    displaySales();
    updateSummary();
}
// ================= Display Sales =================



function displaySales(){

    const table =
        document.getElementById(
            "salesTable"
        );

    table.innerHTML = "";

    sales.forEach(
        (sale,index)=>{

            let fishNames = "";

            sale.orderItems.forEach(
                item=>{

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
                    sale.orderDate
                )
                .toLocaleDateString(
                    "en-GB"
                )
                .replaceAll(
                    "/",
                    "-"
                );

            let paymentClass =
                sale.paymentStatus
                    .toLowerCase();

            let statusClass =
                sale.orderStatus
                    .toLowerCase()
                    .replaceAll(
                        "_",
                        ""
                    );

            table.innerHTML += `

            <tr>

                <td>
                    SALE-${sale.orderId}
                </td>

                <td>
                    ${date}
                </td>

                <td>
                    ${sale.user.fullName}
                </td>

                <td>
                    ${fishNames}
                </td>

                <td>
                    ${sale.totalQuantity}
                    Kg
                </td>

                <td>
                    ₹${sale.totalAmount}
                </td>

                <td class="${paymentClass}">
                    ${sale.paymentStatus}
                </td>

                <td class="${statusClass}">
                    ${sale.orderStatus}
                </td>

                <td>

                    <button
                    class="view-btn"
                    onclick=
                    "viewSale(${index})">

                        View

                    </button>

                </td>

            </tr>

            `;
        });
}

// ================= Summary =================

function updateSummary(){

    let revenue = 0;
    let quantity = 0;

    sales.forEach(
        sale=>{

            revenue +=
                sale.totalAmount;

            quantity +=
                sale.totalQuantity;
        }
    );

    document.getElementById(
        "revenue"
    ).innerHTML =
        "₹" + revenue;

    document.getElementById(
        "quantitySold"
    ).innerHTML =
        quantity + " Kg";

    document.getElementById(
        "orders"
    ).innerHTML =
        sales.length;
}

// ================= Search =================

document.getElementById(
    "search"
).addEventListener(
    "keyup",
    function () {

        let value =
            this.value.toLowerCase();

        let rows =
            document.querySelectorAll(
                "#salesTable tr"
            );

        rows.forEach(row => {

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

// ================= View Sale =================

function viewSale(index){

    const sale =
        sales[index];

    let fishNames = "";

    sale.orderItems.forEach(
        item=>{

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
            sale.orderDate
        )
        .toLocaleDateString(
            "en-GB"
        )
        .replaceAll(
            "/",
            "-"
        );

    document.getElementById(
        "saleDetails"
    ).innerHTML = `

        <p>
            <strong>Sales ID :</strong>
            SALE-${sale.orderId}
        </p>

        <p>
            <strong>Date :</strong>
            ${date}
        </p>

        <p>
            <strong>Customer :</strong>
            ${sale.user.fullName}
        </p>

        <p>
            <strong>Phone :</strong>
            ${sale.user.phoneNumber}
        </p>

        <p>
            <strong>Address :</strong>
            ${sale.deliveryAddress}
        </p>

        <p>
            <strong>Fish :</strong>
            ${fishNames}
        </p>

        <p>
            <strong>Quantity :</strong>
            ${sale.totalQuantity}
            Kg
        </p>

        <p>
            <strong>Total :</strong>
            ₹${sale.totalAmount}
        </p>

        <p>
            <strong>Payment :</strong>
            ${sale.paymentStatus}
        </p>

        <p>
            <strong>Status :</strong>
            ${sale.orderStatus}
        </p>

    `;

    document.getElementById(
        "saleModal"
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
            "saleModal"
        ).style.display =
            "none";

    }
);

// ================= Delivery Button =================

document.getElementById(
    "deliveryBtn"
).addEventListener(
    "click",
    function () {

        window.location.href =
            "delivery.html";

    }
);

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