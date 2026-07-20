 

document.getElementById(
    "generateBtn"
).addEventListener(
    "click",
    loadReport
);

async function loadReport(){

    const startDate =
        document.getElementById(
            "startDate"
        ).value;

    const endDate =
        document.getElementById(
            "endDate"
        ).value;

    if(
        !startDate ||
        !endDate
    ){
        alert(
            "Please select dates"
        );
        return;
    }

   

    const response =
        await fetch(
            `${BASE_URL}/api/reports?startDate=${startDate}&endDate=${endDate}`,
            {
                headers:{
                    Authorization:
                        `Bearer ${token}`
                }
            }
        );

    if(response.ok){

        const report =
            await response.json();

        document.getElementById(
            "totalSales"
        ).innerHTML =
            `₹${report.totalSales}`;

        document.getElementById(
            "totalProcurement"
        ).innerHTML =
            `₹${report.totalProcurement}`;

        document.getElementById(
            "totalExpenses"
        ).innerHTML =
            `₹${report.totalExpenses}`;

        document.getElementById(
            "profit"
        ).innerHTML =
            `₹${report.profit}`;
    }
}

document.getElementById(
    "backBtn"
).addEventListener(
    "click",
    function(){

        window.location.href =
            "admin-dashboard.html";
    }
);

document.getElementById(
    "pdfBtn"
).addEventListener(
    "click",
    
    function(){

        const startDate =
            document.getElementById(
                "startDate"
            ).value;

        const endDate =
            document.getElementById(
                "endDate"
            ).value;

        if(
            !startDate ||
            !endDate
        ){
            alert(
                "Please select dates"
            );
            return;
        }

        window.open(
            `${BASE_URL}/api/reports/pdf?startDate=${startDate}&endDate=${endDate}`
        );
    }
);
   