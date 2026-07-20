// ================= Data =================

let fishes = [];


// ================= Load User Home =================

async function loadHome() {

    try {

       const userId =
    localStorage.getItem(
        "userId"
    );

const response =
    await fetch(
        `${BASE_URL}/api/user-home/${userId}`,
        {
            headers: {
                Authorization:
                    "Bearer " + token
            }
        }
    );

        if (!response.ok) {
            throw new Error(
                "Failed to load data"
            );
        }

        const data =
            await response.json();

        fishes =
            data.fishes || [];

        

        document.getElementById(
            "totalOrders"
        ).innerHTML =
            data.totalOrders;

        document.getElementById(
            "pendingOrders"
        ).innerHTML =
            data.pendingOrders;

        document.getElementById(
            "deliveredOrders"
        ).innerHTML =
            data.deliveredOrders;

        displayFish(fishes);

        

    }
    catch (error) {

        console.log(
            "Error : ",
            error
        );
    }
}

loadHome();


// ================= Display Fish =================

function displayFish(data) {

    const cards =
        document.getElementById(
            "fishCards"
        );

    cards.innerHTML = "";

    if (data.length === 0) {

        cards.innerHTML =
            `
            <h3>
                No Fish Available
            </h3>
            `;

        return;
    }

    data.forEach(fish => {

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

                <p>
                    Available :
                    ${fish.availableStock} Kg
                </p>

                <button
                    onclick=
                    "addToCart(${fish.fishId})">

                    Add To Cart

                </button>

            </div>

        </div>

        `;
    });
}


// ================= Search =================

document.getElementById(
    "search"
).addEventListener(
    "keyup",
    function () {

        const value =
            this.value
                .toLowerCase();

        const filtered =
            fishes.filter(
                fish =>
                    fish.fishName
                        .toLowerCase()
                        .includes(value)
            );

        displayFish(filtered);
    }
);


// ================= Category Filter =================

document.getElementById(
    "category"
).addEventListener(
    "change",
    function () {

        const value =
            this.value;

        if (
            value === "All"
        ) {

            displayFish(
                fishes
            );

            return;
        }

        const filtered =
    fishes.filter(
        fish =>
            fish.category
                .toLowerCase()
                .trim() ===
            value
                .toLowerCase()
                .trim()
    );

        displayFish(filtered);
    }
);


// ================= Add To Cart =================

// ================= Add To Cart =================

async function addToCart(fishId) {

    const userId =
        localStorage.getItem(
            "userId"
        );

    const token =
        localStorage.getItem(
            "token"
        );

    const data = {
        userId: Number(userId),
        fishId: fishId,
        quantity: 1
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
                        JSON.stringify(data)
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

            alert(message);
        }
    }
    catch (error) {

        console.log(error);

        alert(
            "Server Error"
        );
    }
}

// ================= Notifications =================



// ================= Logout =================

document.getElementById(
    "logoutBtn"
).addEventListener(
    "click",
    function (e) {

        e.preventDefault();

        const logout =
            confirm(
                "Are you sure you want to logout?"
            );

        if (logout) {

            localStorage.removeItem(
                "token"
            );

            localStorage.removeItem(
                "userId"
            );

            localStorage.removeItem(
                "role"
            );

            localStorage.removeItem(
                "fullName"
            );

            localStorage.removeItem(
                "email"
            );

            localStorage.removeItem(
                "phoneNumber"
            );

            localStorage.removeItem(
                "address"
            );

            window.location.href =
                "index.html";
        }
    }
);