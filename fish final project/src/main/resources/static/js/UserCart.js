// ================= Data =================
const userId =
    localStorage.getItem(
        "userId"
    );
let cart = [];

loadCart();

// ================= Load Cart =================

async function loadCart() {

   

    if (!userId) {

        alert(
            "Please Login Again."
        );

        return;
    }

    try {

        const token =
            localStorage.getItem(
                "token"
            );

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

        if (!response.ok) {

            throw new Error(
                "Failed To Load Cart"
            );
        }

        cart =
            await response.json();

        displayCart();
    }
    catch (error) {

        console.log(error);
    }
}

// ================= Display Cart =================

function displayCart() {

    const container =
        document.getElementById(
            "cartItems"
        );

    container.innerHTML = "";

    if (cart.length === 0) {

        document.getElementById(
            "emptyCart"
        ).style.display =
            "block";

        document.querySelector(
            ".summary-box"
        ).style.display =
            "none";

        return;
    }

    document.getElementById(
        "emptyCart"
    ).style.display =
        "none";

    document.querySelector(
        ".summary-box"
    ).style.display =
        "block";

    cart.forEach(item => {

        container.innerHTML += `

        <div class="cart-card">

            <img
                src="${BASE_URL}/uploads/${item.imagePath}">

            <div class="cart-info">

                <h3>
                    ${item.fishName}
                </h3>
<p>
    Price :
    ₹${item.sellingPrice}/Kg
</p>

                <div class="quantity-box">

                    <button
                        onclick=
                        "decreaseQty(${item.cartId},
                        ${item.quantity})">

                        -

                    </button>

                    <span>
                        ${item.quantity}
                    </span>

                    <button
                        onclick=
                        "increaseQty(${item.cartId},
                        ${item.quantity})">

                        +

                    </button>

                </div>

                <p>
                    Total :
                    ₹${item.totalAmount}
                </p>

                <button
                    class="remove-btn"
                    onclick=
                    "removeItem(${item.cartId})">

                    Remove

                </button>

            </div>

        </div>

        `;
    });

    updateSummary();
}

// ================= Remove Item =================

async function removeItem(
    cartId
) {

    const confirmDelete =
        confirm(
            "Remove This Item?"
        );

    if (!confirmDelete) {
        return;
    }

    try {

        

        const response =
            await fetch(
                `${BASE_URL}/api/cart/${cartId}`,
                {
                    method:
                        "DELETE",

                    headers: {
                        Authorization:
                            `Bearer ${token}`
                    }
                }
            );

        if (response.ok) {

            loadCart();
        }
        else {

            alert(
                "Failed To Remove Item"
            );
        }
    }
    catch (error) {

        console.log(error);
    }
}

// ================= Increase =================

async function increaseQty(
    cartId,
    quantity
) {

    await updateQuantity(
        cartId,
        quantity + 1
    );
}

// ================= Decrease =================

async function decreaseQty(
    cartId,
    quantity
) {

    if (quantity <= 1) {
        return;
    }

    await updateQuantity(
        cartId,
        quantity - 1
    );
}

// ================= Update Quantity =================

async function updateQuantity(
    cartId,
    quantity
) {

    try {

        

        const response =
            await fetch(
                `${BASE_URL}/api/cart/${cartId}`,
                {
                    method:
                        "PUT",

                    headers: {
                        "Content-Type":
                            "application/json",

                        Authorization:
                            `Bearer ${token}`
                    },

                    body:
                        JSON.stringify({
                            quantity:
                                quantity
                        })
                }
            );

        if (response.ok) {

            loadCart();
        }
        else {

            alert(
                "Failed To Update Quantity"
            );
        }
    }
    catch (error) {

        console.log(error);
    }
}

// ================= Cart Summary =================

function updateSummary() {

    let totalItems =
        cart.length;

    let totalQty = 0;

    let totalAmount = 0;

    cart.forEach(item => {

        totalQty +=
            item.quantity;

        totalAmount +=
            item.totalAmount;
    });

    document.getElementById(
        "totalItems"
    ).innerHTML =
        totalItems;

    document.getElementById(
        "totalQty"
    ).innerHTML =
        totalQty + " Kg";

   let subtotal = totalAmount;

let gst = subtotal * 0.05;

let packingCharge = 30;

let storageCharge = 50;

let deliveryCharge = 100;

let grandTotal =
    subtotal +
    gst +
    packingCharge +
    storageCharge +
    deliveryCharge;

document.getElementById("subTotal").innerHTML =
    "₹" + subtotal.toFixed(2);

document.getElementById("gst").innerHTML =
    "₹" + gst.toFixed(2);

document.getElementById("packingCharge").innerHTML =
    "₹" + packingCharge.toFixed(2);

document.getElementById("storageCharge").innerHTML =
    "₹" + storageCharge.toFixed(2);

document.getElementById("deliveryCharge").innerHTML =
    "₹" + deliveryCharge.toFixed(2);

document.getElementById("grandTotal").innerHTML =
    "₹" + grandTotal.toFixed(2);
}