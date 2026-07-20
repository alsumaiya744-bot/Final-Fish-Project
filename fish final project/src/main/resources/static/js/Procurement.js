


let fishes = [];
let procurements = [];
let selectedProcurementId = null;

// ================= Today's Date =================

document.getElementById("date").value =
    new Date().toISOString().split("T")[0];

// ================= Load Fish Dropdown =================

async function loadFishIds() {
    try {

        const response =
    await fetch(
        "http://localhost:9090/api/fishes",
        {
            headers: {
                Authorization:
                    "Bearer " + token
            }
        }
    );

        fishes =
            await response.json();

        const dropdown =
            document.getElementById("fishId");

        dropdown.innerHTML =
            `<option value="">
                Select Fish ID
            </option>`;

        fishes.forEach(fish => {

            dropdown.innerHTML += `
                <option value="${fish.fishId}">
                    ${fish.fishId}
                </option>
            `;
        });

    } catch (error) {
        console.log(error);
    }
}

// ================= Load Procurement =================

async function loadProcurements() {

    try {

        const response =
    await fetch(
        "http://localhost:9090/api/procurements",
        {
            headers: {
                Authorization:
                    "Bearer " + token
            }
        }
    );

        procurements =
            await response.json();

        const dropdown =
            document.getElementById(
                "procurementId"
            );

        dropdown.innerHTML =
            `<option value="">
                Select Procurement ID
            </option>`;

        procurements.forEach(p => {

            dropdown.innerHTML += `
                <option value="${p.procurementId}">
                    ${p.procurementId}
                </option>
            `;
        });

        displayTable();
        updateSummary();

    } catch (error) {
        console.log(error);
    }
}

// ================= Fish Auto Fill =================

document.getElementById("fishId")
    .addEventListener(
        "change",
        function () {

            const fish =
                fishes.find(
                    f =>
                        f.fishId ==
                        this.value
                );

            if (!fish) {
                return;
            }

            document.getElementById(
                "fishName"
            ).value =
                fish.fishName;

            document.getElementById(
                "category"
            ).value =
                fish.category;

           document.getElementById(
    "purchasePrice"
).value =
    fish.purchasePrice;

            document.getElementById(
                "stock"
            ).value =
                fish.availableStock;
        }
    );

// ================= Procurement Auto Fill =================

document.getElementById(
    "procurementId"
).addEventListener(
    "change",
    function () {

        const p =
            procurements.find(
                x =>
                    x.procurementId ==
                    this.value
            );

        if (!p) {
            return;
        }

        selectedProcurementId =
            p.procurementId;

        document.getElementById(
            "date"
        ).value =
            p.procurementDate;

        document.getElementById(
            "supplierName"
        ).value =
            p.supplierName;

        document.getElementById(
            "supplierPhone"
        ).value =
            p.supplierPhone;

        document.getElementById(
            "fishId"
        ).value =
            p.fish.fishId;

        document.getElementById(
            "fishName"
        ).value =
            p.fish.fishName;

        document.getElementById(
            "category"
        ).value =
            p.fish.category;

        document.getElementById(
    "purchasePrice"
).value =
    p.purchasePrice;

        document.getElementById(
            "stock"
        ).value =
            p.fish.availableStock;

        document.getElementById(
            "quantity"
        ).value =
            p.purchaseQuantity;

        document.getElementById(
            "total"
        ).value =
            p.totalAmount;

        document.getElementById(
            "paymentStatus"
        ).value =
            p.paymentStatus;
    }
);

// ================= Total Calculation =================

document.getElementById(
    "quantity"
).addEventListener(
    "input",
    function () {

        const qty =
            Number(this.value);

       const price =
    Number(
        document.getElementById(
            "purchasePrice"
        ).value
    );

        document.getElementById(
            "total"
        ).value =
            qty * price;
    }
);

// ================= Supplier Phone Validation =================

document.getElementById(
    "supplierPhone"
).addEventListener(
    "input",
    function () {

        this.value =
            this.value
                .replace(/\D/g, "")
                .slice(0, 10);
    }
);

// ================= Add Procurement =================

document.getElementById(
    "addBtn"
).addEventListener(
    "click",
    saveProcurement
);

async function saveProcurement() {

    try {

        const phone =
    document.getElementById(
        "supplierPhone"
    ).value.trim();

if (phone.length !== 10) {

    alert(
        "Supplier phone number must contain exactly 10 digits."
    );

    return;
}

        let fishId =
            document.getElementById(
                "fishId"
            ).value;

        // Create new fish
        if (fishId === "") {

            const newFish = {

                fishName:
                    document.getElementById(
                        "fishName"
                    ).value,

                category:
                    document.getElementById(
                        "category"
                    ).value,

                purchasePrice:
    Number(
        document.getElementById(
            "purchasePrice"
        ).value
    ),

sellingPrice:
    Number(
        document.getElementById(
            "purchasePrice"
        ).value
    ),

                availableStock:
                    Number(
                        document.getElementById(
                            "stock"
                        ).value
                    )
            };

            const fishResponse =
                await fetch(
                    "http://localhost:9090/api/fishes/simple",
                    {
                        method: "POST",
                        headers: {
    "Content-Type":
        "application/json",
    Authorization:
        "Bearer " + token
},
                        body:
                            JSON.stringify(
                                newFish
                            )
                    }
                );

            if (!fishResponse.ok) {
                alert(
                    "Unable to save new fish."
                );
                return;
            }

            const savedFish =
                await fishResponse.json();

            fishId =
                savedFish.fishId;
        }

        const procurement = {

            procurementDate:
                document.getElementById(
                    "date"
                ).value,

            supplierName:
                document.getElementById(
                    "supplierName"
                ).value,

            supplierPhone:
                document.getElementById(
                    "supplierPhone"
                ).value,

            purchaseQuantity:
                Number(
                    document.getElementById(
                        "quantity"
                    ).value
                ),
                purchasePrice:
    Number(
        document.getElementById(
            "purchasePrice"
        ).value
    ),

            totalAmount:
                Number(
                    document.getElementById(
                        "total"
                    ).value
                ),

            paymentStatus:
                document.getElementById(
                    "paymentStatus"
                ).value,

            fish: {
                fishId:
                    Number(fishId)
            }
        };

        const response =
            await fetch(
                "http://localhost:9090/api/procurements",
                {
                    method: "POST",
                     headers: {
                "Content-Type":
                    "application/json",
                Authorization:
                    "Bearer " + token
            },
                    body:
                        JSON.stringify(
                            procurement
                        )
                }
            );

        if (!response.ok) {

            const error =
                await response.text();

            alert(
                "Save Failed\n" +
                error
            );

            return;
        }

        alert(
            "Procurement Saved Successfully"
        );

        clearForm();

        await loadFishIds();
        await loadProcurements();

    } catch (error) {
        console.log(error);
    }
}

