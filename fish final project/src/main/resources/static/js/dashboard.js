// Dynamic Cards

loadDashboard();
async function loadDashboard(){

    const response =
    await fetch(
        `${BASE_URL}/api/dashboard`,
        {
            headers: {
                Authorization:
                    "Bearer " +
                    localStorage.getItem(
                        "token"
                    )
            }
        }
    );
    console.log(response.status);

if (!response.ok) {
    console.log(await response.text());
    return;
}

    const data =
        await response.json();

    document.getElementById(
        "fishPurchased"
    ).innerHTML =
        data.totalFishPurchased +
        " Kg";

    document.getElementById(
        "totalExpense"
    ).innerHTML =
        "₹" +
        data.totalExpense;

    document.getElementById(
        "totalSales"
    ).innerHTML =
        "₹" +
        data.totalSales;

    document.getElementById(
        "netProfit"
    ).innerHTML =
        "₹" +
        data.netProfit;

        document.getElementById(
    "totalOrders"
).innerHTML =
    data.totalOrders;

document.getElementById(
    "totalCustomers"
).innerHTML =
    data.totalCustomers;
}


// Pie Chart

loadExpenseChart();

async function loadExpenseChart(){

    const response =
    await fetch(
        `${BASE_URL}/api/dashboard/expense-chart`,
        {
            headers: {
                Authorization:
                    "Bearer " + token
            }
        }
    );
    const data =
        await response.json();

    const labels =
        data.map(
            e => e.expenseType
        );

    const amounts =
        data.map(
            e => e.amount
        );

    new Chart(
        document.getElementById(
            "expenseChart"
        ),
        {
            type: "pie",
            data: {
                labels: labels,
                datasets: [{
                    data: amounts
                }]
            }
        }
    );
}



loadSalesExpenseChart();

async function loadSalesExpenseChart(){

   const response =
    await fetch(
        `${BASE_URL}/api/dashboard`,
        {
            headers: {
                Authorization:
                    "Bearer " + token
            }
        }
    );

    const data =
        await response.json();

    new Chart(
        document.getElementById(
            "salesExpenseChart"
        ),
        {
            type: "bar",

            data: {
                labels: [
                    "Sales",
                    "Expenses"
                ],

                datasets: [{
                    label:
                        "Amount",

                    data: [
                        data.totalSales,
                        data.totalExpense
                    ]
                }]
            }
        }
    );
}


// Recent Activity

loadActivities();

async function loadActivities() {

    const response =
    await fetch(
        `${BASE_URL}/api/dashboard/activities`,
        {
            headers: {
                Authorization:
                    "Bearer " + token
            }
        }
    );

    const activities =
        await response.json();

    let table =
        document.getElementById(
            "activityTable"
        );

    table.innerHTML = "";

    activities.forEach(
        (a, index) => {

            table.innerHTML += `
                <tr>
                    <td>${index + 1}</td>
                    <td>${a.activity}</td>
                    <td>${a.date}</td>
                </tr>
            `;
        }
    );
}

