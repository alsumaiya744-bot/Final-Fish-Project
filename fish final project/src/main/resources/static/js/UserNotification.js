let notifications = [];

const userId =
    localStorage.getItem(
        "userId"
    );

loadNotifications();

async function loadNotifications(){


    const response =
        await fetch(
            `http://localhost:9090/api/notifications/${userId}`,
            {
                headers:{
                    Authorization:
                        `Bearer ${token}`
                }
            }
        );

    if(response.ok){

        notifications =
            await response.json();

        displayNotifications();
    }
}

function displayNotifications(){

    const container =
        document.getElementById(
            "notificationContainer"
        );

    container.innerHTML = "";

    if(
        notifications.length === 0
    ){
        container.innerHTML =
            `
            <h3>
                No Notifications
            </h3>
            `;

        return;
    }

    notifications.forEach(
        notification=>{

            const date =
                new Date(
                    notification.createdAt
                )
                .toLocaleString(
                    "en-GB"
                );

            container.innerHTML +=
            `

            <div class="notification-card">

                <div class="message">

                    🔔
                    ${notification.message}

                </div>

                <div class="date">

                    ${date}

                </div>

            </div>

            `;
        }
    );
}