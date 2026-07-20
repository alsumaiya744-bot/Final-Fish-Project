// ================= Data =================

let expenses = [];
let currentExpenseId = null;

loadExpenses();



async function loadExpenses() {

   const response =
    await fetch(
        "http://localhost:9090/api/expenses",
        {
            headers: {
                Authorization:
                    "Bearer " + token
            }
        }
    );

    expenses =
        await response.json();

    displayExpenses();
    updateCards();
}


function displayExpenses() {

    const table =
        document.getElementById(
            "expenseTable"
        );

    table.innerHTML = "";

    expenses.forEach(expense => {

        table.innerHTML += `

        <tr>

            <td>
                EXP-${expense.expenseId}
            </td>

            <td>
                ${expense.expenseDate}
            </td>

            <td>
                ${expense.expenseType}
            </td>

            <td>
                ₹${expense.amount}
            </td>

            <td>
                ${expense.description}
            </td>

            <td>

                <button
                    class="edit-btn"
                    onclick=
                    "editExpense(${expense.expenseId})">

                    Edit

                </button>

                <button
                    class="delete-btn"
                    onclick=
                    "deleteExpense(${expense.expenseId})">

                    Delete

                </button>

            </td>

        </tr>

        `;
    });
}

// ================= Save Expense =================

document.getElementById(
    "saveBtn"
).addEventListener(
    "click",
    saveExpense
);

async function saveExpense() {

    const expense = {

        expenseDate:
            document.getElementById(
                "expenseDate"
            ).value,

        expenseType:
            document.getElementById(
                "expenseType"
            ).value,

        amount:
            parseFloat(
                document.getElementById(
                    "amount"
                ).value
            ),

        description:
            document.getElementById(
                "description"
            ).value
    };

    const response =
        await fetch(
            "http://localhost:9090/api/expenses",
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
                        expense
                    )
            }
        );

    if (response.ok) {

        alert(
            "Expense Added Successfully"
        );

        clearForm();

        loadExpenses();
    }
}

// ================= Edit Expense =================

function editExpense(
    expenseId
) {

    const expense =
        expenses.find(
            e =>
                e.expenseId ===
                expenseId
        );

    currentExpenseId =
        expenseId;

    document.getElementById(
        "editDate"
    ).value =
        expense.expenseDate;

    document.getElementById(
        "editType"
    ).value =
        expense.expenseType;

    document.getElementById(
        "editAmount"
    ).value =
        expense.amount;

    document.getElementById(
        "editDescription"
    ).value =
        expense.description;

    document.getElementById(
        "expenseModal"
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
            "expenseModal"
        ).style.display =
            "none";
    }
);

// ================= Update Expense =================

document.getElementById(
    "updateBtn"
).addEventListener(
    "click",
    updateExpense
);

async function updateExpense() {

    const expense = {

        expenseDate:
            document.getElementById(
                "editDate"
            ).value,

        expenseType:
            document.getElementById(
                "editType"
            ).value,

        amount:
            parseFloat(
                document.getElementById(
                    "editAmount"
                ).value
            ),

        description:
            document.getElementById(
                "editDescription"
            ).value
    };

    const response =
        await fetch(
            `http://localhost:9090/api/expenses/${currentExpenseId}`,
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
                        expense
                    )
            }
        );

    if (response.ok) {

        alert(
            "Expense Updated"
        );

        document.getElementById(
            "expenseModal"
        ).style.display =
            "none";

        loadExpenses();
    }
}

// ================= Delete Expense =================

async function deleteExpense(
    expenseId
) {

    const confirmDelete =
        confirm(
            "Delete this expense?"
        );

    if (!confirmDelete) {
        return;
    }

    const response =
        await fetch(
            `http://localhost:9090/api/expenses/${expenseId}`,
            {
                method: "DELETE",
                headers: {
                Authorization:
                    "Bearer " + token
            }
            }
        );

    if (response.ok) {

        alert(
            "Expense Deleted"
        );

        loadExpenses();
    }
}

// ================= Summary Cards =================

function updateCards() {

    let totalExpense = 0;
    let monthlyExpense = 0;
    let todayExpense = 0;

    const today =
        new Date()
            .toISOString()
            .split("T")[0];

    const month =
        new Date()
            .getMonth();

    const year =
        new Date()
            .getFullYear();

    expenses.forEach(expense => {

        totalExpense +=
            expense.amount;

        const expenseDate =
            new Date(
                expense.expenseDate
            );

        if (
            expenseDate.getMonth()
            === month
            &&
            expenseDate.getFullYear()
            === year
        ) {
            monthlyExpense +=
                expense.amount;
        }

        if (
            expense.expenseDate
            === today
        ) {
            todayExpense +=
                expense.amount;
        }
    });

    document.getElementById(
        "totalExpense"
    ).innerHTML =
        "₹" + totalExpense;

    document.getElementById(
        "monthlyExpense"
    ).innerHTML =
        "₹" + monthlyExpense;

    document.getElementById(
        "todayExpense"
    ).innerHTML =
        "₹" + todayExpense;

    document.getElementById(
        "totalRecords"
    ).innerHTML =
        expenses.length;
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
                "#expenseTable tr"
            );

        rows.forEach(row => {

            const text =
                row.innerText
                    .toLowerCase();

            row.style.display =
                text.includes(
                    value
                )
                    ? ""
                    : "none";
        });
    }
);

// ================= Clear Form =================

function clearForm() {

    document.getElementById(
        "expenseDate"
    ).value = "";

    document.getElementById(
        "expenseType"
    ).value = "";

    document.getElementById(
        "amount"
    ).value = "";

    document.getElementById(
        "description"
    ).value = "";
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