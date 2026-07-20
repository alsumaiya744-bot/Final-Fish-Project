

const userId =
    localStorage.getItem(
        "userId"
    );

document.getElementById(
    "customerName"
).value =
localStorage.getItem(
    "fullName"
);

document.getElementById(
    "email"
).value =
localStorage.getItem(
    "email"
);

document.getElementById(
    "phone"
).value =
localStorage.getItem(
    "phoneNumber"
);

document.getElementById(
    "address"
).value =
localStorage.getItem(
    "address"
);

let cart = [];

loadCart();

async function loadCart() {

   

    const response =
        await fetch(
            `${BASE_URL}/api/cart/${userId}`,
            {
                headers: {
                    Authorization:
                        `Bearer ${token}`
                }
            }
        );

    cart =
        await response.json();
        console.log(cart);

    displayOrder();
}

let totalQty = 0;
let totalAmount = 0;

function displayOrder() {

    const orderItems =
        document.getElementById(
            "orderItems"
        );

    orderItems.innerHTML = "";

    totalQty = 0;
    totalAmount = 0;

    cart.forEach(item => {

        totalQty +=
            item.quantity;

        totalAmount +=
            item.totalAmount;

        orderItems.innerHTML +=
        `
        <div class="order-item">

            <span>
                ${item.fishName}
                (${item.quantity} Kg)
            </span>

            <span>
                ₹${item.totalAmount}
            </span>

        </div>
        `;
    });

    document.getElementById(
        "totalQty"
    ).innerHTML =
        totalQty + " Kg";

   let subTotal = totalAmount;

let gst = subTotal * 0.05;

let handling = 30;

let storage = 50;

let delivery = 100;

let grandTotal =
    subTotal +
    gst +
    handling +
    storage +
    delivery;

document.getElementById("subTotal").innerHTML =
    "₹" + subTotal.toFixed(2);

document.getElementById("gst").innerHTML =
    "₹" + gst.toFixed(2);

document.getElementById("handling").innerHTML =
    "₹" + handling.toFixed(2);

document.getElementById("storage").innerHTML =
    "₹" + storage.toFixed(2);

document.getElementById("delivery").innerHTML =
    "₹" + delivery.toFixed(2);

document.getElementById("grandTotal").innerHTML =
    "₹" + grandTotal.toFixed(2);
}

let paymentMethod = "";
let paymentStatus = "PENDING";

document
.querySelectorAll(
    'input[name="payment"]'
)
.forEach(radio => {

    radio.addEventListener(
        "change",
        function () {

            paymentMethod =
                this.value;

            if (
                paymentMethod ===
                "UPI"
            ) {

                document.getElementById(
                    "upiOptions"
                ).style.display =
                    "flex";
            }
            else {

                document.getElementById(
                    "upiOptions"
                ).style.display =
                    "none";
            }
        }
    );
});

document.getElementById(
    "placeOrderBtn"
).addEventListener(
    "click",
    placeOrder
);

async function placeOrder() {

    const address =
        document.getElementById(
            "address"
        ).value.trim();

    if (
        address === ""
    ) {

        alert(
            "Please enter address"
        );

        return;
    }

    if (
        paymentMethod === ""
    ) {

        alert(
            "Select payment method"
        );

        return;
    }

    if (
        paymentMethod ===
        "Cash on Delivery"
    ) {

        paymentStatus =
            "PENDING";

        saveOrder(
            address,
            null
        );
    }
    else {

    paymentStatus = "PENDING";

    openRazorpay(
        address
    );
}
}

function openRazorpay(
    address
) {


    let gst = totalAmount * 0.05;

let grandTotal =
    totalAmount +
    gst +
    30 +
    50 +
    100;

    const options = {

        key:
            "rzp_test_T7HTCq9AwK4FGr",

       

amount: grandTotal * 100,

        currency:
            "INR",

        name:
            "Al Sultan Seafood",

        description:
            "Fish Order Payment",

        handler:
            function (response) {

                paymentStatus =
                    "PAID";

                document.getElementById(
                    "paymentStatus"
                ).innerHTML =
                    "Paid";

                saveOrder(
                    address,
                    response.razorpay_payment_id
                );
            }
    };

    const rzp =
        new Razorpay(
            options
        );

    rzp.open();
}

async function saveOrder(
    address,
    paymentId
) {

    let gst = totalAmount * 0.05;

let grandTotal =
    totalAmount +
    gst +
    30 +
    50 +
    100;
   

    const data = {

    userId: Number(userId),

    paymentMethod: paymentMethod,

    paymentStatus: paymentStatus,

    deliveryAddress: address,

    paymentId: paymentId,

    totalAmount: grandTotal
};
    const response =
        await fetch(
            `${BASE_URL}/api/orders`,
            {
                method:
                    "POST",

                headers: {
                    "Content-Type":
                        "application/json",

                    Authorization:
                        `Bearer ${token}`
                },

                body:
                    JSON.stringify(
                        data
                    )
            }
        );

    if (
        response.ok
    ) {

        alert(
            "Order Placed Successfully"
        );

        window.location.href =
            "UserMyOrder.html";
    }
    else {

        alert(
            "Failed to place order"
        );
    }
}