// ================= Update =================

document.getElementById(
    "updateBtn"
).addEventListener(
    "click",
    async function () {

        const phone =
    document.getElementById(
        "supplierPhone"
    ).value.trim();

if (phone.length !== 10) {

    alert(
        "Supplier phone number must contain exactly 10 digits."
    );

    return;
}

        if (
            selectedProcurementId ==
            null
        ) {
            alert(
                "Select Procurement"
            );
            return;
        }

        const procurement = {

            procurementDate:
                document.getElementById(
                    "date"
                ).value,

            supplierName:
                document.getElementById(
                    "supplierName"
                ).value,

            supplierPhone:
                document.getElementById(
                    "supplierPhone"
                ).value,

            purchaseQuantity:
                Number(
                    document.getElementById(
                        "quantity"
                    ).value
                ),

                purchasePrice:
    Number(
        document.getElementById(
            "purchasePrice"
        ).value
    ),

            totalAmount:
                Number(
                    document.getElementById(
                        "total"
                    ).value
                ),

            paymentStatus:
                document.getElementById(
                    "paymentStatus"
                ).value,

            fish: {
                fishId:
                    Number(
                        document.getElementById(
                            "fishId"
                        ).value
                    )
            }
        };

        const response =
            await fetch(
                `http://localhost:9090/api/procurements/${selectedProcurementId}`,
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
                            procurement
                        )
                }
            );

        if (response.ok) {

            alert(
                "Updated Successfully"
            );

            clearForm();

            await loadFishIds();
            await loadProcurements();
        }
    }
);

// ================= Delete =================

document.getElementById(
    "deleteBtn"
).addEventListener(
    "click",
    async function () {

        if (
            selectedProcurementId ==
            null
        ) {
            alert(
                "Select Procurement"
            );
            return;
        }

        await fetch(
    `http://localhost:9090/api/procurements/${selectedProcurementId}`,
    {
        method: "DELETE",
        headers: {
            Authorization:
                "Bearer " + token
        }
    }
);
        alert(
            "Deleted Successfully"
        );

        clearForm();

        await loadFishIds();
        await loadProcurements();
    }
);

// ================= Display Table =================

function displayTable() {

    const table =
        document.getElementById(
            "procurementTable"
        );

    table.innerHTML = "";

    procurements.forEach(
        p => {

            table.innerHTML += `
                <tr>
                    <td>${p.procurementId}</td>
                    <td>${p.procurementDate}</td>
                    <td>${p.supplierName}</td>
                    <td>${p.fish.fishName}</td>
                    <td>${p.purchaseQuantity}</td>
                    <td>₹${p.totalAmount}</td>
                    <td>${p.paymentStatus}</td>
                </tr>
            `;
        }
    );
}

// ================= Summary =================

function updateSummary() {

    let amount = 0;
    let qty = 0;

    procurements.forEach(
        p => {

            amount +=
                p.totalAmount;

            qty +=
                p.purchaseQuantity;
        }
    );

    document.getElementById(
        "purchaseAmount"
    ).innerHTML =
        "₹" + amount;

    document.getElementById(
        "purchaseQty"
    ).innerHTML =
        qty + " Kg";

    document.getElementById(
        "totalOrders"
    ).innerHTML =
        procurements.length;
}

// ================= Clear =================

function clearForm() {

    selectedProcurementId =
        null;

    document
        .querySelector("form");

    document.getElementById(
        "procurementId"
    ).value = "";

    document.getElementById(
        "fishId"
    ).value = "";

    document.getElementById(
        "supplierName"
    ).value = "";

    document.getElementById(
        "supplierPhone"
    ).value = "";

    document.getElementById(
        "fishName"
    ).value = "";

    document.getElementById(
        "category"
    ).value = "";

    document.getElementById(
    "purchasePrice"
).value = "";
    document.getElementById(
        "stock"
    ).value = "";

    document.getElementById(
        "quantity"
    ).value = "";

    document.getElementById(
        "total"
    ).value = "";

    document.getElementById(
        "paymentStatus"
    ).value = "";

    document.getElementById(
        "date"
    ).value =
        new Date()
            .toISOString()
            .split("T")[0];
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

        const rows =
            document.querySelectorAll(
                "#procurementTable tr"
            );

        rows.forEach(row => {

            const text =
                row.innerText
                    .toLowerCase();

            row.style.display =
                text.includes(value)
                    ? ""
                    : "none";
        });
    }
);

// ================= Initial Load =================

loadFishIds();
loadProcurements();