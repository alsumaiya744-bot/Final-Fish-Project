// Sample Logged In Customer

let customer = {

    customerId: "CUS-001",
    name: "Sumaiya",
    email: "sumaiya@gmail.com",
    phone: "9876543210",
    address: "Ramanathapuram",
    joinedDate: "28-06-2026"

};

// Load Profile

document.getElementById(
    "customerId"
).value =
customer.customerId;

document.getElementById(
    "name"
).value =
customer.name;

document.getElementById(
    "email"
).value =
customer.email;

document.getElementById(
    "phone"
).value =
customer.phone;

document.getElementById(
    "address"
).value =
customer.address;

document.getElementById(
    "joinedDate"
).value =
customer.joinedDate;


// Upload Image

document.getElementById(
    "profileImage"
).addEventListener(
    "change",
    function(){

        let file =
        this.files[0];

        if(file){

            let reader =
            new FileReader();

            reader.onload =
            function(e){

                document.getElementById(
                    "profilePreview"
                ).src =
                e.target.result;

            };

            reader.readAsDataURL(
                file
            );
        }

    }
);


// Save Changes

document.getElementById(
    "saveBtn"
).addEventListener(
    "click",
    function(){

        customer.name =
        document.getElementById(
            "name"
        ).value;

        customer.phone =
        document.getElementById(
            "phone"
        ).value;

        customer.address =
        document.getElementById(
            "address"
        ).value;

        alert(
            "Profile Updated Successfully"
        );

        // Later:
        // fetch('/api/customers/profile',{
        //     method:'PUT',
        //     headers:{
        //         'Content-Type':
        //         'application/json'
        //     },
        //     body:
        //     JSON.stringify(customer)
        // });

    }
);


// Back Button

document.getElementById(
    "backBtn"
).addEventListener(
    "click",
    function(){

        window.location.href =
        "user-home.html";

    }
);