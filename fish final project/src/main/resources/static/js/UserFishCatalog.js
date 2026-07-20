// ================= Load Fish From Backend =================

let fishes = [];
let cart = [];

loadFishes();

async function loadFishes() {

    try {

        const response =
    await fetch(
        "${BASE_URL}/api/fishes",
        {
            headers: {
                Authorization:
                    "Bearer " + token
            }
        }
    );
        fishes =
            await response.json();

        displayFish(fishes);

    }
    catch (error) {

        console.log(
            "Error loading fishes",
            error
        );
    }
}


// ================= Display Fish =================

function displayFish(data) {

    let cards =
        document.getElementById(
            "fishCards"
        );

    cards.innerHTML = "";

    if (data.length === 0) {

        cards.innerHTML =
            "<h3>No Fish Available</h3>";

        return;
    }

    data.forEach(fish => {

        let statusClass = "";

        if (fish.availableStock > 50) {

            statusClass =
                "stock-high";
        }
        else if (
            fish.availableStock >= 20
        ) {

            statusClass =
                "stock-medium";
        }
        else {

            statusClass =
                "stock-low";
        }

        cards.innerHTML += `

        <div class="fish-card">

            <img
                src="${BASE_URL}/uploads/${fish.imagePath}"
                alt="${fish.fishName}">

            <div class="fish-info">

                <h3>
                    ${fish.fishName}
                </h3>

                <p>
                    Category :
                    ${fish.category}
                </p>

                <p>
                    Price :
                    ₹${fish.sellingPrice}/Kg
                </p>

                <p class="${statusClass}">
                    Available :
                    ${fish.availableStock} Kg
                </p>

                <div class="quantity-box">

                    <button
                        onclick="decrease(${fish.fishId})">

                        -

                    </button>

                    <span
                        id="qty-${fish.fishId}">

                        1

                    </span>

                    <button
                        onclick="increase(${fish.fishId})">

                        +

                    </button>

                </div>

                <div class="button-group">

                    <button
                        class="cart-btn"
                        onclick="addToCart(${fish.fishId})">

                        Add To Cart

                    </button>

                    <button
                        class="view-btn"
                        onclick="viewDetails(${fish.fishId})">

                        View

                    </button>

                </div>

            </div>

        </div>

        `;
    });

}


// ================= Search =================

document
    .getElementById("search")
    .addEventListener(
        "keyup",
        function () {

            let value =
                this.value.toLowerCase();

            let filtered =
                fishes.filter(
                    fish =>
                        fish.fishName
                            .toLowerCase()
                            .includes(value)
                );

            displayFish(filtered);
        }
    );


// ================= Category =================

document
    .getElementById("category")
    .addEventListener(
        "change",
        function () {

            let value =
                this.value;

            if (value === "All") {

                displayFish(fishes);

                return;
            }

            let filtered =
                fishes.filter(
                    fish =>
                        fish.category
                        === value
                );

            displayFish(filtered);
        }
    );


// ================= Sort =================

document
    .getElementById("sort")
    .addEventListener(
        "change",
        function () {

            let value =
                this.value;

            let sorted =
                [...fishes];

            if (value === "low") {

                sorted.sort(
                    (a, b) =>
                         a.sellingPrice -
        b.sellingPrice
                );
            }
            else if (
                value === "high"
            ) {

                sorted.sort(
                    (a, b) =>
                         a.sellingPrice -
        b.sellingPrice
                );
            }
            else if (
                value === "name"
            ) {

                sorted.sort(
                    (a, b) =>
                        a.fishName
                            .localeCompare(
                                b.fishName
                            )
                );
            }

            displayFish(sorted);
        }
    );


// ================= Quantity =================

function increase(id) {

    let span =
        document.getElementById(
            `qty-${id}`
        );

    let qty =
        Number(
            span.innerHTML
        );

    span.innerHTML =
        qty + 1;
}

function decrease(id) {

    let span =
        document.getElementById(
            `qty-${id}`
        );

    let qty =
        Number(
            span.innerHTML
        );

    if (qty > 1) {

        span.innerHTML =
            qty - 1;
    }
}


// ================= Add To Cart =================

async function addToCart(fishId) {

    const userId =
        localStorage.getItem(
            "userId"
        );

   

    if (!userId || !token) {

        alert(
            "Please login again."
        );

        window.location.href =
            "login.html";

        return;
    }

    const qty =
        Number(
            document.getElementById(
                `qty-${fishId}`
            ).innerHTML
        );

    const data = {
        userId:
            Number(userId),

        fishId:
            fishId,

        quantity:
            qty
    };

    try {

        const response =
            await fetch(
                "${BASE_URL}/api/cart",
                {
                    method: "POST",

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

        if (response.ok) {

            alert(
                "Added To Cart Successfully"
            );
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
// ================= View Details =================

function viewDetails(id) {

    let fish =
        fishes.find(
            f =>
                f.fishId === id
        );

    alert(

        "Fish Name : "
        + fish.fishName +

        "\nCategory : "
        + fish.category +

        "\nPrice : ₹"
        + fish.sellingPrice  +

        "/Kg\nAvailable : "
        + fish.availableStock
        + " Kg"

    );
}