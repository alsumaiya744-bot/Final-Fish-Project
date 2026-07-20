

let fishes = [];
let selectedFishId = null;

// ================= Load Fish =================

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

        loadFishDropdown();

        displayFish();

    } catch (error) {
        console.log(error);
    }
}

// ================= Load Dropdown =================

function loadFishDropdown() {

    const dropdown =
        document.getElementById(
            "fishId"
        );

    dropdown.innerHTML =
        `
        <option value="">
            New Fish
        </option>
        `;

    fishes.forEach(fish => {

        dropdown.innerHTML += `
            <option value="${fish.fishId}">
                ${fish.fishId}
            </option>
        `;
    });
}

// ================= Fish ID Change =================

document.getElementById(
    "fishId"
).addEventListener(
    "change",
    function () {

        const id =
            this.value;

        if (id === "") {

            clearForm();

            return;
        }

        const fish =
            fishes.find(
                f =>
                    f.fishId == id
            );

        if (!fish) {
            return;
        }

        selectedFishId =
            fish.fishId;

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
    "sellingPrice"
).value =
    fish.sellingPrice;

        document.getElementById(
            "quantity"
        ).value =
            fish.availableStock;

        document.getElementById(
            "storage"
        ).value =
            fish.storageType;
    }
);

// ================= Add Fish =================

document.getElementById(
    "addBtn"
).addEventListener(
    "click",
    saveFish
);

async function saveFish() {

    try {

        const formData =
            new FormData();

        formData.append(
            "fishName",
            document.getElementById(
                "fishName"
            ).value
        );

        formData.append(
            "category",
            document.getElementById(
                "category"
            ).value
        );

       formData.append(
    "purchasePrice",
    document.getElementById(
        "purchasePrice"
    ).value
);

formData.append(
    "sellingPrice",
    document.getElementById(
        "sellingPrice"
    ).value
);

        formData.append(
            "availableStock",
            document.getElementById(
                "quantity"
            ).value
        );

        formData.append(
            "storageType",
            document.getElementById(
                "storage"
            ).value
        );

        const image =
            document.getElementById(
                "image"
            ).files[0];

        if (image) {

            formData.append(
                "image",
                image
            );
        }

        const response =
            await fetch(
                "${BASE_URL}/api/fishes",
                {
                    method: "POST",
                    headers: {
                Authorization:
                    "Bearer " + token
            },
                    body: formData
                }
            );

        if (response.ok) {

            alert(
                "Fish Added Successfully"
            );

            clearForm();

            await loadFishes();
        }
        else {

            alert(
                "Unable To Save Fish"
            );
        }

    }
    catch (error) {

        console.log(error);
    }
}


// ================= Update Fish =================

document.getElementById(
    "updateBtn"
).addEventListener(
    "click",
    updateFish
);


async function updateFish() {

    if (selectedFishId == null) {
        alert("Select Fish ID");
        return;
    }

    let selectedFish =
        fishes.find(
            f => f.fishId == selectedFishId
        );

    let imagePath =
        selectedFish
            ? selectedFish.imagePath
            : "";

    const image =
        document.getElementById("image").files[0];

    if (image) {

        const formData =
            new FormData();

        formData.append(
            "file",
            image
        );

        const uploadResponse =
            await fetch(
                "${BASE_URL}/api/fishes/upload",
                {
                    method: "POST",
                    headers: {
                Authorization:
                    "Bearer " + token
            },
                    body: formData
                }
            );

        imagePath =
            await uploadResponse.text();
    }

    const fish = {
        fishName:
            document.getElementById("fishName").value,

        category:
            document.getElementById("category").value,

        purchasePrice:
    Number(
        document.getElementById(
            "purchasePrice"
        ).value
    ),

sellingPrice:
    Number(
        document.getElementById(
            "sellingPrice"
        ).value
    ),

        availableStock:
            Number(
                document.getElementById("quantity").value
            ),

        storageType:
            document.getElementById("storage").value,

        imagePath:
            imagePath
    };

    try {

        const response =
            await fetch(
                `${BASE_URL}/api/fishes/${selectedFishId}`,
                {
                    method: "PUT",
                    headers: {
    "Content-Type":
        "application/json",
    Authorization:
        "Bearer " + token
},
                    body:
                        JSON.stringify(fish)
                }
            );

        if (response.ok) {
            alert("Updated Successfully");
            clearForm();
            await loadFishes();
        }
        else {
            alert("Update Failed");
        }

    } catch (error) {
        console.log(error);
    }
}



// ================= Delete Fish =================

document.getElementById(
    "deleteBtn"
).addEventListener(
    "click",
    deleteFish
);

async function deleteFish() {

    if (
        selectedFishId == null
    ) {

        alert(
            "Select Fish ID"
        );

        return;
    }

    try {

        const response =
    await fetch(
        `${BASE_URL}/api/fishes/${selectedFishId}`,
        {
            method: "DELETE",
            headers: {
                Authorization:
                    "Bearer " + token
            }
        }
    );

if (response.ok) {

    alert("Deleted Successfully");

    clearForm();

    await loadFishes();

} else {

    const message =
        await response.text();

    alert("Delete Failed : " + message);
}

        clearForm();

        await loadFishes();

    } catch (error) {
        console.log(error);
    }
}

// ================= Display =================

function displayFish() {

    const cards =
        document.getElementById(
            "fishCards"
        );

    const table =
        document.getElementById(
            "fishTable"
        );

    cards.innerHTML = "";
    table.innerHTML = "";

    fishes.forEach(fish => {

        let status =
            "LOW STOCK";

        if (
            fish.availableStock > 50
        ) {

            status =
                "HIGH STOCK";
        }
        else if (
            fish.availableStock > 20
        ) {

            status =
                "MEDIUM STOCK";
        }

       cards.innerHTML += `
<div class="fish-card">

    <img
        src="${BASE_URL}/uploads/${fish.imagePath}"
        width="150">

    <div class="fish-card-body">

        <h3>${fish.fishName}</h3>

        <p>${fish.category}</p>

       <p>₹${fish.sellingPrice}/Kg</p>

        <p>${fish.availableStock} Kg</p>

        <p>${status}</p>

    </div>

</div>
`;

                

        table.innerHTML += `

            <tr>

                <td>
                    ${fish.fishId}
                </td>

                <td>
                    ${fish.fishName}
                </td>

                <td>
                    ${fish.category}
                </td>

                <td>
    ₹${fish.sellingPrice}/kg
</td>

                <td>
                    ${fish.availableStock}
                </td>

                <td>
                    ${fish.storageType}
                </td>

                <td>
                    ${status}
                </td>

                <td>
                    ${fish.imagePath}
                </td>

            </tr>
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

        const cards =
            document.querySelectorAll(
                ".fish-card"
            );

        cards.forEach(card => {

            const text =
                card.innerText
                    .toLowerCase();

            card.style.display =
                text.includes(value)
                    ? "block"
                    : "none";
        });
    }
);

// ================= Clear =================

function clearForm() {

    selectedFishId =
        null;

    document.getElementById(
        "fishId"
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
    "sellingPrice"
).value = "";

    document.getElementById(
        "quantity"
    ).value = "";

    document.getElementById(
        "storage"
    ).value = "";

    document.getElementById(
        "image"
    ).value = "";
}

// ================= Back =================

document.getElementById(
    "backBtn"
).addEventListener(
    "click",
    function () {

        window.location.href =
            "admin-dashboard.html";
    }
);




loadFishes();